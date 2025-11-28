
import java.util.*;

public class Game {
    private int kapital = 2000;
    private int einsatz;
    private Deck deck;
    private Player player;
    private Dealer dealer;
    

    public Game() {
        deck = new Deck();
        new BlackjackFrame();
        player = new Player("Spieler");
        dealer = new Dealer();
    }

    // Getter für Kapital
    public int getKapital() {
        return kapital;
    }

    // Startet eine neue Runde mit Einsatz
    public void startNewRound(int einsatz) {
        this.einsatz = einsatz;
        kapital -= einsatz;

        // Hände leeren
        player.hand.clear();
        dealer.hand.clear();

        // Karten austeilen
        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
    }

    // Spieler zieht eine Karte
    public void playerHit() {
        player.addCard(deck.drawCard());
    }

    // Prüfen, ob Spieler über 21 ist
    public boolean isPlayerBust() {
        return player.isBust();
    }

    // Dealer spielt seine Runde
    public void dealerTurn() {
        dealer.play(deck);
    }

    // Ergebnis der Runde berechnen
    public String getResult() {
        int pVal = player.calculateValue();
        int dVal = dealer.calculateValue();

        if (pVal > 21) {
            return "Bust! Dealer gewinnt.";
        }
        if (dVal > 21 || pVal > dVal) {
            kapital += einsatz * 2;
            return "Du gewinnst!";
        } else if (pVal == dVal) {
            kapital += einsatz;
            return "Unentschieden.";
        } else {
            return "Dealer gewinnt.";
        }
    }

    // Anzeige für GUI
    public String getPlayerHand() {
        return player.hand.toString() + " (Wert: " + player.calculateValue() + ")";
    }

    public String getDealerHand() {
        return dealer.hand.toString() + " (Wert: " + dealer.calculateValue() + ")";
    }

    public String getDealerFirstCard() {
        return dealer.hand.get(0).toString() + ", [versteckt]";
    }
}
