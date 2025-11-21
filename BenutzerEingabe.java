import java.util.*;
public class BenutzerEingabe {
    private int kapital           = 2000;
    private int einsatzWert       = 0;
    private final List<Card> deck = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    // Dealer- und Player-Werte
    private int dValue = 0;
    private int pValue = 0;
    public static void main(String[] args) {
 
        new BenutzerEingabe().startGameLoop();
 
    }
    private void startGameLoop() {
        initDeck();
        shuffleDeck();
        new Text();
        This.TextSetzen(Willkommen am Blackjack-Tisch!)
        System.out.println("Willkommen am Blackjack-Tisch!");
        while (kapital > 0) {
            einsatzSetzen();
            playOneHand();
            System.out.println("Aktuelles Kapital: " + kapital);
            if (!askYesNo("Weiter spielen? (j/n): ")) {
                System.out.println("Du verlässt den Tisch mit Kapital: " + kapital);
                break;
            }
            if (deck.size() < 15) {
                initDeck();
                shuffleDeck();
                System.out.println("Neues Deck gemischt.");
            }
        }
        if (kapital <= 0) {
            System.out.println("Dein Kapital ist aufgebraucht ; Spiel beendet.");
        }
        scanner.close(); 
    }
    private void playOneHand() {
        // Dealer zieht zwei Karten
        Card d1 = karteZiehen();
        Card d2 = karteZiehen();
        dValue = d1.getValue() + d2.getValue();
        if (dValue == 21) {
            System.out.println("Dealer hat Blackjack! Karten: " + d1 + ", " + d2);
            return;
        } else {
            System.out.println("Dealer zeigt: " + d1 + ", [versteckt]");
        }
        // Player zieht zwei Karten
        Card p1 = karteZiehen();
        Card p2 = karteZiehen();
        pValue = p1.getValue() + p2.getValue();
        System.out.println("Player zeigt: " + p1 + ", " + p2 + " (Wert: " + pValue + ")");
        if (pValue == 21) {
            System.out.println("Player hat Blackjack!!!");
            kapital += einsatzWert + (int) (einsatzWert * 1.5);
        }
        // Double-Down Option
        if (askYesNo("Double? Einsatz verdoppeln und genau eine Karte ziehen? (j/n): ")) {
            if (kapital >= einsatzWert) {
                kapital -= einsatzWert;
                einsatzWert *= 2;
                Card c = karteZiehen();
                pValue += c.getValue();
                System.out.println("Gezogene Karte (Double): " + c + " ; neuer Wert: " + pValue);
            } else {
                System.out.println("Nicht genügend Kapital zum Verdoppeln.");
                stayOrHit();
            }
            // nach Double immer sofort zur Dealer-Phase
            dealerTurn(d2);
            return;
        }
        // reguläres Hit/Stay
        stayOrHit();
        dealerTurn(d2);
    }
    /** Dealer-Phase: aufdecken, ggf. nachziehen, dann auswerten */
 
    private void dealerTurn(Card hiddenCard) {
        System.out.println("Dealer offenbart: " + hiddenCard + " (Wert: " + dValue + ")");
        // Dealer zieht, bis dValue >= 17
        while (dValue < 17) {
            Card c = karteZiehen();
            dValue += c.getValue();
            System.out.println("Dealer zieht: " + c + " ; Wert: " + dValue);
        }
        // Endwert auswerten
        evaluateOutcome(pValue, einsatzWert);
    }
    private void stayOrHit() {
        while (true) {
            System.out.print("Hit oder Stay? (s/h): "); 
            String antwort = scanner.nextLine().trim().toLowerCase();
            if ("h".equals(antwort)) {
                Card c = karteZiehen();
                pValue += c.getValue();
                System.out.println("Gezogene Karte: " + c + " ; neuer Wert: " + pValue);
                if (pValue > 21) {
                    System.out.println("Bust!");
                    break;
                }
            } else if ("s".equals(antwort)) {
                break;
            } else {
                System.out.println("Bitte 'h' oder 's' eingeben.");
            }
        }
    }
    private void einsatzSetzen() {
        boolean gesetzt = false;
        while (!gesetzt) {
            System.out.println("Dein Kapital: " + kapital);
            System.out.print("Einsatz (ganzzahlig >0): ");
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Ungueltige Eingabe.");
                continue;
            }
            int eing = scanner.nextInt();
            scanner.nextLine();
            if (eing <= 0 || eing > kapital) {
                System.out.println("Ungueltiger Einsatz.");
            } else {
                einsatzWert = eing;
                gesetzt      = true;
                kapital     -= einsatzWert;
            }
        }
    }
    private boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String antwort = scanner.nextLine().trim().toLowerCase();
            if ("j".equals(antwort) || "ja".equals(antwort)) return true;
            if ("n".equals(antwort) || "nein".equals(antwort)) return false;
            System.out.println("Bitte 'j' oder 'n' eingeben.");
        }
    }
    private void evaluateOutcome(int playerValue, int bet) {
        if (playerValue > 21) {
            System.out.println("Player Bust ; Einsatz verloren.");
        } else if (dValue > 21 || playerValue > dValue) {
            System.out.println("Player gewinnt!");
            kapital += bet * 2;
        } else if (playerValue == dValue) {
            System.out.println("Unentschieden ; Einsatz zurück.");
            kapital += bet;
        } else {
            System.out.println("Dealer gewinnt.");
        }
    }
    private void initDeck() {
        deck.clear();
        for (int i = 0; i < 6; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    deck.add(new Card(rank, suit));
                }
            }
        }
    }
    private void shuffleDeck() {
         Collections.shuffle(deck);
    }
    private Card karteZiehen() {
        if (deck.isEmpty()) throw new IllegalStateException("Deck ist leer");
        return deck.remove(0);
    }
    // --- Nested enum für Rang und Wert ---
    private enum Rank {
        TWO("2", 2), THREE("3", 3), FOUR("4", 4),
        FIVE("5", 5), SIX("6", 6), SEVEN("7", 7),
        EIGHT("8", 8), NINE("9", 9), TEN("10", 10),
        J("J", 10), Q("Q", 10), K("K", 10), A("A", 11);
        private final String symbol;
        private final int value;
        Rank(String symbol, int value) { this.symbol = symbol; this.value = value; }
        public int getValue() { return value; }
        @Override public String toString() { return symbol; }
    }
    // --- Nested enum für Farben ---
    private enum Suit { Pik, Herz, Raute, Kreuz }
    // --- Kartendatenklasse ---
    private static class Card {
        private final Rank rank;
        private final Suit suit;
        Card(Rank rank, Suit suit) { this.rank = rank; this.suit = suit; }
        public int getValue() { return rank.getValue(); }
        @Override public String toString() { return rank + " " + suit; }
    }
}