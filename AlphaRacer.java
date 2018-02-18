package alpharacer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.*;

public class AlphaRacer implements ActionListener{

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
        frame.setActionListeners(this);
    }
    
    KeyAdapter ka = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            String temp = ("" + e.getKeyChar()).toUpperCase();
            frame.inputLetter(temp);
            if (frame.currentIndex == 26) {
                frame.timer.stop();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frame.exitButton) {
            System.exit(0);
        }
        else if (e.getSource() == frame.statsButton) {
            List<String> stats = repository.getStats();
            JOptionPane.showMessageDialog(null, stats.get(0) + stats.get(1) + stats.get(2));
            frame.centerPanel.requestFocus();
        }
    }

}
