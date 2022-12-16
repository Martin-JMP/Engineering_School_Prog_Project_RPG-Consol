package com.isep.rpg;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public abstract class Combatant {


    public Combatant(String n, int h, int A) {
        name = n;
        hp = h;
        Armur = A;
    }

    public String getName(){
        return name;
    }

    public int getHp(){
        return hp;
    }

    public int getArmures(){
        return Armur;
    }


    public abstract void fight(Combatant combatant);

    public abstract void protectionWin(Combatant combatant);

    public abstract void Rajouterarrow(Combatant combatant);

    public abstract void Rajoutermana(Combatant combatant);

    public abstract void afficherdegat(Combatant combatant);

    public abstract void affichermana(Combatant combatant);

    public abstract void afficherfleche(Combatant combatant);

    public abstract void afficherNBPotionVie(Combatant combatant);

    public abstract void afficherNBPotionDegat(Combatant combatant);

    public abstract void protectionLoose(Combatant combatant);

    public abstract void heal(Combatant combatant);


    public abstract void manger(Combatant combatant);

    public abstract void PotionVie(Combatant combatant);

    public abstract void PotionForce(Combatant combatant);
    public abstract void RajouterPotionVie(Combatant combatant);

    public abstract void RajouterPotionDegat(Combatant combatant);
    public abstract void afficherdegatPotionVie(Combatant combatant);
    public abstract void afficherdegatPotionDegat(Combatant combatant);
    public abstract void afficherhealFOOD(Combatant combatant);
    public abstract void afficherdegatNBPotionVie(Combatant combatant);

    public abstract void afficherdegatNBPotionDegat(Combatant combatant);

    public void loose(int healthpoint){
        // hp = hp - healthpoint
        Armur -= healthpoint;
        if(Armur < 0)
        {
            int rest = 0 - Armur;
            Armur = 0;
            hp -= rest;
        }
        //hp -= healthpoint;
    }

    public void loosearmure(int armure2){
        // hp = hp - healthpoint
        Armur = Armur - armure2;
    }
    public void win(int healthpoint){
            hp += healthpoint;
            if(hp >=100) {
                int rest = hp - 100;
                hp = 100;
                Armur += rest;
                System.out.println("Votre vie a déjà atteint le maximum de 100\u2764, nous vous rajoutons donc " + rest + "\uD83D\uDEE1");
            } else{
                System.out.println("Vous avez récupéré +" + healthpoint + "\u2764, vous avez maintenant " + hp + "\u2764");
            }
    }


    public void winarmure(int armure2){
        if (Armur>=50){
            System.out.println("Votre armure à déjà atteint le maximum de 50\uD83D\uDEE1");
        }
        else {
            Armur = armure2 + 20;
            if (Armur >= 50) {
                int rest = Armur - 50;
                Armur = 50;
                System.out.println("Vous avez récupéré " + rest + "\uD83D\uDEE1 en plus, Vous avez le maximum d'armure");
            } else {
                System.out.println("Vous avez récupéré 20\uD83D\uDEE1 en plus, Vous avez maintenant " + Armur+"\uD83D\uDEE1");


            }
        }
    }

    private String name;
    private int hp; // static partagé à tous les individus

    private int Armur;

}
