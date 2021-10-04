/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author ss10155
 */
public class WDWWO {
    
    public static int popsize=20; //population size
    public static int npar=5; //dimention of the pbm
    public static int maxit=500; //maximum number of iterations
    public static int RT=3;   //RT 
    public static double g=0.2; // gravitational constant
    public static double alp = 0.4;	// constants in the update eq.
    public static double c = 0.4; //coriolis effect
    public static double maxV = 0.3; //maximum allowed speed.
    public static double dimMin =  -5;	//Lower dimension boundary
    public static double dimMax= 5;//Upper dimension boundary.
    public static double best;
    public static Random r=new Random();
    
    
    public static  double WDWWO  (){
       //% Initialize WDWWO population, position and velocity:
        //% Randomize population in the range of [-1, 1]:
        
        Random r=new Random();
        double pos[][]=new double[popsize][npar];
        double vel[][]=new double[popsize][npar];
        
        for(int i=0;i<popsize;i++){
            for(int j=0;j<npar;j++){
                pos[i][j]=2*(r.nextDouble()*-0.5);
                vel[i][j]= maxV*2*(r.nextDouble()*-0.5);
            }
        }
//        %---------------------------------------------------------------
//% Evaluate initial population: 
        double pres[]=new double[popsize];
        ArrayList<Double> presalst=new ArrayList<Double>();
        for(int K=0;K<popsize;K++){
            double x = 0; 
            for(int j=0;j<npar;j++){
                double v= (dimMax-dimMin)*(pos[K][j]+1)/2+dimMin;
                x+=v*v;                
            }
            presalst.add(x);
            pres[K]=x;
        }        
        //Finding best air parcel in initial population :        
        double minpres=Collections.min(presalst); //minimum pres
        double globalpres=minpres;
        int indx= presalst.indexOf(minpres);
        ArrayList<Double> globalpos = new ArrayList<Double>();
        for(int i=0;i<pos[indx].length;i++){
            globalpos.add(pos[indx][i]);
        }
//        %-----------------------------------------------------------------
//        % Rank the air parcels:
        ArrayList<Double> presalstsort=new ArrayList<Double>(presalst);
        Collections.sort(presalstsort);
        ArrayList<Integer> rank_ind=new ArrayList<Integer>();
        for(int i=0;i<presalstsort.size();i++){
            for(int j=0;j<presalst.size();j++){
                if(presalstsort.get(i)==presalst.get(j)){
                    rank_ind.add(j);
                }
            }
        }
        
        //Sort the air parcels:
        double pos1[][]=new double[popsize][npar];
        double vel1[][]=new double[popsize][npar];
        
        for(int i=0;i<popsize;i++){
            int ind=rank_ind.get(i);
            for(int j=0;j<npar;j++){
                pos1[i][j]=pos[ind][j];
            }
        }
        
        double keepglob=minpres;
        
        // Start iterations :
        
        for(int ij=2;ij<maxit;ij++){//% iteration counter
            // Update the velocity:
            ArrayList<Integer> a=new ArrayList<Integer>();
            ArrayList<ArrayList<Double>> vel__=new ArrayList<ArrayList<Double>>();
            for(int i=1;i<popsize;i++){
                //% choose random dimensions:
                for(int a1=0;a1<npar;a1++){
                    a.add(a1);
                }
                Collections.shuffle(a);
               //choose velocity based on random dimension:
               ArrayList<Double>velot=new ArrayList<Double>();
               for(int ve=0;ve<a.size();ve++){
                   int in=a.get(ve);
                   velot.add(vel[i][in]) ;
               }
               ArrayList<Double> vel_=new ArrayList<Double>();
               
               for(int s=0;s<vel[i].length;s++){
                  double xx=(1-alp)*vel[i][s]-(g*pos1[i][s])+
                          Math.abs(1-(1/i))*globalpos.get(s)-pos1[i][s]
                          *RT+c*velot.get(s)/i;
                  vel_.add(xx);
               }
               vel__.add(vel_);              
                
            }
            
            // Check velocity:
            
            for(int v=0;v<vel__.size();v++){
                for(int v1=0;v1<vel__.get(v).size();v1++){
                    if(vel__.get(v).get(v1)>maxV){
                        vel__.get(v).set(v1, maxV);
                    }
                    if(vel__.get(v).get(v1)<-maxV){
                        vel__.get(v).set(v1, -maxV);
                    }
                    pos1[v][v1]=pos1[v][v1]+vel__.get(v).get(v1);
                }
                
            }
            //% Update air parcel positions   
            
            for(int p=0;p<pos1.length;p++){
                for(int p1=0;p1<pos1[p].length;p1++){
                    if(pos1[p][p1]>1.0){
                        pos1[p][p1]=1.0;
                    }
                    if(pos1[p][p1]<-1.0){
                        pos1[p][p1]=-1.0;
                    }
                }
            }
             //% Evaluate population: (Pressure)
             ArrayList<Double>pre=new ArrayList<Double>();
            for(int K=0;K<popsize;K++){
            double x = 0; 
            for(int j=0;j<npar;j++){
                double v= (dimMax-dimMin)*(pos1[K][j]+1)/2+dimMin;
                x+=v*v;                
            }
            pre.add(x);
            pres[K]=x;           
//           
            } 
            // % Finding best particle in population
             minpres=Collections.min(pre);
             indx=pre.indexOf(minpres); //% min location for this iteration
             
        	           
            ArrayList<Double>  minpos =new ArrayList<Double>();
            for(int k=0;k<pos1[indx].length;k++){
                minpos.add(pos1[indx][k]);
            }
            //% Sort the air parcels position, velocity and pressure:
           // % Updating the global best:
           if(minpres <globalpres){
               globalpres = minpres    ;//% initialize global minimum
               globalpos = minpos;
               best=globalpres;
           }
            
        }
        
        //weight=r.nextDouble();
        
        return best;
    }
   
    
}
