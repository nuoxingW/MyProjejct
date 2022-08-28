package Myproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

 
 

public class Solution implements  Comparable<Solution> {
	public static boolean withPrice;
	private int[] tops;   // 顶点集合
	static Problems problem=new Problems();
    private static  double[][] matrix;    /**边集数组*/
  //  private List<Edge> edgeList = new ArrayList<Edge>();     //边集数组*
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
     public void FindminTree(int nodeNum) throws FileNotFoundException, IOException {
         int index = 0;   
         int[] tends = new int[nodeNum*nodeNum];     // 用于保存"已有最小生成树"中每个顶点在该最小树中的终点。
         EData[] rets = new EData[nodeNum*nodeNum];   
         EData[] edges;                      // 图对应的所有边
         // 获取"图中所有的边"
         edges = getEdges(nodeNum);
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
                 System.out.println(edges1[i].weight+","+edges1[i].start+","+edges1[i].end);
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
     private int getPosition(int ch) {
    	// System.out.println(ch);
         for(int i=0; i<tops.length; i++){
             if(tops[i]==ch){
                 return i;
             }
         }
         return -1;
     }
     private EData[] getEdges(int nodeNum) throws FileNotFoundException, IOException {
         int index=0;
         EData[] edges;
         int num=nodeNum*nodeNum;
         edges = new EData[num];
         for (int i=0; i < tops.length; i++) {
             for (int j=0; j < tops.length; j++) {
              //   if (matrix[i][j]!=Integer.MAX_VALUE) {
                	 //System.out.println(tops[i]+","+tops[j]);
                     edges[index++] = new EData(tops[i], tops[j], matrix[i][j]);
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
     public static void main(String[] args) throws FileNotFoundException, IOException {
            Solution s=new Solution();
            problem.read(fileName);
            int num=problem.getNodeNum();
             s.createEdge(num);
             s.FindminTree(num);
        
        

   }
}
