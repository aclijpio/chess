package game.status;

import game.Player;
import game.status.GameStatus;

public class StartedGame extends GameStatus {


    public StartedGame(GameInitialization gameInitialization) {
        super(gameInitialization.firstPlayer, gameInitialization.secondPlayer);
    }

    @Override
    public Player next() {
        if (shift){
            shift = false;
            return secondPlayer;
        } else{
            shift = true;
            return firstPlayer;
        }
    }
}
