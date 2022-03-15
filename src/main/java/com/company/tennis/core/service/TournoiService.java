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

    public void getTournoi ( Long id){
        tournoiRepository.getById(  id );
    }
}
