package Myproject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

import javax.swing.plaf.basic.BasicBorders.MarginBorder;
import javax.swing.text.AbstractDocument.LeafElement;
import javax.tools.DocumentationTool.DocumentationTask;

 
 

public class Solution implements  Comparable<Solution> {
	protected static     boolean withPrice = false;
	private  static int[] tops;   // 顶点集合
	static Problems problem=new Problems();
    private  static double[][] matrix;   /**边集数组*/
    private  static ArrayList<EData> list=new ArrayList<>();
 	public static  String[] Path;
   //边的结构体
    private  static  class EData implements  Comparable<EData> {
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
    public static double[][] getMatrix() {	
		return matrix;
	}
	//矩阵获得某两点之间的距离
	private static  double Get_arc(int i,int j) throws FileNotFoundException, IOException{
 		return problem.getDists(i, j);
	}
	//某个簇集群包含的节点号
	private static    String Getarr(String arrString,int i) throws FileNotFoundException, IOException{ 
	    String[]  arr=problem.getClusters().split("],");
		   for(int j=0 ; j<arr.length;j++){
				  arr[j]= arr[j].replace("[", "");
				  arr[j]= arr[j].replace("]", "");
			   }
		return arr[i];
	}
	// 节点号 所对应的X,Y坐标
	private  static   String  getNodexy(int i) throws FileNotFoundException, IOException{
		 ArrayList<Point> array = new ArrayList<>();
	     array = problem.getPoints();
	     Point piont = array.get(i);
 		 return  piont.getX()+ "\t" + piont.getY()+  "\t0\t0\n";
	}
	private  static   double  getNodex(int i) throws FileNotFoundException, IOException{
		 ArrayList<Point> array = new ArrayList<>();
	     array=problem.getPoints();
	     Point piont = array.get(i);
		 return  piont.getX();
	}
	private  static   double  getNodey(int i) throws FileNotFoundException, IOException{
		 ArrayList<Point> array = new ArrayList<>();
	     array = problem.getPoints();
	     Point piont = array.get(i);
		 return  piont.getY();
	}
	@Override
	public int  compareTo(Solution s) {
          return 1;
	}
	 // 初始化"顶点数"和"边数"
     private static void createEdge(int index,Problems pro) throws FileNotFoundException, IOException {
    	 tops= new int[index]; 
         for(int i=0;i<index;i++){
               tops[i]= pro.getVex(i);  
         }
         matrix = new double[index][index];
         for(int i=0;i<index;i++){
        	 for(int j=0;j<index;j++){
            	matrix[i][j]=Get_arc(i, j);
              }
         }
     }
	 // 初始化"顶点数"和"边数"
     private  static void createEdge(int index) throws FileNotFoundException, IOException {
      	 tops= new int[index]; 
         for(int i=0;i<index;i++){
              tops[i]= problem.getVex(i);  
         }
        matrix = new double[index][index];
        for(int i=0;i<index;i++){
            for(int j=0;j<index;j++){
            	matrix[i][j]=Get_arc(i, j);
            }
         }
     }
     private  int getPosition(int ch) {
    	 for(int i=0; i<tops.length; i++){
             if(tops[i]==ch){
                 return i;
             }
         }
         return -1;
     }
     //获取加入的边
     private  static EData[] getEdges(int nodeNum,ArrayList arr) throws FileNotFoundException, IOException {
         int index=0;
         EData[] edges;
         int num=nodeNum*nodeNum;
         edges = new EData[num];
         for (int i=0; i < arr.size(); i++) {
             for (int j=0; j < arr.size(); j++) {
             	 edges[index++] = new EData(Integer.valueOf(arr.get(i).toString().trim()), Integer.valueOf(arr.get(j).toString().trim()), matrix[Integer.valueOf(arr.get(i).toString().trim())][Integer.valueOf(arr.get(j).toString().trim())]);
             }
         }
          return edges;
     }
     private static ArrayList<EData> sortEdges(EData[] edges, int elen) {
     	 double  [] weight = new  double [elen];
     	 list.clear();
     	 for(int i=0;i<elen;i++) {
     		 weight[i]=edges[i].weight;
     		 list.add(edges[i]);
     	 }
      	 //排序
         Collections.sort(list);
     	 return list; 
     	 
     }
     private    int getEnd(int[] tends, int i) {
         while (tends[i] != 0){
             i = tends[i];
         }
         return i;
     }
     //其中每个簇包含的节点
    public   String Get_Cluster(int i) throws FileNotFoundException, IOException{
 	    String arr=Getarr(problem.getClusters(), i);
 		return arr;
	}
    //从中获得节点号
/*    private     int   getNode(int nodeNum){
    	Map map= problem.getNodemap(); 
        Object node= map.get(nodeNum);
        int Num=Integer.valueOf(node.toString());
        return  Num;   	
    }*/
    //将字符串转为簇的数组
	 private      String[] ChooseNode(String nodeNumString){
		String[] nodeList = nodeNumString.trim().split(",");  
	    return nodeList;
	  }
     public     ArrayList<Integer>  Choose(int numOfCluster,double[][] matrix) throws FileNotFoundException, IOException{
    	ArrayList<Integer> arr=new ArrayList<>();//创建一个容器的存放每个簇中的一个顶点
		for(int i=0;i<numOfCluster;i++){		 
	  	String nodeList[]=ChooseNode(Get_Cluster(i));
		Random random = new Random();
 	    int num = random.nextInt(nodeList.length) ;
  		int start=ChooseNodeofCluster(Get_Cluster(i), num);
 		arr.add(start);
		}
           return arr;
	  }
	  //从每个簇中随机选择一个顶点
	 private     int ChooseNodeofCluster(String nodeNumString,int num){
		 String[] nodeList = nodeNumString.trim().split(","); 
         String Node=nodeList[num];
         int Node1=Integer.valueOf(Node.trim());
	     return Node1;
	  }
     private  <K, V> Set<K> getKeysByLoop(HashMap<Integer, String> map, V value) {
		 Set keys = new HashSet<>();
		 for (Entry entry : map.entrySet()) {
		        if (entry.getValue().equals(value.toString().trim())) {
		            keys.add(entry.getKey());
		        }
		  }
	 	    return keys;
	 }
     //节点号与距离数组中的对应关系
/*     public      HashMap  getMap(ArrayList arr,HashMap map){
      	 HashMap map1=new HashMap();
         for(int i=0;i<arr.size();i++){
        	String value= getKeysByLoop(map, arr.get(i)).toString().trim().replace("[", "").replace("]", "");
        	map1.put(arr.get(i).toString().trim(),value);
         }
     	 return map1;	 
     }*/
     private  double FindminTree(int nodeNum,ArrayList arr) throws FileNotFoundException, IOException {
         int index = 0;   
         int[] tends = new int[nodeNum*nodeNum];     // 用于保存"已有最小生成树"中每个顶点在该最小树中的终点。
         EData[] rets = new EData[nodeNum*nodeNum];   
         EData[] edges;                      // 图对应的所有边
         // 获取"图中所有的边"
         edges = getEdges(nodeNum,arr);
         
         // 将边按照"权"的大小进行排序(从小到大)
          EData[] edges1 = new EData[nodeNum*nodeNum];   
          sortEdges(edges, nodeNum*nodeNum);
          for(int i=0;i<nodeNum*nodeNum;i++){
        	edges1[i]=list.get(i);
          }
          int starts[]=new int[nodeNum]; 	
          int ends[]=new int[nodeNum];
          int q=0;
          //System.out.println(arr);
          sortEdges(edges1, nodeNum*nodeNum);
          //System.out.println(list);
          Path=null;
          Path= new String[nodeNum*nodeNum]; 
          for (int i=0; i<nodeNum*nodeNum ; i=i+2) {
        	  int  p1=edges1[i].start;
        	  int  p2=edges1[i].end;
              int m = getEnd(tends, p1);                 // 获取p1在"已有的最小生成树"中的终点
              int n = getEnd(tends, p2);                 // 获取p2在"已有的最小生成树"中的终点
             // 如果m!=n，意味着"边i"与"已经添加到最小生成树中的顶点"没有形成环路
             if (m != n) {//没有形成环路
                 // 设置m就是在" 已有的最小生成树"中终点集合的索引，它的值就是终点n，
                 //例如 C D线段 ,m的值就是C在tends集合中的索引位置，n就是D在tends集合中m索引位置的值
                 tends[m] = n;
                 rets[index++] = edges1[i];   
                 starts[q]=edges1[i].start;
                 ends[q]=edges1[i].end;
                 //System.out.println(Path);
               //  System.out.println(nodeNum);
                // System.out.println(q);
              //   System.out.println(q);
                 Path[q]=("("+starts[q]+","+ends[q]+")");
                 q++;
                 if(q==(nodeNum-2)){
                	 break;
                 }
             }
         }
         double length = 0;
         for (int i = 0; i < index; i++){
             length += rets[i].weight;
         }
         return length;
     }
     //生成最小广义生成树的一个解     map是对应关系的map key是素组对应 value是节点号  map1
     public  double  getSolution(int num,ArrayList arr) throws FileNotFoundException, IOException{    
		  double length = 0;		
  		  length = FindminTree(num,arr);   
		  return length;
     }
	public  HashMap getSolution(ArrayList arr) throws FileNotFoundException, IOException{
          int num=  problem.getNumOfCluster();
       //  HashMap  map= problem.getNodemap();
         int num1= problem.getNodeNum();
         createEdge(num1);
         if(arr==null){
                arr=  Choose(num,matrix);
         }
         double length = getSolution(num,arr);
         HashMap map2 = new HashMap();
         map2.put("length",length);  //返回本次长度
         map2.put("arr", arr);//返回本次选择的节点号
 	     String[] listPath=Path;
	     map2.put("list", listPath);//返回本次选择的节点号
         return map2;	 
     }
     //交换生成一个新解
     public ArrayList<String> findSwap(final int i,final int j,String [] Tpos) {
         //交换 
         String temp=Tpos[i];
  		 Tpos[i]=Tpos[j];
  		 Tpos[j]=temp;
         List<String> listA = Arrays.asList(Tpos);
         ArrayList<String> listB = new ArrayList<String>(listA);
         return listB;
      }
     public ArrayList  arrToarraylist(String [] Tpos){
    	  List<String> listA = Arrays.asList(Tpos);
          ArrayList<String> listB = new ArrayList<String>(listA);
          return listB; 
     }
     public ArrayList<String> findReverse(String[] Tpos){
    	  for(int i = 0;i < Tpos.length /2;i++){
        	    String temp = Tpos[i];
        	    Tpos[i] =Tpos[Tpos.length - i - 1];
        	    Tpos[Tpos.length - i -1] = temp;
          }   
          List<String> listA = Arrays.asList(Tpos);
          ArrayList<String> listB = new ArrayList<String>(listA);
    	 return listB;
     }
     //插入f'd'g'v'r
     public ArrayList<String> findInsert(String[] Tpos,int m){
    	 String[] TposCopy = new String[Tpos.length];// 定义一个新的数组，与原来的数组长度相同
     	 int length= Tpos.length;
          for (int i = 0; i < Tpos.length; i++) {
        	  TposCopy[i] = Tpos[i];
         }
         for (int i = 0; i < Tpos.length; i++) {
             int tmp = (i + m) % length;// 计算新的位置
             Tpos[tmp] = TposCopy[i];
         }
         List<String> listA = Arrays.asList(Tpos);
         ArrayList<String> listB = new ArrayList<String>(listA);
 	     return listB;
    }
     //返回一个簇中的节点号
     public    String  chooseNode(int ClusterNum) throws FileNotFoundException, IOException{
    	 String arr= Get_Cluster(ClusterNum);
    	 String[] cluster=arr.split(","); 
         Random random=new Random();
         int r=random.nextInt(cluster.length);
    	 return cluster[r];
     }
     
