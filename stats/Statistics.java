package stats;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The statistics class deals with keeping the stats in check.
 * @author Group 16
 *
 */
public class Statistics {
    private int score;
    private int life;
    private int time;
    private Timer timer;

    /**
     * A constructor that initializes the variables to its default values
     */
    public Statistics() {
        score = 0;
        life = 2;
        time = 30;
    }
    
    /**
     * The start method creates the timer and starts the game loop
     */
    public void start() {
        int delay = 0;
        int period = 1000;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
        	public void run() {
        		checkTime();
        		}
        	}
        , delay, period);
    }
    
    /** 
     * @return the time as it goes down by 1 every second
     */
    public int checkTime() {
        if (time == 0) {
            System.out.println("Time's up");
            timer.cancel();
        }
        System.out.println("Time left: " + time);
        return --time;
    }
    
    /**
     * @return time
     */
    public int getTime() {
        return time;
    }
    
    /**
     * Adds the time as you collect the time up collectible
     */
    public void addTime() {
        time += 10;
    }
    
    /**
     * @param newTime
     */
    public void setTime(int newTime) {
        time = newTime;
    }
    
    /**
     * @return score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * 
     * @return life
     */
    public int getLife() {
        return life;
    }
    
    /**
     * Adds the score as you collect the fishes
     */
    public void addScore() {
        score++;
    }
    
    /**
     * Adds the life as you collect the life up collectible
     */
    public void addLife() {
        life++;
    }
    
    /**
     * Remove one life as you get eaten by the polar bear
     */
    public void removeLife() {
        life--;
    }
    
    /**
     * Stops the clock whenever the game ends
     */
    public void stopClock(){
        timer.cancel();
    }
    /**
     * @param score
     */
    public void setScore(int score){
        this.score = score;
    }
    
    /** 
     * @param lives
     */
    public void setLives(int lives){
        this.life = lives;
    }
}