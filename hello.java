package Main;



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
public class hello extends JPanel {  
   
    public static JFrame helloFrame;
    public static int NoOfCluster;
    
    public static double dx;
    public static double dy;
    
   public hello(double x,double y) {      
       this.dx=x;this.dy=x;
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
             
      g2.drawLine(Network.BORDER_GAP, getHeight() - Network.BORDER_GAP, Network.BORDER_GAP, Network.BORDER_GAP);
      g2.drawLine((Network.PREF_W-Network.BORDER_GAP), (getHeight() - Network.BORDER_GAP), (Network.PREF_W-Network.BORDER_GAP), Network.BORDER_GAP);      
      g2.drawLine(Network.BORDER_GAP, (getHeight() - Network.BORDER_GAP), (getWidth() - Network.BORDER_GAP), (getHeight() - Network.BORDER_GAP));     
      g2.drawLine(Network.BORDER_GAP, Network.BORDER_GAP , (Network.PREF_W-Network.BORDER_GAP), Network.BORDER_GAP); 
         
      
      Stroke oldStroke = g2.getStroke();
      g2.setColor(Network.GRAPH_COLOR);
      g2.setStroke(Network.GRAPH_STROKE);      

      g2.setStroke(oldStroke);      
      g2.setColor(Color.BLACK);

      for(int i=0;i<Network.N;i++){
          int ovalW = Network.GRAPH_POINT_WIDTH;
          int ovalH = Network.GRAPH_POINT_WIDTH;
          g2.fillOval((int)Network.points[i][0], (int)Network.points[i][1], ovalW, ovalH); 
      }
      
      g2.setColor(Color.BLACK);
      g2.fillOval(SinkX, SinkY, SinkSize, SinkSize);
      g2.drawString("Sink", SinkX, SinkY);
      
      
      float[] dash1 = { 2f, 0f, 2f };
      g2.setColor(Color.RED);
    
        g2.drawLine((int)dx, (int)dy, SinkX, SinkY); 
        g2.drawString("hello", SinkX, SinkX); 
        BasicStroke bs1 = new BasicStroke(1, 
        BasicStroke.CAP_BUTT, 
        BasicStroke.JOIN_ROUND, 
        1.0f, 
        dash1,
        2f);
        g2.setStroke(bs1);
   
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(Network.PREF_W, Network.PREF_H);
   }

   private static void createAndShowGui(double x,double y) {       
      hello hlo = new hello(x,y);

      helloFrame  = new JFrame("Hello");
      helloFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      helloFrame.getContentPane().add(hlo);
      helloFrame.pack();
      helloFrame.setLocationByPlatform(true);
      helloFrame.setVisible(true);
   }

   public static void main(double x,double y) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui(x,y);            
         }
      });
      
   }

    
      
 
}