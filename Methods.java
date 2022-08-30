package Myproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.w3c.dom.NodeList;

public class Methods {
   	static Solution solution=new Solution();
   	static Problems  problem =new Problems();
	//从每个个簇中选择一个顶点
	  public static Solution  Choose() throws FileNotFoundException, IOException{
	  int  numOfCluster	=problem.getNumOfCluster();
		for(int i=0;i<numOfCluster;i++){		 
		String nodeList[]=ChooseNode(solution.Get_Cluster(i));
		 int start=ChooseNodeofCluster(solution.Get_Cluster(i), i);
			for(int J=0;J<nodeList.length;J++){
			 int end=ChooseNodeofCluster(solution.Get_Cluster(i), J);
			// solution.getNode(end);
	    	 solution.getDistance(start, end);
	    	 System.out.println(solution.getNode(end)+"------>"+solution.getNode(start));
			}
		}
		return null;  
	  }
	  public  static String[] ChooseNode(String nodeNumString){
		   Random random = new Random();
		   String[] nodeList = nodeNumString.split(","); 
		   int num = random.nextInt(nodeList.length) + 1;
		   String Node=nodeList[num];
		   nodeList= remove(nodeList, Node);
	       return nodeList;
	  }
	  public  static int ChooseNodeofCluster(String nodeNumString,int num){
 
		   String[] nodeList = nodeNumString.split(","); 
           Integer Node=Integer.valueOf(nodeList[num]);
	       return Node;
	  }
	  //移除某个元素
	  private static String[] remove(String[] arr, String num) {
		  String[] tmp = new String[arr.length - 1];
	        int idx = 0;
	        boolean hasRemove = false;
	        for (int i = 0; i < arr.length; i++) {
	 
	            if (!hasRemove && arr[i] == num) {
	                hasRemove = true;
	                continue;
	            }
	 
	            tmp[idx++] = arr[i];
	        }
	 
	        return tmp;
	    } 
 
}