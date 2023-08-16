package game.status;

import aclij.pio.Color;
import game.Player;

import java.util.Random;

public class GameInitialization extends GameStatus{


    public GameInitialization(Player firstPlayer, Player secondPlayer) {
        super(firstPlayer, secondPlayer);
        this.next();
    }

    @Override
    public Player next() {
        if(new Random().nextBoolean()){
            firstPlayer.color = Color.WHITE;
            secondPlayer.color = Color.BLACK;
            shift = true;
            return firstPlayer;
        } else {
            firstPlayer.color = Color.BLACK;
            secondPlayer.color = Color.WHITE;
            return secondPlayer;
        }
    }
}
