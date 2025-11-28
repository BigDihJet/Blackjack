import javax.swing.*;
import java.awt.*;
public class TablePanel extends JPanel {
   public TablePanel() {
       setPreferredSize(new Dimension(800, 500));
       setBackground(new Color(0, 100, 0)); // Dunkelgrün wie Spieltisch
   }
   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       Graphics2D g2 = (Graphics2D) g;
       g2.setColor(Color.WHITE);
       g2.setStroke(new BasicStroke(3));
       // Tischrand
       g2.drawOval(550, 280, 800, 500);
       // Blackjack Text
       g2.setFont(new Font("Arial", Font.BOLD, 36));
       g2.drawString("BLACKJACK", 840, 367);
       // ´úntertitel
       g2.setFont(new Font("Arial", Font.PLAIN, 18));
       g2.drawString("Dealer muss bis 16 ziehen und ab 17 bleiben.", 250, 150);
       // Kartenplätze für Spieler
       for (int i = 0; i < 5; i++) {
           g2.drawRect(150 + i * 100, 350, 70, 100);
       }
       // Dealer Kartenplätze
       g2.drawRect(360, 200, 70, 100);
       g2.drawRect(440, 200, 70, 100);
    }}