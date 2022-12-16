package com.isep.rpg;

public class Food extends Consumable {

    private int quantité;
    public Food(String name, int vie, int quantité) {
        super(name);
        this.vie = vie;
        this.quantité = quantité;
    }

    public int getvie() {

        return vie;
    }

    // Une arme inflige des points de dégats
    private int vie;

    // Méthode pour obtenir la quantité de nourriture
    public int getQuantité() {
        return this.quantité;
    }

    // Méthode pour mettre à jour la quantité de nourriture
    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public void consommer(int quantité) {
        this.quantité-= quantité;
    }
}

