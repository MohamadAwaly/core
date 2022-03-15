package com.company.tennis.core.entity;

public class Match {

    private Long id;
    private Joueur vainqueur;
    private Joueur finaliste;
    private Epreuve epreuve;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Joueur getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur( Joueur vainqueur ) {
        this.vainqueur = vainqueur;
    }

    public Joueur getFinaliste() {
        return finaliste;
    }

    public void setFinaliste( Joueur finaliste ) {
        this.finaliste = finaliste;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve( Epreuve epreuve ) {
        this.epreuve = epreuve;
    }
}