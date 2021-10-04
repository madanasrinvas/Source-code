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
import static Main.Network.energy;
import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

@SuppressWarnings("serial")
public class CHselection extends JPanel {  
   
public static ArrayList<Integer>  CH=new ArrayList<Integer>();  
public static JFrame Frame3;

   public CHselection() {      
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
      
      g2.setColor(Color.BLACK);
      g2.fillOval((int)SinkX, (int)SinkY, SinkSize, SinkSize);
      g2.drawString("Sink", (int)SinkX, (int)SinkY);
      
     
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
               
      int xx=0;
        
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
        
        //WDO        
         
        double w=WDWWO.WDWWO(); 
        
        //Distance calculation
    ArrayList<ArrayList<Double>> distance=new ArrayList<ArrayList<Double>>();
    for(int i=0;i<Cluster.NoOfCluster;i++){
        ArrayList<Double> d=new ArrayList<Double>();        
        for(int j=0;j<Kmedoid.Kmedoid.get(i).size();j++){
            double x1=Network.points[Kmedoid.Kmedoid.get(i).get(j)][0];
            double y1=Network.points[Kmedoid.Kmedoid.get(i).get(j)][1];            
                double x2=Network.SinkX;
                double y2=Network.SinkY;
                double f2=Math.sqrt(Math.pow((x2-x1),2)+(Math.pow((y2-y1),2))); //=Euclidean distance between node jn and x                
            d.add(f2);  
        }
      distance.add(d); 
    }
    
    /// Energy calculation
    ArrayList<ArrayList<Double>> Energy=new ArrayList<ArrayList<Double>>();
       for(int i=0;i<Cluster.NoOfCluster;i++){
           ArrayList<Double> e=new ArrayList<Double>();
           for(int j=0;j<Kmedoid.Kmedoid.get(i).size();j++){
               double E=energy.get(Kmedoid.Kmedoid.get(i).get(j));
               E=(1/E);
               e.add(E);
           }
           Energy.add(e);
       }   
       
        //  CH selection
    
        for(int i=0;i<Cluster.NoOfCluster;i++){
            
            ArrayList<Double> fitness=new ArrayList<Double>();
            for(int j=0;j<Kmedoid.Kmedoid.get(i).size();j++){
                double f1=Energy.get(i).get(j);
                double f2=distance.get(i).get(j);
                double F_x=w*f1+(1-w)*f2;
                fitness.add(F_x);
            }
            
            double maxF=Collections.max(fitness);
            int max_index=fitness.indexOf(maxF);           
            CH.add(Kmedoid.Kmedoid.get(i).get(max_index));
                
        }       
      // draw circle
      for(int i=0;i<Cluster.NoOfCluster;i++){
          g2.setColor(Color.BLACK);  
          g2.drawString("CH"+""+i, (int)Network.points[CH.get(i)][0], (int)Network.points[CH.get(i)][1]);
      }  
        
      
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(Network.PREF_W, Network.PREF_H);
   }

   private static void createAndShowGui() {
      
      CHselection ch = new CHselection();

      Frame3 = new JFrame("Cluster Head");
      Frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Frame3.getContentPane().add(ch);
      Frame3.pack();
      Frame3.setLocationByPlatform(true);
      Frame3.setVisible(true);
   }

   public static void main() {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();            
         }
      });
      
   }

    private double totEnergy(ArrayList<Integer> get) {
        double totalE=0.0;
        for(int i=0;i<get.size();i++){
            totalE+=energy.get(get.get(i));
        }
        return totalE;
    }
      
    
      
 
}