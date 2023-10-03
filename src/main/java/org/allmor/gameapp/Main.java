package main.java.org.allmor.gameapp;

import main.java.org.allmor.gameapp.characters.enemy.Enemy;
import main.java.org.allmor.gameapp.characters.hero.Hero;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        entry();
    }

    private static void entry() {
        Hero hero = new Hero();
        Enemy enemy = new Enemy();

        action(hero, enemy);
    }

    private static void action(Hero hero, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);

        while (hero.isAlive() && enemy.isAlive()) {
            while (true) {
                System.out.println("У героя " + hero.getHealth() + "/" + hero.getMaxHealth() + " очков здоровья...");
                System.out.println("Эликсиров восстановления здоровья: " + hero.getHealthPotions() + "/4");
                System.out.println(">>>Выберите действие:\n1 - атаковать\n2 - выпить зелье");
                int input = scanner.nextInt();
                if (input == 1) {
                    hero.attack(enemy);
                    break;
                } else if (input == 2) {
                    hero.restoreHP();
                } else {
                    System.out.println("Неправильный ввод");
                }
            }

            if (enemy.isAlive())
                enemy.attack(hero);
            else {
                System.out.println("Враг повержен!");
                break;
            }

            if (!hero.isAlive()) {
                System.out.println("Герой повержен!");
                break;
            }
        }
    }
}
