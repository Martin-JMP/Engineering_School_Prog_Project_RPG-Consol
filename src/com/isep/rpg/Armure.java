package com.isep.rpg;

public class Armure extends Item{

    public Armure(String name, int Armures) {
        super(name);
        this.Armures = Armures;
    }

    public int getArmures() {

        return Armures;
    }

    // Une arme inflige des points de dégats
    private int Armures;
}
