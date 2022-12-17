package com.isep.rpg;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Warrior extends Hero{

    int qtpotionforce = 3;

    int qtpotionvie = 3;

    int qtfood = 5;

    public Warrior(String n, int h, int A) {
        super(n, h, A);
    }

    public void fight(Combatant combatant) {

        combatant.loose(weapon.getDamagePoints() );
    }

    @Override
    public void heal(Combatant combatant) {

    }

    public void protectionLoose(Combatant combatant) {
        if (getArmures()>10){
            combatant.loosearmure(10);
            System.out.println("Très bien, vous êtes protégé, il vous reste encore " + getArmures() + " armures ");
        } else if (getArmures()>1){
            int reste = 10-getArmures();
            System.out.println("Vous n'avez pas assée d'armure, il vous manque " + reste + " armures pour pouvoir vous protégez \nVous encaissez pas conséquent "+getArmures()+" points de dégat");
            combatant.loose(getArmures());
        } else {
            System.out.println("Malheuresement vous n'avez plus d'armure, pour pouvoir vous protégez \nVous encaissez pas conséquent 5 points de dégat");
            combatant.loose(5);
        }
    }
    private Armure armure;

    public void protectionWin(Combatant combatant) {
        combatant.winarmure(armure.getArmures());
     }

    @Override
    public void Rajouterarrow(Combatant combatant) {

    }

    @Override
    public void Rajoutermana(Combatant combatant) {

    }

    @Override
    public void afficherdegat(Combatant combatant) {
        System.out.print("" + weapon.getDamagePoints() + "\uD83D\uDCA5) ");
    }

    @Override
    public void affichermana(Combatant combatant) {

    }

    @Override
    public void afficherfleche(Combatant combatant) {

    }

    public void afficherNBPotionVie(Combatant combatant) {
        System.out.print(qtpotionvie + "\uD83D\uDC8A) ");
    }

    public void afficherNBPotionDegat(Combatant combatant) {
        System.out.print(qtpotionforce + "\uD83D\uDCA3) ");
    }

    @Override
    public void take(Item item) {
        if (item instanceof Weapon) {
            weapon = (Weapon) item;

        }
        else if (item instanceof Food) {
            food = (Food) item;
        }
        else if (item instanceof Potion) {
            potion = (Potion) item;
        }

        else if (item instanceof Armure) {
            armure = (Armure) item;
        }

        else {
            Game.displayMessage("Oups ! " + item.getName() + " est inutile...");
        }
    }

    private Weapon weapon;

    public void manger(Combatant combatant) {
        if(qtfood > 0) {
            qtfood = qtfood -1 ;
            combatant.win(food.getvie());
            System.out.println("Il vous reste " + qtfood +" pommes disponibles.");
        } else if(qtfood<=0){
            System.out.println("Désolé, vous n'avez plus de pomme à votre disposition");
        }
    }

    private Food food;

    public void PotionVie(Combatant combatant) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        if(qtpotionvie > 0) {
            File mp3FilePotionVie = new File("src/Potion Vie.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(mp3FilePotionVie);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            qtpotionvie = qtpotionvie -1 ;
            combatant.win(potion.getVie());
            System.out.println("Il vous reste " + qtpotionvie +" Potion de vie disponible.");
        } else if(qtpotionvie<=0){
            System.out.println("Désolé, vous n'avez plus de potion de vie à votre disposition");
        }
    }

    public void PotionForce(Combatant combatant) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if(qtpotionforce>0 ) {
            File mp3FilePotionForce = new File("src/Potion Degat.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(mp3FilePotionForce);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            qtpotionforce = qtpotionforce - 1;
            combatant.loose(potion.getDamagePoints());
            System.out.println("Il vous reste " + qtpotionforce +" Potion de force disponible.");
        } else if(qtpotionforce<=0){
            System.out.println("Désoler, vous n'avez plus de potion de dégat à votre disposition");
        }
    }

    public void RajouterPotionVie(Combatant combatant){
        qtpotionvie = qtpotionvie + 2;
    }


    public void RajouterPotionDegat(Combatant combatant){
        qtpotionforce = qtpotionforce + 2;
    }

    public void afficherdegatPotionVie(Combatant combatant) {
        System.out.print(" - (" + potion.getVie() + "⚕\uD83D\uDC8A) ");
    }

    public void afficherdegatPotionDegat(Combatant combatant) {
        System.out.print("" + potion.getDamagePoints() + "\uD83D\uDCA5\uD83D\uDCA3) ");
    }

    public void afficherhealFOOD(Combatant combatant) {
        System.out.print(" - (" + food.getvie() + "⚕"+ qtfood +"\uD83C\uDF4F) ");
    }

    public void afficherdegatNBPotionVie(Combatant combatant) {
        System.out.print(" - (" + potion.getVie() + "⚕"+ qtpotionvie + "\uD83D\uDC8A) ");
    }

    public void afficherdegatNBPotionDegat(Combatant combatant) {
        System.out.print("" + potion.getDamagePoints() + "\uD83D\uDCA5"+ qtpotionforce +"\uD83D\uDCA3) ");
    }


    private Potion potion;
}

