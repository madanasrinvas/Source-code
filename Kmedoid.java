/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author ss10155
 */

import static Main.Network.column;
import java.util.ArrayList;

public class Kmedoid {
    
    public static int maxIterations ;
    public static ArrayList<ArrayList<Integer>> Kmedoid=new ArrayList<ArrayList<Integer>>();
    
//    public static void main(String args[])  {
	public static void main(int clusters,double[][] points)  {            
		                      
                int row=points.length;
               
                
		sortPointsByX(points);              
		
		// Calculate initial medoid
		double[][] medoid = new double[clusters][column];
		for(int i=0; i<medoid.length; i++) {
			medoid[i][0] = points[(int) (Math.floor((row*1.0/clusters)/2) + i*row/clusters)][0];                        
			medoid[i][1] = points[(int) (Math.floor((row*1.0/clusters)/2) + i*row/clusters)][1];
		}

		// Create skeletons for clusters
		ArrayList<Integer>[] oldClusters = new ArrayList[clusters];
		ArrayList<Integer>[] newClusters = new ArrayList[clusters];

		for(int i=0; i<clusters; i++) {
			oldClusters[i] = new ArrayList<Integer>();
			newClusters[i] = new ArrayList<Integer>();
		}

		// Make the initial clusters
		formClusters(oldClusters, medoid, points);
		int iterations = 0;

		// Showtime
		while(true) {
			updatemedoid(oldClusters, medoid, points);
			formClusters(newClusters, medoid, points);
			iterations++;
			if(iterations > maxIterations || checkEquality(oldClusters, newClusters))
				break;
			else
				resetClusters(oldClusters, newClusters);
		}

		
		displayOutput(oldClusters, points);		
	}

	

	static void sortPointsByX(double[][] points) {
		double[] temp;

		// Bubble Sort
		for(int i=0; i<points.length; i++)
		    for(int j=1; j<(points.length-i); j++)
			if(points[j-1][0] > points[j][0]) {
			    temp = points[j-1];
			    points[j-1] = points[j];
			    points[j] = temp;
			}
	}

	static void updatemedoid(ArrayList<Integer>[] clusterList, double[][] medoid, double[][] points) {
		double totalX = 0;
		double totalY = 0;
		for(int i=0; i<clusterList.length; i++) {
			totalX = 0;
			totalY = 0;
			for(int index: clusterList[i]) {
				totalX += points[index][0];
				totalY += points[index][1];
			}
			medoid[i][0] = totalX/clusterList[i].size();
			medoid[i][1] = totalY/clusterList[i].size();
		}
	}

	static void formClusters(ArrayList<Integer>[] clusterList, double[][] medoid, double[][] points) {
		double distance[] = new double[medoid.length];
		double minDistance = 999999999;
		int minIndex = 0;

		for(int i=0; i<points.length; i++) {
			minDistance = 999999999;
			for(int j=0; j<medoid.length; j++) {
				distance[j] = Math.sqrt(Math.pow((points[i][0] - medoid[j][0]), 2) + Math.pow((points[i][1] - medoid[j][1]), 2));
				if(distance[j] < minDistance) {
					minDistance = distance[j];
					minIndex = j;
				}
			}
			clusterList[minIndex].add(i);
		}
	}

	static boolean checkEquality(ArrayList<Integer>[] oldClusters, ArrayList<Integer>[] newClusters) {
		for(int i=0; i<oldClusters.length; i++) {
			// Check only lengths first
			if(oldClusters[i].size() != newClusters[i].size())
				return false;

			// Check individual values if lengths are equal
			for(int j=0; j<oldClusters[i].size(); j++)
				if(oldClusters[i].get(j) != newClusters[i].get(j))
					return false;
		}

		return true;
	}

	static void resetClusters(ArrayList<Integer>[] oldClusters, ArrayList<Integer>[] newClusters) {
		for(int i=0; i<newClusters.length; i++) {
			// Copy newClusters to oldClusters
			oldClusters[i].clear();
			for(int index: newClusters[i])
				oldClusters[i].add(index);

			// Clear newClusters
			newClusters[i].clear();
		}
	}

	static void displayOutput(ArrayList<Integer>[] clusterList, double[][] points) {
		for(int i=0; i<clusterList.length; i++) {
                    ArrayList<Integer> in=new ArrayList<Integer>();
			String clusterOutput = "\n\n[";
			for(int index: clusterList[i]) {                           
				clusterOutput += "(" + points[index][0] + ", " + points[index][1] + "), ";
                                in.add(index);
                        }   
                        Kmedoid.add(in);
			//System.out.println(clusterOutput.substring(0, clusterOutput.length()-2) + "]");
		}
	}
}
