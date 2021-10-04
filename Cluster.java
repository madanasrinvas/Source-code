package Main;



import static Main.Network.B1X;
import static Main.Network.B1Y;
import static Main.Network.B2X;
import static Main.Network.B2Y;
import static Main.Network.B3X;
import static Main.Network.B3Y;
import static Main.Network.B4X;
import static Main.Network.B4Y;
import static Main.Network.BatterySize;
import static Main.Network.SinkSize;
import static Main.Network.SinkX;
import static Main.Network.SinkY;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import javax.swing.*;

@SuppressWarnings("serial")
public class Cluster extends JPanel {  
   
    public static JFrame Frame2;
    public static int NoOfCluster;
    
   public Cluster() {      
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setStroke(new BasicStroke(Network.linewidth));       
      g2.drawLine(Network.BORDER_GAP, getHeight() - Network.BORDER_GAP, Network.BORDER_GAP, Network.BORDER_GAP);
      g2.drawLine((Network.PREF_W-Network.BORDER_GAP), (getHeight() - Network.BORDER_GAP), (Network.PREF_W-Network.BORDER_GAP), Network.BORDER_GAP);      
      g2.drawLine(Network.BORDER_GAP, (getHeight() - Network.BORDER_GAP), (getWidth() - Network.BORDER_GAP), (getHeight() - Network.BORDER_GAP));     
      g2.drawLine(Network.BORDER_GAP, Network.BORDER_GAP , (Network.PREF_W-Network.BORDER_GAP), Network.BORDER_GAP); 
         
      
      Stroke oldStroke = g2.getStroke();
      g2.setColor(Network.GRAPH_COLOR);
      g2.setStroke(Network.GRAPH_STROKE);      

      g2.setStroke(oldStroke);      
      //g2.setColor(Color.BLACK);

        NoOfCluster=4;
        Kmedoid.main(NoOfCluster, Network.points);
               
        g2.setColor(Color.BLACK);
        g2.fillOval((int)SinkX, (int)SinkY, SinkSize, SinkSize);
        g2.drawString("Sink", (int)SinkX, (int)SinkY);int xx=0;        
        
       
      g2.setColor(Color.ORANGE);
      g2.fillOval(B1X, B1Y, BatterySize, BatterySize);
      g2.drawString("B1", B1X, B1Y);     
      
      g2.setColor(Color.ORANGE);
      g2.fillOval(B2X, B2Y, BatterySize, BatterySize);
      g2.drawString("B2", B2X, B2Y);     
      
      g2.setColor(Color.ORANGE);
      g2.fillOval(B3X, B3Y, BatterySize, BatterySize);
      g2.drawString("B3", B3X, B3Y);  
      
      g2.setColor(Color.ORANGE);
      g2.fillOval(B4X, B4Y, BatterySize, BatterySize);
      g2.drawString("B4", B4X, B4Y);    
        
        
        for (int i = 0; i < Network.N; i++) {
                if(Kmedoid.Kmedoid.get(0).contains(i)){
                    int x = (int)Network.points[i][0];
                    int y = (int)Network.points[i][1];
                    int ovalW = Network.GRAPH_POINT_WIDTH;
                    int ovalH = Network.GRAPH_POINT_WIDTH;
                    g2.setColor(Color.BLUE);
                    g2.fillOval(x, y, ovalW, ovalH);  xx++;
                }
                else if(Kmedoid.Kmedoid.get(1).contains(i)){
                    int x = (int)Network.points[i][0];
                    int y = (int)Network.points[i][1];
                    int ovalW = Network.GRAPH_POINT_WIDTH;
                    int ovalH = Network.GRAPH_POINT_WIDTH;
                    g2.setColor(Color.GREEN);
                    g2.fillOval(x, y, ovalW, ovalH);  xx++;
                }
                else if(Kmedoid.Kmedoid.get(2).contains(i)){
                    int x = (int)Network.points[i][0];
                    int y = (int)Network.points[i][1];
                    int ovalW = Network.GRAPH_POINT_WIDTH;
                    int ovalH = Network.GRAPH_POINT_WIDTH;
                    g2.setColor(Color.RED);
                    g2.fillOval(x, y, ovalW, ovalH); xx++;
                }
                
                else if(Kmedoid.Kmedoid.get(3).contains(i)){
                    int x = (int)Network.points[i][0];
                    int y = (int)Network.points[i][1];
                    int ovalW = Network.GRAPH_POINT_WIDTH;
                    int ovalH = Network.GRAPH_POINT_WIDTH;
                    g2.setColor(Color.MAGENTA);
                    g2.fillOval(x, y, ovalW, ovalH); xx++;
                }
                
                else if(Kmedoid.Kmedoid.get(4).contains(i)){
                    int x = (int)Network.points[i][0];
                    int y = (int)Network.points[i][1];
                    int ovalW = Network.GRAPH_POINT_WIDTH;
                    int ovalH = Network.GRAPH_POINT_WIDTH;
                    g2.setColor(Color.ORANGE);
                    g2.fillOval(x, y, ovalW, ovalH); xx++;
                }
                
                else if(Kmedoid.Kmedoid.get(5).contains(i)){
                    int x = (int)Network.points[i][0];
                    int y = (int)Network.points[i][1];
                    int ovalW = Network.GRAPH_POINT_WIDTH;
                    int ovalH = Network.GRAPH_POINT_WIDTH;
                    g2.setColor(Color.DARK_GRAY);
                    g2.fillOval(x, y, ovalW, ovalH); xx++;
                }     
      }       
        
   
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(Network.PREF_W, Network.PREF_H);
   }

   private static void createAndShowGui() {
      
      Cluster ch = new Cluster();

      Frame2  = new JFrame("Cluster");
      Frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Frame2.getContentPane().add(ch);
      Frame2.pack();
      Frame2.setLocationByPlatform(true);
      Frame2.setVisible(true);
   }

   public static void main() {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();            
         }
      });
      
   }

    
      
 
}