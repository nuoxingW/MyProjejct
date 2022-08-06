package Myproject;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution implements  Comparable<Solution> {

	public static boolean withPrice;
	public void setLastImproving(int n) { this.lastImproving = n;}
	public int getLastImproving() { return this.lastImproving; }
	public int getCityNumber() { return nTour.length; }

	//public void setTourLength(long tourLength) { this.tourLength = tourLength; }
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
 
	public Solution() {
		nTour = new int[Problems.getProblem().getCityNumber()];
		pTour = new int[nTour.length];
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
	public Solution(boolean isGreedyRandom) {
		Problems p = Problems.getProblem();
		if ( isGreedyRandom ) {
			nTour = greedyRandomTour(Solution.greedyListLength);
		} else {
			nTour = randomTour();
		}
		pTour = new int[nTour.length];
		for (int i = 0; i< nTour.length; i++ ) {
			pTour[nTour[i]] = i;
		}
		tourLength = p.evaluate(nTour);
		//System.out.println(tourLength);
	}

	private int[] randomTour() {
		// TODO Auto-generated method stub
		return null;
	}
	private int[] greedyRandomTour(int greedyListLength2) {
		// TODO Auto-generated method stub
		return null;
	}
 
	public Solution(int[] tour) {
		nTour = tour.clone();
		pTour = new int[nTour.length];
		for (int i = 0; i< nTour.length; i++ ) {
			pTour[nTour[i]] = i;
		}
		tourLength = Problems.getProblem().evaluate(nTour);
	}

	public Solution(Solution s) {
		this.nTour = s.nTour.clone();
		this.pTour = s.pTour.clone();
		tourLength = s.tourLength;
	}


	private void reverse(int from, int to, double delta) {
		int city_from = from;
		int city_from_next = nTour[from];
		int city_to = to;
		int city_to_next = nTour[to];
		while (  city_to != from) { //city_to != from
			nTour[city_from] = city_to;
			int temp = pTour[city_to];
			pTour[city_to] = city_from;
			city_from  = city_to;	    	          	  
			city_to = temp;
		}
		nTour[city_from_next] = city_to_next;
		pTour[city_to_next] = city_from_next;

		tourLength += delta;
		ivsTimes++;
	}

	/**
	 * Insert a block of city specified by cityFrom (included) and cityEnd (included) after 
	 *        city cityTo
	 *        
	 * @param cityTo
	 * @param cityFrom
	 * @param cityEnd
	 * @param delta
	 */
	private void insert(int cityTo, int cityFrom, int cityEnd, double delta) {
		int ci = cityTo;
		int cj = cityFrom;
		int ck = cityEnd;
		int nci = nTour[ci];
		int nck = nTour[ck];
		int pcj = pTour[cj];
		nTour[ci] = cj; 
		nTour[ck] = nci; 
		nTour[pcj] = nck;
		pTour[cj] = ci; 
		pTour[nci] = ck; 
		pTour[nck] = pcj;

		tourLength += delta;
		insTimes++;
	}

	/**
	 * swap two block of cities (included)
	 * 
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 * @param delta
	 */
	private void swap(int x1, int x2, int y1, int y2, double delta) {//include x1
		int px1 = pTour[x1];
		int nx2 = nTour[x2];
		int py1 = pTour[y1];
		int ny2 = nTour[y2];
		nTour[px1] = y1; nTour[y2] = nx2; nTour[py1] = x1; nTour[x2] = ny2;
		pTour[y1] = px1; pTour[nx2] = y2; pTour[x1] = py1; pTour[ny2] = x2;

		tourLength += delta;
		swpTimes++;
	}




 




 
}
