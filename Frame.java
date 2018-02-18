package alpharacer;

import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

public class Frame extends JFrame implements ActionListener {
    
    JPanel logoPanel;
    JLabel flameRight;
    JLabel flameLeft;
    JLabel logo;
    List<JLabel> spaces;
    JPanel superPanel;
    JPanel eastPanel;
    JPanel eastInfoPanel;
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
    JButton changeUserButton;
    JLabel usernameLabel;
    int currentIndex;
    
    Color backgroundColor;

    public Frame() {
        logoPanel = new JPanel();
        flameRight = new JLabel();
        flameLeft = new JLabel();
        usernameLabel = new JLabel();
        eastInfoPanel = new JPanel();
        backgroundColor = new Color(200, 20, 20);
        exitButton = new JButton("Exit");
        statsButton = new JButton("Stats");
        changeUserButton = new JButton("Change");
        logo = new JLabel("AlphaRacer");
        infoPanel = new JPanel();
        superPanel = new JPanel();
        eastPanel = new JPanel();
        southPanel = new JPanel();
        highscoreTextLabel = new JLabel("- Highscores -");
        highscoreLabels = new ArrayList<>();
        mainPanel = new JPanel();
        timerLabel = new JLabel("0.00");
        centerPanel = new JPanel();
        highscorePanel = new JPanel();
        timer = new Timer(10, this);
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
        eastPanel.setPreferredSize(new Dimension(210, 0));
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

        logoPanel.setLayout(new BorderLayout());
        logoPanel.setBackground(backgroundColor);
        logoPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        logo.setBackground(backgroundColor);
        logo.setOpaque(true);
        logo.setFont(new Font("SansSerif", 3, 40));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        flameRight.setIcon(new ImageIcon(new ImageIcon("pics/FlamesRight.jpg").getImage().getScaledInstance(160, 50, Image.SCALE_SMOOTH)));
        flameLeft.setIcon(new ImageIcon(new ImageIcon("pics/FlamesLeft.jpg").getImage().getScaledInstance(160, 50, Image.SCALE_SMOOTH)));
        flameRight.setBackground(backgroundColor);
        flameLeft.setBackground(backgroundColor);
        flameRight.setHorizontalAlignment(SwingConstants.LEFT);
        flameLeft.setHorizontalAlignment(SwingConstants.RIGHT);
        flameRight.setOpaque(true);
        flameLeft.setOpaque(true);
        logoPanel.setPreferredSize(new Dimension(30, 120));
        logoPanel.add(flameLeft, BorderLayout.WEST);
        logoPanel.add(logo, BorderLayout.CENTER);
        logoPanel.add(flameRight, BorderLayout.EAST);

        southPanel.setLayout(new BorderLayout());
        infoPanel.setLayout(new GridLayout(1, 5, 50, 50));
        infoPanel.setPreferredSize(new Dimension(0, 60));
        infoPanel.setBackground(backgroundColor);
        
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setBackground(Color.WHITE);
        timerLabel.setOpaque(true);
        timerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        timerLabel.setFont(new Font("Courier", 1, 20));
        infoPanel.add(spaces.get(7));
        infoPanel.add(exitButton);
        infoPanel.add(timerLabel);
        infoPanel.add(statsButton);
        infoPanel.add(spaces.get(8));
        
        southPanel.add(spaces.get(9), BorderLayout.NORTH);
        southPanel.add(infoPanel, BorderLayout.CENTER);
        southPanel.add(spaces.get(10), BorderLayout.SOUTH);
        
        superPanel.add(logoPanel, BorderLayout.NORTH);
        superPanel.add(spaces.get(1), BorderLayout.WEST);
        superPanel.add(southPanel, BorderLayout.SOUTH);
        superPanel.add(mainPanel, BorderLayout.CENTER);
        
        eastInfoPanel.setBackground(backgroundColor);
        eastInfoPanel.setLayout(new GridLayout(1, 2, 0, 10));
        eastInfoPanel.setBorder(BorderFactory.createEmptyBorder(8, 25, 5, 25));
        eastInfoPanel.setPreferredSize(new Dimension(0, 40));
        usernameLabel.setFont(new Font("SansSerif", 1, 12));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        eastInfoPanel.add(changeUserButton);
        eastInfoPanel.add(usernameLabel);
        
        eastPanel.add(eastInfoPanel, BorderLayout.NORTH);
        eastPanel.add(spaces.get(11), BorderLayout.WEST);
        eastPanel.add(spaces.get(5), BorderLayout.EAST);
        spaces.get(6).setPreferredSize(new Dimension(0, 20));
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
        changeUserButton.addActionListener(al);
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
        timerLabel.setText("0.00");
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
            duration = duration.plusMillis(10);
            timerLabel.setText(String.format("%.2f", (duration.toMillis()*1.0)/1000));
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
    
    public void showStats(List<String> stats){
        JOptionPane.showMessageDialog(null, stats.get(0) + stats.get(1) + stats.get(2));
        centerPanel.requestFocus();
    }
    
    public String changeUser(){
        String input = "";
        while(true){
            input = JOptionPane.showInputDialog("Enter initials");
            if (input == null) {
                System.exit(0);
            }
            else if(input.length() == 3){
                break;
            }
            else {
                JOptionPane.showMessageDialog(null, "Initials must be 3 letters");
            }
        }
        input = input.toUpperCase();
        usernameLabel.setText("Player: " + input);
        revalidate();
        centerPanel.requestFocus();
        return input;
    }
    
    public void showVerdict(String verdict){
        String temp = duration.toString();
        temp = temp.substring(2, temp.length() - 1);
        JOptionPane.showMessageDialog(null, temp + verdict);
    }
}