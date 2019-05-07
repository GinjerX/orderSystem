package com.ps.property;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Utils {
   public static int getInt(){
	   Scanner sc=new Scanner(System.in);
	   return sc.nextInt();
	   
   }
   public static  double getDouble(){
	   Scanner sc=new Scanner(System.in);
	   return sc.nextDouble();
	   
   }
   public static float getFloat(){
	   Scanner sc=new Scanner(System.in);
	   return sc.nextFloat();
   }
   public boolean getBoolean(){
	   Scanner sc=new Scanner(System.in);
	   return  sc.nextBoolean();
   }
   public byte getByte(){
	   Scanner sc=new Scanner(System.in);
	   return sc.nextByte();
   }
   public static String getString(){
	   Scanner sc=new Scanner(System.in);
	   String name = sc.nextLine();
	   return name;
   }
   public  int  getRandom(int i){
	   java.util.Random random=new java.util.Random();// ���������
	   int result=random.nextInt(i);// ��i[0,10)�����е�������ע�ⲻ����10
	   return result+1;   
   }
   /**
    * ��ȡ��ǰϵͳʱ��
    * @return
    */
   public  static  String getDate(){
	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	   return df.format(new Date());
   }
  
   /**
    * ��ȡ������
    * @return
    */
   public static   String getOrderId(){
	   SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	   return df.format(new Date())+"_A";
   }
   public static void main(String[] args) {
		  System.out.println(getTimeDifference("2017-07-12 10:10:10","2017-07-11 10:10:10"));
   }
   
   
   
   /**
	 * ��ȡ�������ڵ�ʱ���
	 * @param endDate  ����ʱ��
	 * @param currentDate   ��ǰʱ��
	 * @return
	 */
	public  static  long  getTimeDifference(String endDate ,String currentDate){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date end = df.parse(endDate);
		    Date current = df.parse(currentDate);
		    long diff = end.getTime() - current.getTime();
		    long days = diff / (1000 * 60 * 60 * 24);
		    return  days;
		} catch (Exception e) {
			System.out.println("��ʱ������"+e);
		}
		return 0;
	}

   
}
