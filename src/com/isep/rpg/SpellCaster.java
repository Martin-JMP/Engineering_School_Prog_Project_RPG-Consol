package com.isep.rpg;

public abstract class SpellCaster extends Hero {
    public SpellCaster(String n, int h, int A) {
        super(n, h, A);
    }
    public abstract void take(Item item);
}

