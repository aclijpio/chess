package game;

import game.status.GameStatus;
import game.status.StartedGame;

import java.util.Set;

public class Games {

    Set<StartedGame> games;
    int GAMES_COUNT;
    public Games() {
    }
    public void start(StartedGame game){
        GAMES_COUNT++;
        games.add(game);
    }
    public void finish(GameStatus game){
        GAMES_COUNT--;
        games.remove(game);
    }
    public int getGAMES_COUNT() {
        return GAMES_COUNT;
    }
}
