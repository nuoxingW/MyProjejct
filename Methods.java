package Myproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
   	static double  α = 0.30; //参数  接受扰动Pertubation 1 概率
   	static  double  β = 0.40;
   	static double  γ = 0.45;
   	static double  δ = 0.15;
   	static int iter = 0;
    static int history = 0;
    static int seconds = 60000;//运行的秒数
 	protected static String fileName="C:\\Users\\Nuoxing.W\\Desktop\\gMST\\datas\\instances\\20KROA100.GTP";
   	static double minLength = Integer.MAX_VALUE;//定义一个最优的最短路径
    static String  Tpos[];
    static HashMap<String,String> InitialSoutionmap;//定义用来存放原始解的map
    static HashMap<String,String> InitialSoutionmap1;//定义用来存放原始解的map1
    static HashMap<String,String> InitialSoutionmap2;//定义用来存放原始解的map2\
    static List eliteListcooy = new ArrayList();
    static List eliteList  = new ArrayList();
    static int eliteListlength = 20;
    static int times;//循环次数
    public static void findOptimaltree() throws FileNotFoundException, IOException{
    	int flag=0;
      	int num = problem.getClustersLength(); //获得簇的数量
        Tpos = new String[num]; //存放选择顶点数组
        Tpos = null;//初始化
        InitialSoutionmap = solution.getSolution(null);          //初始化一个解      
        InitialSoutionmap2=InitialSoutionmap1=InitialSoutionmap;
        Object objectArray = InitialSoutionmap.get("arr"); 
        Tpos = objectArray.toString().replace("[","").replace("]","").trim().split(",");
        //System.out.println(InitialSoutionmap);
        long startMili = System.currentTimeMillis();
        while(1==1){
         	// System.out.println(InitialSoutionmap);
        	if(eliteList.size()>eliteListlength-1){
        		eliteList.remove(0);
                eliteList.add(InitialSoutionmap); //存放一个解和长度
        	}else{
                eliteList.add(InitialSoutionmap); //存放一个解和长度
        	}
         	Double s= random.nextDouble(); // 随机生成一个接受扰动的概率
         	int t =0;
            if(s>α){
                //Perturbation1
                times=random.nextInt(num);
                Tpos=  Perturbation1(Tpos,num);
                ArrayList list = solution.arrToarraylist(Tpos);
                InitialSoutionmap = solution.getSolution(list);
               // ArrayList list = solution.arrToarraylist(Tpos);
               // InitialSoutionmap = solution.getSolution(list,fileName);
            }else if(eliteList!=null){
            	  t = random.nextInt(eliteList.size());
              //for(t=0;t<eliteList.size();t++){
                    Map<String,String> map1 = (HashMap)eliteList.get(t);
                    objectArray = map1.get("arr"); 
                    Tpos = objectArray.toString().replace("[", "").replace("]", "").trim().split(",");
                    Tpos= Perturbation2(Tpos, num);
                    ArrayList list = solution.arrToarraylist(Tpos);
                    InitialSoutionmap= solution.getSolution(list);
                    eliteList.remove(t);
                    flag=1;
                     eliteList.add(t, InitialSoutionmap);
                
            //    }

            }
          
            	if(eliteList.size()>eliteListlength-1){
            		if(flag==1){
            			//eliteList.add(t, InitialSoutionmap);
            		}else{
            			eliteList.remove(0);
                        eliteList.add(InitialSoutionmap); //存放一个解和长度	
            		}
                    flag=0;
            	}else{
            		if(flag==1){
            		//	eliteList.add(t, InitialSoutionmap);
            		}else{
                        eliteList.add(InitialSoutionmap); //存放一个解和长度	
            		}
                    flag=0;	
            	}  	
           double c=random.nextDouble();
           if(c<β){
        	   InitialSoutionmap = proportionanlSelcetionLS1(num,eliteList); 
           }else if(β<c&&c<β+γ){
        	   InitialSoutionmap = proportionanlSelcetionLS2(num,eliteList); 
           }else{
        	   InitialSoutionmap = proportionanlSelcetionLS3(num,eliteList,null); 
           }
	       	if(eliteList.size() > eliteListlength-1){
	    		eliteList.remove(0);
	            eliteList.add(InitialSoutionmap); //存放一个解和长度

	    	}else{
	            eliteList.add(InitialSoutionmap); //存放一个解和长度
	    	}   
            improvedBestsolution(num,eliteList);
           if(history==100){
        	 int S= random.nextInt(eliteList.size());
        	 HashMap map3 = (HashMap)eliteList.get(S);
        	 InitialSoutionmap=proportionanlSelcetionLS2(num, eliteList);
         	 history=0;
           }
      
           if(iter%10000==0){
        	   eliteListcooy=eliteList;
         	   int cishu = eliteListcooy.size();
        	   for(int i=0;i<cishu;i++){
        		   InitialSoutionmap=(HashMap<String, String>) eliteListcooy.get(i);
        		   InitialSoutionmap=proportionanlSelcetionLS3(num,eliteList,InitialSoutionmap);
	               	if(eliteList.size()>eliteListlength-1){
	            		eliteList.remove(0);
	                    eliteList.add(InitialSoutionmap); //存放一个解和长度
	            	}else{
	                    eliteList.add(InitialSoutionmap); //存放一个解和长度
	            	}
         		   Object length = InitialSoutionmap1.get("length");
        		   double length1 = (double)length;
        		   Object length2 = InitialSoutionmap.get("length");
        		   double length3 = (double)length2;
        		   if(length1>length3){
        			   InitialSoutionmap1=InitialSoutionmap;
        		   }
        	   }
   	          Object length2 =InitialSoutionmap2.get("length");
		      double length1=(double)length2;
		      Object length3 =InitialSoutionmap1.get("length");
			  double length4=(double)length3;
        	  if(length1>length4){
        		   InitialSoutionmap2 = InitialSoutionmap1; 
        	   }
        		   InitialSoutionmap = InitialSoutionmap1=solution.getSolution(null);
               	if(eliteList.size()>eliteListlength-1){
            		eliteList.remove(0);
                    eliteList.add(InitialSoutionmap); //存放一个解和长度
            	}else{
                    eliteList.add(InitialSoutionmap); //存放一个解和长度
            	}   
            }
           iter=iter+1;
           long endMili=System.currentTimeMillis();//结束时间
           if((endMili-startMili)>(seconds)){
         	    break;
           }
        }
        System.out.println(eliteList);
        System.out.println(InitialSoutionmap);
        System.out.println(InitialSoutionmap1);
        System.out.println(InitialSoutionmap2);
    }
    private static void sortEliteList(List eliteList) {
        Collections.sort(eliteList, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer length1 = Integer.valueOf(o1.get("length").toString().hashCode()) ;//length1是从你list里面拿出来的一个
                Integer length2 = Integer.valueOf(o2.get("length").toString().hashCode()) ; //length2是从你list里面拿出来的第二个name
                return length1.compareTo(length2);
            }
        });
 	}
	//判断是否有这个元素 用于判断随机后是否该顶点已经存在
    public static boolean findIsExist(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }
    //Perturbation1 num=簇数  arr一个解
    private static String[] Perturbation1(String []arr,int num) throws FileNotFoundException, IOException{
   	 for(int i=0;i<times;i++){
            int clusterNum=random.nextInt(num);
             String newNode= solution.chooseNode(clusterNum);
             Boolean isExsist=findIsExist(Tpos, newNode);
             if(isExsist==false){
              arr[clusterNum]=newNode;
          	 //  break;
             }
          }
   	return arr;
   }
    //Perturbation2 num=簇数  arr一个解
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
   //list当前解集合 clusterNum总数
   private static HashMap proportionanlSelcetionLS1(int clusterNum,List eliteList) throws FileNotFoundException, IOException{
	   HashMap map=(HashMap) eliteList.get(eliteList.size()-1);
	   String[] arr=map.get("arr").toString().replace("[", "").replace("]", "").trim().split(",");
	   String arr1[]=arr.clone();
 	   double length=Double.valueOf(map.get("length").toString());
	   int chooseClusternum=random.nextInt(clusterNum);
	   String newarr = solution.Get_Cluster(chooseClusternum);
	   String newarr1[]=newarr.split(",");
 	   for(int i=0;i<newarr1.length;i++){
		   arr[chooseClusternum]=newarr1[i];
	       ArrayList list1= solution.arrToarraylist(arr);
	       map= solution.getSolution(list1);
    	   if(Double.valueOf(map.get("length").toString())<length){
	        	    return map;
	       }else{
	    	   arr=arr1.clone();
	       }
 	   }
	   for(int j=chooseClusternum;j<clusterNum;j++) {
		   newarr=solution.Get_Cluster(chooseClusternum);
		   newarr1=newarr.split(",");
		   for(int i=0;i<newarr1.length;i++){
			   arr[chooseClusternum]=newarr1[i];
 			   ArrayList list1= solution.arrToarraylist(arr);
			   map= solution.getSolution(list1);
			   Object length2 =map.get("length");
 			   double length1=(double)length2;
 			   if(length1<length){
			        return map;
			   }else{
				   arr=arr1.clone();
			   }
		   }   			  
	  }
	   return map;
   }
   private static HashMap<String, String> proportionanlSelcetionLS2(int clusterNum,List eliteList) throws FileNotFoundException, IOException{
	   HashMap map = (HashMap) eliteList.get(eliteList.size()-1);
	   HashMap map1=new HashMap<>();
 	   String[] arr = map.get("arr").toString().replace("]", "").replace("[", "").trim().split(",");
 	   String []arr1=arr.clone();
 	   map1=map;
	   int chooseClusternum=random.nextInt(clusterNum);
	   double length=Double.valueOf(map.get("length").toString());
	   int j=chooseClusternum;
 	   for(;j<clusterNum;j++) {
			   String newarr=solution.Get_Cluster(chooseClusternum);
			   String newarr1[]=newarr.split(",");
			   for(int i=0;i<newarr1.length;i++){
				   arr[j]=newarr1[i];
 			       ArrayList list1= solution.arrToarraylist(arr);
 			       map= solution.getSolution(list1);
			       if(Double.valueOf(map.get("length").toString())<length){
			    	   arr1=arr.clone();
		 			   length=Double.valueOf(map.get("length").toString());
		 			   map1=map;
  			       }else{
 			    	   arr=arr.clone();
 			       }
			   }
			    chooseClusternum++;
		  }
		return map1;
   }
   private static HashMap proportionanlSelcetionLS3(int clusterNum,List eliteList,HashMap InitialSoutionmap4) throws FileNotFoundException, IOException{
	   HashMap map1=new HashMap<>();
	   HashMap map=new HashMap<>();
	   if(InitialSoutionmap4==null){
		    map=(HashMap) eliteList.get(eliteList.size()-1);
	   }else{
		    map= InitialSoutionmap4;
	   }
		   String[] arr = map.get("arr").toString().replace("[", "").replace("]", "").trim().split(",");
		   String arr1[]=arr.clone();
	 	   double length=Double.valueOf(map.get("length").toString());
	 	   map1=map;
			  for(int j = 0;j<clusterNum;j++) {
				   String newarr=solution.Get_Cluster(j);
				   String newarr1[]=newarr.split(",");
				   for(int i=0;i<newarr1.length;i++){
					   arr[j]=newarr1[i];
	  			       ArrayList list1= solution.arrToarraylist(arr);
				       map= solution.getSolution(list1);
				       if(Double.valueOf(map.get("length").toString())<length){
		 			      arr1=arr.clone();     
		 			      length=Double.valueOf(map.get("length").toString());
		 			      map1=map;
				       }else{
				    	  arr=arr1.clone();
				       }
				   }   			  
			  }
			  return map1;	
   } 

   private static void improvedBestsolution(int clusterNum,List eliteList) throws FileNotFoundException, IOException{
 	   Object length= InitialSoutionmap1.get("length");
	   double length1=(double)length;
	   Object length2= InitialSoutionmap.get("length");
	   double length3=(double)length2;
	   if(length1>length3){
		   InitialSoutionmap=  proportionanlSelcetionLS3(clusterNum,eliteList,null); // 第二次进行ls3 
	       	if(eliteList.size()>eliteListlength-1){
	    		eliteList.remove(0);
                eliteList.add(InitialSoutionmap); //存放一个解和长度
	    	}else{
	            eliteList.add(InitialSoutionmap); //存放一个解和长度
	    	}    
  		   InitialSoutionmap1=InitialSoutionmap;
 	   }else{
		   history=history+1;
	   }  
   }
   
   
   //test
   public static void main(String[] args) throws FileNotFoundException, IOException {	 
  ///for(int j=0;j<20;j++){
 		 problem.read(fileName);
 		 System.out.println(fileName);
 	 	 findOptimaltree();
 	  	/* Object object= InitialSoutionmap1.get("list");
 	 	 String[] minlist=(String[])object;
 	 	 Object length= InitialSoutionmap1.get("length");
 	     double length1 = (double)length;
 	 	 Object object1= InitialSoutionmap2.get("list");
 	 	 String[] minlist1=(String[])object1;
 	 	 Double lengthZ1=0.0;
 	 	 for(int i=0;i<eliteListlength;i++){
 	 	 	 Object map = eliteList.get(i);
 	 		 if(map!=null){
 	 	 	      HashMap map1=(HashMap) map;
 	 	 	     Double lengthZ=  Double.valueOf(map1.get("length").toString());
 	 	 	    lengthZ1+=lengthZ; 
 	 		 }
 	 	 } */
   	   // System.out.println("当γ为:"+γ+"精英解中的平均长度:"+lengthZ1/eliteListlength);
/* 	     File file = new File("D:\\rpa\\rpa1\\*27PR264.GTP.txt");
 	        if(!file.exists()){
 	        file.getParentFile().mkdirs();
 	    }
 	        file.createNewFile();
 	    // write
 	    FileWriter fw = new FileWriter(file, true);
 	    BufferedWriter bw = new BufferedWriter(fw);
 	        bw.write(fileName+"\r\n");
 	    //    bw.write("当*为:"+γ+"精英解中的平均长度:"+lengthZ1/eliteListlength+"\r\n");
 	       bw.write("当*为:*精英解中的平均长度:"+lengthZ1/eliteListlength+"\r\n");
 	        bw.flush();
 	        bw.close();
 	        fw.close(); */
 	 
 	   //solution.save("D:\\lin318(2).txt", minlist.length*minlist.length, minlist,length1);
 	   //solution.save("D:\\lin318(1).txt", minlist.length*minlist.length, minlist1,length1);
 	   }	 
 //	 }

}