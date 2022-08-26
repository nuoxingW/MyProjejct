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
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.Line;

public class Problems {
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
    private  List clusters;
    private  int[] vex =new int[nodeNum];
	public void read(String fileName) throws FileNotFoundException,IOException {	
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
	    	clusters = new ArrayList();
	     	clusterNum = new int[nodeNum];
	     	clusterPos = new int[nodeNum];
	     	vex =new int[nodeNum];
	     	largestCluster = 0;
	     	int num=0;
 	     	int numOfCluster = scan.nextInt();
	     	for (int i = 0; i < numOfCluster; i++) {
	     		List<Integer> c = new ArrayList<>();
	     		int m = scan.nextInt();
       		for (int j = 0; j < m; j++) {
	     			int node = scan.nextInt();
 	     		//	c.add(node - 1);
	     			c.add(node-1);
 	     	        vex[num]=node-1;
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
	     		//System.out.println(prize[i]);
	     	}
	     	a = scan.nextInt();
	     	assert(a == -999);
	    }
	    
	  public int getVex(int i) {
		return vex[i];
	}



		//to calculate the distance between cities
	  	public void calcuDistance() {
	  		dists = new double[nodeNum][nodeNum];
	  		for (int i=0; i<nodeNum;i++) {
	  			//System.out.println(i+"i的数量");
	  			for (int j=0; j<nodeNum;j++) {
	  				//System.out.println(j+"j的数量");
	  				if (i==j) {
	 					dists[i][j]=Integer.MAX_VALUE;
	 				//	System.out.println(dists[i][j]);
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
	  	        System.out.println(dists);
	  	}
	    public static void main(String[] args) throws FileNotFoundException, IOException {      
        Problems problem =new Problems();
            problem.read("D:\\23GR229.GTP");
            /*	    System.out.println(problem.getDists().length);
            System.out.println(problem.getPos());
            final Integer[] arr={1,2,3};
            arr[0]=3;
            System.out.println(Arrays.toString(arr));

          final  int[]  list= problem.getClusterPos(); 
            list[1]=2;
            if (list != null) {  //如果数组不为空，执行下面的语句
                for (int i = 0; i < list.length; i++) {     //for 循环遍历数组
                    if (i < list.length - 1){         //如果数组下标不是最后一个
                        System.out.println(list[i] + ", ");//输出下标对应的值和，
                    } else {                                //如果是最后一个下标，输出对应的值
                        System.out.println(list[i]);
                    }
                }
            }
            System.out.println(problem.getClusterPos()[1]); */
 
 /*           if (problem.getPos() != null) {  //如果数组不为空，执行下面的语句
                for (int i = 0; i < problem.getClusterPos().length; i++) {     //for 循环遍历数组
                    if (i < problem.getClusterPos().length - 1) {         //如果数组下标不是最后一个
                        System.out.print(problem.getClusterPos().toString() + ", ");//输出下标对应的值和，
                    } else {                                //如果是最后一个下标，输出对应的值
                        System.out.print(problem.getClusterPos().toString());
                    }
                }
            }*/
	 	 
	}


 
	    public  ArrayList getPoints() {
			return points;
		}
/*        public Object getPoints(int i) {
        	ArrayList  list=getPoints();
        	 
			return list ;
		}*/


		public int getNodeNum() {
			return nodeNum;
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

        