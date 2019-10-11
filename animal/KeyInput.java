package animal;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class contains the keyListener that penguin uses
 * to respond to input and move.
 * 
 * @author Group 16
 */
public class KeyInput extends KeyAdapter {
    private final Penguin p;
    public KeyInput(Penguin p){
        this.p = p;
    }

    public void keyPressed(KeyEvent e){
        p.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        p.keyReleased(e);
    }

    public void keyTyped(KeyEvent e){
        p.keyTyped(e);

    }
}
