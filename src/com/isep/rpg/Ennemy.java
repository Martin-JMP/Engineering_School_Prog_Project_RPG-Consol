package com.isep.rpg;

public class Ennemy extends Combatant{
    public Ennemy(String n, int h, int A, int damagePoints) {
        super(n, h, A);
        this.damagePoints = damagePoints;
    }

    public int getDamagePoints() {

        return damagePoints;
    }

    // Les points de dégats sont intégrés aux ennemis (ils n'ont pas d'arme)
    private int damagePoints;



    public void fight(Combatant combatant) {
        combatant.loose(getDamagePoints());
    }

    @Override
    public void manger(Combatant combatant) {

    }
    public void heal(Combatant combatant) {

    }

    public void protectionWin(Combatant combatant) {

    }

    @Override
    public void Rajouterarrow(Combatant combatant) {

    }

    @Override
    public void Rajoutermana(Combatant combatant) {

    }


    public void afficherdegat(Combatant combatant) {
        System.out.print(getDamagePoints());
    }

    @Override
    public void affichermana(Combatant combatant) {

    }


    @Override
    public void afficherfleche(Combatant combatant) {

    }

    @Override
    public void afficherNBPotionVie(Combatant combatant) {

    }

    @Override
    public void afficherNBPotionDegat(Combatant combatant) {

    }

    public void protectionLoose(Combatant combatant) {

    }

    public void PotionVie(Combatant combatant) {

    }

    public void PotionForce(Combatant combatant) {

    }

    @Override
    public void RajouterPotionVie(Combatant combatant) {

    }

    @Override
    public void RajouterPotionDegat(Combatant combatant) {

    }

    @Override
    public void afficherdegatPotionVie(Combatant combatant) {

    }

    @Override
    public void afficherdegatPotionDegat(Combatant combatant) {

    }

    @Override
    public void afficherhealFOOD(Combatant combatant) {

    }

    @Override
    public void afficherdegatNBPotionVie(Combatant combatant) {

    }

    @Override
    public void afficherdegatNBPotionDegat(Combatant combatant) {

    }


}
