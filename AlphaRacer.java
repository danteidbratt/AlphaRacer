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

    private void start() {
        frame.setupFrame();
        frame.setHighscores(repository.getTop5());
        frame.setKeyListener(ka);
    }

    KeyAdapter ka = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            String temp = ("" + e.getKeyChar()).toUpperCase();
            frame.inputLetter(temp);
            if (frame.currentIndex == 26) {
                frame.timer.stop();
                System.out.println(frame.duration.toMillis());
                System.out.println(repository.registerScore(frame.duration.toMillis()));
                newGame();
            }
        }
    };
    
    private void newGame(){
        frame.setHighscores(repository.getTop5());
        frame.resetGame();
    }

    public static void main(String[] args) {
        AlphaRacer alphaRacer = new AlphaRacer();
        alphaRacer.start();
    }

}
