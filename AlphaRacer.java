package alpharacer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AlphaRacer {

    Frame frame;
    Repository repository;
    
    public AlphaRacer() {
        frame = new Frame();
        repository = new Repository();
    }
    
    private void start(){
        frame.setupFrame();
        frame.setHighscores(repository.getTop5());
        frame.setKeyListener(ka);
    }
    
    KeyAdapter ka = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            String temp = ("" + e.getKeyChar()).toUpperCase();
            frame.inputLetter(temp);
        }
    };
    
    public static void main(String[] args) {
        AlphaRacer alphaRacer = new AlphaRacer();
        alphaRacer.start();
    }

}