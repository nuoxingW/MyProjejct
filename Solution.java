package Myproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import javax.tools.DocumentationTool.DocumentationTask;

 
 

public class Solution implements  Comparable<Solution> {
	public static boolean withPrice;
	private int[] tops;   // 顶点集合
	static Problems problem=new Problems();
    private static  double[][] matrix;    /**边集数组*/
    private ArrayList<EData> list=new ArrayList<>();
	protected static String fileName="D:\\23GR229.GTP";
  //边的结构体
    private static class EData implements  Comparable<EData> {
        int start; // 边的起点
        int end;   // 边的终点
        double weight; // 边的权重
 
        public EData(int tops, int tops2, double matrix) {
            this.start = tops;
            this.end = tops2;
            this.weight = matrix;
        }
        public int compareTo(EData o) {
            return (int) (this.weight - o.weight);
        }
    };
    
	//矩阵获得某两点之间的距离
	private static double Get_arc(int i,int j) throws FileNotFoundException, IOException{
 		return problem.getDists(i, j);
	}
	//某个簇集群包含的节点号
	public  String Getarr(String arrString,int i) throws FileNotFoundException, IOException{ 
	    String[]  arr=problem.getClusters().split("],");
		   for(int j=0 ; j<arr.length;j++){
				  arr[j]= arr[j].replace("[", "");
				  arr[j]= arr[j].replace("]", "");
			   }
		return arr[i];
	}
	// 节点号 所对应的X,Y坐标
	public  String  getNodexy(int i) throws FileNotFoundException, IOException{
		 ArrayList<Point> array=new ArrayList<>();
	     array=problem.getPoints();
	     Point piont=array.get(i);
	    // System.out.println("节点号："+piont.getNum()+"X坐标："+piont.getX()+"Y坐标："+piont.getY());
		return  piont.getX()+","+piont.getY();
		
	}
	@Override
	public int  compareTo(Solution s) {
          return 1;
	}
	 // 初始化"顶点数"和"边数"
     private void createEdge(int index) throws FileNotFoundException, IOException {
     	tops= new int[index]; 
        for(int i=0;i<index;i++){
             tops[i]= problem.getVex(i);  
        }
        matrix = new double[index][index];
        for(int i=0;i<index;i++){
            for(int j=0;j<index;j++){
            	matrix[i][j]=Get_arc(i, j);
            	//System.out.println(matrix[i][j]+"Weight");
              }
         }
       // System.out.println(matrix);
    }
   
