package Main;



import static Main.CHselection.CH;
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
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

@SuppressWarnings("serial")
public class SendRequest extends JPanel {  
   
public static int ChID;public static  int NodeID;  
public static JFrame Frame5;

   public SendRequest(int nodeId,int ChId ) {    
       this.ChID=ChId;this.NodeID=nodeId;
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
        
     
      for(int i=0;i<Cluster.NoOfCluster;i++){
          g2.setColor(Color.BLACK);  
          g2.drawString("CH"+""+i, (int)Network.points[CH.get(i)][0], (int)Network.points[CH.get(i)][1]);
      }  
      
      float[] dash1 = { 1f, 0f, 1f };
      
        int x1=(int)Network.points[NodeID][0];
        int y1=(int)Network.points[NodeID][1];
        int x2=(int)Network.points[ChID][0];
        int y2=(int)Network.points[ChID][1];
        
        
        
        BasicStroke bs1 = new BasicStroke(1, 
        BasicStroke.CAP_BUTT, 
        BasicStroke.JOIN_ROUND, 
        1.0f, 
        dash1,
        2f);
        g2.setColor(Color.black);
        g2.setStroke(bs1);
        g2.drawLine(x1, y1,x2,y2);
        g2.drawString("request", x2, y2);        
      
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(Network.PREF_W, Network.PREF_H);
   }

   private static void createAndShowGui(int nodeId,int ChId) {
      
      SendRequest ch = new SendRequest(nodeId,ChId);

      Frame5 = new JFrame("SendRequest");
      Frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Frame5.getContentPane().add(ch);
      Frame5.pack();
      Frame5.setLocationByPlatform(true);
      Frame5.setVisible(true);
   }

   public static void main(int nodeId,int ChId) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui(nodeId,ChId);            
         }
      });
      
   }
   
public static ArrayList<Integer> BoostupNodes;
    
    public static void CheckEnergy(){
        BoostupNodes=new ArrayList<Integer>();
        double threshold=Findthreshold(Network.energy);        
        for(int i=0;i<Network.N;i++){
            if(threshold>Network.energy.get(i)){
                BoostupNodes.add(i);
            }
        }
    }

    private static double Findthreshold(ArrayList<Double> energy) {
       ArrayList<Double> energy1=new ArrayList<Double>(energy);
       Collections.sort(energy1);
       double x=energy1.get((energy1.size()*1)/4);
       return x;
       
    }
    
    public static double F(double r){
       double x=0;double l=r*3.1;
       for(int i=0;i<(int) l;i++){
           x++;                   
       } 
       double a=PacketTransfer.getRound(x);
       return x;
   }
      
 
}