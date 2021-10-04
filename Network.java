package Main;



import Graph.Run;
import static Main.CHselection.Frame3;
import static Main.Cluster.Frame2;
import static Main.Cluster.NoOfCluster;
import static Main.PacketTransfer.Frame4;
import static Main.SendRequest.Frame5;
import static Main.Charging.Frame6;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

@SuppressWarnings("serial")
public class Network extends JPanel {
   
   public static final int PREF_W = 600;  //600;
   public static final int PREF_H = 450;  //450;
   
   public static int N=100;
   public static double Energy=50.0; //J
   public static int pktSize=512;
   
   public static int SinkX;
   public static int SinkY;
   public static final int SinkSize = 30;
   public static final int BatterySize = 20;
   
   
   public static int B1X;
   public static int B1Y;
   
   public static int B2X;
   public static int B2Y;
   
   public static int B3X;
   public static int B3Y;
   
   public static int B4X;
   public static int B4Y;  
   
   public static final int BORDER_GAP = 20;
   public static final int BORDER_GAP1 = 30;
   public static final Color GRAPH_COLOR = Color.green;
   public static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
   public static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   public static final int GRAPH_POINT_WIDTH = 8;  
   public static int linewidth = 5;

   public static int row=0;
   public static int column=0;
   
   public static double points[][];
   public static ArrayList<Double> energy=new ArrayList<Double>();  
   public static Random r=new Random();
   
   public static int B_XY[][]=new int[4][2];
   
   public static JFrame frame1;  
    
