
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlackjackGUI extends JFrame {
    private Game game;
    private JLabel kapitalLabel, dealerLabel, playerLabel, statusLabel;
    private JTextField einsatzField;
    private JButton startButton, hitButton, stayButton;

    public BlackjackGUI() {
        setTitle("Blackjack");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        game = new Game();

        // Oben: Kapital und Einsatz
        JPanel topPanel = new JPanel();
        kapitalLabel = new JLabel("Kapital: " + game.getKapital());
        einsatzField = new JTextField(5);
        startButton = new JButton("Einsatz setzen & Start");
        topPanel.add(kapitalLabel);
        topPanel.add(new JLabel("Einsatz:"));
        topPanel.add(einsatzField);
        topPanel.add(startButton);
        add(topPanel, BorderLayout.NORTH);

        // Mitte: Kartenanzeige
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        dealerLabel = new JLabel("Dealer: ");
        playerLabel = new JLabel("Spieler: ");
        centerPanel.add(dealerLabel);
        centerPanel.add(playerLabel);
        add(centerPanel, BorderLayout.CENTER);

        // Unten: Buttons und Status
        JPanel bottomPanel = new JPanel();
        hitButton = new JButton("Hit");
        stayButton = new JButton("Stay");
        statusLabel = new JLabel("Willkommen!");
        bottomPanel.add(hitButton);
        bottomPanel.add(stayButton);
        bottomPanel.add(statusLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        // Button-Aktionen
        startButton.addActionListener(e -> startGame());
        hitButton.addActionListener(e -> hit());
        stayButton.addActionListener(e -> stay());

        hitButton.setEnabled(false);
        stayButton.setEnabled(false);
    }

    private void startGame() {
        try {
            int einsatz = Integer.parseInt(einsatzField.getText());
            if (einsatz <= 0 || einsatz > game.getKapital()) {
                statusLabel.setText("Ungültiger Einsatz!");
                return;
            }
            game.startNewRound(einsatz);
            kapitalLabel.setText("Kapital: " + game.getKapital());
            dealerLabel.setText("Dealer: " + game.getDealerFirstCard());
            playerLabel.setText("Spieler: " + game.getPlayerHand());
            statusLabel.setText("Hit oder Stay?");
            hitButton.setEnabled(true);
            stayButton.setEnabled(true);
        } catch (NumberFormatException ex) {
            statusLabel.setText("Bitte Zahl eingeben!");
        }
    }

    private void hit() {
        game.playerHit();
        playerLabel.setText("Spieler: " + game.getPlayerHand());
        if (game.isPlayerBust()) {
            statusLabel.setText("Bust! Dealer gewinnt.");
            endRound();
        }
    }

    private void stay() {
        game.dealerTurn();
        dealerLabel.setText("Dealer: " + game.getDealerHand());
        statusLabel.setText(game.getResult());
        kapitalLabel.setText("Kapital: " + game.getKapital());
        endRound();
    }

    private void endRound() {
        hitButton.setEnabled(false);
