
import java.util.*;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        initDeck();
        shuffle();
    }

    private void initDeck() {
        cards.clear();
        for (int i = 0; i < 6; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    cards.add(new Card(rank, suit));
                }
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) throw new IllegalStateException("Deck ist leer!");
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }
}
