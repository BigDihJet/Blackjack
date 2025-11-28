
import java.util.*;

public class Game {
    private int kapital = 2000;
    private int einsatz;
    private final Scanner scanner = new Scanner(System.in);
    private Deck deck;
    private Player player;
    private Dealer dealer;

    public void start() {
        System.out.println("Willkommen am Blackjack-Tisch!");
        deck = new Deck();
        player = new Player("Spieler");
        dealer = new Dealer();

        while (kapital > 0) {
            einsatzSetzen();
            playOneHand();
            System.out.println("Aktuelles Kapital: " + kapital);

            if (!askYesNo("Weiter spielen? (j/n): ")) {
                System.out.println("Du verlässt den Tisch mit Kapital: " + kapital);
                break;
            }

            if (deck.size() < 15) {
                deck = new Deck();
                System.out.println("Neues Deck gemischt.");
            }
        }
        System.out.println("Spiel beendet.");
    }

    private void einsatzSetzen() {
        boolean gesetzt = false;
        while (!gesetzt) {
            System.out.println("Dein Kapital: " + kapital);
            System.out.print("Einsatz (ganzzahlig >0): ");
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Ungültige Eingabe.");
                continue;
            }
            int eing = scanner.nextInt();
            scanner.nextLine();
            if (eing <= 0 || eing > kapital) {
                System.out.println("Ungültiger Einsatz.");
            } else {
                einsatz = eing;
                kapital -= einsatz;
                gesetzt = true;
            }
        }
    }

    private void playOneHand() {
        player.hand.clear();
        dealer.hand.clear();

        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        dealer.showHand(false);

        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        player.showHand(true);

        if (player.calculateValue() == 21) {
            System.out.println("Blackjack! Du gewinnst.");
            kapital += einsatz + (int)(einsatz * 1.5);
            return;
        }

        while (!player.isBust() && askYesNo("Hit? (j/n): ")) {
            player.addCard(deck.drawCard());
            player.showHand(true);
        }

        if (player.isBust()) {
            System.out.println("Bust! Einsatz verloren.");
            return;
        }

        dealer.showHand(true);
        dealer.play(deck);

        evaluateOutcome();
    }

    private void evaluateOutcome() {
        int pVal = player.calculateValue();
        int dVal = dealer.calculateValue();

        if (dVal > 21 || pVal > dVal) {
            System.out.println("Du gewinnst!");
            kapital += einsatz * 2;
        } else if (pVal == dVal) {
            System.out.println("Unentschieden. Einsatz zurück.");
            kapital += einsatz;
        } else {
            System.out.println("Dealer gewinnt.");
        }
    }

    private boolean askYesNo(String prompt) {
        System.out.print(prompt);
        String antwort = scanner.nextLine().trim().toLowerCase();
        return antwort.equals("j") || antwort.equals("ja");
    }
}
