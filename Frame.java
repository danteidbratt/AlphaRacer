package alpharacer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

public class Frame extends JFrame implements ActionListener{

    List<JPanel> spaces;
    JPanel mainPanel;
    JLabel infoPanel;
    JPanel centerPanel;
    JLabel timerLabel;
    List<String> letters;
    List<JLabel> alphaLabels;
    Timer timer;
    int timeUnits;
    int currentIndex;

    public Frame(){
        timeUnits = 0;
        mainPanel = new JPanel();
        infoPanel = new JLabel();
        timerLabel = new JLabel();
        centerPanel = new JPanel();
        timer = new Timer(100, this);
        spaces = new ArrayList<>();
        alphaLabels = new ArrayList<>();
        letters = new ArrayList<>();
        currentIndex = 0;
        
        for (int i = 0; i < 4; i++) {
            spaces.add(new JPanel());
        }
        
        for (int i = 0; i < 26; i++) {
            letters.add(String.valueOf((char)(65+i)));
            alphaLabels.add(new JLabel(letters.get(i)));
        }
    }
    
    public void setupFrame(){
        setLayout(new BorderLayout());
        setVisible(true);
        for (JPanel space : spaces) {
            space.setPreferredSize(new Dimension(30, 30));
            space.setBackground(Color.LIGHT_GRAY);
        }
        mainPanel.setLayout(new GridLayout(3, 1));
        centerPanel.setLayout(new GridLayout(1, 26));
        for (JLabel letter : alphaLabels) {
            centerPanel.add(letter);
        }
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(infoPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(timerLabel);
        add(spaces.get(0), BorderLayout.NORTH);
        add(spaces.get(1), BorderLayout.WEST);
        add(spaces.get(2), BorderLayout.EAST);
        add(spaces.get(3), BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
        setPreferredSize(new Dimension(600, 200));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        centerPanel.requestFocus();
    }
    
    public void setKeyListener(KeyListener kl){
        centerPanel.addKeyListener(kl);
    }
    
    public boolean inputLetter(String input){
        if(input.equals(letters.get(currentIndex))){
            if(currentIndex == 0) {
                timer.start();
            }
            alphaLabels.get(currentIndex).setForeground(Color.GREEN);
            currentIndex++;
            return true;
        }
        alphaLabels.get(currentIndex).setForeground(Color.RED);
        currentIndex = 0;
        for (JLabel al : alphaLabels) {
            al.setForeground(Color.BLACK);
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == timer) {
            String temp = String.valueOf(timeUnits);
            temp = temp.substring(0, temp.length()-1) + "." + temp.substring(temp.length()-1);
            timerLabel.setText(temp);
            timeUnits++;
        }
    }
}