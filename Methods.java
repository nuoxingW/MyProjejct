package Myproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import org.w3c.dom.NodeList;

public class Methods {
   	static Solution solution=new Solution();
   	static Problems  problem =new Problems();
	//从每个个簇中选择一个顶点
	  public  Solution  Choose(int numOfCluster,HashMap map,double[][] matrix) throws FileNotFoundException, IOException{
 	   System.out.println(numOfCluster);
 	    double  distance=Integer.MAX_VALUE;
		for(int i=0;i<numOfCluster;i++){		 
		String nodeList[]=ChooseNode(solution.Get_Cluster(i));
		System.out.println(nodeList+"nodelist");
 		 int start=ChooseNodeofCluster(nodeList, i);
 		  String key=getKeysByLoop(map, start).toString().trim().replace("[", "");
 		   key =key.replace("]", "");
			for(int J=0;J<nodeList.length;J++){
			//	System.out.println(i);
			//	System.out.println(J);
			 int end=ChooseNodeofCluster(nodeList, J);
			  String key1=getKeysByLoop(map, end).toString().trim().replace("[", "");
			  key1=key1.trim().replace("]", "");
			//  System.out.println(key1+"|"+key);			     
            double max=	 solution.getDistance(Integer.parseInt(key.trim()),Integer.parseInt(key1.trim()),map,matrix);
             //   System.out.println(max);
            if(max<distance){
                    	 
                    }
			}
		}
		return null;  
	  }
	  public  static String[] ChooseNode(String nodeNumString){
		   Random random = new Random();
		   String[] nodeList = nodeNumString.trim().split(","); 
		   int num = random.nextInt(nodeList.length) ;
		   System.out.println(num);
 		   String Node=nodeList[num];
 		   System.out.println(Node);
		   nodeList= remove(nodeList, Node);
	       return nodeList;
	  }
	  public  static int ChooseNodeofCluster(String[] nodeList,int num){
		  // String[] nodeList = nodeNumString.trim().split(","); 
		//   System.out.println(nodeList[num]);
           String Node=nodeList[num];
           System.out.println(Node);
           int Node1=Integer.valueOf(Node.trim());
	       return Node1;
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

     private <K, V> Set<K> getKeysByLoop(HashMap<Integer, String> map, V value) {
		    Set keys = new HashSet<>();
		    for (Entry entry : map.entrySet()) {
		    	//System.out.println(entry.getValue()+"|");
		        if (entry.getValue().equals(value.toString())) {
		        //	System.out.println(entry.getKey()+"entry.getKey()");
		            keys.add(entry.getKey());
		        }
		    }
		    return keys;
		}
      
 
}