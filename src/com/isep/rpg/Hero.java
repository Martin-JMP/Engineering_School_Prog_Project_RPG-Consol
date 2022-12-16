package com.isep.rpg;

public abstract class Hero extends Combatant{
    public Hero(String n, int h, int A) {
        super(n, h, A);

    }

    public abstract void take(Item item);
}
