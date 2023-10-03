package main.java.org.allmor.gameapp.characters.enemy;

import main.java.org.allmor.gameapp.characters.hero.Hero;
import main.java.org.allmor.gameapp.dice.DiceRollable;

import java.util.Random;

public class Enemy implements DiceRollable {

    private String name;
    private int attack;
    private int defense;
    private int health;
    private int damageMin;
    private int damageMax;

    public Enemy() {
        this.name = "Стрыга";
        initStats();
        getInfo();
    }

    private void initStats() {
        Random rnd = new Random();
        this.attack = rnd.nextInt(0, 40) + 1;
        this.defense = rnd.nextInt(0, 60) + 1;
        this.health = rnd.nextInt(100, 250);
        this.damageMin = 1;
        this.damageMax = 25;
    }

    public void attack(Hero hero) {
        int modifier = Math.abs(attack - hero.getDefense()) + 1;

        for (int i = 0; i < modifier; i++) {
            if (!hero.isAlive())
                break;

            if (diceRoll() >= 5) {
                int d = new Random().nextInt(damageMin, damageMax + 1);
                System.out.println(name + " наносит герою " + d + " единиц урона...");
                hero.getDamage(d);
                System.out.println("У героя осталось " + hero.getHealth() + " единиц здоровья...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println(name + " промахивается...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getDamage(int damage) {
        this.health -= Math.min(damage, health);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getDefense() {
        return defense;
    }

    private void getInfo() {
        System.out.println("***************************************");
        System.out.println("Враг: " + name);
        System.out.println("Атака: " + attack);
        System.out.println("Защита: " + defense);
        System.out.println("Здоровье: " + health);
        System.out.println("Минимальный урон: " + damageMin);
        System.out.println("Максимальный урон: " + damageMax);
        System.out.println("***************************************");
    }

    public int getHealth() {
        return health;
    }
}
