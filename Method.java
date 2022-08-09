package Myproject;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Method {
	Problems problem;
	
  	public void calcuDistance() {
  		problem.dists = new double[problem.nodeNum][problem.nodeNum];
  		for (int i=0; i<problem.nodeNum;i++) {
  			for (int j=0; j<problem.nodeNum;j++) {
  				if (i==j) {
 					problem.dists[i][j]=Integer.MAX_VALUE;
   				} else {
  					double distance;
  					distance = (problem.pos[i][0]-problem.pos[j][0]);
  					System.out.println(distance+"distance!!!!!!!!");
  					distance *= distance;
  					distance += (problem.pos[i][1]-problem.pos[j][1])*(problem.pos[i][1]-problem.pos[j][1]);
  					if (problem.fileName.toLowerCase().contains("att") && !Solution.withPrice) {
  						distance = Math.ceil(Math.sqrt(distance/10.0));
  						System.out.println("在这里计算");
  					} else {
  						distance = Math.sqrt(distance);	 
  					}
					problem.dists[i][j] = (int)(distance + 0.5);
				   System.out.println(distance+"distance!!!!!!!!!!");
					System.out.println(problem.dists[i][j]); 
  				}
  			}
  		}
  	}
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	
      
        Problems.Problems("C:\\Users\\Nuoxing.W\\Desktop\\1.txt");
        Solution s = new Solution(true);
		// TODO Auto-generated method stub 
 	 
}
}
