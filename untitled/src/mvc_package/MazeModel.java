package mvc_package;

import game.Difficulty;

public class MazeModel {

    private Difficulty difficulty;
    private boolean exitFound = false;

    public MazeModel(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean isExitFound() {
        return exitFound;
    }

    public void setExitFound(boolean exitFound) {
        this.exitFound = exitFound;
    }
}
