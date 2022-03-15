package com.company.tennis.core;

import com.company.tennis.core.entity.Joueur;

import com.company.tennis.core.entity.Tournoi;
import com.company.tennis.core.service.JoueurService;
import com.company.tennis.core.service.TournoiService;

public class TestDeConnection {

    public static void main( String... args ) {
//        JoueurService joueurService = new JoueurService();
//        Joueur noah = new Joueur();
//        noah.setNom( "Noah" );
//        noah.setPrenom( "Yannick" );
//        noah.setSexe( 'H' );
//        joueurService.creatJoueur(noah);
//        System.out.println("L'identifiant du joueur créé est " + noah.getId());

        TournoiService tournoiService = new TournoiService();
        Tournoi tournoi = new Tournoi();
        tournoi.setCode( "te" );
        tournoi.setNom( "testno" );
        tournoiService.creatTournoi( tournoi );

    }

}
