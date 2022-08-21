package Myproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Solution implements  Comparable<Solution> {
	public static boolean withPrice;
	Problems problem=new Problems();
	protected String fileName="D:\\23GR229.GTP";
	public Object Get_Vex() throws FileNotFoundException, IOException{
		problem.read(fileName);
		return problem.getPoints();
	}
	//矩阵获得某两点之间的距离
	public double Get_arc(int i,int j) throws FileNotFoundException, IOException{
		problem.read(fileName);
		return problem.getDists(i, j);
	}
	//一个簇所包含的节点号
	public String Get_Cluster(int i) throws FileNotFoundException, IOException{
		problem.read(fileName);
	    String arr=Getarr(problem.getClusters(), i);
		return arr;
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
	 public static void main(String[] args) throws FileNotFoundException, IOException {
		Solution s=new Solution();
	    Problems problem =new Problems();
	    problem.read("D:\\23GR229.GTP");
        int xy=s.getPrizeLength();
        System.out.println(xy);
  }
 
 

	@Override
	public int  compareTo(Solution s) {
          return 1;
	}


}
