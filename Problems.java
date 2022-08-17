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
	private Problems problem = null;
 
	private ArrayList  points;
	private int  nodeNum;
	private double pos[][];
	private int[] clusterNum = new int[nodeNum];
	private  int lusterPos[] = new int[nodeNum];
	private int largestCluster ;
	private int  numOfCluster ;
	private double[][] dists;
	private int clusterPos[];
	private double[] prize;
	private FileReader data;
    private  ArrayList clusters;
	public void read(String fileName) throws FileNotFoundException,IOException {	
		System.out.println(fileName);
		data = new FileReader(fileName);
  		Scanner scan = new Scanner(data);
  		readFileGTP(scan);
        
	}
 
 
	
 //读取文件
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
	    	System.out.println("#3333333333");	    	
	    	clusters = new ArrayList();
	     	clusterNum = new int[nodeNum];
	     	clusterPos = new int[nodeNum];
	     	largestCluster = 0;
	     	int numOfCluster = scan.nextInt();
	     	for (int i = 0; i < numOfCluster; i++) {
	     		List<Integer> c = new ArrayList<>();
	     		int m = scan.nextInt();
	     		System.out.println(m+"mmmmmmmm");
      		for (int j = 0; j < m; j++) {
	     			int node = scan.nextInt();
	     			c.add(node - 1);
	     			clusterNum[node - 1] = clusters.size();
	     			System.out.println(clusters.size()+"cluster.size()");
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
	  			System.out.println(i+"i的数量");
	  			for (int j=0; j<nodeNum;j++) {
	  				System.out.println(j+"j的数量");
	  				if (i==j) {
	 					dists[i][j]=Integer.MAX_VALUE;
	   				} else {
	  					double distance;
	  					distance = (pos[i][0]-pos[j][0]);
	  					distance *= distance;
	  					distance += (pos[i][1]-pos[j][1])*(pos[i][1]-pos[j][1]);
	  					if (Solution.withPrice) {
	  						distance = Math.ceil(Math.sqrt(distance/10.0));
	  					} else {
	  						distance = Math.sqrt(distance);
	  					}
						dists[i][j] = (int)(distance + 0.5);
	  				}
	  			}
	  		}
	  	}
	    public static void main(String[] args) throws FileNotFoundException, IOException {      
	        Problems problem =new Problems();
            problem.read("D:\\23GR229.GTP");
            System.out.println(problem.getNodeNum());
	       // TODO Auto-generated method stub 
	 	 
	}
 


		public ArrayList getPoints() {
			return points;
		}



		public void setPoints(ArrayList points) {
			this.points = points;
		}



		public int getNodeNum() {
			return nodeNum;
		}



		public void setNodeNum(int nodeNum) {
			this.nodeNum = nodeNum;
		}



		public double[][] getPos() {
			return pos;
		}



		public void setPos(double[][] pos) {
			this.pos = pos;
		}



		public int[] getClusterNum() {
			return clusterNum;
		}



		public void setClusterNum(int[] clusterNum) {
			this.clusterNum = clusterNum;
		}



		public int[] getLusterPos() {
			return lusterPos;
		}



		public void setLusterPos(int[] lusterPos) {
			this.lusterPos = lusterPos;
		}



		public int getLargestCluster() {
			return largestCluster;
		}



		public void setLargestCluster(int largestCluster) {
			this.largestCluster = largestCluster;
		}



		public int getNumOfCluster() {
			return numOfCluster;
		}



		public void setNumOfCluster(int numOfCluster) {
			this.numOfCluster = numOfCluster;
		}



		public double[][] getDists() {
			return dists;
		}



		public void setDists(double[][] dists) {
			this.dists = dists;
		}



		public int[] getClusterPos() {
			return clusterPos;
		}



		public void setClusterPos(int[] clusterPos) {
			this.clusterPos = clusterPos;
		}



		public double[] getPrize() {
			return prize;
		}



		public void setPrize(double[] prize) {
			this.prize = prize;
		}



		public ArrayList getClusters() {
			return clusters;
		}



		public void setClusters(ArrayList clusters) {
			this.clusters = clusters;
		}  	 
	    
}

        