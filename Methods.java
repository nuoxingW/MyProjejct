package Myproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
    static  Random random =new Random();
   	static Solution solution=new Solution();
   	static Problems  problem =new Problems();
   	static double  ��=0.3; //����  �����Ŷ�Pertubation 1 ����
   	static double  ��=0.4;
   	static double  ��=0.45;
   	static double  ��=0.15;
   	static int iter=0;
    static int history=0;
	protected static String fileName="D:\\23GR229.GTP";
   	static double  minLength=Integer.MAX_VALUE;//����һ�����ŵ����·��
    static String  Tpos[];
    static HashMap<String,String> InitialSoutionmap;//�����������ԭʼ���map
    static HashMap<String,String> InitialSoutionmap1;//�����������ԭʼ���map1
    static HashMap<String,String> InitialSoutionmap2;//�����������ԭʼ���map2\
    static List<Map> eliteListcooy = new ArrayList();
    static List<Map>  eliteList = new ArrayList() ;  //��ž�Ӣ��
    static int times;//ѭ������
    private static double findOptimaltree() throws FileNotFoundException, IOException{
        int num=problem.getClustersLength(); //��ôص�����
        Tpos = new String[num]; //���ѡ�񶥵�����
        Tpos=null;//��ʼ��
        InitialSoutionmap=solution.getSolution(null);          //��ʼ��һ����      
        InitialSoutionmap2=InitialSoutionmap1=InitialSoutionmap;
         Object objectArray =InitialSoutionmap.get("arr"); 
         Tpos=objectArray.toString().replace("[", "").replace("]", "").split(",");
  /*       List<String> listA = Arrays.asList(Tpos);
         ArrayList<String> listB = new ArrayList<String>(listA);*/
         eliteList.add(InitialSoutionmap); //���һ����ͳ���
        while(1==1){
            Double s= random.nextDouble(); // �������һ�������Ŷ��ĸ���
            if(s<��){
                //Perturbation1
                times=random.nextInt(num);
                Tpos=  Perturbation1(Tpos,num);
                ArrayList list= solution.arrToarraylist(Tpos);
                InitialSoutionmap= solution.getSolution(list);
            }else if(eliteList!=null){
               int  t=random.nextInt(eliteList.size());
               Map<String,String> map1=(HashMap)eliteList.get(t);
               objectArray =map1.get("arr"); 
               Tpos=objectArray.toString().replace("[", "").replace("]", "").split(",");
               Tpos= Perturbation2(Tpos, num);
               ArrayList list= solution.arrToarraylist(Tpos);
               InitialSoutionmap= solution.getSolution(list);
            }
            eliteList.add(InitialSoutionmap);
           double c=random.nextDouble();
            // double c=0.1;
           if(c<��){
        	   InitialSoutionmap=proportionanlSelcetionLS1(num); 
           }else if(��<c&&c<��+��){
        	   InitialSoutionmap=proportionanlSelcetionLS2(num); 
           }else{
        	   InitialSoutionmap= proportionanlSelcetionLS3(num); 
           }
           eliteList.add(InitialSoutionmap);
           System.out.println("!1111111111111111");
           improvedBestsolution(num);
           System.out.println(history+"|||!!2222222");

           if(history==100){
        	 int t= random.nextInt(eliteList.size());
        	 HashMap map3=(HashMap)eliteList.get(t);
        	 InitialSoutionmap=proportionanlSelcetionLS2(num);
        	 history=0;
           }
           if(iter%10000==0){
               System.out.println("!3333333");
        	   eliteListcooy=eliteList;
        	   System.out.println(eliteListcooy.size()+"eliteList.size()");
        	   int cishu=eliteListcooy.size();
        	   for(int i=0;i<cishu;i++){
        		   InitialSoutionmap=(HashMap<String, String>) eliteListcooy.get(i);
        		   System.out.println(i+"iiiiiiiiiii");
                   System.out.println("!44444444444");

        		   InitialSoutionmap=proportionanlSelcetionLS3(num);
        		   eliteList.add(InitialSoutionmap);
        		      Object length= InitialSoutionmap1.get("length");
        		      double length1=(double)length;
        		      Object length2= InitialSoutionmap.get("length");
        		      double length3=(double)length2;
        		   if(length1>length3){
        			   InitialSoutionmap1=InitialSoutionmap;
        		   }
        	   }
   	          Object length2 =InitialSoutionmap2.get("length");
		      double length1=(double)length2;
		      Object length3 =InitialSoutionmap1.get("length");
			  double length4=(double)length3;
        	   if(length1>length4){
        		   InitialSoutionmap2=InitialSoutionmap1; 
        	   }
        		   InitialSoutionmap=InitialSoutionmap1=solution.getSolution(null);
        		   eliteList.add(InitialSoutionmap);
        		 
        		   System.out.println(eliteList);
           }
           iter=iter+1;
        }
    }
    //�ж��Ƿ������Ԫ�� �����ж�������Ƿ�ö����Ѿ�����
    public static boolean findIsExist(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }
    //Perturbation1 num=����  arrһ����
    private static String[] Perturbation1(String []arr,int num) throws FileNotFoundException, IOException{
    	 for(int i=0;i<times;i++){
             int clusterNum=random.nextInt(num);
              String newNode= solution.chooseNode(clusterNum);
              Boolean isExsist=findIsExist(Tpos, newNode);
              if(isExsist==false){
               arr[clusterNum]=newNode;
           	   break;
              }
           }
    	return arr;
    }
      //Perturbation1 num=����  arrһ����
   private static String[] Perturbation2(String []arr,int num) throws FileNotFoundException, IOException{
	   for(int i=0;i<0.1*num;i++){
		   int clusterNum=random.nextInt(num);    
           String newNode= solution.chooseNode(clusterNum);
           Boolean isExsist=findIsExist(Tpos, newNode);
           if(isExsist==false){
               arr[clusterNum]=newNode;
            }
        }
           
             return arr;
   }
   //list��ǰ�⼯�� clusterNum����
   private static HashMap proportionanlSelcetionLS1(int clusterNum) throws FileNotFoundException, IOException{
	//   boolean flag=false;
	   //int currentCluster=random.nextInt(clusterNum);
	   HashMap map=(HashMap) eliteList.get(0);
	   String[] arr=map.get("arr").toString().replace("[", "").replace("]", "").split(",");
	   double length=Double.valueOf(map.get("length").toString());
	   int chooseClusternum=random.nextInt(clusterNum);
	   String newarr=solution.Get_Cluster(chooseClusternum);
	   String newarr1[]=newarr.split(",");
	   for(int i=0;i<newarr1.length;i++){
		   arr[clusterNum-1]=newarr1[i];
	       //Tpos=arr.toString().replace("]", "").replace("[", "").split(",");
	       ArrayList list1= solution.arrToarraylist(arr);
	        map= solution.getSolution(list1);
	        if(Double.valueOf(map.get("length").toString())<length){
	        	return map;
	        	//eliteList.add(map);
	        	//flag=true;
	        //	break;
	        }
	   }
 
		  for(int j=chooseClusternum;j<clusterNum;j++) {
			    newarr=solution.Get_Cluster(chooseClusternum);
			    newarr1=newarr.split(",");
			   for(int i=0;i<newarr1.length;i++){
				   arr[clusterNum-1]=newarr1[i];
			     //  Tpos=arr.toString().split(",");
			       ArrayList list1= solution.arrToarraylist(arr);
			        map= solution.getSolution(list1);
			        Object length2 =map.get("length");
 				     double length1=(double)length2;
			        if(length1<length){
     		          //  eliteList.remove(1);
			        //	eliteList.add(map);   
			        	//flag=true;
			        	return map;

			        }
			   }   			  
		  }
 
	return map;
   }
   private static HashMap proportionanlSelcetionLS2(int clusterNum) throws FileNotFoundException, IOException{
	   HashMap map=(HashMap) eliteList.get(0);
	   String[] arr=map.get("arr").toString().replace("]", "").replace("[", "").split(",");
	   int chooseClusternum=random.nextInt(clusterNum);
	   double length=Double.valueOf(map.get("length").toString());
		  for(int j=chooseClusternum;j<clusterNum;j++) {
			   String newarr=solution.Get_Cluster(chooseClusternum);	   
			    String newarr1[]=newarr.split(",");
			   for(int i=0;i<newarr1.length;i++){
				   //System.out.println(newarr1.length);
				   arr[clusterNum-1]=newarr1[i];
			      // Tpos=arr.toString().split(",");
			       ArrayList list1= solution.arrToarraylist(arr);
			        map= solution.getSolution(list1);
			        
			        if(Double.valueOf(map.get("length").toString())<length){
			        	return map;
 			           // eliteList.add(1, map); 
			        }
			   }   			  
		  }
		return map;
    }
   private static HashMap NproportionanlSelcetionLS2(int clusterNum,HashMap map) throws FileNotFoundException, IOException{
 	   String[] arr=map.get("arr").toString().split(",");
	   int chooseClusternum=random.nextInt(clusterNum);
	   int length=Integer.valueOf(map.get("length").toString());
		  for(int j=chooseClusternum;j<clusterNum;j++) {
			   String newarr=solution.Get_Cluster(chooseClusternum);
			    String newarr1[]=newarr.split(",");
			   for(int i=0;i<newarr1.length;i++){
				   arr[clusterNum]=newarr1[i];
			       Tpos=arr.toString().split(",");
			       ArrayList list1= solution.arrToarraylist(Tpos);
			        map= solution.getSolution(list1);
			        if(Integer.valueOf(map.get("length").toString())<length){
 			            eliteList.add(1, map); 
			        }
			   }   			  
		  }
		  return map;
    }
   private static HashMap proportionanlSelcetionLS3(int clusterNum) throws FileNotFoundException, IOException{
	   HashMap map=(HashMap) eliteList.get(0);
	   String[] arr=map.get("arr").toString().replace("[", "").replace("]", "").split(",");
 	   double length=Double.valueOf(map.get("length").toString());
		  for(int j=0;j<clusterNum;j++) {
			   String newarr=solution.Get_Cluster(j);
			    String newarr1[]=newarr.split(",");
			   for(int i=0;i<newarr1.length;i++){
				   arr[j]=newarr1[i];
			      // Tpos=arr.toString().split(",");
			       ArrayList list1= solution.arrToarraylist(arr);
			        map= solution.getSolution(list1);
			        if(Double.valueOf(map.get("length").toString())<length){
	 			           return map; 			         
			        }
			   }   			  
		  }
		  return map; 	
   }
   private static void improvedBestsolution(int clusterNum) throws FileNotFoundException, IOException{
	      //System.out.println(InitialSoutionmap1);
	      Object length= InitialSoutionmap1.get("length");
	      double length1=(double)length;
	      Object length2= InitialSoutionmap.get("length");
	      double length3=(double)length2;
	   if(length1>length3){
		 InitialSoutionmap=  proportionanlSelcetionLS3(clusterNum); //type=1 �ڶ��ν���ls3 
		 eliteList.add(InitialSoutionmap);
		 InitialSoutionmap1=InitialSoutionmap;
	   }else{
		   history=history+1;
	   }
 
	   
   }
   public static void main(String[] args) throws FileNotFoundException, IOException {
	 problem.read(fileName);
	  // String newarr=solution.Get_Cluster(23);	   
 //   System.out.println(newarr);
	  findOptimaltree();
  	 //System.out.println(solution.Get_Cluster(2));
  	 

/*        Random random =new Random();
         for (int i = 0; i <100; i++) {
            System.out.println(random.nextInt(13));

		}*/
     //   problem.read(fileName);
     // Methods.findOptimaltree();
 	  // int t0=500;
  	  // System.out.println(t0*Math.pow(0.99,1000));  
       
  	   //System.out.println(a);

      // int num=problem.getClustersLength();
      // System.out.println(num); 
/*
     Solution s=new Solution();
	   Methods method=new Methods();
	   problem.read(fileName);
	  System.out.println(findOptimaltree()+"��������"); */
   }
}