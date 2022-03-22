package com.company.tennis.core.service;

import com.company.tennis.core.HibernateUtil;
import com.company.tennis.core.entity.Tournoi;
import com.company.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {

    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public void creatTournoi(Tournoi tournoi) {

        tournoiRepository.create(tournoi);
    }

    public Tournoi getTournoi(Long id) {

        Session session = null;
        Transaction tx = null;
        Tournoi tournoi = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoi = tournoiRepository.getById(id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return tournoi;
    }
}
