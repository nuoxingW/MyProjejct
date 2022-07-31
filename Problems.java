package Myproject;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.Line;

public class Problems {
	public static Problems problem = null;
	public static String fileName = null;
	public static ArrayList  points;
	public static ArrayList clusters;
    int  nodeNum;
    double pos[][];
	int clusterNum[] = new int[nodeNum];
	int lusterPos[] = new int[nodeNum];
	int largestCluster = 0;
	int  numOfCluster ;
	double[][] dists;
 
 
	private Problems(String fileName) throws FileNotFoundException,IOException {
		Problems.fileName = fileName;
		FileReader data;
		data = new FileReader(fileName);
		Scanner scan = new Scanner(data);
         readFileGTP(scan);
 	} 
 
	public static Problems getProblem() {
		if (problem == null) {
			try {
				problem = new Problems(fileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return problem;
	}

 //¶ÁÈ¡ÎÄ¼þ
    private void readFileGTP(Scanner scan) {
    	points = new ArrayList<>();
    	nodeNum = scan.nextInt();
     	pos = new double[nodeNum][2];
    	for (int i = 0; i < nodeNum; i++) {
     		pos[i][0] = scan.nextDouble();
    		pos[i][1] = scan.nextDouble();	 
    	  points.add(new Point(i, pos[i][0], pos[i][1]));
    	}
    	calcuDistance();    	
        clusters = new ArrayList();
        clusterNum = new int[nodeNum];
        int	clusterPos[] = new int[nodeNum];
       	largestCluster = 0;
     	 numOfCluster = scan.nextInt();
     	for (int i = 0; i < numOfCluster; i++) {
     		Line c = (Line) new ArrayList();
     		int m = scan.nextInt();
     		for (int j = 0; j < m; j++) {
     			int node = scan.nextInt();
     			((ArrayList) c).add(node - 1);
     			clusterNum[node - 1] = clusters.size();
     			clusterPos[node - 1] = ((ArrayList) c).size() - 1;
     		}
     		clusters.add(c);
     		if (((ArrayList) c).size() > ((ArrayList) clusters.get(largestCluster)).size()) {
     			largestCluster = clusters.size() - 1;
     		}
     	}
     	
     	for (int i = 0; i < numOfCluster; i++) {//center of each cluster
     		scan.nextInt();
     	}
     	int a = scan.nextInt();
     	assert(a == -999);
         double	prize[] = new double[nodeNum];
     	for (int i = 0; i < nodeNum; i++) {
     		prize[i] = scan.nextInt();
     		//System.out.println(prize[i]);
     	}
     	a = scan.nextInt();
     	assert(a == -999);
    }
    
  //to calculate the distance between cities
	  	public void calcuDistance() {
	  		dists = new double[nodeNum][nodeNum];
	  		for (int i=0; i<nodeNum;i++) {
	  			for (int j=0; j<nodeNum;j++) {
	  				if (i==j) {
	 					dists[i][j]=Integer.MAX_VALUE;
	   				} else {
	  					double distance;
	  					distance = (pos[i][0]-pos[j][0]);
	  					distance *= distance;
	  					distance += (pos[i][1]-pos[j][1])*(pos[i][1]-pos[j][1]);
	  					if (fileName.toLowerCase().contains("att") && !Solution.withPrice) {
	  						distance = Math.ceil(Math.sqrt(distance/10.0));
	  					} else {
	  						distance = Math.sqrt(distance);
	  					}
						dists[i][j] = (int)(distance + 0.5);
	  				}
	  			}
	  		}
	  	}
}

