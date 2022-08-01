package Myproject;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.Line;

public class Problems {
	public static Problems problem = null;
	public static String fileName = null;
	public static ArrayList  points;
 
	int  nodeNum;
    double pos[][];
    int[] clusterNum = new int[nodeNum];
    int lusterPos[] = new int[nodeNum];
	int largestCluster ;
	int  numOfCluster ;
	double[][] dists;
	int clusterPos[];
	double[] prize;
 
 
 

	public void Problems(String fileName) throws FileNotFoundException,IOException {
		Problems.fileName = fileName;
		FileReader data;
		data = new FileReader(fileName);
  		Scanner scan = new Scanner(data);
         readFileGTP(scan);
 	} 
 
 
	
 //¶ÁÈ¡ÎÄ¼þ
    private void readFileGTP(Scanner scan) {
    	points = new ArrayList<>();
    	nodeNum = (int) scan.nextDouble(); 
    	System.out.println(nodeNum);
    	ArrayList clusters = new ArrayList();
    	System.out.println(nodeNum);
     	pos = new double[nodeNum][2];
    	for (int i = 0; i < nodeNum; i++) {
     		pos[i][0] = scan.nextDouble();
    		pos[i][1] = scan.nextDouble();
    		System.out.println(pos[i][0]);
    		points.add(new Point(i, pos[i][0], pos[i][1]));
    	}
    	calcuDistance();
//    	dists = new double[nodeNum][nodeNum];
//    	for (int i = 0; i < nodeNum; i++) {
//    		for (int j = i + 1; j < nodeNum; j ++) {
//    			double s = (pos[i][0] - pos[j][0]) * (pos[i][0] - pos[j][0]);
//    			s += (pos[i][1] - pos[j][1]) * (pos[i][1] - pos[j][1]);
//    			dists[i][j] = (int)(Math.sqrt(s) + 0.5);
//    			dists[j][i] = dists[i][j];
//    		}
//    	}
 
     	clusterNum = new int[nodeNum];
     	clusterPos = new int[nodeNum];
     	largestCluster = 0;
     	double numOfCluster = scan.nextDouble();
     	for (int i = 0; i < numOfCluster; i++) {
     		List<Integer> c = new ArrayList<>();
     		int  m = (int) scan.nextDouble();
     		for (int j = 0; j < m; j++) {
     			int node = (int) scan.nextDouble();
     			System.out.println(clusters.size()+"clsters");
     			System.out.println(nodeNum+"nodeNum");
     			c.add(node - 1);
     			clusterNum[node - 1] = clusters.size();
     			clusterPos[node - 1] = c.size() - 1;
     		}
     		clusters.add(c);
     		if (c.size() > ((ArrayList) clusters.get(largestCluster)).size()) {
     			largestCluster = clusters.size() - 1;
     		}
     	}
     	
     	for (int i = 0; i < numOfCluster; i++) {//center of each cluster
     		scan.nextInt();
     	}
     	int a = scan.nextInt();
     	assert(a == -999);
     	prize = new double[nodeNum];
     	for (int i = 0; i < nodeNum; i++) {
     		prize[i] = scan.nextDouble();
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

        