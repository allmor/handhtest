package main.java.org.allmor.gameapp.dice;

import java.util.Random;

public interface DiceRollable {

    default int diceRoll() {
        return new Random().nextInt(0, 6) + 1;
    }
}
