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

    JLabel logo;
    List<JLabel> spaces;
    JPanel superPanel;
    JPanel eastPanel;
    JPanel southPanel;
    JLabel highscoreTextLabel;
    JPanel mainPanel;
    JPanel centerPanel;
    JPanel infoPanel;
    JPanel highscorePanel;
    List<JLabel> highscoreLabels;
    JLabel timerLabel;
    List<String> letters;
    List<JLabel> alphaLabels;
    Timer timer;
    Duration duration;
    JPanel[] highscoreRow = new JPanel[5];
    JLabel[][] highscores = new JLabel[5][3];
    JButton exitButton;
    JButton statsButton;
    int currentIndex;
    
    Color backgroundColor;

    public Frame() {
        backgroundColor = new Color(200, 20, 20);
        exitButton = new JButton("Exit");
        statsButton = new JButton("Stats");
        logo = new JLabel("- AlphaRacer -");
        infoPanel = new JPanel();
        superPanel = new JPanel();
        eastPanel = new JPanel();
        southPanel = new JPanel();
        highscoreTextLabel = new JLabel("- Highscores -");
        highscoreLabels = new ArrayList<>();
        mainPanel = new JPanel();
        timerLabel = new JLabel("0.0");
        centerPanel = new JPanel();
        highscorePanel = new JPanel();
        timer = new Timer(100, this);
        spaces = new ArrayList<>();
        alphaLabels = new ArrayList<>();
        letters = new ArrayList<>();
        currentIndex = 0;
        duration = Duration.ofSeconds(0);

        for (JLabel[] h : highscores) {
            for (int j = 0; j < h.length; j++) {
                h[j] = new JLabel();
            }
        }

        for (int i = 0; i < highscoreRow.length; i++) {
            highscoreRow[i] = new JPanel();
            highscoreRow[i].setLayout(new GridLayout(1, 3));
            highscoreRow[i].setOpaque(true);
        }

        for (int i = 0; i < 16; i++) {
            spaces.add(new JLabel());
        }

        for (int i = 0; i < 26; i++) {
            letters.add(String.valueOf((char) (65 + i)));
            alphaLabels.add(new JLabel(letters.get(i)));
        }
    }

    public void setupFrame() {
        setTitle("AlphaRacer");
        setLayout(new BorderLayout());
        setVisible(true);
        for (JLabel space : spaces) {
            space.setPreferredSize(new Dimension(30, 30));
            space.setBackground(backgroundColor);
            space.setOpaque(true);
        }
        superPanel.setLayout(new BorderLayout());
        eastPanel.setLayout(new BorderLayout());
        eastPanel.setPreferredSize(new Dimension(180, 0));
        eastPanel.setBackground(Color.BLACK);
        highscoreTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        highscoreTextLabel.setOpaque(true);
        eastPanel.add(highscorePanel, BorderLayout.CENTER);
        mainPanel.setLayout(new GridLayout(1, 1));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        centerPanel.setLayout(new GridLayout(1, 26));
        for (JLabel letter : alphaLabels) {
            letter.setFont(new Font("SansSerif", 1, 14));
            centerPanel.add(letter);
        }
        mainPanel.add(centerPanel);
        highscorePanel.setLayout(new GridLayout(6, 1, 0, 2));
        highscorePanel.setOpaque(false);
        highscorePanel.add(highscoreTextLabel, 0);
        highscorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        logo.setText("------ AlphaRacer ------");
        logo.setBackground(backgroundColor);
        logo.setOpaque(true);
        logo.setFont(new Font("SansSerif", 3, 40));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setPreferredSize(new Dimension(30, 100));

        southPanel.setLayout(new BorderLayout());
        infoPanel.setLayout(new GridLayout(1, 5, 50, 50));
        infoPanel.setPreferredSize(new Dimension(0, 60));
        infoPanel.setBackground(backgroundColor);
        
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setBackground(Color.WHITE);
        timerLabel.setOpaque(true);
        timerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        timerLabel.setFont(new Font("SansSerif", 1, 20));
        infoPanel.add(spaces.get(7));
        infoPanel.add(exitButton);
        infoPanel.add(timerLabel);
        infoPanel.add(statsButton);
        infoPanel.add(spaces.get(8));
        
        southPanel.add(spaces.get(9), BorderLayout.NORTH);
        southPanel.add(infoPanel, BorderLayout.CENTER);
        southPanel.add(spaces.get(10), BorderLayout.SOUTH);
        
        superPanel.add(logo, BorderLayout.NORTH);
        superPanel.add(spaces.get(1), BorderLayout.WEST);
        superPanel.add(spaces.get(2), BorderLayout.EAST);
        superPanel.add(southPanel, BorderLayout.SOUTH);
        superPanel.add(mainPanel, BorderLayout.CENTER);
        eastPanel.add(spaces.get(4), BorderLayout.NORTH);
        eastPanel.add(spaces.get(5), BorderLayout.EAST);
        eastPanel.add(spaces.get(6), BorderLayout.SOUTH);
        
                
        add(superPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        setPreferredSize(new Dimension(840, 300));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        centerPanel.requestFocus();
    }

    public void setKeyListener(KeyListener kl) {
        centerPanel.addKeyListener(kl);
    }
    
    public void setActionListeners(ActionListener al) {
        exitButton.addActionListener(al);
        statsButton.addActionListener(al);
    }

    public void inputLetter(String input) {
        if (input.equals(letters.get(currentIndex))) {
            correctInput();
        } else {
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
        timerLabel.setText("0.0");
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

    public void setHighscores(List<Highscore> top5) {
        for (int i = 0; i < top5.size(); i++) {
            highscores[i][0].setText(" " + (i + 1) + ":");
            highscores[i][0].setFont(new Font("SansSerif", 1, 15));
            highscores[i][1].setText(String.valueOf(top5.get(i).getScore()));
            highscores[i][2].setText(top5.get(i).getInitials());
            highscoreRow[i].add(highscores[i][0]);
            highscoreRow[i].add(highscores[i][1]);
            highscoreRow[i].add(highscores[i][2]);
            highscorePanel.add(highscoreRow[i], i + 1);
        }
        revalidate();
    }

}
