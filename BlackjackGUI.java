
import javax.swing.*;
import java.awt.*;

public class BlackjackGUI extends JFrame {
    private Game game;
    private JLabel kapitalLabel, dealerLabel, playerLabel, statusLabel;
    private JTextField einsatzField;
    private JButton startButton, hitButton, stayButton;

    public BlackjackGUI() {
        setTitle("Blackjack Tisch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        game = new Game();

        // LayeredPane für Hintergrund + Overlay
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        // Tisch-Hintergrund
        TablePanel tablePanel = new TablePanel();
        tablePanel.setBounds(0, 0, 800, 600);
        layeredPane.add(tablePanel, Integer.valueOf(0));

        // Overlay für Kartenanzeige
        JPanel overlayPanel = new JPanel(new GridLayout(2, 1));
        overlayPanel.setOpaque(false);
        overlayPanel.setBounds(0, 200, 800, 100);
        dealerLabel = new JLabel("Dealer: ");
        playerLabel = new JLabel("Spieler: ");
        dealerLabel.setForeground(Color.WHITE);
        playerLabel.setForeground(Color.WHITE);
        dealerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        overlayPanel.add(dealerLabel);
        overlayPanel.add(playerLabel);
        layeredPane.add(overlayPanel, Integer.valueOf(1));

        // Steuerung unten
        JPanel controlPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        controlPanel.setBounds(0, 450, 800, 100);
        controlPanel.setOpaque(true);
        controlPanel.setBackground(new Color(0, 80, 0));

        kapitalLabel = new JLabel("Kapital: " + game.getKapital());
        einsatzField = new JTextField(5);
        startButton = new JButton("Einsatz setzen & Start");
        hitButton = new JButton("Hit");
        stayButton = new JButton("Stay");
        statusLabel = new JLabel("Willkommen!");

        controlPanel.add(kapitalLabel);
        controlPanel.add(new JLabel("Einsatz:"));
        controlPanel.add(einsatzField);
        controlPanel.add(startButton);
        controlPanel.add(hitButton);
        controlPanel.add(stayButton);

        hitButton.setEnabled(false);
        stayButton.setEnabled(false);

        startButton.addActionListener(e -> startGame());
        hitButton.addActionListener(e -> hit());
        stayButton.addActionListener(e -> stay());

        layeredPane.add(controlPanel, Integer.valueOf(2));

        add(layeredPane);
        setVisible(true);
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
        stayButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BlackjackGUI());
    }
}