   public Network() {      
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      row=N;
      column=2;
      points=new double[row][column];  
      
      for (int i = 0; i < N; i++) {           
           energy.add(50.0); 
      }
       
      // create x and y axes 
      
      g2.setStroke(new BasicStroke(linewidth));
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
      g2.drawLine((PREF_W-BORDER_GAP), (getHeight() - BORDER_GAP), (PREF_W-BORDER_GAP), BORDER_GAP);      
      g2.drawLine(BORDER_GAP, (getHeight() - BORDER_GAP), (getWidth() - BORDER_GAP), (getHeight() - BORDER_GAP));     
      g2.drawLine(BORDER_GAP, BORDER_GAP , (PREF_W-BORDER_GAP), BORDER_GAP);          
      
      Stroke oldStroke = g2.getStroke();
      g2.setColor(GRAPH_COLOR);
      g2.setStroke(GRAPH_STROKE);      

      g2.setStroke(oldStroke);      
      g2.setColor(Color.BLACK);
      for (int i = 0; i < N; i++) {
         int x=BORDER_GAP1 + r.nextInt ((PREF_W-BORDER_GAP1) - BORDER_GAP1);
         int y=BORDER_GAP1 + r.nextInt ((PREF_H-BORDER_GAP1) - BORDER_GAP1);    
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         points[i][0]=(double)x;
         points[i][1]=(double)y;  
         g2.fillOval(x, y, ovalW, ovalH);
      }
      
      double sx=(PREF_W-BORDER_GAP)/2;
      double sy=(PREF_H-BORDER_GAP)/2;
      SinkX=(int)sx;
      SinkY=(int)sy;
      
      B1X=BORDER_GAP1 + r.nextInt ((PREF_W-BORDER_GAP1) - BORDER_GAP1);
      B1Y=BORDER_GAP1 + r.nextInt ((PREF_H-BORDER_GAP1) - BORDER_GAP1);
      B_XY[0][0]=B1X;B_XY[0][1]=B1Y;
      
      B2X=BORDER_GAP1 + r.nextInt ((PREF_W-BORDER_GAP1) - BORDER_GAP1);
      B2Y=BORDER_GAP1 + r.nextInt ((PREF_H-BORDER_GAP1) - BORDER_GAP1);
      B_XY[1][0]=B2X;B_XY[1][1]=B2Y;
      
      B3X=BORDER_GAP1 + r.nextInt ((PREF_W-BORDER_GAP1) - BORDER_GAP1);
      B3Y=BORDER_GAP1 + r.nextInt ((PREF_H-BORDER_GAP1) - BORDER_GAP1);
      B_XY[2][0]=B3X;B_XY[2][1]=B3Y;
      
      B4X=BORDER_GAP1 + r.nextInt ((PREF_W-BORDER_GAP1) - BORDER_GAP1);
      B4Y=BORDER_GAP1 + r.nextInt ((PREF_H-BORDER_GAP1) - BORDER_GAP1);
      B_XY[3][0]=B4X;B_XY[3][1]=B4Y;      
      
      
      g2.setColor(Color.BLACK);
      g2.fillOval(SinkX, SinkY, SinkSize, SinkSize);
      g2.drawString("BS", SinkX, SinkY);
     // g2.drawPolygon(new int[] {SinkX,SinkX-10, SinkX-20}, new int[] {SinkY,SinkY-10, SinkY-20}, 3);
      
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

    //send hellow message  (send hello to reduce energy)      
      ReduceEnergy(); 
        
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   private static void createAndShowGui() {
      
      Network mainPanel = new Network();
      frame1 = new JFrame("Network");
      frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame1.getContentPane().add(mainPanel);
      frame1.pack();
      frame1.setLocationByPlatform(true);
      frame1.setVisible(true);
      
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();   
         }
      });
            try {
                TimeUnit.SECONDS.sleep(3);
                frame1.dispose();
                Cluster.main();
             } catch (InterruptedException ex) {                 
                 Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            try {
                TimeUnit.SECONDS.sleep(3);
                Frame2.dispose();                
                CHselection.main();
             } catch (InterruptedException ex) {                 
                 Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            try {
                TimeUnit.SECONDS.sleep(3);
                Frame3.dispose();                
                packettransfer();
             } catch (InterruptedException ex) {                 
                 Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
             }
            try {
                TimeUnit.SECONDS.sleep(1);
                Frame4.dispose(); 
                for(int i=0;i<N;i++){
                    int nodeId=i;
                    if(SendRequest.BoostupNodes.contains(nodeId)){
                        int CH_Id=CHselection.CH.get(SearchNodeInWhichCluster(nodeId));
                        SendRequest.main(nodeId,CH_Id);
                        TimeUnit.SECONDS.sleep(1);
                        Frame5.dispose(); 
                    }                    
                }
                
             } catch (InterruptedException ex) {                 
                 Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            try {
                TimeUnit.SECONDS.sleep(1);
                Frame5.dispose(); 
                GSFO.Schedule();
                for(int i=0;i<SendRequest.BoostupNodes.size();i++){
                  GSFO.moveloc(i);                   
                  Charging.main();
                  TimeUnit.SECONDS.sleep(2);
                  Frame6.dispose();
                }
                P.performance();
                Run.mainI(); 
                
             } catch (InterruptedException ex) {                 
                 Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
             }
             
   } 
   
   private static ArrayList<String> Loss() {
       
        ArrayList<Integer> n=new ArrayList<Integer>();
        ArrayList<Integer> P=new ArrayList<Integer>();
        ArrayList<String> pl=new ArrayList<String>();
        for(int i=0;i<10;i++){
            n.add(N*(i+1));P.add(3*(i+1));
        }
        
        int index=n.indexOf(N);
        for(int i=0;i<P.get(index);i++){pl.add("n");}
        for(int i=P.get(index);i<N;i++){pl.add("y");}         
        
        ArrayList<String> pKL=new ArrayList<String>();
        Collections.shuffle(pl);
        for(int i=0;i<pl.size();i++){
            if(pl.get(i).equals("n")){
                int ls=400+r.nextInt(410-400);
                pKL.add(ls+"");
            }
            else{
                pKL.add(pktSize+"");
            }
        }
        return pKL;
    }
   
   
   private static void packettransfer() {
       
        try {
            ArrayList<String> pkt=new ArrayList<String>();
            pkt=Loss();            
            
            for (int i = 0; i < Network.N; i++) {                
                int nodeId=i;                
                if(Kmedoid.Kmedoid.get(0).contains(i)){  
                    
                    int CHNode=CHselection.CH.get(0);                    
                    int x = (int)Network.points[i][0];
                    int y = (int)Network.points[i][1];
                    
                    int CHx = (int)Network.points[CHNode][0];
                    int CHy = (int)Network.points[CHNode][1];
                    
                    PacketTransfer.main(x,y,CHx,CHy,pkt.get(i),nodeId,energy.get(nodeId));
                    
                }
                else if(Kmedoid.Kmedoid.get(1).contains(i)){
                    
                    int CHNode=CHselection.CH.get(1);
                    int x = (int)Network.points[i][0];
                    int y = (int)Network.points[i][1];
                    
                    int CHx = (int)Network.points[CHNode][0];
                    int CHy = (int)Network.points[CHNode][1];  
                    
                    PacketTransfer.main(x,y,CHx,CHy,pkt.get(i),nodeId,energy.get(nodeId));
                    
                }
                else if(Kmedoid.Kmedoid.get(2).contains(i)){
                    
                    int CHNode=CHselection.CH.get(2);
                    int x = (int)Network.points[i][0];
                    int y = (int)Network.points[i][1];
                    
                    int CHx = (int)Network.points[CHNode][0];
                    int CHy = (int)Network.points[CHNode][1];                    
                    
                    PacketTransfer.main(x,y,CHx,CHy,pkt.get(i),nodeId,energy.get(nodeId));
                    
                }
                
                else if(Kmedoid.Kmedoid.get(3).contains(i)){
                    
                    int CHNode=CHselection.CH.get(3);
                    int x = (int)Network.points[i][0];
                    int y = (int)Network.points[i][1];
                    
                    int CHx = (int)Network.points[CHNode][0];
                    int CHy = (int)Network.points[CHNode][1];                    
                                        
                    PacketTransfer.main(x,y,CHx,CHy,pkt.get(i),nodeId,energy.get(nodeId));
                    
                }
                
                else if(Kmedoid.Kmedoid.get(4).contains(i)){
                    
                    int CHNode=CHselection.CH.get(4);
                    int x = (int)Network.points[i][0];
                    int y = (int)Network.points[i][1];
                    
                    int CHx = (int)Network.points[CHNode][0];
                    int CHy = (int)Network.points[CHNode][1];                    
                                        
                    PacketTransfer.main(x,y,CHx,CHy,pkt.get(i),nodeId,energy.get(nodeId));                    
                    
                }
                
                else if(Kmedoid.Kmedoid.get(5).contains(i)){
                    
                    int CHNode=CHselection.CH.get(5);
                    int x = (int)Network.points[i][0];
                    int y = (int)Network.points[i][1];
                    
                    int CHx = (int)Network.points[CHNode][0];
                    int CHy = (int)Network.points[CHNode][1];                    
                                        
                    PacketTransfer.main(x,y,CHx,CHy,pkt.get(i),nodeId,energy.get(nodeId));
                    
                }  
                TimeUnit.SECONDS.sleep(1);
                Frame4.dispose();                
                    
             }   
             SendRequest.CheckEnergy();

             } catch (InterruptedException ex) {                 
                Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
             }
        
    }
   
   
   private void ReduceEnergy() {
       
    ArrayList<Double> e=new ArrayList<Double>();    
        
        for(int i=0;i<N;i++){
                double x1=Network.points[i][0];
                double y1=Network.points[i][1];                
                double x=Network.SinkX;
                double y=Network.SinkY;                
                double D_d1=Math.sqrt(Math.pow((x-x1),2)+(Math.pow((y-y1),2))); //=Euclidean distance between node sink
                e.add(D_d1);
        }
        
        ArrayList<Double> e_cpy=new ArrayList<Double>(e);     
        Collections.sort(e);double Initial=0.0001;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(e_cpy.get(i)==e.get(j)){
                    Initial+=r.nextDouble()*0.5;
                    energy.set(j, (energy.get(i)-Initial));
                }
            }
        }        
    }
   
    private static int SearchNodeInWhichCluster(int nodeId) {
        int ch=0;
        for(int i=0;i<NoOfCluster;i++){
            if(Kmedoid.Kmedoid.get(i).contains(nodeId)){
                ch=i;
            }
        }
        return ch;
    }
 
}