     private int getPosition(int ch) {
    	// System.out.println(ch);
         for(int i=0; i<tops.length; i++){
             if(tops[i]==ch){
                 return i;
             }
         }
         return -1;
     }
     private EData[] getEdges(int nodeNum,HashMap map,int a[],int b[]) throws FileNotFoundException, IOException {
         int index=0;
         EData[] edges;
         int num=nodeNum*nodeNum;
         edges = new EData[num];
         for (int i=0; i < a.length; i++) {
             for (int j=0; j < a.length; j++) {
              //   if (matrix[i][j]!=Integer.MAX_VALUE) {
                	 //System.out.println(tops[i]+","+tops[j]);
            	 //System.out.println(a[i]+"|"+a[j]+"|");
            	// System.out.println(Integer.valueOf(map.get(b[i]).toString()));
                     edges[index++] = new EData(a[i], a[j], matrix[Integer.valueOf(map.get(b[i]).toString())][Integer.valueOf(map.get(b[j]).toString())]);
                //  }
             }
         }
  
         return edges;
     }
     private ArrayList<EData> sortEdges(EData[] edges, int elen) {
    	// List<Object> list=new ArrayList<>();
    	 double  [] weight = new  double [elen];
    	 //	 System.out.println("下一行");
     	 for(int i=0;i<elen;i++){
     		 //System.out.println(i);
     		weight[i]=edges[i].weight;
     		list.add(edges[i]);
     	 // System.out.print(weight[i]+" ");
    	 }
     	 //排序
         Collections.sort(list);
     	 return list; 
     	 
     }
     private int getEnd(int[] tends, int i) {
         while (tends[i] != 0){
             i = tends[i];
         }
         return i;
     }
     //其中每个簇包含的节点
    public  String Get_Cluster(int i) throws FileNotFoundException, IOException{
     //	System.out.println(i);
	    String arr=Getarr(problem.getClusters(), i);
	  //  System.out.println(arr);
		return arr;
	}
    //确定起点和终点的距离
    public   double   getDistance(int Start ,int end,HashMap map,double[] []matrix,boolean flag){
     	//System.out.println(Start);
    	String Start1=Start+"";
    	String node= map.get(Start1).toString();
      //  System.out.println(node+"NODE");
        int Num=Integer.parseInt(node); 
    	String end1=end+"";
         String node1= map.get(end1).toString();
       // int Num1=Integer.valueOf(node1.toString());
        int Num1=Integer.parseInt(node1); 
        if(flag== true){
   	      System.out.println((Integer.valueOf(node)+1)+"------>"+(Num1+1)+"");

        }
        //System.out.println(matrix[Num][Num1]);
        return  matrix[Num][Num1];
    }
    //确定一个顶点遍历与它相连的顶点
    public   double   getOneDistance(int Start ,HashMap map,double[] []matrix){
     	//System.out.println(Start);
    	String Start1=Start+"";
    	String node= map.get(Start1).toString();
        double  distance=0;
        double  distance1=Integer.MAX_VALUE;
      //  System.out.println(node+"NODE");
        int Num=(Integer.parseInt(node)-1); 
         for (int i = 0; i < matrix.length; i++) {
        	 distance= matrix[Num][i];
        	 if(distance<distance1){
        	    distance1=distance;	 
        	 }
		}
        return  distance1;
    }
    //从中获得节点号
    public  int   getNode(int nodeNum){
    	  Map map= problem.getNodemap(); 
          Object node= map.get(nodeNum);
          int Num=Integer.valueOf(node.toString());
          return  Num;   	
    }
    //将字符串转为簇的数组
	  public  static String[] ChooseNode(String nodeNumString){
		   String[] nodeList = nodeNumString.trim().split(",");  
	       return nodeList;
	  }
    public  ArrayList<Integer>  Choose(int numOfCluster,double[][] matrix) throws FileNotFoundException, IOException{
 	    ArrayList<Integer> arr=new ArrayList<>();//创建一个容器的存放每个簇中的一个顶点
		for(int i=0;i<numOfCluster;i++){		 
	  	String nodeList[]=ChooseNode(Get_Cluster(i));
		Random random = new Random();
 	    int num = random.nextInt(nodeList.length) ;
 	    //System.out.println(nodeList.length);
 	    //System.out.println(num+"num");
 		int start=ChooseNodeofCluster(Get_Cluster(i), num);
 		arr.add(start);
		}
		//System.out.println(arr.size());
           return arr;
	  }
	  //从每个簇中随机选择一个顶点
	  public  static int ChooseNodeofCluster(String nodeNumString,int num){
		   String[] nodeList = nodeNumString.trim().split(","); 
           String Node=nodeList[num];
           int Node1=Integer.valueOf(Node.trim());
	       return Node1;
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
     //节点号与距离数组中的对应关系
     public  HashMap  getMap(ArrayList arr,HashMap map){
      	 HashMap map1=new HashMap();
         for(int i=0;i<arr.size();i++){
        	String value= getKeysByLoop(map, arr.get(i)).toString().trim().replace("[", "").replace("]", "");
        	map1.put(arr.get(i), value);
         }
    	 return map1;	
     }
     
     //生成最小广义生成树的一个解     map是对应关系的map key是素组对应 value是节点号  map1
     public void  getSolution(HashMap<Integer, String> map,int num) throws FileNotFoundException, IOException{    
    	 int [] a = null;
    	 int [] b = null;
    	 int FB = 0;
    	 int FB2 = 0;
    	 int  count=0;
         double minDistance=Integer.MAX_VALUE;
    	 double distance=0;
    	 a= new int[num]; 
    	 b= new int[num]; 
    	 Set keys = new HashSet();
		   for (Entry entry : map.entrySet()) {
			//   System.out.println(entry.getValue().toString());
			   String temp=entry.getValue().toString();
		     a[count]=	 Integer.valueOf(temp.trim().toString()); //节点
		     b[count]=	 Integer.valueOf(entry.getKey().toString()); //节点

              count++;	 
		    }
/*		 for(int i=0;i<a.length;i++){
			 for(int j=0;j<a.length;j++){
			      distance= matrix[i][j];
			     if(distance<minDistance){
			    	 FB=i;
			    	 FB2=j;
			    	 minDistance=distance;
			     }
			     if(j==a.length){	  
			   	      System.out.println(map.get(i).toString()+"------>"+map.get(j).toString()+"");	 
			     }
			 }
		 }*/
		   FindminTree(count, map, a,b);
     }
     public void FindminTree(int nodeNum,HashMap map,int a[],int b[]) throws FileNotFoundException, IOException {
         int index = 0;   
         int[] tends = new int[nodeNum*nodeNum];     // 用于保存"已有最小生成树"中每个顶点在该最小树中的终点。
         EData[] rets = new EData[nodeNum*nodeNum];   
         EData[] edges;                      // 图对应的所有边
         // 获取"图中所有的边"
         edges = getEdges(nodeNum, map, a,b);
         // 将边按照"权"的大小进行排序(从小到大)
          EData[] edges1 = new EData[nodeNum*nodeNum];   
          sortEdges(edges, nodeNum*nodeNum);
          //System.out.println(list.get(1).end+","+list.get(1).weight);
          for(int i=0;i<nodeNum*nodeNum;i++){
        	edges1[i]=list.get(i);
          }
        
         for (int i=0; i<nodeNum*nodeNum ; i++) {
        //	 System.out.println(edges[i].start+","+edges[i].end);
             int p1 = getPosition(edges1[i].start);      // 获取第i条边的"起点"的序号
             int p2 = getPosition(edges1[i].end);        // 获取第i条边的"终点"的序号
             int m = getEnd(tends, p1);                 // 获取p1在"已有的最小生成树"中的终点
             int n = getEnd(tends, p2);                 // 获取p2在"已有的最小生成树"中的终点
             // 如果m!=n，意味着"边i"与"已经添加到最小生成树中的顶点"没有形成环路
             if (m != n) {//没有形成环路
                 // 设置m就是在"已有的最小生成树"中终点集合的索引，它的值就是终点n，
                 //例如 C D线段 ,m的值就是C在tends集合中的索引位置，n就是D在tends集合中m索引位置的值
                 tends[m] = n;
                 rets[index++] = edges1[i];   
                 System.out.println(edges1[i].start+","+edges1[i].end);
             }
         }
         int length = 0;
         for (int i = 0; i < index; i++){
             length += rets[i].weight;
         }
         //System.out.print(length);
/*         for (int i = 0; i < index; i++){
          System.out.print(rets[i].start+","+rets[i].end);
            
         }*/
         System.out.printf("\n"); 
         System.out.println("结束");
         System.out.printf("\n"); 
     }
     public static void main(String[] args) throws FileNotFoundException, IOException {
            Solution s=new Solution();
          //  Methods method=new Methods();
            problem.read(fileName);
            HashMap  map= problem.getNodemap();
            int num=  problem.getNumOfCluster();
            int num1= problem.getNodeNum();
            s.createEdge(num1);
            ArrayList arr=  s.Choose(num,matrix); 
            System.out.println(arr);
            HashMap map1= s.getMap(arr, map);
            System.out.println(map1);
            s.getSolution(map1,num);
   } 
   
}
