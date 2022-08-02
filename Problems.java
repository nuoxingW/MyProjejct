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
  
	FileReader data;

	public void Problems(String fileName) throws FileNotFoundException,IOException {
		Problems.fileName = fileName;	
		data = new FileReader(fileName);
  		Scanner scan = new Scanner(data);
         readFileGTP(scan);
         clusternum();
  	} 
 
 
	
 //读取文件
    private void readFileGTP(Scanner scan) {
    	 points = new ArrayList<>();
         int count=0;
         int s=0;
         nodeNum=1600;
       	 pos = new double[nodeNum][2];
    	  while(scan.hasNext()){
    		  count++;
    		  if(count>15){
    			  scan.nextDouble();
    	     		pos[s][0] = scan.nextDouble();
    	    		pos[s][1] = scan.nextDouble();
    	    		System.out.print(pos[s][0]+"  ");
    	    		System.out.println(pos[s][1]);
    	    		s++;
    	    		points.add(new Point(s, pos[s][0], pos[s][1]));
    			    scan.nextLine();
    			    System.out.println(s+"s的值");
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
    //	scan.close();

    }
    
    public void clusternum() throws FileNotFoundException{
		FileReader data1 = new FileReader("C:\\Users\\Nuoxing.W\\Desktop\\36LIN318.GTP.txt");
  		Scanner scan = new Scanner(data1);
        ArrayList clusters = new ArrayList();
     	clusterNum = new int[nodeNum];
     	clusterPos = new int[nodeNum];
     	largestCluster = 0;
        scan.skip("");
        scan.skip("");
      	int numOfCluster = scan.nextInt();
      	System.out.println(numOfCluster+"===================");
       	for (int i = 0; i < numOfCluster; i++) {
     		List<Integer> c = new ArrayList<>();
     		int m =  scan.nextInt();
     		scan.nextLine();
     		System.out.println(m+"==========");
     		for (int j = 0; j < m; j++) {
     			scan.nextLine();
     			int node =   scan.nextInt();
/*     			System.out.println(node+"node");
     			System.out.println(clusterNum.length+"length");
     			System.out.println(clusters.size()+"nodeNum");*/
     			System.out.println(node);
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

        