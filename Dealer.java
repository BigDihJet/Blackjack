
public class Dealer extends Player {
    public Dealer() {
        super("Dealer");
    }

    public void play(Deck deck) {
        while (calculateValue() < 17) {
            addCard(deck.drawCard());
            System.out.println("Dealer zieht: " + hand.get(hand.size() - 1));
        }
    }
}
