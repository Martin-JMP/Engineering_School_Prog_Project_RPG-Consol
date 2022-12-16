package com.isep.rpg;

public class Potion extends Consumable{

    public Potion(String name, int vie, int damagePoints) {
        super(name);
        this.vie = vie;
        this.damagePoints = damagePoints;
    }

    public int getDamagePoints() {
        return damagePoints;
    }


    private int damagePoints;


    public int getVie() {
        return vie;
    }

    private int vie;



}



