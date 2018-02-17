package alpharacer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AlphaRacer {

    Frame frame;
    
    
    public AlphaRacer() {
        frame = new Frame();
    }
    
    private void start(){
        frame.setupFrame();
        frame.setKeyListener(ka);
    }
    
    KeyAdapter ka = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            String temp = ("" + e.getKeyChar()).toUpperCase();
            if(frame.inputLetter(temp))
                System.out.println("OK");
            else
                System.out.println("FALSE");
        }
    };
    
    public static void main(String[] args) {
        AlphaRacer alphaRacer = new AlphaRacer();
        alphaRacer.start();
    }

}