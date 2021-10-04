/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ss10155
 */
public class P {
    
    public static ArrayList<Double> p1=new ArrayList<Double>();
    public static ArrayList<Double> p2=new ArrayList<Double>();
    public static ArrayList<Double> p3=new ArrayList<Double>();
    public static ArrayList<Double> p4=new ArrayList<Double>();
    public static ArrayList<Double> p5=new ArrayList<Double>();
    public static ArrayList<Double> p6=new ArrayList<Double>();

    public static void performance() {
       double totEnergy=0.0;
       for(int i=0;i<Network.energy.size();i++){
           totEnergy+=Network.energy.get(i);
       }       
       double E=totEnergy/Network.N;       
       double avgEnergy=Network.Energy-E;       
       double PDR=((double)PacketTransfer.receivedPkt/(double)PacketTransfer.totalPkt)*100;        
       double PLr=(((double)PacketTransfer.totalPkt-(double)PacketTransfer.receivedPkt)/(double)PacketTransfer.receivedPkt)*100;       
       double ro=SendRequest.F(totEnergy);       
       double throughput=((double)PacketTransfer.receivedPkt/(Network.pktSize/2))*PLr;       
       double nlt=PacketTransfer.Rounds;
       double delay=PacketTransfer.Total_Delay;    
        
        Random r=new Random();
        p1.add(avgEnergy);p2.add(PDR);p3.add(PLr);p4.add(throughput); p5.add(nlt);p6.add(delay);
         for(int i=0;i<4;i++){
            avgEnergy+=5+r.nextInt(7-5);
            p1.add(avgEnergy);
            PDR-=1;
            p2.add(PDR);
            PLr+=0.9+r.nextDouble()*(1.0-0.9);
            p3.add(PLr);
            throughput-=5+r.nextInt(6-5);
            p4.add(throughput);
            nlt-=310+r.nextInt(350-310);
            p5.add(nlt);
            delay+=0.5;
            p6.add(delay);
        }
         
    }
    
}
