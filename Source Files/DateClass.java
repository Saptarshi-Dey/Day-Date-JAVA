
public class DateClass
{
    public static final int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int dd=0,MM=0,yyyy=0;
    public DateClass(int x,int y,int z){ dd=x; MM=y; yyyy=z;}
    
    private static int MOD(int x){
      if(x<0) return x*(-1);
      else return x;
    }
    
    private static long MOD2(long x){
        if(x<0) return x*(-1);
        else return x;
      }
    
    public boolean equals(DateClass d2){
    	if(this.dd == d2.dd && this.MM == d2.MM && this.yyyy == d2.yyyy) return true;
    	else return false;
    }
    public DateClass(String Raw_DATE){ 
      String x[]=Raw_DATE.split("/");
      dd=Integer.parseInt(x[0]);
      MM=Integer.parseInt(x[1]);
      yyyy=Integer.parseInt(x[2]);
    }
    
    static int countLeapYears(DateClass d)
    {
        int years = d.yyyy;
        if(d.MM <= 2) years--;
        return years / 4 - years / 100 + years / 400;
    }
    
    public static long getDifference(DateClass dt1, DateClass dt2)
    {
        long n1 = dt1.yyyy * 365 + dt1.dd;
        for (int i = 0; i < dt1.MM - 1; i++) n1 += monthDays[i];
        n1 += countLeapYears(dt1);
        
        long n2 = dt2.yyyy * 365 + dt2.dd;
        for (int i = 0; i < dt2.MM - 1; i++) n2 += monthDays[i];
        n2 += countLeapYears(dt2);
 
        return MOD2(n2 - n1);
    }
    
    private static DateClass[] Large_Small(DateClass dt1, DateClass dt2){
        long n1 = dt1.yyyy * 365 + dt1.dd;
        long n2 = dt2.yyyy * 365 + dt2.dd;
        DateClass d[]=new DateClass[2];
        
        for (int i = 0; i < dt1.MM - 1; i++) n1 += monthDays[i];
        n1 += countLeapYears(dt1);
            
        for (int i = 0; i < dt2.MM - 1; i++) n2 += monthDays[i];
        n2 += countLeapYears(dt2);
            
        if(n1>n2){d[0]=dt1; d[1]= dt2;}
        else if(n1<n2){d[1]=dt1; d[0]= dt2;}
        
        return d;
    }
    
    public static boolean before(DateClass dt1){
    	  DateClass d2=new DateClass(DateFunctions.getCurrentDate());
	   	  DateClass d[]=Large_Small(dt1,d2);
	   	  return d[0].equals(d2);
    }
    
    public static DateClass getDateDifference(DateClass dt1, DateClass dt2){
        int x=0,y=0,z=0;
        DateClass dt[]=Large_Small(dt1,dt2);
        int x1=dt[1].dd,y1=dt[1].MM,z1=dt[1].yyyy;
        int x2=dt[0].dd,y2=dt[0].MM,z2=dt[0].yyyy;
        if(x2<x1){
           if(y2==2 && z2%4==0) x2+=29;
           else if(y2>1) x2+=monthDays[y2-2]; 
           else if(y2==1) x2+=31;
           y2--;
        }
        if(y2<y1){ y2+=12; z2--;}
        x=x2-x1; y=y2-y1; z=MOD(z2-z1);
        DateClass d=new DateClass(x,y,z);
        return d;
    }
}