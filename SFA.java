/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author ss10155
 */
public class SFA {    
    public static int n; // number of sun flower
    public static double p=0.05; //pollination rate best values 
    public static double m=0.1; //mortality rate
    public static int d=2; //problem dimention
    public static int LB=-5;//lower bounds
    public static int UB=5;//upper bounds
   public static int n_iter =100;//max number os iterations/gerations
      
    
    public static void main(int N) {
        // TODO code application logic here
       n=N;
       Random r=new Random();
       ArrayList<ArrayList<Double>> Plants=new ArrayList<ArrayList<Double>>();
       ArrayList<Double>Fitness=new ArrayList<Double>(); 
       for(int i=0;i<n;i++){
            ArrayList<Double> x=new ArrayList<Double>();
            for(int j=0;j<d;j++){
                double v=LB+(UB-LB)*r.nextDouble();
                x.add(v);
            }
            Plants.add(x);
            Fitness.add(Fitness(x));
        }
       
      double fmin= Collections.min(Fitness);
      int I=Fitness.indexOf(fmin);
      ArrayList<Double> Best=Plants.get(I);
      ArrayList<ArrayList<Double>> S=new ArrayList<ArrayList<Double>>(Plants);
       
      for(int t=1;t<n_iter;t++){
          for(int i=1;i<n;i++){
              //pollination
              for(int j=1;j<(int)(p*n);j++){
                  for(int ss=0;ss<d;ss++){
                      double v=Plants.get(j).get(ss)-Plants.get(j+1).get(ss)*r.nextDouble()+Plants.get(j+1).get(ss);
                      S.get(j).set(ss,v);
                  }
              }
              //step
              for(int j=1;j<Plants.size();j++){
                  for(int ss=0;ss<d;ss++){
                      double v=Plants.get(j).get(ss)+(Plants.get(j).get(ss));
                      S.get(j).set(ss, v);
                  }
              }
              
              // mortality of m% plants
              for(int j=(int)(n*(1-m)+1);j<n;j++){
                  for(int k=0;k<d;k++){
                     S.get(j).set(k,((UB-LB)*r.nextDouble()+LB));
                  }
              }
              
              double Fnew=Fitness(S.get(i));
              
              if(Fnew<=Fitness.get(i)){
                  Plants.set(i, S.get(i));
                  Fitness.set(i, Fnew);
              }
              if(Fnew<=fmin){
                  for(int k=0;k<Best.size();k++){
                      Best.set(k, S.get(i).get(k));
                  }
                  fmin=Fnew;
              }
          }
      }
    }

    private static double Fitness(ArrayList<Double> x) {
       double x1=100*Math.pow((x.get(1)-Math.pow((x.get(0)),2)),2)+Math.pow((1-x.get(0)),2);
       return x1;        
    }
    
}
