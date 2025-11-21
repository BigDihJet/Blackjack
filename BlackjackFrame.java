import javax.swing.*;
public class BlackjackFrame extends JFrame {
   public BlackjackFrame() {
       setTitle("Blackjack Tisch");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       add(new TablePanel());
       pack();
       setLocationRelativeTo(null);
       setVisible(true);
   }
}

