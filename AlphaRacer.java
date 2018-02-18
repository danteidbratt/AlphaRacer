package alpharacer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AlphaRacer implements ActionListener{

    Frame frame;
    Repository repository;
    String playerInitials;
    long score;
    

    public AlphaRacer() {
        frame = new Frame();
        repository = new Repository();
    }

    private void start() {
        frame.setupFrame();
        frame.setHighscores(repository.getTop5());
        frame.setKeyListener(ka);
        frame.setActionListeners(this);
        playerInitials = frame.changeUser();
    }
    
    KeyAdapter ka = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            String temp = ("" + e.getKeyChar()).toUpperCase();
            frame.inputLetter(temp);
            if (frame.currentIndex == 26) {
                frame.timer.stop();
                score = frame.duration.toMillis();
                frame.showVerdict(repository.registerScore(score, playerInitials));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frame.exitButton) {
            System.exit(0);
        }
        else if (e.getSource() == frame.statsButton) {
            frame.showStats(repository.getStats());
        }
        else if (e.getSource() == frame.changeUserButton) {
            playerInitials = frame.changeUser();
        }
    }
    
}