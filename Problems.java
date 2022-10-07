package Myproject;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.sound.sampled.Line;

public class Problems {
	private static ArrayList  points;
	private static   String fileName;
	private static int  nodeNum;
	private static double pos[][];
	private static int[] clusterNum = new int[nodeNum];
	private  static int lusterPos[] = new int[nodeNum];
	private static int largestCluster ;
	private static int  numOfCluster ;
	private static double[][] dists;
	private static int clusterPos[];
	private static double[] prize;
	private static FileReader data;
    private static    List clusters;
    private static  HashMap Nodemap =  new HashMap();
    private static  int[] vex =new int[nodeNum];
	public   void read(String fileName) throws FileNotFoundException,IOException {	
		this.fileName=fileName;
 		data = new FileReader(fileName);
  		Scanner scan = new Scanner(data);
  		readFileGTP(scan);
	}
 //读取文件
	 private static void readFileGTP(Scanner scan) {
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
	     	clusterPos = new int[nodeNum];
	     	vex =new int[nodeNum];
	     	largestCluster = 0;
	     	int num=0;
 	     	numOfCluster = scan.nextInt();
	     	for (int i = 0; i < numOfCluster; i++) {
	     		List<Integer> c = new ArrayList<>();
	     		int m = scan.nextInt();
       		for (int j = 0; j < m; j++) {
	     			int node = scan.nextInt();
 	     			c.add(node-1);
 	     	        vex[num]=node-1;           
  	     	        Nodemap.put(new Integer(node-1).toString(),String.valueOf(num));
	     			clusterNum[node - 1] = clusters.size();
	     			clusterPos[node - 1] = c.size() - 1;
	     		    num++;
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
 	     	}
	     	a = scan.nextInt();
	     	assert(a == -999);
	    }
	public HashMap getNodemap() {
		return Nodemap;
	}
	public int[] getVex() {
		return vex;
	}
	public int getVex(int i) {
 		return vex[i];
	}
		//to calculate the distance between node
	  	private static  void calcuDistance() {
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
	  	  System.out.println(dists);
	  	  
 	  	}
	  	
/*	  	private static int getKMin(int[] a, int k) {
			if(a == null || a.length<k){
				return Integer.MIN_VALUE;
			}
			return quikSort(a,0,a.length-1,k);
		}*/
/*	private static int quikSort(int[] a, int low, int high, int k) {
//			第0个元素作为枢纽
			int i = low;
			int j=high;
			int tmp = a[i];
			if(low > high){
				return Integer.MIN_VALUE;
			}
//			快速排序
			while(i<j){
				while(i<j && a[j]>=tmp){
					j--;
				}
				if(i<j){
					a[i++] = a[j];
				}
				while(i<j && a[i]<tmp){
					i++;
				}
				if(i<j){
					a[j--] = a[i];
				}
			}//
			a[i] = tmp;
//			判断i+1与k的大小
			if(i+1 == k){
				return tmp;
			}else if(i+1 > k){
				return quikSort(a, low, i-1, k);
			}else{
				return quikSort(a, i+1, high, k);
			}
		}*/
 
	    public static void main(String[] args) throws FileNotFoundException, IOException {      
        Problems problem =new Problems();
        problem.read("C:\\Users\\Nuoxing.W\\Desktop\\gMST\\datas\\instances\\11EIL51.GTP");
		double[][] a = problem.getDists();
 		//System.out.print(kmin);
		 double[] ss = getColMin(a); 
		    for (int i = 0; i < ss.length; i++) { 
		      System.out.print(ss[i] + " "); 
		    } 
	}
	    public static double[] getColMin(double[][] a) { 
	        double[] res = new double[a.length]; 
	        for (int i = 0; i < a.length; i++) { 
	          double[] s = a[i]; 
	          Arrays.sort(s); 
	          res[i] = s[0]; 
	        } 
	        return res; 
	      } 
	    public  ArrayList getPoints() {
			return points;
		}
		public int getNodeNum() {
			return nodeNum;
		}
		//获得簇的大小
		public  int numOfCluster(){
			return numOfCluster;
		}
		private final double[][] getPos() {
			return pos;
		}
		public  double  getPos(int i,int  j){
		      double[][] list=getPos();  
			  return list[i][j];
		}

		private final int[] getClusterNum() {
			return clusterNum;
		}
		public  int  getClusterNum(int i){
		      int [] list=getClusterNum();  
			  return list[i];
		}


		public   final int[]  getLusterPos() {
			return lusterPos;
		}
		public int getLargestCluster() {
			return largestCluster;
		}
		public int getNumOfCluster() {
			return numOfCluster;
		}
        private final double[][] getDists() {
			return dists;
		}
		public int  distLength(){
			return dists.length;
		}
		public  double  getDists(int i,int j){
			double [][] list=getDists();
		    double  arc=	list[i][j];
			  return arc;
		}      
        public int  getDistsLength(){
        	return getDists().length;
        }


		private final int[] getClusterPos() {
			return clusterPos;
		}

		public  int  getClusterPos(int i){
			int [] list=getClusterPos();  
			  return list[i];
		}    
        public int  getClusterPosLength(){
        	return getClusterPos().length;
        }

		private final double[] getPrize() {
			return prize;
		}
		public double getPrize(int i) {
			double [] list=getPrize();  
			  return list[i];
		}
        public int  getPrizeLength(){
        	return getPrize().length;
        }

		public FileReader getData() {
			return data;
		}

	    public  String getClusters() {
	    	String str= clusters.toString();
			return str;
		}

        public int  getClustersLength(){
        	return clusters.size();
        }


		 
}

        