package com.company.tennis.core.service;

import com.company.tennis.core.entity.Tournoi;
import com.company.tennis.core.repository.TournoiRepositoryImpl;

public class TournoiService {

    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService( ){
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public void creatTournoi( Tournoi tournoi ) {
        tournoiRepository.create( tournoi );
    }

    public Tournoi getTournoi ( Long id){
        return tournoiRepository.getById(  id );
    }
}
