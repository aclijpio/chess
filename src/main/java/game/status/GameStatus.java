package game.status;

import game.Player;

public abstract class GameStatus {


    boolean shift;
    Player firstPlayer;
    Player secondPlayer;

    public GameStatus(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }
    public String getStatus(){
        return this.getClass().getSimpleName();
    }
    public StartedGame start(GameInitialization gameInitialization){
        return new StartedGame(gameInitialization);
    }

    public abstract Player next();

}
