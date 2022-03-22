package com.company.tennis.core.repository;

import com.company.tennis.core.DataSourceProvider;
import com.company.tennis.core.HibernateUtil;
import com.company.tennis.core.entity.Joueur;
import com.company.tennis.core.entity.Tournoi;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create(Tournoi tournoi) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.persist(tournoi);

    }

    public void update(Tournoi tournoi) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE tournoi SET NOM=?,CODE=? WHERE ID=?");

            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());

            preparedStatement.setLong(3, tournoi.getId());

            preparedStatement.executeUpdate();

            System.out.println("Tournoi modifié");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Long id) {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.delete(tournoi);
        System.out.println("Tournoi supprimé");
    }

    public Tournoi getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("Tournoi lu");
        return session.get(Tournoi.class, id);
    }

    public List<Tournoi> list() {
        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<>();

        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM tournoi");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Tournoi tournoi = new Tournoi();
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
                tournois.add(tournoi);
            }

            System.out.println("Tournoi lus");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tournois;
    }
}
