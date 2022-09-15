package Myproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
	protected static String fileName="D:\\23GR229.GTP";
   	static double  minLength=Integer.MAX_VALUE;//����һ�����ŵ����·��
    static int Temperature=50000; //�˻��ʼ�¶�
    static double  q = 0.95;
    static int  L = 1000; //һ���¶ȵ����Ĵ���
    static double e=Math.E;
    static double T_end  =Math.pow(e, -8);
    static double prob = 0;
    static int  times = 400;
    static String  Tpos[];
    private static double findOptimaltree() throws FileNotFoundException, IOException{
        int num=problem.getClustersLength();
        Tpos = new String[num];
        Tpos=null;
        Random random =new Random();
        double Ntemperature=Temperature;
        HashMap map=solution.getSolution(null);
        while(Ntemperature>T_end){
        //for���ﻹҪ��forѭ��
      
         for (int j = 0; j <10000; j++) {
        	 //System.out.println(map.get("length").toString());;
             Double length=Double.valueOf(map.get("length").toString());
              if (Tpos!=null) {
            	  int i=random.nextInt(num);
            	  int b=random.nextInt(num);
                	 ArrayList <String> temp=solution.findSwap(i, b, Tpos);
                    map=  solution.getSolution(temp);
                    length=Double.valueOf(map.get("length").toString());
//                    System.out.println(Math.exp(length-minLength)/Ntemperature);
                    prob=random.nextDouble();
	               if(length<minLength){
	            	 System.out.println("��ǰ����С��֮ǰ���Ž⣬ֱ�Ӹ���Ϊ�½�,֮ǰ����Ϊ"+minLength+"���º󳤶�Ϊ"+length);
	                minLength=length;	
	          	    Tpos  = map.get("arr").toString().substring(1,map.get("arr").toString().length()-1).trim().split(",");
	               }else if(length>minLength&&prob<1/Math.exp(length-minLength)/Ntemperature){
		            System.out.println("��ǰ���ȴ���֮ǰ���Ž⣬��һ�����ʸ���Ϊ�½�,֮ǰ����Ϊ"+minLength+",���º󳤶�Ϊ"+length);
	                minLength=length;
	  	           Tpos  = map.get("arr").toString().substring(1,map.get("arr").toString().length()-1).trim().split(",");
	              }
  	         	Ntemperature*=q;	
		      }else{
		    	    minLength=length;	
	                Tpos  = map.get("arr").toString().substring(1,map.get("arr").toString().length()-1).split(",");
 	                 //System.out.println(Tpos);
  	            	 Ntemperature*=q;	  
		      }
          }
    	}
        System.out.println(Ntemperature);
        System.out.println(minLength);
        System.out.println(Tpos);
     	return minLength;
    }

    
    
   public static void main(String[] args) throws FileNotFoundException, IOException {
/*        Random random =new Random();
         for (int i = 0; i <100; i++) {
            System.out.println(random.nextInt(13));

		}*/
       problem.read(fileName);
      Methods.findOptimaltree();
  	    
       
  	   //System.out.println(a);

      // int num=problem.getClustersLength();
      // System.out.println(num); 

	 /*  Solution s=new Solution();
	   Methods method=new Methods();
	  System.out.println(method.findOptimaltree()+"��������"); */
   }
}