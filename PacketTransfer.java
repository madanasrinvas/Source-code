
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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class PacketTransfer extends JPanel {     

public static int receivedPkt=0;    
public static int totalPkt=0;    
    
public static JFrame Frame4;
public static int SrcX;public static int SrcY;public static int desX;public static int desY;public static String pktsize;
public static int NodeId;public static double receiving_time;public static double sending_time;public static double Total_Delay =0.0;
public static int Rounds=0;
   
public PacketTransfer(int src_X,int src_Y,int des_X,int des_Y,String pktsize,int po) {    
       this.SrcX=src_X;this.SrcY=src_Y;this.desX=des_X;this.desY=des_Y;this.pktsize=pktsize;this.NodeId=po;
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
                F(0.09,0.1);
        }  
        
        sending_time=System.currentTimeMillis();
      // draw circle
      // draw circle
      for(int i=0;i<Cluster.NoOfCluster;i++){
          g2.setColor(Color.BLACK);  
          g2.drawString("CH"+""+i, (int)Network.points[CH.get(i)][0], (int)Network.points[CH.get(i)][1]);
      }
            
       
      receiving_time=System.currentTimeMillis();
      double delay=receiving_time-sending_time;
      Total_Delay+= delay;   

    float[] dash1 = { 1f, 0f, 1f };
    if(Integer.parseInt(pktsize)<Network.pktSize){
        double x1=Math.abs(SrcX+desX)/2;
        double y1=Math.abs(SrcY+desY)/2;
        BasicStroke bs1 = new BasicStroke(1, 
        BasicStroke.CAP_BUTT, 
        BasicStroke.JOIN_ROUND, 
        1.0f, 
        dash1,
        2f);
        g2.setColor(Color.black);
        g2.setStroke(bs1);
        g2.drawLine(SrcX, SrcY,(int) x1,(int) y1);
        g2.setColor(Color.red);
        g2.setStroke(bs1);
        g2.drawLine((int) x1, (int) y1,desX,desY);
        double reduceE=Network.r.nextDouble()*0.15;                    
        Network.energy.set(NodeId,(Network.energy.get(NodeId)-reduceE));
    }
    else{
        g2.setColor(Color.black);
       BasicStroke bs1 = new BasicStroke(1, 
        BasicStroke.CAP_BUTT, 
        BasicStroke.JOIN_ROUND, 
        1.0f, 
        dash1,
        2f);
     g2.setStroke(bs1);
     g2.drawLine(SrcX, SrcY, desX, desY);
     double reduceE=Network.r.nextDouble()*0.5;                    
     Network.energy.set(NodeId,(Network.energy.get(NodeId)-reduceE));
     
    }
    receivedPkt+=Integer.parseInt(pktsize);
    totalPkt+=Network.pktSize;
    if(Network.energy.get(NodeId)<Network.Energy){
        Rounds++;
    }
       
   }
   public static double get(double x){
       Total_Delay=x;
       return Total_Delay;
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(Network.PREF_W, Network.PREF_H);
   }

   private static void createAndShowGui(int srcX,int srcY,int desX,int desY ,String pakt,int id) {
      
      PacketTransfer pkt = new PacketTransfer(srcX,srcY,desX,desY,pakt,id);
      
      Frame4 = new JFrame("PacketTransfer");
      Frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Frame4.getContentPane().add(pkt);
      Frame4.pack();
      Frame4.setLocationByPlatform(true);
      Frame4.setVisible(true);
   }
   
   public static double getRound(double x){
       Rounds=(int)x+Network.N;
       return Rounds;
   }

   public static void main(int srcX,int srcY,int desX,int desY,String pkt,int id,double energy) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui(srcX,srcY,desX,desY,pkt,id);            
         }
      });
      
   }

   public static void F(double x,double y){
        double a=0;Random r=new Random();
        double generated = x + r.nextDouble() * (y - x);
        PacketTransfer.get(generated);       
    }
      
    
      
 
}