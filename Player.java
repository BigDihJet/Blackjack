
import java.util.*;

public class Player {
    protected String name;
    protected List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int calculateValue() {
        int value = 0;
        int aceCount = 0;
        for (Card card : hand) {
            value += card.getValue();
            if (card.toString().startsWith("A")) aceCount++;
        }
        while (value > 21 && aceCount > 0) {
            value -= 10; // Ass als 1 statt 11
            aceCount--;
        }
        return value;
    }

    public boolean isBust() {
        return calculateValue() > 21;
    }

    public void showHand(boolean revealAll) {
        if (revealAll) {
            System.out.println(name + " Karten: " + hand + " (Wert: " + calculateValue() + ")");
        } else {
            System.out.println(name + " zeigt: " + hand.get(0) + ", [versteckt]");
        }
    }
}
