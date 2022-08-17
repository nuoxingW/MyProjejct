package Myproject;

import java.awt.geom.RectangularShape;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution implements  Comparable<Solution> {

	public static boolean withPrice;
	public void setLastImproving(int n) { this.lastImproving = n;}
	public int getLastImproving() { return this.lastImproving; }
	public int getCityNumber() { return nTour.length; }
	public double getTourLength() { return tourLength; }
	public int[] getTour() { return nTour; }

	public double tourLength;
	private int lastImproving = 0;

	public static long ivsTimes;
	public static long insTimes;
	public static long swpTimes;

	public static int greedyListLength = 10;


	protected int[] nTour;
	protected int[] pTour;
	Problems problem;
  
	public   int[] getNodenum(){
        try {
			problem.read("D:\\23GR229.GTP");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return problem.getClusterNum();
	
	}	

 

	@Override
	public int compareTo(Solution s) {
		if ( tourLength < s.tourLength) {
			return 1;
		} else if ( tourLength == s.tourLength) {
			return 0;
		} else {
			return -1;
		}
	}


}