 	public void save(String fileName,int edgesLength,String[] minlist,double length) { 		
		try {
			PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
			int nodeNum = problem.getNodeNum();
			printWriter.write(nodeNum + "\t0\t0\t0\t0\n");
			printWriter.write(nodeNum + "\t0\t0\t0\t0\n");
			for (int i = 0; i < nodeNum; i++) {
				printWriter.write(i + "\t" + getNodexy(i));
			}
			//String map.get("list")
			//System.out.println(minlist.length);
		 	 for(int i=0;i<minlist.length;i++){
		  	 	 String arr[]= minlist[i].toString().replace("(", "").replace(")", "").split(",");			     
					 printWriter.write(getNodex(Integer.valueOf(arr[0])) + "\t" + getNodey(Integer.valueOf(arr[0])) + "\t");
					 printWriter.write(getNodex(Integer.valueOf(arr[1])) + "\t" + getNodey(Integer.valueOf(arr[1])) + "\t" + Get_arc(Integer.valueOf(arr[0]),Integer.valueOf(arr[1]) ) + "\n");
		 	 }
/*			for (int i = 0; i <edgesLength; i++) {
			//	printWriter.write(nodes[edges[i].p1].x + "\t" + nodes[edges[i].p1].y + "\t");
			//	printWriter.write(nodes[edges[i].p2].x + "\t" + nodes[edges[i].p2].y + "\t" + edges[i].dist + "\n");
			} */

		 	printWriter.write(length + "\t0\t0\t0\t0\n");

			printWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
 	
/*    public void kruskal() {
        //创建图和边集数组
     //   createGraph(9);
        //可以由图转出边集数组并按权从小到大排序，这里为了方便观察直接写出来了
     //   createEdages();
        
        //定义一个数组用来判断边与边是否形成环路
        int[] parent = new int[9];
        
        *//**权值总和*//*
        int sum = 0;
        
        int n, m;
        
        //遍历边
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge= edgeList.get(i);
            n = find(parent, edge.getBegin());
            m = find(parent, edge.getEnd());
            
            //说明形成了环路或者两个结点都在一棵树上
            //注：书上没有讲解为什么这种机制可以保证形成环路，思考了半天，百度了也没有什么好的答案，研究的时间不多，就暂时就放一放吧
            if (n != m) {
                parent[n] = m;
                System.out.println("(" + edge.getBegin() + "," + edge.getEnd() + ")" +edge.getWeight());
                
                sum += edge.getWeight();
            }
        }
        
        System.out.println("权值总和为：" + sum);
    }
 */
     public    void main(String[] args) throws FileNotFoundException, IOException {
     //    problem.read(fileName);
  	     //System.out.println(getNodexy(1)); 
  	     //System.out.println(getNodexy(0)); 
         //System.out.println(Get_arc(1, 0)) 
         ;
      /*    HashMap map= problem.getNodemap(); 
       	   System.out.println(map);
       	   int arr[] ={ 7,  5,  8,  84,  30,  179,  43,  48,  79,  117,  171,  131,  149,  156,  192,  199,  204,  215,  210,  217,  223,  225,  228};
       	   Arrays.sort(arr); //Collections.sort() for List,Vector
       	   String a=Arrays.toString(arr); //toString the List or Vector
       	   String ar[]=a.substring(1,a.length()-1).split(", ");
            List<String> listA = Arrays.asList(ar);
            ArrayList<String> listB = new ArrayList<String>(listA);
            HashMap map1= getMap(listB, map);
            System.out.println(map1); */
     }
  
     
}
