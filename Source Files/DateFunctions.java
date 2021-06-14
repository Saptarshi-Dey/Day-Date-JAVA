import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;

public class DateFunctions
{
   public static String array[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
   private static String days[]={"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
   private static int codes[]={0,3,3,6,1,4,6,2,5,0,3,5};
   private static int leap_codes[]={6,2,3,6,1,4,6,2,5,0,3,5};
   
   public static boolean IsLeapYear(int year) {
		if(year%100==0) {
			if(year%400==0) return true;
			else return false;
		}
		else if(year%4==0) return true;
		else return false;
   }
   
   public static String getCurrentDate(){
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
       LocalDateTime now = LocalDateTime.now();  
       return dtf.format(now);
   }
   
   private static int CentCODE(int year){
     int arr[]={6,4,2,0};
     int a=year/100;
     return arr[a%4];
   }
   
   public static String GetDay(DateClass d){
       int day=d.dd;
       int month=d.MM;
       int year=d.yyyy;
       int index=0,temp=year%100;
       if(IsLeapYear(year)) index=day+leap_codes[month-1]+CentCODE(year)+temp+(temp/4)+1;
       else index=day+codes[month-1]+CentCODE(year)+temp+(temp/4)+1;
       return days[index%7];
   } 
   
}