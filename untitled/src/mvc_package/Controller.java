package mvc_package;
import game.Difficulty;
import game.Enemy;
import game.Player;
import game.TimerListener;

import java.util.ArrayList;
import java.util.Timer;

public class Controller {
    private final int delay = 50;
    private Timer timer;
    private Player player;
    private ArrayList<Enemy> enemies;
    private GameView view;


    public Controller(TimerListener timerListener, Player player, GameView gameView, ArrayList<Enemy> enemies) {

        this.timer = new Timer();
        this.player = player;
        this.enemies = enemies;


    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public GameView getView() {
        return view;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public StartMenuController() {
        view = new GameView();
        registerListeners();
        view.showView();
    }

    private void registerListeners() {
        view.basicButton.addActionListener(e -> startGame(Difficulty.BASIC));
        view.normalButton.addActionListener(e -> startGame(Difficulty.NORMAL));
        view.difficultButton.addActionListener(e -> startGame(Difficulty.DIFFICULT));
    }

    private void startGame(Difficulty difficulty) {
        new MazeController(difficulty);
        view.dispose();
    }

}
