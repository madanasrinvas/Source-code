/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import static Main.SendRequest.BoostupNodes;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ss10155
 */
public class GSFO {
    
   public static ArrayList<Integer> ScheduleNode=new ArrayList<Integer>();
   public static ArrayList<Integer> ScheduleB=new ArrayList<Integer>();
   
   public static int moveX;
   public static int moveY;
   public static int B;
   
     public static void main () {        
            menu(false,Network.N);
    }

    private static void menu (boolean flag,int particles) {
        Swarm swarm;
        Particle.FunctionType function;
        int  epochs;
        double inertia, cognitive, social;

        function = getFunction();
        particles = 100;
        epochs = 100;
        swarm = new Swarm(function, particles, epochs);
        swarm.run();
    }

    private static Particle.FunctionType getFunction () {
        Particle.FunctionType function = null;
        function = Particle.FunctionType.Ackleys;//Ackley function        
        return function;
    }

   
    
   public static void Schedule(){
       
       ArrayList<Double> E_x=new ArrayList<Double>();
       for(int i=0;i<BoostupNodes.size();i++){
           double e=Network.energy.get(BoostupNodes.get(i));
           E_x.add(e);
       }
       
       ArrayList<Double> E_x_coy=new ArrayList<Double>(E_x);
       Collections.sort(E_x);
       ArrayList<Integer> index=new ArrayList<Integer>();
       for(int i=0;i<E_x.size();i++){
           for(int j=0;j<E_x_coy.size();j++){
               if(E_x.get(i)==E_x_coy.get(j)){
                   int nodeId=BoostupNodes.get(j);                   
                   scheduleNode(nodeId);
               }
           }
       }
       
       ArrayList<Integer> Bst=new ArrayList<Integer>();
       for(int i=0;i<BoostupNodes.size();i++){
           int x=f(BoostupNodes.get(i),i);           
           ScheduleB.add(x);
       }    
       
   }

    private static void scheduleNode(int nodeId) {
       ScheduleNode.add(nodeId);
    }
    
    public static int  f(int BoostupNodeId, int x) {
        
        ArrayList<Integer> path=new ArrayList<Integer>();
        
        double x1=Network.points[BoostupNodeId][0];
        double y1=Network.points[BoostupNodeId][1];       
       
            ArrayList<Integer> position=new ArrayList<Integer>();
            ArrayList<Double> distance=new ArrayList<Double>();
            for(int j=0;j<Network.B_XY.length;j++){                
                    double x2=Network.B_XY[j][0];
                    double y2=Network.B_XY[j][1];
                    double ds=Math.sqrt(Math.pow((x2-x1),2)+(Math.pow((y2-y1),2))); 
                    distance.add(ds);
                    position.add(j);
                
            }
            
            double md=Collections.min(distance); 
            int index=distance.indexOf(md);
            int indexPosition=position.get(index);  
            
        return indexPosition;        
    }

    public static void moveloc(int No) {
        
        
        
        double x1=Network.points[ScheduleNode.get(No)][0];
        double y1=Network.points[ScheduleNode.get(No)][1];
        
         ArrayList<Integer> position=new ArrayList<Integer>();
            ArrayList<Double> distance=new ArrayList<Double>();
            for(int j=0;j<Network.B_XY.length;j++){                
                    double x2=Network.points[j][0];
                    double y2=Network.points[j][1];
                    double ds=Math.sqrt(Math.pow((x2-x1),2)+(Math.pow((y2-y1),2))); 
                    if(ds!=0.0){
                        distance.add(ds);
                        position.add(j);
                    }
            }
            ArrayList<Double> distanceC=new ArrayList<Double>(distance);
            Collections.sort(distance);int in=0;
            for(int i=0;i<distance.size();i++){                
                for(int j=0;j<distance.size();j++){
                    if(distance.get(i)==distanceC.get(j)){
                        in=j;break;
                    }
                }
            }
            
            in=position.get(in);
            double x=Network.points[in][0];
            double y=Network.points[in][1];
            
            
       switch (in) {
           case 0:
               setB1(x,y);
               break;
           case 1:
               setB2(x,y);
               break;
           case 2:
               setB3(x,y);
               break;
           case 3:
               setB4(x,y);
               break;
           default:
               break;
       }
            
    }
    
  public static void setB1(double x,double y){
      B=1;
      moveX=Network.B1X;
      moveY=Network.B1Y;
      Network.B1X=(int)x;
      Network.B1Y=(int)y;
  }
  
  public static void setB2(double x,double y){
      B=2;
      moveX=Network.B2X;
      moveY=Network.B2Y;
      Network.B2X=(int)x;
      Network.B2Y=(int)y;
  }
  public static void setB3(double x,double y){
      B=3;
      moveX=Network.B3X;
      moveY=Network.B3Y;
      Network.B3X=(int)x;
      Network.B3Y=(int)y;
  }
  public static void setB4(double x,double y){
      B=4;
      moveX=Network.B4X;
      moveY=Network.B4Y;
      Network.B4X=(int)y;
      Network.B4Y=(int)y;
  }

}
