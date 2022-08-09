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
 
	public static int  nodeNum=318;
	public static double pos[][];
	public static int[] clusterNum = new int[nodeNum];
	public static  int lusterPos[] = new int[nodeNum];
	public static int largestCluster ;
	public static int  numOfCluster ;
	public static double[][] dists;
	public static int clusterPos[];
	public static double[] prize;
	public static FileReader data;
	public  static void Problems(String fileName) throws FileNotFoundException,IOException {
		Problems.fileName = fileName;	
		data = new FileReader(fileName);
  		Scanner scan = new Scanner(data);
         readFileGTP(scan);
         clusternum();
  	} 
 
 
	
 //读取文件
    public static void readFileGTP(Scanner scan) {
    	 points = new ArrayList<>();
         int count=0;
         int s=0;
        
       	 pos = new double[nodeNum][2];
    	  while(scan.hasNext()){
    		  count++;
    		  if(count>10){
    			  scan.nextDouble();
    	     		pos[s][0] = scan.nextDouble();
    	    		pos[s][1] = scan.nextDouble();
    	    	 //	System.out.print(pos[s][0]+"  ");
    	    	 	///System.out.println(pos[s][1]);
    	    		s++;
    	    		//points.add(new Point(s, pos[s][0], pos[s][1]));
    			    scan.nextLine();
     			    if(s==317){
    			    	System.out.println("跳出循环");
    			    	break;
    			    }
    		  }else{		 
    		     scan.nextDouble();
    		  }
    		  
    	  }
    	 
    	// nodeNum=  scan.hasNext(); 
    	 

     	 //System.out.println(nodeNum);
    

/*    	for (int i = 1; i < nodeNum; i++) {
     		pos[i][0] = scan.nextDouble();
    		pos[i][1] = scan.nextDouble();
    		System.out.println( pos[i][0]);
    		System.out.println( pos[i][1]);
    		
    	}*/
    	  Method method =new Method();;
    	  method.calcuDistance();
//    	dists = new double[nodeNum][nodeNum];
//    	for (int i = 0; i < nodeNum; i++) {
//    		for (int j = i + 1; j < nodeNum; j ++) {
//    			double s = (pos[i][0] - pos[j][0]) * (pos[i][0] - pos[j][0]);
//    			s += (pos[i][1] - pos[j][1]) * (pos[i][1] - pos[j][1]);
//    			dists[i][j] = (int)(Math.sqrt(s) + 0.5);
//    			dists[j][i] = dists[i][j];
//    		}
//    	}
    //	scan.close();

    }
    
    public static void clusternum() throws FileNotFoundException{
		FileReader data1 = new FileReader("C:\\Users\\Nuoxing.W\\Desktop\\36LIN318.GTP.txt");
  		Scanner scan = new Scanner(data1);
        ArrayList clusters = new ArrayList();
     	clusterNum = new int[nodeNum];
     	clusterPos = new int[nodeNum];
     	largestCluster = 0;
        scan.skip("");
        scan.skip("");
      	int numOfCluster = scan.nextInt();
  
      	//System.out.println(numOfCluster+"===================");
       	for (int i = 0; i < 1; i++) {
     		List<Integer> c = new ArrayList<>();
     		int m =  scan.nextInt();
     		scan.nextLine();
     	 //	System.out.println(m+"==========");
     		for (int j = 0; j < m; j++) {
     			scan.nextLine();
     			int node =   scan.nextInt();
/*     			System.out.println(node+"node");
     			System.out.println(clusterNum.length+"length");0
     			System.out.println(clusters.size()+"nodeNum");*/
     	
     			c.add(node - 1);		                                              
     				clusterNum[node - 1] = clusters.size();
     				System.out.println(c.size()+"size");
     				System.out.println(node+"node");
     				System.out.println(numOfCluster+"mmmm");
         			clusterPos[node - 1] = c.size() - 1;	
     		}
     		clusters.add(c);
     		if (c.size() > ((ArrayList) clusters.get(largestCluster)).size()) {
     			largestCluster = clusters.size() - 1;
     		}
     	}
 
    }
  //to calculate the distance between cities


class Item implements Comparable<Item>{
	private int id;
	private double value;
	private double density;
	private double weight;
	
	public Item(int id, double value, double weight) {
		this.id = id;
		this.value = value;
		this.weight = weight;
		density = value / weight;
	}
	
	
	
	public int getID() { return id; }
	public double getValue() { return value;}
	public double getWeight() { return weight;}
	public double getDensity() { return density; }



	@Override
	public int compareTo(Item o) {
		if (this.density > o.density) {
			return 1;
		} else if (this.density == o.density) {
			return 0;
		} else {
			return -1;
		}
	}
}
	public static Problems getProblem() {
		return problem;
	}



	public static void setProblem(Problems problem) {
		Problems.problem = problem;
	}



	public int getCityNumber() {
		// TODO Auto-generated method stub
		return 0;
	}



	public double evaluate(int[] nTour) {
		// TODO Auto-generated method stub
		return 0;
	}

	  	 
}

        