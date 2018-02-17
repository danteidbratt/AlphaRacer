package alpharacer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.time.Duration;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

public class Frame extends JFrame implements ActionListener {

    double fifth;
    List<JPanel> spaces;
    JPanel superPanel;
    JPanel eastPanel;
    JLabel highscoreTextLabel;
    JPanel mainPanel;
    JLabel infoPanel;
    JPanel centerPanel;
    JPanel highscorePanel;
    List<JLabel> highscoreLabels;
    JLabel timerLabel;
    List<String> letters;
    List<JLabel> alphaLabels;
    Timer timer;
    Duration duration;
    List<Highscore> top5;
    int currentIndex;

    public Frame() {
        superPanel = new JPanel();
        eastPanel = new JPanel();
        highscoreTextLabel = new JLabel("- Highscores -");
        highscoreLabels = new ArrayList<>();
        mainPanel = new JPanel();
        infoPanel = new JLabel();
        timerLabel = new JLabel();
        centerPanel = new JPanel();
        highscorePanel = new JPanel();
        timer = new Timer(100, this);
        spaces = new ArrayList<>();
        alphaLabels = new ArrayList<>();
        letters = new ArrayList<>();
        currentIndex = 0;
        duration = Duration.ofSeconds(0);

        for (int i = 0; i < 4; i++) {
            spaces.add(new JPanel());
        }

        for (int i = 0; i < 26; i++) {
            letters.add(String.valueOf((char) (65 + i)));
            alphaLabels.add(new JLabel(letters.get(i)));
        }
    }
    
    public void setupFrame() {
        setTitle("- AlphaRacer -");
        setLayout(new BorderLayout());
        setVisible(true);
        for (JPanel space : spaces) {
            space.setPreferredSize(new Dimension(30, 30));
            space.setBackground(Color.LIGHT_GRAY);
        }
        superPanel.setLayout(new BorderLayout());
        eastPanel.setLayout(new BorderLayout());
        eastPanel.setPreferredSize(new Dimension(140, 0));
        highscoreTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        eastPanel.add(highscoreTextLabel, BorderLayout.NORTH);
        eastPanel.add(highscorePanel, BorderLayout.CENTER);
        mainPanel.setLayout(new GridLayout(3, 1));
        centerPanel.setLayout(new GridLayout(1, 26));
        for (JLabel letter : alphaLabels) {
            letter.setFont(new Font("SansSerif", 1, 14));
            centerPanel.add(letter);
        }
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(infoPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(timerLabel);
        highscorePanel.setLayout(new GridLayout(5, 3, 5, 5));
        superPanel.add(spaces.get(0), BorderLayout.NORTH);
        superPanel.add(spaces.get(1), BorderLayout.WEST);
        superPanel.add(spaces.get(2), BorderLayout.EAST);
        superPanel.add(spaces.get(3), BorderLayout.SOUTH);
        superPanel.add(mainPanel, BorderLayout.CENTER);
        add(superPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        setPreferredSize(new Dimension(700, 200));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        centerPanel.requestFocus();
    }

    public void setKeyListener(KeyListener kl) {
        centerPanel.addKeyListener(kl);
    }

    public void inputLetter(String input) {
        if (input.equals(letters.get(currentIndex))) {
            correctInput();
        }
        else {
            resetGame();
        }
    }

    private void correctInput() {
        if (currentIndex == 0) {
            timer.start();
        }
        alphaLabels.get(currentIndex).setForeground(Color.GREEN);
        currentIndex++;
    }

    public void resetGame() {
        timer.stop();
        timerLabel.setText("");
        duration = Duration.ZERO;
        currentIndex = 0;
        for (JLabel al : alphaLabels) {
            al.setForeground(Color.BLACK);
        }
        centerPanel.requestFocus();
        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            duration = duration.plusMillis(100);
            String temp = duration.toString();
            temp = temp.substring(2, temp.length() - 1);
            timerLabel.setText(temp);
        }
    }
    
    public void setHighscores(List<Highscore> top5){
        this.top5 = top5;
        highscorePanel.removeAll();
        for (int i = 0; i < 5; i++) {
            highscorePanel.add(new JLabel("  " + (i+1) + ":"));
            highscorePanel.add(new JLabel(String.valueOf(top5.get(i).getScore())));
            highscorePanel.add(new JLabel(top5.get(i).getInitials()));
        }
        fifth = top5.get(4).getScore();
        revalidate();
    }
    
}