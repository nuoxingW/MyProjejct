package Myproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
 

public class Solution implements  Comparable<Solution> {
	public static boolean withPrice;
	private static  int[] parent;//定义一个数组记录访问的边
	private static  String[] vex ;//记录每次选择的边
	private int[] tops;   // 顶点集合
	static Problems problem=new Problems();
    private static  double[][] matrix;    /**边集数组*/
  //  private List<Edge> edgeList = new ArrayList<Edge>();     //边集数组*

	protected static String fileName="D:\\23GR229.GTP";
	public Object Get_Vex() throws FileNotFoundException, IOException{
		problem.read(fileName);
		return problem.getPoints();
	}
	//矩阵获得某两点之间的距离
	private static double Get_arc(int i,int j) throws FileNotFoundException, IOException{
		problem.read(fileName);
 		return problem.getDists(i, j);
	}
	//一个簇所包含的节点号
	public String Get_Cluster(int i) throws FileNotFoundException, IOException{
		problem.read(fileName);
	    String arr=Getarr(problem.getClusters(), i);
		return arr;
	}
	//簇的个数
	public  int  get_ClsuserSize() throws FileNotFoundException, IOException{
		problem.read(fileName);
		int  clusterLength=problem.getClustersLength();
		return clusterLength;
	}
	//节点数
	public   int getNodenum() throws FileNotFoundException, IOException{ 
			problem.read(fileName);
	        return problem.getNodeNum();
	}
	//某个簇集群包含的节点号
	public  String Getarr(String arrString,int i) throws FileNotFoundException, IOException{ 
		problem.read(fileName);
	    String[]  arr=problem.getClusters().split("],");
		   for(int j=0 ; j<arr.length;j++){
				  arr[j]= arr[j].replace("[", "");
				  arr[j]= arr[j].replace("]", "");
			   }
		return arr[i];
	}
	// 节点号 所对应的X,Y坐标
	public  String  getNodexy(int i) throws FileNotFoundException, IOException{
		 problem.read(fileName);
		 ArrayList<Point> array=new ArrayList<>();
	     array=problem.getPoints();
	     Point piont=array.get(i);
	    // System.out.println("节点号："+piont.getNum()+"X坐标："+piont.getX()+"Y坐标："+piont.getY());
		return  piont.getX()+","+piont.getY();
		
	}
	//每个节点所对应的prize
	public  double  getPrize(int i) throws FileNotFoundException, IOException{
		 problem.read(fileName);
	     double prize=problem.getPrize(i);
	     return prize;
		
	}
	// prize[] 的长度
	public  int  getPrizeLength() throws FileNotFoundException, IOException{
		 problem.read(fileName);
	     int prizeLength=problem.getPrizeLength();
	     return prizeLength;
		
	}
	public int getVex(int i) throws FileNotFoundException, IOException{
		problem.read(fileName);
	   return  problem.getVex(i);
	}
	public int distsLength() throws FileNotFoundException, IOException{
		 problem.read(fileName);
	     int distsLength=problem.getDistsLength();
	     return distsLength;
	}
    public static void main(String[] args) throws FileNotFoundException, IOException {
           Solution s=new Solution();
           s.createEdge(s.getNodenum());
           s.kruskal();

  }
	@Override
	public int  compareTo(Solution s) {
          return 1;
	}
	 // 初始化"顶点数"和"边数"
     private void createEdge(int index) throws FileNotFoundException, IOException {
     	problem.read(fileName);
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
     
     
     public void kruskal() throws FileNotFoundException, IOException {
         int index = 0;                   
         int[] tends = new int[getNodenum()*getNodenum()];     // 用于保存"已有最小生成树"中每个顶点在该最小树中的终点。
         EData[] rets = new EData[getNodenum()*getNodenum()];   
         EData[] edges;                      // 图对应的所有边
         // 获取"图中所有的边"
         edges = getEdges();
         // 将边按照"权"的大小进行排序(从小到大)
         int num=getNodenum()*getNodenum();
         System.out.println(edges[0].weight);
         sortEdges(edges, num);
         for (int i=0; i<100; i++) {
             int p1 = getPosition(edges[i].start);      // 获取第i条边的"起点"的序号
             int p2 = getPosition(edges[i].end);        // 获取第i条边的"终点"的序号
  
             int m = getEnd(tends, p1);                 // 获取p1在"已有的最小生成树"中的终点
             int n = getEnd(tends, p2);                 // 获取p2在"已有的最小生成树"中的终点
  
  
             // 如果m!=n，意味着"边i"与"已经添加到最小生成树中的顶点"没有形成环路
             if (m != n) {//没有形成环路
                 // 设置m就是在"已有的最小生成树"中终点集合的索引，它的值就是终点n，
                 //例如 C D线段 ,m的值就是C在tends集合中的索引位置，n就是D在tends集合中m索引位置的值
                 tends[m] = n;
                 rets[index++] = edges[i];           // 保存结果，保存kruskal最小生成树的边
             }
         }
         int length = 0;
         for (int i = 0; i < index; i++){
             length += rets[i].weight;
         }
         System.out.printf("Kruskal=%d: ", length);
         for (int i = 0; i < index; i++){
             System.out.printf("(%c,%c) ", rets[i].start, rets[i].end);
         }
         System.out.printf("\n"); 
     }
     private int getPosition(int ch) {
         for(int i=0; i<tops.length; i++){
             if(tops[i]==ch){
                 return i;
             }
         }
         return -1;
     }
     //边的结构体
     private static class EData {
         int start; // 边的起点
         int end;   // 边的终点
         double weight; // 边的权重
  
         public EData(int tops, int tops2, double matrix) {
             this.start = tops;
             this.end = tops2;
             this.weight = matrix;
         }

 
     };
     private EData[] getEdges() throws FileNotFoundException, IOException {
         int index=0;
         EData[] edges;
          edges = new EData[getNodenum()*getNodenum()];
         for (int i=0; i < tops.length; i++) {
             for (int j=i+1; j < tops.length; j++) {
                 if (matrix[i][j]!=Integer.MAX_VALUE) {
                     edges[index++] = new EData(tops[i], tops[j], matrix[i][j]);
                  }
             }
         }
  
         return edges;
     }
     private void sortEdges(EData[] edges, int elen) {
         for (int i=0; i<elen; i++) {
             for (int j=i+1; j<elen; j++) {
            	// System.out.println(i);
            	 //System.out.println(edges[i].weight+"/"+edges[j].weight);
                  if (edges[i].weight > edges[j].weight) {
                     // 交换"边i"和"边j"
                     EData tmp = edges[i];
                     edges[i] = edges[j];
                     edges[j] = tmp;
                 }
             }
         }
     }
     private int getEnd(int[] tends, int i) {
         while (tends[i] != 0){
             i = tends[i];
         }
         return i;
     }
  

}
