package com.company.tennis.core.service;

import com.company.tennis.core.entity.Joueur;
import com.company.tennis.core.repository.JoueurRepositoryImpl;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    public void creatJoueur(Joueur joueur) {
        joueurRepository.create(joueur);
    }

    public Joueur getJoueur(Long id) {
        return joueurRepository.getById(id);
    }

    public void listJoueur() {
        joueurRepository.list();
    }
}
