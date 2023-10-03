package main.java.org.allmor.gameapp.characters.hero;

import main.java.org.allmor.gameapp.characters.enemy.Enemy;
import main.java.org.allmor.gameapp.dice.DiceRollable;

import java.util.Random;

public class Hero implements DiceRollable {

    private String name;
    private int attack;
    private int defense;
    private int health;
    private int damageMin;
    private int damageMax;
    private final int maxHealth;
    private int healthPotions;
    private Random rnd = new Random();

    public Hero() {
        this.name = "Геральт";
        this.attack = rnd.nextInt(0, 30) + 1;
        this.defense = rnd.nextInt(0, 30) + 1;
        this.health = rnd.nextInt(50, 100);
        this.damageMin = 3;
        this.damageMax = 9;
        this.healthPotions = 4;
        this.maxHealth = health;

        getInfo();
    }

    public void attack(Enemy enemy) {
        int modifier = Math.abs(attack - enemy.getDefense()) + 1;

        for (int i = 0; i < modifier; i++) {
            if (!enemy.isAlive())
                break;

            if (diceRoll() >= 5) {
                int d = new Random().nextInt(damageMin, damageMax + 1);
                System.out.println(name + " наносит врагу " + d + " единиц урона...");
                enemy.getDamage(d);
                System.out.println("У врага осталось " + enemy.getHealth() + " единиц здоровья...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(name + " промахивается...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void restoreHP() {
        if (health == maxHealth) {
            System.out.println("Вы здоровы");
        } else if (!isAlive()) {
            System.out.println("Персонаж мертв");
        } else if (healthPotions > 0) {
            int restHP = (int) (maxHealth * 0.3);
            System.out.println(name + " восстанавливает " + restHP + " единиц здоровья...");
            health = Math.min(maxHealth, health + restHP);
            healthPotions--;
        } else {
            System.out.println("Эликсиров больше нет");
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
        System.out.println("Герой: " + name);
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealthPotions() {
        return healthPotions;
    }
}
