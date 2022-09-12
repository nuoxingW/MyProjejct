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

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.omg.PortableInterceptor.INACTIVE;
import org.w3c.dom.NodeList;

public class Methods {
   	static Solution solution=new Solution();
   	static Problems  problem =new Problems();
	//从每个个簇中选择一个顶点
	  public  Solution  Choose(int numOfCluster,HashMap map,double[][] matrix) throws FileNotFoundException, IOException{
        String KeyFB2 = null;
        String keyFB = null;
        String nodeString ="";//记录只有一个顶点的簇
 	    double  distance=Integer.MAX_VALUE;
 	    double distance1=0;
 	    double length = 0;
		for(int i=0;i<numOfCluster;i++){		 
		String nodeList[]=ChooseNode(solution.Get_Cluster(i));
 		//System.out.println(nodeList+"nodelist");
 		 int start=ChooseNodeofCluster(solution.Get_Cluster(i), 0);
 		  String key=getKeysByLoop(map, start).toString().trim().replace("[", "");
 		   key =key.replace("]", "");
 		   if(nodeList.length==0){
 			 //  System.out.println(nodeList);
 			    nodeString+=","+i;
 			    System.out.println("该簇只有一个顶点");
 		   }else{
 	 			for(int J=0;J<nodeList.length+1;J++){
 	 				//	System.out.println(i);
 	 				//	System.out.println(J);
 	 				  int end=ChooseNodeofCluster(solution.Get_Cluster(i), J);
 	 				  String key1=getKeysByLoop(map, end).toString().trim().replace("[", "");
 	 				  key1=key1.trim().replace("]", "");
 	 	             double max=	 solution.getDistance(Integer.parseInt(key.trim()),Integer.parseInt(key1.trim()),map,matrix,false);
 	 	               if(max<distance){
 	 	               	 KeyFB2= key1;
 	 	               	  keyFB= key;	 
 	 	               }
 	 	               if(J == nodeList.length){
 	 	            	max= solution.getDistance(Integer.parseInt(keyFB.trim()),Integer.parseInt(KeyFB2.trim()),map,matrix,true); 
 	 	            	length+=max;
 	 	               }
 	 				}  
 		   }
		}
		   System.out.println(matrix.length+"problem.getNodeNum()"); 
		    String[] oneNodearr = nodeString.trim().split(",");  
		  for (int j = 0; j < oneNodearr.length; j++) {
			  System.out.println(oneNodearr[j]);
			  if (!oneNodearr[j].equals("")) {
					  int node= Integer.valueOf(Integer.valueOf(oneNodearr[j])); 
					 //  distance= solution.getOneDistance(node, map, matrix);
					  distance1+=solution.getOneDistance(node, map, matrix);
		    	}
			}
		System.out.println(distance1);
		System.out.println(length);
		return null;  
	  }
	  public  static String[] ChooseNode(String nodeNumString){
		   Random random = new Random();
		   String[] nodeList = nodeNumString.trim().split(","); 
		   int num = random.nextInt(nodeList.length) ;
		   //System.out.println(num);
 		   String Node=nodeList[num];
 		   //System.out.println(Node);
		   nodeList= remove(nodeList, Node);
	       return nodeList;
	  }
	  public  static int ChooseNodeofCluster(String nodeNumString,int num){
		   String[] nodeList = nodeNumString.trim().split(","); 
		//   System.out.println(nodeList[num]);
           String Node=nodeList[num];
           //System.out.println(Node);
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