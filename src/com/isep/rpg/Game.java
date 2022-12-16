package com.isep.rpg;

import com.isep.utils.InputParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import javax.sound.sampled.*;


public class Game {

    public Game(InputParser inputParser) {

        this.inputParser = inputParser;

        // Il faut normalement 5 héros de types différents...
        heros = new ArrayList<>();

        // Il faut normalement 5 ennemis de types différents...
        enemies = new ArrayList<>();

    }


    public void start() throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
        File mp3File = new File("5-minutes-of-peaceful-piano-music-relaxing-mind-clearing-music-quick-piano-music-relaxing-music.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(mp3File);

        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        //clip.stop();



        String Bleu = "\u001B[34m";
        String Violet = "\u001B[35m";
        String Violet_line = "\033[4;35m";
        String PURPLE_BACKGROUND = "\033[45m";
        //String GREEN_BACKGROUND = "\033[42m";
        //String RED_BACKGROUND = "\033[41m";
        //String BLUE_BACKGROUND = "\033[44m";
        //String BLACK_BACKGROUND = "\033[40m";
        String RESET = "\u001B[0m";

        //System.out.println("bfhdbvs" + PURPLE_BACKGROUND);

        Scanner scanner = new Scanner(System.in);
        displayMessage("Combien de Hero voulez-vous ?");
        int NBHERO = scanner.nextInt();
        while (NBHERO < 1 || NBHERO >4){
            displayMessage("Choix incorrect. Veuillez choisir un minimum de 1 hero et un maximum de 4 heros.");
            displayMessage("Votre choix: ");
            NBHERO = scanner.nextInt();
        }
        displayMessage("Liste des Heros disponibles :");
        displayMessage("[1]-Hunter");
        displayMessage("[2]-Mage");
        displayMessage("[3]-Healer");
        displayMessage("[4]-Warrior");

        for (int i = 1; i <= NBHERO; i++) {
            System.out.println("Veuillez choisir votre " + i + "e Hero :");
            int ChoixHero = scanner.nextInt();
            while (ChoixHero < 1 || ChoixHero >4){
                System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 4 correspondant à un hero.");
                System.out.println("Votre choix : ");
                ChoixHero = scanner.nextInt();
            }
            if (ChoixHero == 1) {
                Hero hunter = new Hunter("Hunter", 1, 2); // création du hero
                hunter.take(new Weapon("Arrow", 20)); // création de l'arme utilisée par le Hero avec le nombre de dammage
                hunter.take(new Food("Pomme",7,5));
                hunter.take((new Potion("Potion",25,30)));
                hunter.take((new Armure("Armure",25)));
                heros.add(hunter);
            } else if (ChoixHero == 2) {
                Hero mage = new Mage("Mage", 100, 25); // création du hero
                mage.take(new Weapon("Multiple attaque", 10)); // création de l'arme utilisée par le Hero avec le nombre de dammage
                mage.take(new Food("Pomme",7,5));
                mage.take((new Potion("Potion",25,30)));
                mage.take((new Armure("Armure",25)));
                heros.add(mage);
            } else if (ChoixHero == 3) {
                Hero healer = new Healer("Healer", 100,25);// création du hero
                healer.take(new Weapon("Soins", 15)); // création de l'arme utilisée par le Hero avec le nombre de dammage
                healer.take(new Food("Pomme",7,5));
                healer.take((new Potion("Potion",25,30)));
                healer.take((new Armure("Armure",25)));
                heros.add(healer);
            } else if (ChoixHero == 4) {
                Hero warrior = new Warrior("Warrior", 100, 25); // création du hero
                warrior.take(new Weapon("Knife", 15)); // création de l'arme utilisée par le Hero avec le nombre de dammage
                warrior.take(new Food("Pomme",7,5));
                warrior.take((new Potion("Potion",25,30)));
                warrior.take((new Armure("Armure",25)));
                heros.add(warrior);
            }

                Random random = new Random();
                int valuetest = random.nextInt(5) + 2;
                enemies.add(new Ennemy("Monstre", 20, 0,valuetest));
        }

        int damagePointsHunter= 20;
        int damagePointsMage= 10;
        int damagePointsWarrior= 15;
        int HealingHealer= 15;
        int damagePointsPotionDegat= 30;
        int HealingPotionVie= 25;
        int FOOD= 7;
        int QTFOOD= 5;

        Random random = new Random();
        for (int j = 0; j <= 4; j++) {
            if (j == 1) {
                for (int i = 1; i <= heros.size(); i++) {

                    int valuetest = random.nextInt(5) + 5; // Initialisation du chiffre aléatoire entre 1 et 2
                    enemies.add(new Ennemy("Monstre", 30, 0, valuetest)); // création de l'ennemie avec damagepoints aléatoire entre 1 et 3
                }

                    int ixHero3 = 0;
                for (int i = 1; i <= heros.size(); i++) {
                    Combatant goodOnerécompense = heros.get(ixHero3);
                    displayMessage("******Vous pouvez choisir un avantage pour "+Bleu+ goodOnerécompense.getName() + RESET +" afin de bien débuter votre prochain combat.****** \n Liste des récompenses disponibles :");
                    if(goodOnerécompense.getName()=="Hunter" || goodOnerécompense.getName()=="Mage" || goodOnerécompense.getName()=="Warrior"){ displayMessageReturn("[1]-Augmenter les dégats de l'arme (aléatoirement entre 2 ou 4 de plus) |"+ Bleu +" Actuellement (");
                        goodOnerécompense.afficherdegat(heros.get(ixHero3));
                        displayMessage(RESET);}
                    else if(goodOnerécompense.getName()=="Healer"){displayMessageReturn("[1]-Augmenter les soins (aléatoirement entre 2 ou 4 de plus) |"+ Bleu +" Actuellement (");
                        goodOnerécompense.afficherdegat(heros.get(ixHero3));
                        displayMessage(RESET);}
                    displayMessageReturn("[2]-Augmenter l'efficacité des potion et de la nourriture |"+ Bleu +" Actuellement (");
                        goodOnerécompense.afficherdegatPotionDegat(heros.get(ixHero3));
                        goodOnerécompense.afficherdegatPotionVie(heros.get(ixHero3));
                        goodOnerécompense.afficherhealFOOD(heros.get(ixHero3));
                        displayMessage(RESET);
                    if(goodOnerécompense.getName()=="Hunter"){displayMessageReturn("[3]-Rajouter des flèches à votre arc |"+ Bleu +" Actuellement (");
                        goodOnerécompense.afficherfleche(heros.get(ixHero3));
                        displayMessage(RESET);}
                    else if(goodOnerécompense.getName()=="Mage"){displayMessageReturn("[3]-Rajouter 50 Mana |"+ Bleu +" Actuellement (");
                        goodOnerécompense.affichermana(heros.get(ixHero3));
                        displayMessage(RESET);}
                    else if(goodOnerécompense.getName()=="Healer" || goodOnerécompense.getName()=="Warrior" ){displayMessage("-------------------------------------");}
                    displayMessage("[4]-Rajouter des boucliers |"+ Bleu +" Actuellement (" + goodOnerécompense.getArmures() +"\uD83D\uDEE1)"+ RESET);
                    displayMessageReturn("[5]-Rajouter des potions de vie ou de dégat dans l'inventaire |"+ Bleu +" Actuellement potion de vie(");
                        goodOnerécompense.afficherNBPotionVie(heros.get(ixHero3)); displayMessageReturn("- potion de dégat(");
                        goodOnerécompense.afficherNBPotionDegat(heros.get(ixHero3)); displayMessage(RESET);
                    System.out.println(" ");
                    System.out.println("Que voulez-vous choisir comme récompense pour vos heros");
                    int choixrécompense = scanner.nextInt();
                    while (choixrécompense < 1 || choixrécompense > 5) {
                        System.out.println("Choix incorrect!!! Veuillez choisir un nombre entre 1 et 5 correspondant à l'action voulut.");
                        System.out.println("Votre choix: ");
                        choixrécompense = scanner.nextInt();
                    }

                    if (choixrécompense == 1) {
                        int nombreAleatoire = new Random().nextInt(4)+5;

                        if(heros.get(ixHero3) instanceof Warrior){
                            damagePointsWarrior= nombreAleatoire+damagePointsWarrior;
                            ((Warrior) heros.get(ixHero3)).take(new Weapon("Knife", damagePointsWarrior));
                        }
                        else if(heros.get(ixHero3) instanceof Hunter){
                            damagePointsHunter= nombreAleatoire+damagePointsHunter;
                            ((Hunter) heros.get(ixHero3)).take(new Weapon("Arrow", damagePointsHunter));
                        }
                        else if(heros.get(ixHero3) instanceof Mage){
                            damagePointsMage= nombreAleatoire+damagePointsMage;
                            ((Mage) heros.get(ixHero3)).take(new Weapon("Multiple attaque", damagePointsMage));
                        }
                        else if(heros.get(ixHero3) instanceof Healer){
                            HealingHealer= nombreAleatoire+HealingHealer;
                            ((Healer) heros.get(ixHero3)).take(new Weapon("Soin", HealingHealer));
                        }
                        ixHero3 = ixHero3 + 1;

                     } else if (choixrécompense == 2) {
                        int nombreAleatoirePotionDegat = new Random().nextInt(2)+damagePointsPotionDegat;
                        nombreAleatoirePotionDegat += 2;
                        int nombreAleatoirePotionVie = new Random().nextInt(2)+HealingPotionVie;
                        nombreAleatoirePotionVie += 2;
                        int nombreAleatoireFood = new Random().nextInt(2)+FOOD;
                        nombreAleatoireFood += 2;
                        if(heros.get(ixHero3) instanceof Warrior){
                            damagePointsPotionDegat= nombreAleatoirePotionDegat*3/2;
                            HealingPotionVie= nombreAleatoirePotionVie*3/2;
                            ((Warrior) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                            FOOD= nombreAleatoireFood*3/2;
                            ((Warrior) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                        }
                        else if(heros.get(ixHero3) instanceof Hunter){
                            damagePointsPotionDegat= nombreAleatoirePotionDegat*3/2;
                            HealingPotionVie= nombreAleatoirePotionVie*3/2;
                            ((Hunter) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                            FOOD= nombreAleatoireFood*3/2;
                            ((Hunter) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                        }
                        else if(heros.get(ixHero3) instanceof Mage){
                            damagePointsPotionDegat= nombreAleatoirePotionDegat*3/2;
                            HealingPotionVie= nombreAleatoirePotionVie*3/2;
                            ((Mage) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                            FOOD= nombreAleatoireFood*3/2;
                            ((Mage) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                        }
                        else if(heros.get(ixHero3) instanceof Healer){
                            damagePointsPotionDegat= nombreAleatoirePotionDegat*3/2;
                            HealingPotionVie= nombreAleatoirePotionVie*3/2;
                            ((Healer) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                            FOOD= nombreAleatoireFood*3/2;
                            ((Healer) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                        }
                        ixHero3 = ixHero3 + 1;

                    } else if (choixrécompense == 3) {
                        if(goodOnerécompense.getName() == "Hunter"){
                            goodOnerécompense.Rajouterarrow(heros.get(1));
                        } else if(goodOnerécompense.getName() == "Mage") {
                            goodOnerécompense.Rajoutermana(heros.get(ixHero3));
                        }
                        ixHero3 = ixHero3 + 1;
                    } else if (choixrécompense == 4) {
                            if(heros.get(ixHero3) instanceof Healer){
                                goodOnerécompense.protectionWin(heros.get(ixHero3));
                            }  else if(heros.get(ixHero3) instanceof Hunter) {
                                goodOnerécompense.protectionWin(heros.get(ixHero3));
                            } else if(heros.get(ixHero3) instanceof Mage) {
                                goodOnerécompense.protectionWin(heros.get(ixHero3));
                            } else if(heros.get(ixHero3) instanceof Warrior) {
                                goodOnerécompense.protectionWin(heros.get(ixHero3));
                            }
                        ixHero3 = ixHero3 + 1;

                    } else if (choixrécompense == 5) {
                        System.out.println("[1]-Rajouter des potions de vie / [2]-Rajouter des potions de dégat");
                        int choixrécompensepotion = scanner.nextInt();
                        while (choixrécompensepotion < 1 || choixrécompensepotion > 2) {
                            System.out.println("Choix incorrect!!! Veuillez choisir un nombre entre 1 et 2 correspondant au choix voulut.");
                            System.out.println("Votre choix: ");
                            choixrécompensepotion = scanner.nextInt();
                        }
                        if (choixrécompensepotion == 1){
                            goodOnerécompense.RajouterPotionVie(heros.get(ixHero3));
                        } else if(choixrécompensepotion == 2){
                            goodOnerécompense.RajouterPotionDegat(heros.get(ixHero3));
                        }
                        ixHero3 = ixHero3 + 1;
                    }
                }

            } else if (j == 2) {
                for (int i = 1; i <= heros.size(); i++) {
                    int valuetest = random.nextInt(3) + 7; // Initialisation du chiffre aléatoire entre 1 et 2
                    enemies.add(new Ennemy("Monstre", 40, 0,valuetest)); // création de l'ennemie avec damagepoints aléatoire entre 1 et 3
                }
                int ixHero3 = 0;
                for (int i = 1; i <= heros.size(); i++) {
                    Combatant goodOnerécompense = heros.get(ixHero3);
                    displayMessage("******Vous pouvez choisir un avantage pour "+Bleu+ goodOnerécompense.getName() + RESET +" afin de bien débuter votre prochain combat.****** \n Liste des récompenses disponibles :");
                    if(goodOnerécompense.getName()=="Hunter" || goodOnerécompense.getName()=="Mage" || goodOnerécompense.getName()=="Warrior"){ displayMessageReturn("[1]-Augmenter les dégats de l'arme (aléatoirement entre 2 ou 4 de plus) |"+ Bleu +" Actuellement (");
                        goodOnerécompense.afficherdegat(heros.get(ixHero3));
                        displayMessage(RESET);}
                    else if(goodOnerécompense.getName()=="Healer"){displayMessageReturn("[1]-Augmenter les soins (aléatoirement entre 2 ou 4 de plus) |"+ Bleu +" Actuellement (");
                        goodOnerécompense.afficherdegat(heros.get(ixHero3));
                        displayMessage(RESET);}
                    displayMessageReturn("[2]-Augmenter l'efficacité des potion et de la nourriture |"+ Bleu +" Actuellement (");
                    goodOnerécompense.afficherdegatPotionDegat(heros.get(ixHero3));
                    goodOnerécompense.afficherdegatPotionVie(heros.get(ixHero3));
                    goodOnerécompense.afficherhealFOOD(heros.get(ixHero3));
                    displayMessage(RESET);
                    if(goodOnerécompense.getName()=="Hunter"){displayMessageReturn("[3]-Rajouter des flèches à votre arc |"+ Bleu +" Actuellement (");
                        goodOnerécompense.afficherfleche(heros.get(ixHero3));
                        displayMessage(RESET);}
                    else if(goodOnerécompense.getName()=="Mage"){displayMessageReturn("[3]-Rajouter 50 Mana |"+ Bleu +" Actuellement (");
                        goodOnerécompense.affichermana(heros.get(ixHero3));
                        displayMessage(RESET);}
                    else if(goodOnerécompense.getName()=="Healer" || goodOnerécompense.getName()=="Warrior" ){displayMessage("-------------------------------------");}
                    displayMessage("[4]-Rajouter des boucliers |"+ Bleu +" Actuellement (" + goodOnerécompense.getArmures() +"\uD83D\uDEE1)"+ RESET);
                    displayMessageReturn("[5]-Rajouter des potions de vie ou de dégat dans l'inventaire |"+ Bleu +" Actuellement potion de vie(");
                    goodOnerécompense.afficherNBPotionVie(heros.get(ixHero3)); displayMessageReturn("- potion de dégat(");
                    goodOnerécompense.afficherNBPotionDegat(heros.get(ixHero3)); displayMessage(RESET);
                    System.out.println(" ");
                    System.out.println("Que voulez-vous choisir comme récompense pour vos heros");
                    int choixrécompense = scanner.nextInt();
                    while (choixrécompense < 1 || choixrécompense > 5) {
                        System.out.println("Choix incorrect!!! Veuillez choisir un nombre entre 1 et 5 correspondant à l'action voulut.");
                        System.out.println("Votre choix: ");
                        choixrécompense = scanner.nextInt();
                    }

                    if (choixrécompense == 1) {
                        int nombreAleatoire = new Random().nextInt(4);

                        if(heros.get(ixHero3) instanceof Warrior){
                            damagePointsWarrior= nombreAleatoire+damagePointsWarrior;
                            ((Warrior) heros.get(ixHero3)).take(new Weapon("Knife", damagePointsWarrior));
                        }
                        else if(heros.get(ixHero3) instanceof Hunter){
                            damagePointsHunter= nombreAleatoire+damagePointsHunter;
                            ((Hunter) heros.get(ixHero3)).take(new Weapon("Arrow", damagePointsHunter));
                        }
                        else if(heros.get(ixHero3) instanceof Mage){
                            damagePointsMage= nombreAleatoire+damagePointsMage;
                            ((Mage) heros.get(ixHero3)).take(new Weapon("Multiple attaque", damagePointsMage));
                        }
                        else if(heros.get(ixHero3) instanceof Healer){
                            HealingHealer= nombreAleatoire+HealingHealer;
                            ((Healer) heros.get(ixHero3)).take(new Weapon("Soin", HealingHealer));
                        }
                        ixHero3 = ixHero3 + 1;

                    } else if (choixrécompense == 2) {
                        int nombreAleatoirePotionDegat = new Random().nextInt(2)+damagePointsPotionDegat;
                        nombreAleatoirePotionDegat += 2;
                        int nombreAleatoirePotionVie = new Random().nextInt(2)+HealingPotionVie;
                        nombreAleatoirePotionVie += 2;
                        int nombreAleatoireFood = new Random().nextInt(2)+FOOD;
                        nombreAleatoireFood += 2;
                        if(heros.get(ixHero3) instanceof Warrior){
                            damagePointsPotionDegat= nombreAleatoirePotionDegat*2;
                            HealingPotionVie= nombreAleatoirePotionVie*2;
                            ((Warrior) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                            FOOD= nombreAleatoireFood*2;
                            ((Warrior) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                        }
                        else if(heros.get(ixHero3) instanceof Hunter){
                            damagePointsPotionDegat= nombreAleatoirePotionDegat*2;
                            HealingPotionVie= nombreAleatoirePotionVie*2;
                            ((Hunter) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                            FOOD= nombreAleatoireFood*2;
                            ((Hunter) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                        }
                        else if(heros.get(ixHero3) instanceof Mage){
                            damagePointsPotionDegat= nombreAleatoirePotionDegat*2;
                            HealingPotionVie= nombreAleatoirePotionVie*2;
                            ((Mage) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                            FOOD= nombreAleatoireFood*2;
                            ((Mage) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                        }
                        else if(heros.get(ixHero3) instanceof Healer){
                            damagePointsPotionDegat= nombreAleatoirePotionDegat*2;
                            HealingPotionVie= nombreAleatoirePotionVie*2;
                            ((Healer) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                            FOOD= nombreAleatoireFood*2;
                            ((Healer) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                        }
                        ixHero3 = ixHero3 + 1;

                    } else if (choixrécompense == 3) {
                        if(goodOnerécompense.getName() == "Hunter"){
                            goodOnerécompense.Rajouterarrow(heros.get(1));
                        } else if(goodOnerécompense.getName() == "Mage") {
                            goodOnerécompense.Rajoutermana(heros.get(ixHero3));
                        }
                        ixHero3 = ixHero3 + 1;
                    } else if (choixrécompense == 4) {
                        if(heros.get(ixHero3) instanceof Healer){
                            goodOnerécompense.protectionWin(heros.get(ixHero3));
                        }  else if(heros.get(ixHero3) instanceof Hunter) {
                            goodOnerécompense.protectionWin(heros.get(ixHero3));
                        } else if(heros.get(ixHero3) instanceof Mage) {
                            goodOnerécompense.protectionWin(heros.get(ixHero3));
                        } else if(heros.get(ixHero3) instanceof Warrior) {
                            goodOnerécompense.protectionWin(heros.get(ixHero3));
                        }
                        ixHero3 = ixHero3 + 1;

                    } else if (choixrécompense == 5) {
                        System.out.println("[1]-Rajouter des potions de vie / [2]-Rajouter des potions de dégat");
                        int choixrécompensepotion = scanner.nextInt();
                        while (choixrécompensepotion < 1 || choixrécompensepotion > 2) {
                            System.out.println("Choix incorrect!!! Veuillez choisir un nombre entre 1 et 2 correspondant au choix voulut.");
                            System.out.println("Votre choix: ");
                            choixrécompensepotion = scanner.nextInt();
                        }
                        if (choixrécompensepotion == 1){
                            goodOnerécompense.RajouterPotionVie(heros.get(ixHero3));
                        } else if(choixrécompensepotion == 2){
                            goodOnerécompense.RajouterPotionDegat(heros.get(ixHero3));
                        }
                        ixHero3 = ixHero3 + 1;
                    }
                }


            } else if (j == 3) {
                for (int i = 1; i <= heros.size(); i++) {
                    int valuetest = random.nextInt(4) + 10; // Initialisation du chiffre aléatoire entre 1 et 2
                    enemies.add(new Ennemy("Monstre", 50,0,valuetest)); // création de l'ennemie avec damagepoints aléatoire entre 1 et 3
                }
                int ixHero3 = 0;
                for (int i = 1; i <= heros.size(); i++) {
                    Combatant goodOnerécompense = heros.get(ixHero3);
                    displayMessage("******Vous pouvez choisir un avantage pour "+Bleu+ goodOnerécompense.getName() + RESET +" afin de bien débuter votre prochain combat.****** \n Liste des récompenses disponibles :");
                    if(goodOnerécompense.getName()=="Hunter" || goodOnerécompense.getName()=="Mage" || goodOnerécompense.getName()=="Warrior"){ displayMessageReturn("[1]-Augmenter les dégats de l'arme (aléatoirement entre 2 ou 4 de plus) |"+ Bleu +" Actuellement (");
                        goodOnerécompense.afficherdegat(heros.get(ixHero3));
                        displayMessage(RESET);}
                    else if(goodOnerécompense.getName()=="Healer"){displayMessageReturn("[1]-Augmenter les soins (aléatoirement entre 2 ou 4 de plus) |"+ Bleu +" Actuellement (");
                        goodOnerécompense.afficherdegat(heros.get(ixHero3));
                        displayMessage(RESET);}
                    displayMessageReturn("[2]-Augmenter l'efficacité des potion et de la nourriture |"+ Bleu +" Actuellement (");
                    goodOnerécompense.afficherdegatPotionDegat(heros.get(ixHero3));
                    goodOnerécompense.afficherdegatPotionVie(heros.get(ixHero3));
                    goodOnerécompense.afficherhealFOOD(heros.get(ixHero3));
                    displayMessage(RESET);
                    if(goodOnerécompense.getName()=="Hunter"){displayMessageReturn("[3]-Rajouter des flèches à votre arc |"+ Bleu +" Actuellement (");
                        goodOnerécompense.afficherfleche(heros.get(ixHero3));
                        displayMessage(RESET);}
                    else if(goodOnerécompense.getName()=="Mage"){displayMessageReturn("[3]-Rajouter 50 Mana |"+ Bleu +" Actuellement (");
                        goodOnerécompense.affichermana(heros.get(ixHero3));
                        displayMessage(RESET);}
                    else if(goodOnerécompense.getName()=="Healer" || goodOnerécompense.getName()=="Warrior" ){displayMessage("-------------------------------------");}
                    displayMessage("[4]-Rajouter des boucliers |"+ Bleu +" Actuellement (" + goodOnerécompense.getArmures() +"\uD83D\uDEE1)"+ RESET);
                    displayMessageReturn("[5]-Rajouter des potions de vie ou de dégat dans l'inventaire |"+ Bleu +" Actuellement potion de vie(");
                    goodOnerécompense.afficherNBPotionVie(heros.get(ixHero3)); displayMessageReturn("- potion de dégat(");
                    goodOnerécompense.afficherNBPotionDegat(heros.get(ixHero3)); displayMessage(RESET);
                    System.out.println(" ");
                    System.out.println("Que voulez-vous choisir comme récompense pour vos heros");
                    int choixrécompense = scanner.nextInt();
                    while (choixrécompense < 1 || choixrécompense > 5) {
                        System.out.println("Choix incorrect!!! Veuillez choisir un nombre entre 1 et 5 correspondant à l'action voulut.");
                        System.out.println("Votre choix: ");
                        choixrécompense = scanner.nextInt();
                    }

                    if (choixrécompense == 1) {
                        int nombreAleatoire = new Random().nextInt(4);

                        if(heros.get(ixHero3) instanceof Warrior){
                            damagePointsWarrior= nombreAleatoire+damagePointsWarrior;
                            ((Warrior) heros.get(ixHero3)).take(new Weapon("Knife", damagePointsWarrior));
                        }
                        else if(heros.get(ixHero3) instanceof Hunter){
                            damagePointsHunter= nombreAleatoire+damagePointsHunter;
                            ((Hunter) heros.get(ixHero3)).take(new Weapon("Arrow", damagePointsHunter));
                        }
                        else if(heros.get(ixHero3) instanceof Mage){
                            damagePointsMage= nombreAleatoire+damagePointsMage;
                            ((Mage) heros.get(ixHero3)).take(new Weapon("Multiple attaque", damagePointsMage));
                        }
                        else if(heros.get(ixHero3) instanceof Healer){
                            HealingHealer= nombreAleatoire+HealingHealer;
                            ((Healer) heros.get(ixHero3)).take(new Weapon("Soin", HealingHealer));
                        }
                        ixHero3 = ixHero3 + 1;

                    } else if (choixrécompense == 2) {
                        int nombreAleatoirePotionDegat = new Random().nextInt(2)+damagePointsPotionDegat;
                        nombreAleatoirePotionDegat += 2;
                        int nombreAleatoirePotionVie = new Random().nextInt(2)+HealingPotionVie;
                        nombreAleatoirePotionVie += 2;
                        int nombreAleatoireFood = new Random().nextInt(2)+FOOD;
                        nombreAleatoireFood += 2;
                        if(heros.get(ixHero3) instanceof Warrior){
                            damagePointsPotionDegat= nombreAleatoirePotionDegat*2;
                            HealingPotionVie= nombreAleatoirePotionVie*2;
                            ((Warrior) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                            FOOD= nombreAleatoireFood*2;
                            ((Warrior) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                        }
                        else if(heros.get(ixHero3) instanceof Hunter){
                            damagePointsPotionDegat= nombreAleatoirePotionDegat*2;
                            HealingPotionVie= nombreAleatoirePotionVie*2;
                            ((Hunter) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                            FOOD= nombreAleatoireFood*2;
                            ((Hunter) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                        }
                        else if(heros.get(ixHero3) instanceof Mage){
                            damagePointsPotionDegat= nombreAleatoirePotionDegat*2;
                            HealingPotionVie= nombreAleatoirePotionVie*2;
                            ((Mage) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                            FOOD= nombreAleatoireFood*2;
                            ((Mage) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                        }
                        else if(heros.get(ixHero3) instanceof Healer){
                            damagePointsPotionDegat= nombreAleatoirePotionDegat*2;
                            HealingPotionVie= nombreAleatoirePotionVie*2;
                            ((Healer) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                            FOOD= nombreAleatoireFood*2;
                            ((Healer) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                        }
                        ixHero3 = ixHero3 + 1;

                    } else if (choixrécompense == 3) {
                        if(goodOnerécompense.getName() == "Hunter"){
                            goodOnerécompense.Rajouterarrow(heros.get(1));
                        } else if(goodOnerécompense.getName() == "Mage") {
                            goodOnerécompense.Rajoutermana(heros.get(ixHero3));
                        }
                        ixHero3 = ixHero3 + 1;
                    } else if (choixrécompense == 4) {
                        if(heros.get(ixHero3) instanceof Healer){
                            goodOnerécompense.protectionWin(heros.get(ixHero3));
                        }  else if(heros.get(ixHero3) instanceof Hunter) {
                            goodOnerécompense.protectionWin(heros.get(ixHero3));
                        } else if(heros.get(ixHero3) instanceof Mage) {
                            goodOnerécompense.protectionWin(heros.get(ixHero3));
                        } else if(heros.get(ixHero3) instanceof Warrior) {
                            goodOnerécompense.protectionWin(heros.get(ixHero3));
                        }
                        ixHero3 = ixHero3 + 1;

                    } else if (choixrécompense == 5) {
                        System.out.println("[1]-Rajouter des potions de vie / [2]-Rajouter des potions de dégat");
                        int choixrécompensepotion = scanner.nextInt();
                        while (choixrécompensepotion < 1 || choixrécompensepotion > 2) {
                            System.out.println("Choix incorrect!!! Veuillez choisir un nombre entre 1 et 2 correspondant au choix voulut.");
                            System.out.println("Votre choix: ");
                            choixrécompensepotion = scanner.nextInt();
                        }
                        if (choixrécompensepotion == 1){
                            goodOnerécompense.RajouterPotionVie(heros.get(ixHero3));
                        } else if(choixrécompensepotion == 2){
                            goodOnerécompense.RajouterPotionDegat(heros.get(ixHero3));
                        }
                        ixHero3 = ixHero3 + 1;
                    }
                }

            } else if (j == 4) {
                System.out.println("Bien joué, vous avez vaincu tous les monstres.");
                System.out.println("Vous etes maintant au combat finale face au Dragon!!!!");
                    int valuetest = random.nextInt(6) + 40; // Initialisation du chiffre aléatoire entre 1 et 2
                    enemies.add(new Dragon("Dragon", 300,0,valuetest)); // création de l'ennemie avec damagepoints aléatoire entre 1 et 3

            int ixHero3 = 0;
            for (int i = 1; i <= heros.size(); i++) {
                Combatant goodOnerécompense = heros.get(ixHero3);
                displayMessage("******Vous pouvez choisir un avantage pour "+Bleu+ goodOnerécompense.getName() + RESET +" afin de bien débuter votre prochain combat.****** \n Liste des récompenses disponibles :");
                if(goodOnerécompense.getName()=="Hunter" || goodOnerécompense.getName()=="Mage" || goodOnerécompense.getName()=="Warrior"){ displayMessageReturn("[1]-Augmenter les dégats de l'arme (aléatoirement entre 2 ou 4 de plus) |"+ Bleu +" Actuellement (");
                    goodOnerécompense.afficherdegat(heros.get(ixHero3));
                    displayMessage(RESET);}
                else if(goodOnerécompense.getName()=="Healer"){displayMessageReturn("[1]-Augmenter les soins (aléatoirement entre 2 ou 4 de plus) |"+ Bleu +" Actuellement (");
                    goodOnerécompense.afficherdegat(heros.get(ixHero3));
                    displayMessage(RESET);}
                displayMessageReturn("[2]-Augmenter l'efficacité des potion et de la nourriture |"+ Bleu +" Actuellement (");
                goodOnerécompense.afficherdegatPotionDegat(heros.get(ixHero3));
                goodOnerécompense.afficherdegatPotionVie(heros.get(ixHero3));
                goodOnerécompense.afficherhealFOOD(heros.get(ixHero3));
                displayMessage(RESET);
                if(goodOnerécompense.getName()=="Hunter"){displayMessageReturn("[3]-Rajouter des flèches à votre arc |"+ Bleu +" Actuellement (");
                    goodOnerécompense.afficherfleche(heros.get(ixHero3));
                    displayMessage(RESET);}
                else if(goodOnerécompense.getName()=="Mage"){displayMessageReturn("[3]-Rajouter 50 Mana |"+ Bleu +" Actuellement (");
                    goodOnerécompense.affichermana(heros.get(ixHero3));
                    displayMessage(RESET);}
                else if(goodOnerécompense.getName()=="Healer" || goodOnerécompense.getName()=="Warrior" ){displayMessage("-------------------------------------");}
                displayMessage("[4]-Rajouter des boucliers |"+ Bleu +" Actuellement (" + goodOnerécompense.getArmures() +"\uD83D\uDEE1)"+ RESET);
                displayMessageReturn("[5]-Rajouter des potions de vie ou de dégat dans l'inventaire |"+ Bleu +" Actuellement potion de vie(");
                goodOnerécompense.afficherNBPotionVie(heros.get(ixHero3)); displayMessageReturn("- potion de dégat(");
                goodOnerécompense.afficherNBPotionDegat(heros.get(ixHero3)); displayMessage(RESET);
                System.out.println(" ");
                System.out.println("Que voulez-vous choisir comme récompense pour vos heros");
                int choixrécompense = scanner.nextInt();
                while (choixrécompense < 1 || choixrécompense > 5) {
                    System.out.println("Choix incorrect!!! Veuillez choisir un nombre entre 1 et 5 correspondant à l'action voulut.");
                    System.out.println("Votre choix: ");
                    choixrécompense = scanner.nextInt();
                }

                if (choixrécompense == 1) {
                    int nombreAleatoire = new Random().nextInt(4);

                    if(heros.get(ixHero3) instanceof Warrior){
                        damagePointsWarrior= nombreAleatoire+damagePointsWarrior;
                        ((Warrior) heros.get(ixHero3)).take(new Weapon("Knife", damagePointsWarrior));
                    }
                    else if(heros.get(ixHero3) instanceof Hunter){
                        damagePointsHunter= nombreAleatoire+damagePointsHunter;
                        ((Hunter) heros.get(ixHero3)).take(new Weapon("Arrow", damagePointsHunter));
                    }
                    else if(heros.get(ixHero3) instanceof Mage){
                        damagePointsMage= nombreAleatoire+damagePointsMage;
                        ((Mage) heros.get(ixHero3)).take(new Weapon("Multiple attaque", damagePointsMage));
                    }
                    else if(heros.get(ixHero3) instanceof Healer){
                        HealingHealer= nombreAleatoire+HealingHealer;
                        ((Healer) heros.get(ixHero3)).take(new Weapon("Soin", HealingHealer));
                    }
                    ixHero3 = ixHero3 + 1;

                } else if (choixrécompense == 2) {
                    int nombreAleatoirePotionDegat = new Random().nextInt(2)+damagePointsPotionDegat;
                    nombreAleatoirePotionDegat += 2;
                    int nombreAleatoirePotionVie = new Random().nextInt(2)+HealingPotionVie;
                    nombreAleatoirePotionVie += 2;
                    int nombreAleatoireFood = new Random().nextInt(2)+FOOD;
                    nombreAleatoireFood += 2;
                    if(heros.get(ixHero3) instanceof Warrior){
                        damagePointsPotionDegat= nombreAleatoirePotionDegat*2;
                        HealingPotionVie= nombreAleatoirePotionVie*2;
                        ((Warrior) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                        FOOD= nombreAleatoireFood*2;
                        ((Warrior) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                    }
                    else if(heros.get(ixHero3) instanceof Hunter){
                        damagePointsPotionDegat= nombreAleatoirePotionDegat*2;
                        HealingPotionVie= nombreAleatoirePotionVie*2;
                        ((Hunter) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                        FOOD= nombreAleatoireFood*2;
                        ((Hunter) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                    }
                    else if(heros.get(ixHero3) instanceof Mage){
                        damagePointsPotionDegat= nombreAleatoirePotionDegat*2;
                        HealingPotionVie= nombreAleatoirePotionVie*2;
                        ((Mage) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                        FOOD= nombreAleatoireFood*2;
                        ((Mage) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                    }
                    else if(heros.get(ixHero3) instanceof Healer){
                        damagePointsPotionDegat= nombreAleatoirePotionDegat*2;
                        HealingPotionVie= nombreAleatoirePotionVie*2;
                        ((Healer) heros.get(ixHero3)).take(new Potion("Potion", HealingPotionVie,damagePointsPotionDegat));

                        FOOD= nombreAleatoireFood*2;
                        ((Healer) heros.get(ixHero3)).take(new Food("Pomme", FOOD, QTFOOD));
                    }
                    ixHero3 = ixHero3 + 1;

                } else if (choixrécompense == 3) {
                    if(goodOnerécompense.getName() == "Hunter"){
                        goodOnerécompense.Rajouterarrow(heros.get(1));
                    } else if(goodOnerécompense.getName() == "Mage") {
                        goodOnerécompense.Rajoutermana(heros.get(ixHero3));
                    }
                    ixHero3 = ixHero3 + 1;
                } else if (choixrécompense == 4) {
                    if(heros.get(ixHero3) instanceof Healer){
                        goodOnerécompense.protectionWin(heros.get(ixHero3));
                    }  else if(heros.get(ixHero3) instanceof Hunter) {
                        goodOnerécompense.protectionWin(heros.get(ixHero3));
                    } else if(heros.get(ixHero3) instanceof Mage) {
                        goodOnerécompense.protectionWin(heros.get(ixHero3));
                    } else if(heros.get(ixHero3) instanceof Warrior) {
                        goodOnerécompense.protectionWin(heros.get(ixHero3));
                    }
                    ixHero3 = ixHero3 + 1;

                } else if (choixrécompense == 5) {
                    System.out.println("[1]-Rajouter des potions de vie / [2]-Rajouter des potions de dégat");
                    int choixrécompensepotion = scanner.nextInt();
                    while (choixrécompensepotion < 1 || choixrécompensepotion > 2) {
                        System.out.println("Choix incorrect!!! Veuillez choisir un nombre entre 1 et 2 correspondant au choix voulut.");
                        System.out.println("Votre choix: ");
                        choixrécompensepotion = scanner.nextInt();
                    }
                    if (choixrécompensepotion == 1){
                        goodOnerécompense.RajouterPotionVie(heros.get(ixHero3));
                    } else if(choixrécompensepotion == 2){
                        goodOnerécompense.RajouterPotionDegat(heros.get(ixHero3));
                    }
                    ixHero3 = ixHero3 + 1;
                }
            }}

                int value = random.nextInt(2) + 1; // Initialisation du chiffre aléatoire entre 1 et 2

                if (value == 1) { // Les Heros commencent à jouer
                    System.out.println("Les heros commencent à jouer");

                    // Boucle de jeu
                    for (int i = 1; i <= 20; i++) {
                        if (enemies.size() == 0) {
                            if(j==4){
                                Feux();
                                break;
                            }
                            else{
                                break;
                            } }
                        System.out.println("-------------------------- Round n°" + i + " --------------------------");

                        displayStatus(heros, enemies);
                        int ixHero = 0;
                        int ixEnemies = 0;


                        for (int k = 1; k <= heros.size(); k++) {
                            if (enemies.size() == 0) {
                                System.out.println("bRAVOIl n'y a plus de monstre, Combat suivant.");
                                break;
                            }
                            Combatant goodOne = heros.get(ixHero);
                            Combatant badOne = enemies.get(ixEnemies);
                            if (goodOne.getName() == "Healer") {
                                System.out.println("Que voulez-vous faire avec " + goodOne.getName() + " ?");
                                System.out.println("[1]-Soigner / [2]-Proteger / [3]-Manger / [4]-potion");
                                int choixhealerround = scanner.nextInt();
                                while (choixhealerround < 1 || choixhealerround > 4) {
                                    System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 4 correspondant à l'action voulut");
                                    System.out.println("Votre choix: ");
                                    choixhealerround = scanner.nextInt();
                                }

                                if (choixhealerround == 1) {
                                    System.out.println("Qui voulez-vous soigner ?");
                                    Statusennemies(heros);
                                    int choixhealer = scanner.nextInt();
                                    int ixHero2 = 0;
                                    while (choixhealer < 1 || choixhealer > heros.size()) {
                                        System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et " + heros.size() + " correspondant à l'action voulut");
                                        System.out.println("Votre choix: ");
                                        choixhealer = scanner.nextInt();
                                    }
                                    if (choixhealer == 1) {
                                        ixHero2 = 0;
                                        goodOne.heal(heros.get(ixHero2));
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        if (goodOne.getHp()>=100){
                                        } else {
                                            displayMessage("Vous venez de soigner " + goodOne.getName() + ", il a maintenant " + goodOne.getHp() + " Hp");
                                        }
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero--;
                                            k--;
                                        }

                                    } else if (choixhealer == 2) {
                                        ixHero2 = 1;
                                        goodOne.heal(heros.get(ixHero2));
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        if (goodOne.getHp()>=100){
                                        } else {
                                            displayMessage("Vous venez de soigner " + goodOne.getName() + ", il a maintenant " + goodOne.getHp() + " Hp");
                                        }
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    } else if (choixhealer == 3) {
                                        ixHero2 = 2;
                                        goodOne.heal(heros.get(ixHero2));
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        if (goodOne.getHp()>=100){
                                        } else {
                                            displayMessage("Vous venez de soigner " + goodOne.getName() + ", il a maintenant " + goodOne.getHp() + " Hp");
                                        }
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    } else if (choixhealer == 4) {
                                        ixHero2 = 3;
                                        goodOne.heal(heros.get(ixHero2));
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        if (goodOne.getHp()>=100){
                                        } else {
                                            displayMessage("Vous venez de soigner " + goodOne.getName() + ", il a maintenant " + goodOne.getHp() + " Hp");
                                        }
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    }
                                    ixHero = ixHero + 1;

                                } else if (choixhealerround == 2) {
                                    goodOne.protectionLoose(heros.get(ixHero));
                                    if (goodOne.getHp()<=0){
                                        mortHero(heros, ixHero);
                                        ixHero--;
                                        k--;
                                    }
                                    ixHero = ixHero + 1;

                                } else if (choixhealerround == 3) {
                                    System.out.println("Que vouler-vous manger ?");
                                    displayMessageReturn("[1]-Pomme");
                                    goodOne.afficherhealFOOD(heros.get(ixHero));
                                    displayMessage("");
                                    int choixFoodhealer = scanner.nextInt();
                                    while (choixFoodhealer < 1 || choixFoodhealer > 2) {
                                        System.out.println("Choix incorrect. Veuillez choisir le nombre 1");
                                        System.out.println("Votre choix: ");
                                        choixFoodhealer = scanner.nextInt();
                                    }

                                    if (choixFoodhealer == 1) {
                                        goodOne.manger(heros.get(ixHero));
                                        badOne.fight((heros.get(ixHero)));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                    }
                                    ixHero = ixHero + 1;

                                }
                                else if (choixhealerround == 4) {
                                    System.out.println("Que vouler-vous utiliser comme Potion ?");
                                    System.out.print("[1]-Potion de Heal");
                                    goodOne.afficherdegatNBPotionVie(heros.get(ixHero));
                                    System.out.println("");
                                    System.out.print("[2]-Potion de Force (");
                                    goodOne.afficherdegatNBPotionDegat(heros.get(ixHero));
                                    System.out.println(" ");
                                    int choixPotion = scanner.nextInt();
                                    while (choixPotion < 1 || choixPotion > 2) {
                                        System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 2 correspondant à la nourriture voulut");
                                        System.out.println("Votre choix: ");
                                        choixPotion = scanner.nextInt();
                                    }

                                    if (choixPotion == 1) {
                                        goodOne.PotionVie(heros.get(ixHero));
                                        badOne.fight((heros.get(ixHero)));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                    } else if (choixPotion == 2) {
                                        System.out.println("Qui voulez-vous Attaquer ?");
                                        Statusennemies(enemies);
                                        int choixattaqueennemie = scanner.nextInt();
                                        while (choixattaqueennemie < 1 || choixattaqueennemie > enemies.size()) {
                                            System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et " + enemies.size() + " correspondant à l'action voulut");
                                            System.out.println("Votre choix: ");
                                            choixattaqueennemie = scanner.nextInt();
                                        }
                                        if (choixattaqueennemie == 1) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 0, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 2) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 1, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 3) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 2, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 4) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 3, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        }
                                    }
                                    ixHero = ixHero + 1;
                                }
                            } else if (goodOne.getName() == "Mage") {
                                System.out.println("Que voulez-vous faire avec " + goodOne.getName() + " ?");
                                System.out.println("[1]-Magie / [2]-Proteger / [3]-Manger / [4]-Potion");
                                int choixmageround = scanner.nextInt();
                                while (choixmageround < 1 || choixmageround > 4) {
                                    System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 4 correspondant à l'action voulut");
                                    System.out.println("Votre choix: ");
                                    choixmageround = scanner.nextInt();
                                }

                                if (choixmageround == 1) {
                                    if(enemies.size()==1) {
                                        displayMessageReturn("Vous venez d'utiliser de la magie qui inflige (");
                                        goodOne.afficherdegat(heros.get(ixHero));
                                        displayMessage(" points de dégat à chaque Monstre");
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        badOne.fight(heros.get(ixHero));
                                        for (int p = 0; 0<=p; p--){
                                            goodOne.fight((enemies.get(p)));
                                            if (enemies.get(p).getHp() <= 0) {
                                                displayMessage("Bravo, " + goodOne.getName() + " a vaincu " + badOne.getName() + " !!!");
                                                enemies.remove(p);
                                            }
                                        }
                                        if (goodOne.getHp() <= 0) {
                                            displayMessage("Malheuresement nous venons de perdre " + goodOne.getName() + " car il a été vaincu par " + badOne.getName() + " !!!");
                                            heros.remove(ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    }
                                    else if(enemies.size()==2) {
                                        displayMessageReturn("Vous venez d'utiliser de la magie qui inflige (");
                                        goodOne.afficherdegat(heros.get(ixHero));
                                        displayMessage(" points de dégat à chaque Monstre");
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        for (int p = 1; 0<=p; p--){
                                            goodOne.fight((enemies.get(p)));
                                            if (enemies.get(p).getHp() <= 0) {
                                                displayMessage("Bravo, " + goodOne.getName() + " a vaincu " + badOne.getName() + " !!!");
                                                enemies.remove(p);
                                            }
                                        }
                                        if (goodOne.getHp() <= 0) {
                                            displayMessage("Malheuresement nous venons de perdre " + goodOne.getName() + " car il a été vaincu par " + badOne.getName() + " !!!");
                                            heros.remove(ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    }
                                    else if(enemies.size()==3) {
                                        displayMessageReturn("Vous venez d'utiliser de la magie qui inflige (");
                                        goodOne.afficherdegat(heros.get(ixHero));
                                        displayMessage(" points de dégat à chaque Monstre");
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        for (int p = 2; 0<=p; p--){
                                            goodOne.fight((enemies.get(p)));
                                            if (enemies.get(p).getHp() <= 0) {
                                                displayMessage("Bravo, " + goodOne.getName() + " a vaincu " + badOne.getName() + " !!!");
                                                enemies.remove(p);
                                            }
                                        }
                                        if (goodOne.getHp() <= 0) {
                                            displayMessage("Malheuresement nous venons de perdre " + goodOne.getName() + " car il a été vaincu par " + badOne.getName() + " !!!");
                                            heros.remove(ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    }
                                    else if(enemies.size()==4) {
                                        displayMessageReturn("Vous venez d'utiliser de la magie qui inflige (");
                                        goodOne.afficherdegat(heros.get(ixHero));
                                        displayMessage(" points de dégat à chaque Monstre");
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        for (int p = 3; 0<=p; p--){
                                            goodOne.fight((enemies.get(p)));
                                            if (enemies.get(p).getHp() <= 0) {
                                                displayMessage("Bravo, " + goodOne.getName() + " a vaincu " + badOne.getName() + " !!!");
                                                enemies.remove(p);
                                            }
                                        }
                                        if (goodOne.getHp() <= 0) {
                                            displayMessage("Malheuresement nous venons de perdre " + goodOne.getName() + " car il a été vaincu par " + badOne.getName() + " !!!");
                                            heros.remove(ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    }

                                    ixEnemies = 0;
                                    ixHero = ixHero + 1;

                                } else if (choixmageround == 2) {
                                    goodOne.protectionLoose(heros.get(ixHero));
                                    if (goodOne.getHp()<=0){
                                        mortHero(heros, ixHero);
                                        ixHero--;
                                        k--;}
                                    ixHero = ixHero + 1;

                                } else if (choixmageround == 3) {
                                    System.out.println("Que vouler-vous manger ?");
                                    displayMessageReturn("[1]-Pomme");
                                    goodOne.afficherhealFOOD(heros.get(ixHero));
                                    displayMessage("");
                                    int choixFoodmage = scanner.nextInt();
                                    while (choixFoodmage < 1 || choixFoodmage > 2) {
                                        System.out.println("Choix incorrect. Veuillez choisir le nombre 1");
                                        System.out.println("Votre choix: ");
                                        choixFoodmage = scanner.nextInt();
                                    }

                                    if (choixFoodmage == 1) {
                                        goodOne.manger(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                    }
                                    ixHero = ixHero + 1;

                                }
                                else if (choixmageround == 4) {
                                    System.out.println("Que vouler-vous utiliser comme Potion ?");
                                    System.out.print("[1]-Potion de Heal");
                                    goodOne.afficherdegatNBPotionVie(heros.get(ixHero));
                                    System.out.println("");
                                    System.out.print("[2]-Potion de Force (");
                                    goodOne.afficherdegatNBPotionDegat(heros.get(ixHero));
                                    System.out.println(" ");
                                    int choixPotion = scanner.nextInt();
                                    while (choixPotion < 1 || choixPotion > 2) {
                                        System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 2 correspondant à la nourriture voulut");
                                        System.out.println("Votre choix: ");
                                        choixPotion = scanner.nextInt();
                                    }

                                    if (choixPotion == 1) {
                                        goodOne.PotionVie(heros.get(ixHero));
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                    } else if (choixPotion == 2) {
                                        System.out.println("Qui voulez-vous Attaquer ?");
                                        Statusennemies(enemies);
                                        int choixattaqueennemie = scanner.nextInt();
                                        while (choixattaqueennemie < 1 || choixattaqueennemie > enemies.size()) {
                                            System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et " + enemies.size() + " correspondant à l'action voulut");
                                            System.out.println("Votre choix: ");
                                            choixattaqueennemie = scanner.nextInt();
                                        }
                                        if (choixattaqueennemie == 1) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(0));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 0, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 2) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(1));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 1, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 3) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(2));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 2, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 4) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(3));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 3, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        }
                                    }
                                    ixHero = ixHero + 1;
                                }
                            } else {

                                System.out.println("Que voulez-vous faire avec " + goodOne.getName() + " ?");
                                System.out.println("[1]-Attaquer / [2]-Proteger / [3]-Manger / [4]-Potion");
                                int choixherosround = scanner.nextInt();
                                while (choixherosround < 1 || choixherosround > 4) {
                                    System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 4 correspondant à l'action voulut");
                                    System.out.println("Votre choix: ");
                                    choixherosround = scanner.nextInt();
                                }


                                if (choixherosround == 1) {
                                    System.out.println("Qui voulez-vous Attaquer ?");
                                    Statusennemies(enemies);
                                    int choixattaqueennemie = scanner.nextInt();
                                    while (choixattaqueennemie < 1 || choixattaqueennemie > enemies.size()) {
                                        System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et " + enemies.size() + " correspondant à l'action voulut");
                                        System.out.println("Votre choix: ");
                                        choixattaqueennemie = scanner.nextInt();
                                    }
                                    if (choixattaqueennemie == 1) {
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        Attaque(heros, enemies, 0, ixHero);
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero = ixHero-1;
                                            k--;
                                        }
                                    } else if (choixattaqueennemie == 2) {
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        Attaque(heros, enemies, 1, ixHero);
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero = ixHero-1;
                                            k--;
                                        }
                                    } else if (choixattaqueennemie == 3) {
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        Attaque(heros, enemies, 2, ixHero);
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero = ixHero-1;
                                            k--;
                                        }
                                    } else if (choixattaqueennemie == 4) {
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        Attaque(heros, enemies, 3, ixHero);
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero = ixHero-1;
                                            k--;
                                        }
                                    }
                                    ixHero = ixHero + 1;

                                } else if (choixherosround == 2) {
                                    goodOne.protectionLoose(heros.get(ixHero));
                                    if (goodOne.getHp()<=0){
                                        mortHero(heros, ixHero);
                                        ixHero--;
                                        k--;}
                                    ixHero = ixHero + 1;

                                } else if (choixherosround == 3) {
                                    System.out.println("Que vouler-vous manger ?");
                                    displayMessageReturn("[1]-Pomme");
                                    goodOne.afficherhealFOOD(heros.get(ixHero));
                                    displayMessage("");
                                    int choixFood = scanner.nextInt();
                                    while (choixFood != 1) {
                                        System.out.println("Choix incorrect. Veuillez choisir le nombre 1");
                                        System.out.println("Votre choix: ");
                                        choixFood = scanner.nextInt();
                                    }

                                    if (choixFood == 1) {
                                        goodOne.manger(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        badOne.fight(heros.get(ixHero));
                                    }
                                    ixHero = ixHero + 1;

                                }
                                else if (choixherosround == 4) {
                                    System.out.println("Que vouler-vous utiliser comme Potion ?");
                                    System.out.print("[1]-Potion de Heal");
                                    goodOne.afficherdegatNBPotionVie(heros.get(ixHero));
                                    System.out.println(" ");
                                    System.out.print("[2]-Potion de Force (");
                                    goodOne.afficherdegatNBPotionDegat(heros.get(ixHero));
                                    System.out.println(" ");
                                    int choixPotion = scanner.nextInt();
                                    while (choixPotion < 1 || choixPotion > 2) {
                                        System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 2 correspondant à la nourriture voulut");
                                        System.out.println("Votre choix: ");
                                        choixPotion = scanner.nextInt();
                                    }

                                    if (choixPotion == 1) {
                                        goodOne.PotionVie(heros.get(ixHero));
                                    } else if (choixPotion == 2) {
                                        System.out.println("Qui voulez-vous Attaquer ?");
                                        Statusennemies(enemies);
                                        int choixattaqueennemie = scanner.nextInt();
                                        while (choixattaqueennemie < 1 || choixattaqueennemie > enemies.size()) {
                                            System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et " + enemies.size() + " correspondant à l'action voulut");
                                            System.out.println("Votre choix: ");
                                            choixattaqueennemie = scanner.nextInt();
                                        }
                                        if (choixattaqueennemie == 1) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 0, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 2) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 1, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 3) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 2, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 4) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 3, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        }
                                    }
                                    ixHero = ixHero + 1;
                                }
                            }
                        }
                    }

                } else if (value == 2) {
                    System.out.println("Les monstres commencent à jouer");
                    int ixHero5 = 0;
                    int ixEnemies5 = 0;
                    for (int o = 1; o <= heros.size(); o++) {
                        Combatant goodOne = heros.get(ixHero5);
                        Combatant badOne = enemies.get(ixEnemies5);
                        badOne.fight(heros.get(ixHero5));
                        displayMessageReturn(goodOne.getName()+" a subit ");
                        badOne.afficherdegat(enemies.get(ixEnemies5));
                        displayMessage(" de dégat ");
                        if (goodOne.getHp()<=0){
                            mortHero(heros, ixHero5);
                            ixHero5--;
                            o--;
                        }
                        ixHero5++;
                        ixEnemies5++;
                        if(badOne.getName()=="Dragon"){
                            ixEnemies5--;
                        }

                    }
                    // Boucle de jeu
                    for (int i = 1; i <= 20; i++) {
                        if (enemies.size() == 0) {
                            if(j==4){
                                Feux();
                                break;
                            }
                            else{
                                break;
                            }
                        }
                        System.out.println("-------------------------- Round n°" + i + " --------------------------");

                        displayStatus(heros, enemies);
                        int ixHero = 0;
                        int ixEnemies = 0;


                        for (int k = 1; k <= heros.size(); k++) {
                            if (enemies.size() == 0) {
                                System.out.println("Il n'y a plus de monstre, Combat suivant.");
                                break;
                            }
                            Combatant goodOne = heros.get(ixHero);
                            Combatant badOne = enemies.get(ixEnemies);
                            if (goodOne.getName() == "Healer") {
                                System.out.println("Que voulez-vous faire avec " + goodOne.getName() + " ?");
                                System.out.println("[1]-Soigner / [2]-Proteger / [3]-Manger / [4]-potion");
                                int choixhealerround = scanner.nextInt();
                                while (choixhealerround < 1 || choixhealerround > 4) {
                                    System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 4 correspondant à l'action voulut");
                                    System.out.println("Votre choix: ");
                                    choixhealerround = scanner.nextInt();
                                }

                                if (choixhealerround == 1) {
                                    System.out.println("Qui voulez-vous soigner ?");
                                    Statusennemies(heros);
                                    int choixhealer = scanner.nextInt();
                                    int ixHero2 = 0;
                                    while (choixhealer < 1 || choixhealer > heros.size()) {
                                        System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et " + heros.size() + " correspondant à l'action voulut");
                                        System.out.println("Votre choix: ");
                                        choixhealer = scanner.nextInt();
                                    }
                                    if (choixhealer == 1) {
                                        ixHero2 = 0;
                                        goodOne.heal(heros.get(ixHero2));
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        if (goodOne.getHp()>=100){
                                        } else {
                                            displayMessage("Vous venez de soigner " + goodOne.getName() + ", il a maintenant " + goodOne.getHp() + " Hp");
                                        }
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero--;
                                            k--;
                                        }

                                    } else if (choixhealer == 2) {
                                        ixHero2 = 1;
                                        goodOne.heal(heros.get(ixHero2));
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        if (goodOne.getHp()>=100){
                                        } else {
                                            displayMessage("Vous venez de soigner " + goodOne.getName() + ", il a maintenant " + goodOne.getHp() + " Hp");
                                        }
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    } else if (choixhealer == 3) {
                                        ixHero2 = 2;
                                        goodOne.heal(heros.get(ixHero2));
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        if (goodOne.getHp()>=100){
                                        } else {
                                            displayMessage("Vous venez de soigner " + goodOne.getName() + ", il a maintenant " + goodOne.getHp() + " Hp");
                                        }
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    } else if (choixhealer == 4) {
                                        ixHero2 = 3;
                                        goodOne.heal(heros.get(ixHero2));
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        if (goodOne.getHp()>=100){
                                        } else {
                                            displayMessage("Vous venez de soigner " + goodOne.getName() + ", il a maintenant " + goodOne.getHp() + " Hp");
                                        }
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    }
                                    ixHero = ixHero + 1;

                                } else if (choixhealerround == 2) {
                                    goodOne.protectionLoose(heros.get(ixHero));
                                    if (goodOne.getHp()<=0){
                                        mortHero(heros, ixHero);
                                        ixHero--;
                                        k--;
                                    }
                                    ixHero = ixHero + 1;

                                } else if (choixhealerround == 3) {
                                    System.out.println("Que vouler-vous manger ?");
                                    displayMessageReturn("[1]-Pomme");
                                    goodOne.afficherhealFOOD(heros.get(ixHero));
                                    displayMessage("");
                                    int choixFoodhealer = scanner.nextInt();
                                    while (choixFoodhealer < 1 || choixFoodhealer > 2) {
                                        System.out.println("Choix incorrect. Veuillez choisir le nombre 1");
                                        System.out.println("Votre choix: ");
                                        choixFoodhealer = scanner.nextInt();
                                    }

                                    if (choixFoodhealer == 1) {
                                        goodOne.manger(heros.get(ixHero));
                                        badOne.fight((heros.get(ixHero)));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                    }
                                    ixHero = ixHero + 1;

                                }
                                else if (choixhealerround == 4) {
                                    System.out.println("Que vouler-vous utiliser comme Potion ?");
                                    System.out.print("[1]-Potion de Heal");
                                    goodOne.afficherdegatNBPotionVie(heros.get(ixHero));
                                    System.out.println("");
                                    System.out.print("[2]-Potion de Force (");
                                    goodOne.afficherdegatNBPotionDegat(heros.get(ixHero));
                                    System.out.println(" ");
                                    int choixPotion = scanner.nextInt();
                                    while (choixPotion < 1 || choixPotion > 2) {
                                        System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 2 correspondant à la nourriture voulut");
                                        System.out.println("Votre choix: ");
                                        choixPotion = scanner.nextInt();
                                    }

                                    if (choixPotion == 1) {
                                        goodOne.PotionVie(heros.get(ixHero));
                                        badOne.fight((heros.get(ixHero)));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                    } else if (choixPotion == 2) {
                                        System.out.println("Qui voulez-vous Attaquer ?");
                                        Statusennemies(enemies);
                                        int choixattaqueennemie = scanner.nextInt();
                                        while (choixattaqueennemie < 1 || choixattaqueennemie > enemies.size()) {
                                            System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et " + enemies.size() + " correspondant à l'action voulut");
                                            System.out.println("Votre choix: ");
                                            choixattaqueennemie = scanner.nextInt();
                                        }
                                        if (choixattaqueennemie == 1) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 0, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 2) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 1, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 3) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 2, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 4) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 3, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        }
                                    }
                                    ixHero = ixHero + 1;
                                }
                            } else if (goodOne.getName() == "Mage") {
                                System.out.println("Que voulez-vous faire avec " + goodOne.getName() + " ?");
                                System.out.println("[1]-Magie / [2]-Proteger / [3]-Manger / [4]-Potion");
                                int choixmageround = scanner.nextInt();
                                while (choixmageround < 1 || choixmageround > 4) {
                                    System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 4 correspondant à l'action voulut");
                                    System.out.println("Votre choix: ");
                                    choixmageround = scanner.nextInt();
                                }

                                if (choixmageround == 1) {
                                    if(enemies.size()==1) {
                                        displayMessageReturn("Vous venez d'utiliser de la magie qui inflige (");
                                        goodOne.afficherdegat(heros.get(ixHero));
                                        displayMessage(" points de dégat à chaque Monstre");
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        badOne.fight(heros.get(ixHero));
                                        for (int p = 0; 0<=p; p--){
                                            goodOne.fight((enemies.get(p)));
                                            if (enemies.get(p).getHp() <= 0) {
                                                displayMessage("Bravo, " + goodOne.getName() + " a vaincu " + badOne.getName() + " !!!");
                                                enemies.remove(p);
                                            }
                                        }
                                        if (goodOne.getHp() <= 0) {
                                            displayMessage("Malheuresement nous venons de perdre " + goodOne.getName() + " car il a été vaincu par " + badOne.getName() + " !!!");
                                            heros.remove(ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    }
                                    else if(enemies.size()==2) {
                                        displayMessageReturn("Vous venez d'utiliser de la magie qui inflige (");
                                        goodOne.afficherdegat(heros.get(ixHero));
                                        displayMessage(" points de dégat à chaque Monstre");
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        for (int p = 1; 0<=p; p--){
                                            goodOne.fight((enemies.get(p)));
                                            if (enemies.get(p).getHp() <= 0) {
                                                displayMessage("Bravo, " + goodOne.getName() + " a vaincu " + badOne.getName() + " !!!");
                                                enemies.remove(p);
                                            }
                                        }
                                        if (goodOne.getHp() <= 0) {
                                            displayMessage("Malheuresement nous venons de perdre " + goodOne.getName() + " car il a été vaincu par " + badOne.getName() + " !!!");
                                            heros.remove(ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    }
                                    else if(enemies.size()==3) {
                                        displayMessageReturn("Vous venez d'utiliser de la magie qui inflige (");
                                        goodOne.afficherdegat(heros.get(ixHero));
                                        displayMessage(" points de dégat à chaque Monstre");
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        for (int p = 2; 0<=p; p--){
                                            goodOne.fight((enemies.get(p)));
                                            if (enemies.get(p).getHp() <= 0) {
                                                displayMessage("Bravo, " + goodOne.getName() + " a vaincu " + badOne.getName() + " !!!");
                                                enemies.remove(p);
                                            }
                                        }
                                        if (goodOne.getHp() <= 0) {
                                            displayMessage("Malheuresement nous venons de perdre " + goodOne.getName() + " car il a été vaincu par " + badOne.getName() + " !!!");
                                            heros.remove(ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    }
                                    else if(enemies.size()==4) {
                                        displayMessageReturn("Vous venez d'utiliser de la magie qui inflige (");
                                        goodOne.afficherdegat(heros.get(ixHero));
                                        displayMessage(" points de dégat à chaque Monstre");
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        for (int p = 3; 0<=p; p--){
                                            goodOne.fight((enemies.get(p)));
                                            if (enemies.get(p).getHp() <= 0) {
                                                displayMessage("Bravo, " + goodOne.getName() + " a vaincu " + badOne.getName() + " !!!");
                                                enemies.remove(p);
                                            }
                                        }
                                        if (goodOne.getHp() <= 0) {
                                            displayMessage("Malheuresement nous venons de perdre " + goodOne.getName() + " car il a été vaincu par " + badOne.getName() + " !!!");
                                            heros.remove(ixHero);
                                            ixHero--;
                                            k--;
                                        }
                                    }

                                    ixEnemies = 0;
                                    ixHero = ixHero + 1;

                                } else if (choixmageround == 2) {
                                    goodOne.protectionLoose(heros.get(ixHero));
                                    if (goodOne.getHp()<=0){
                                        mortHero(heros, ixHero);
                                        ixHero--;
                                        k--;}
                                    ixHero = ixHero + 1;

                                } else if (choixmageround == 3) {
                                    System.out.println("Que vouler-vous manger ?");
                                    displayMessageReturn("[1]-Pomme");
                                    goodOne.afficherhealFOOD(heros.get(ixHero));
                                    displayMessage("");
                                    int choixFoodmage = scanner.nextInt();
                                    while (choixFoodmage < 1 || choixFoodmage > 2) {
                                        System.out.println("Choix incorrect. Veuillez choisir le nombre 1");
                                        System.out.println("Votre choix: ");
                                        choixFoodmage = scanner.nextInt();
                                    }

                                    if (choixFoodmage == 1) {
                                        goodOne.manger(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                    }
                                    ixHero = ixHero + 1;

                                }
                                else if (choixmageround == 4) {
                                    System.out.println("Que vouler-vous utiliser comme Potion ?");
                                    System.out.print("[1]-Potion de Heal");
                                    goodOne.afficherdegatNBPotionVie(heros.get(ixHero));
                                    System.out.println("");
                                    System.out.print("[2]-Potion de Force (");
                                    goodOne.afficherdegatNBPotionDegat(heros.get(ixHero));
                                    System.out.println(" ");
                                    int choixPotion = scanner.nextInt();
                                    while (choixPotion < 1 || choixPotion > 2) {
                                        System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 2 correspondant à la nourriture voulut");
                                        System.out.println("Votre choix: ");
                                        choixPotion = scanner.nextInt();
                                    }

                                    if (choixPotion == 1) {
                                        goodOne.PotionVie(heros.get(ixHero));
                                        badOne.fight(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                    } else if (choixPotion == 2) {
                                        System.out.println("Qui voulez-vous Attaquer ?");
                                        Statusennemies(enemies);
                                        int choixattaqueennemie = scanner.nextInt();
                                        while (choixattaqueennemie < 1 || choixattaqueennemie > enemies.size()) {
                                            System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et " + enemies.size() + " correspondant à l'action voulut");
                                            System.out.println("Votre choix: ");
                                            choixattaqueennemie = scanner.nextInt();
                                        }
                                        if (choixattaqueennemie == 1) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(0));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 0, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 2) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(1));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 1, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 3) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(2));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 2, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 4) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(3));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 3, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        }
                                    }
                                    ixHero = ixHero + 1;
                                }
                            } else {

                                System.out.println("Que voulez-vous faire avec " + goodOne.getName() + " ?");
                                System.out.println("[1]-Attaquer / [2]-Proteger / [3]-Manger / [4]-Potion");
                                int choixherosround = scanner.nextInt();
                                while (choixherosround < 1 || choixherosround > 4) {
                                    System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 4 correspondant à l'action voulut");
                                    System.out.println("Votre choix: ");
                                    choixherosround = scanner.nextInt();
                                }


                                if (choixherosround == 1) {
                                    System.out.println("Qui voulez-vous Attaquer ?");
                                    Statusennemies(enemies);
                                    int choixattaqueennemie = scanner.nextInt();
                                    while (choixattaqueennemie < 1 || choixattaqueennemie > enemies.size()) {
                                        System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et " + enemies.size() + " correspondant à l'action voulut");
                                        System.out.println("Votre choix: ");
                                        choixattaqueennemie = scanner.nextInt();
                                    }
                                    if (choixattaqueennemie == 1) {
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        Attaque(heros, enemies, 0, ixHero);
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero = ixHero-1;
                                            k--;
                                        }
                                    } else if (choixattaqueennemie == 2) {
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        Attaque(heros, enemies, 1, ixHero);
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero = ixHero-1;
                                            k--;
                                        }
                                    } else if (choixattaqueennemie == 3) {
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        Attaque(heros, enemies, 2, ixHero);
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero = ixHero-1;
                                            k--;
                                        }
                                    } else if (choixattaqueennemie == 4) {
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        Attaque(heros, enemies, 3, ixHero);
                                        if (goodOne.getHp()<=0){
                                            mortHero(heros, ixHero);
                                            ixHero = ixHero-1;
                                            k--;
                                        }
                                    }
                                    ixHero = ixHero + 1;

                                } else if (choixherosround == 2) {
                                    goodOne.protectionLoose(heros.get(ixHero));
                                    if (goodOne.getHp()<=0){
                                        mortHero(heros, ixHero);
                                        ixHero--;
                                        k--;}
                                    ixHero = ixHero + 1;

                                } else if (choixherosround == 3) {
                                    System.out.println("Que vouler-vous manger ?");
                                    displayMessageReturn("[1]-Pomme");
                                    goodOne.afficherhealFOOD(heros.get(ixHero));
                                    displayMessage("");
                                    int choixFood = scanner.nextInt();
                                    while (choixFood != 1) {
                                        System.out.println("Choix incorrect. Veuillez choisir le nombre 1");
                                        System.out.println("Votre choix: ");
                                        choixFood = scanner.nextInt();
                                    }

                                    if (choixFood == 1) {
                                        goodOne.manger(heros.get(ixHero));
                                        displayMessageReturn(goodOne.getName()+" a subit ");
                                        badOne.afficherdegat(enemies.get(ixEnemies));
                                        displayMessage(" de dégat ");
                                        badOne.fight(heros.get(ixHero));
                                    }
                                    ixHero = ixHero + 1;

                                }
                                else if (choixherosround == 4) {
                                    System.out.println("Que vouler-vous utiliser comme Potion ?");
                                    System.out.print("[1]-Potion de Heal");
                                    goodOne.afficherdegatNBPotionVie(heros.get(ixHero));
                                    System.out.println(" ");
                                    System.out.print("[2]-Potion de Force (");
                                    goodOne.afficherdegatNBPotionDegat(heros.get(ixHero));
                                    System.out.println(" ");
                                    int choixPotion = scanner.nextInt();
                                    while (choixPotion < 1 || choixPotion > 2) {
                                        System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et 2 correspondant à la nourriture voulut");
                                        System.out.println("Votre choix: ");
                                        choixPotion = scanner.nextInt();
                                    }

                                    if (choixPotion == 1) {
                                        goodOne.PotionVie(heros.get(ixHero));
                                    } else if (choixPotion == 2) {
                                        System.out.println("Qui voulez-vous Attaquer ?");
                                        Statusennemies(enemies);
                                        int choixattaqueennemie = scanner.nextInt();
                                        while (choixattaqueennemie < 1 || choixattaqueennemie > enemies.size()) {
                                            System.out.println("Choix incorrect. Veuillez choisir un nombre entre 1 et " + enemies.size() + " correspondant à l'action voulut");
                                            System.out.println("Votre choix: ");
                                            choixattaqueennemie = scanner.nextInt();
                                        }
                                        if (choixattaqueennemie == 1) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 0, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 2) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 1, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 3) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 2, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        } else if (choixattaqueennemie == 4) {
                                            displayMessageReturn(goodOne.getName()+" a subit ");
                                            badOne.afficherdegat(enemies.get(ixEnemies));
                                            displayMessage(" de dégat ");
                                            attaquePotion(heros, enemies, 3, ixHero);
                                            if (goodOne.getHp()<=0){
                                                mortHero(heros, ixHero);
                                                ixHero--;
                                                k--;
                                            }
                                        }
                                    }
                                    ixHero = ixHero + 1;
                                }
                            }
                        }
                    }

                }


        }
    }


    private InputParser inputParser;

    private List<Combatant> heros;
    private List<Combatant> enemies;



    // Méthodes d'affichage
    // (STATIQUES pour pouvoir les appeler depuis n'importe où dans le programme)
    //
    // => pourraient éventuellement être déplacées dans le package
    //    "com.isep.utils", en s'inspirant de "InputParser" (méthodes de saisie)

    public static void displayStatus(List<Combatant> h, List<Combatant> e) {
        int ixHeroStatus = 0;
        for (Combatant c: h) {
            System.out.print(c.getName() + "(" + c.getHp() + "\u2764|" + c.getArmures() + "\uD83D\uDEE1|");
            c.afficherdegat(h.get(ixHeroStatus));
            ixHeroStatus ++;
        }
        System.out.println();
        for (Combatant c: e) {
            System.out.print(c.getName() + "(" + c.getHp() + "\uD83D\uDC9B) ");
        }
        System.out.println();
    }


    public static void Statusennemies(List<Combatant> e) {
        for (Combatant c: e) {
            System.out.print(c.getName() + "(" + c.getHp() + "\uD83D\uDC9B) / ");
        }
        System.out.println();
    }

    public static void Statusheros(List<Combatant> e) {
        for (Combatant c: e) {
            System.out.print(c.getName() + "(" + c.getHp() + "\u2764|" + c.getArmures() + "\uD83D\uDEE1| ) ");
        }
        System.out.println();
    }

    public static void mortHeromagie(List<Combatant> heros, int ixHero){
        Combatant goodOne = heros.get(ixHero);
        if (heros.get(ixHero).getHp() <= 0) {
            displayMessage("Malheuresement nous venons de perdre " + goodOne.getName() + " car il a été vaincu !!!");
            heros.remove(ixHero);
        }
    }
    public static void Attaque(List<Combatant> heros, List<Combatant> enemies, int ixEnemies, int ixHero){
        String Verth = "\033[0;92m";
        String RESET = "\u001B[0m";
        Combatant goodOne = heros.get(ixHero);
        Combatant badOne = enemies.get(ixEnemies);
        goodOne.fight(enemies.get(ixEnemies));
        badOne.fight(heros.get(ixHero));
        if (badOne.getHp() <= 0) {
            displayMessage(Verth + "Bravo, " + goodOne.getName() + " a vaincu " + badOne.getName() + " !!!" + RESET);
            enemies.remove(ixEnemies);
        }
    }
    public static void attaquePotion(List<Combatant> heros, List<Combatant> enemies, int ixEnemies, int ixHero) {
        String Verth = "\033[0;92m";
        String RESET = "\u001B[0m";
        Combatant goodOne = heros.get(ixHero);
        Combatant badOne = enemies.get(ixEnemies);
        goodOne.PotionForce(badOne);
        badOne.fight(goodOne);
        if (badOne.getHp() <= 0) {
            displayMessage(Verth + "Bravo, " + goodOne.getName() + " a vaincu " + badOne.getName() + " !!!" + RESET);
            enemies.remove(ixEnemies);
        }
    }

    public static void mortHero(List<Combatant> heros, int ixHero) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File mp3FilemortHero = new File("gta-v-bruitage-de-la-mort-mp3-edxward-kenway-38-youtube-hd-2016.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(mp3FilemortHero);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        Combatant goodOne = heros.get(ixHero);
       if (goodOne.getHp() <= 0) {
            displayMessage("Malheuresement nous venons de perdre " + goodOne.getName() + " car il a été vaincu !!!");
            heros.remove(ixHero);
        }
    }

    public static void mortenemie(List<Combatant> heros, List<Combatant> enemies, int ixEnemies, int ixHero){
        Combatant goodOne = heros.get(ixHero);
        Combatant badOne = enemies.get(ixEnemies);
        if (badOne.getHp() <= 0) {
            displayMessage("Bravo, " + goodOne.getName() + " a vaincu " + badOne.getName() + " !!!");
            enemies.remove(ixEnemies);
        }

    }

    public static void Feux() throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException {
        File mp3Filefeux = new File("Fireworks.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(mp3Filefeux);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();

        String BLACK_BRIGHT = "\033[0;90m";  // BLACK
        String RED_BRIGHT = "\033[0;91m";    // RED
        String GREEN_BRIGHT = "\033[0;92m";  // GREEN
        String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
        String BLUE_BRIGHT = "\033[0;94m";   // BLUE
        String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
        String CYAN_BRIGHT = "\033[0;96m";   // CYAN
        String WHITE_BRIGHT = "\033[0;97m";  // WHITE
        String Verth = "\033[0;92m";
        String RESET = "\u001B[0m";

        retard(PURPLE_BRIGHT + "                                   .''.       " + RESET);
        retard(RED_BRIGHT + "       .''.      .        *''*    :_\\/_:     . " + RESET);
        retard(YELLOW_BRIGHT + "      :_\\/_:   _\\(/_  .:.*_\\/_*   : /\\ :  .'.:.'." + RESET);
        retard(BLUE_BRIGHT + "  .''.: /\\ :   ./)\\   ':'* /\\ * :  '..'.  -=:o:=-" + RESET);
        retard(CYAN_BRIGHT+ " :_\\/_:'.:::.    ' *''*    * '.\\'/.' _\\(/_'.':'.'" + RESET);
        retard(PURPLE_BRIGHT + " : /\\ : :::::     *_\\/_*     -= o =-  /)\\    ' " + RESET);
        retard(CYAN_BRIGHT + "  '..'  ':::'     * /\\ *     .'/.\\'.   '" + RESET);
        retard(WHITE_BRIGHT + "   *      \\         |           \\" + RESET);
        retard(WHITE_BRIGHT + "   *       \\        |           / " + RESET);
        retard(WHITE_BRIGHT + "    *       |       |           \\" + RESET);
        retard(WHITE_BRIGHT + "     *      |       /           /" + RESET);
        retard(Verth + " _____________________________________ " + RESET);

        clip.stop();
    }

    public static void retard(String message) throws InterruptedException {
        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i));
            Thread.sleep(25); // met en pause l'exécution pendant 100 millisecondes
        }
        displayMessage("");
    }


    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void displayMessageReturn(String message) {
        System.out.print(message);
    }
}