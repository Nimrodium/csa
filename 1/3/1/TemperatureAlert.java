
import java.util.stream.IntStream;

/*
 * Temperature Alert System
 */
public class TemperatureAlert
{
    static void test_temp(int temperature){
    System.out.println(temperature);
    if (temperature == 32){
        // is freezing point
        System.out.println("temperature is at freezing point");
    }else if (temperature > 100){
        // temp above 100
        System.out.println("DANGER temperature is above 100 degrees f");
    }else if (temperature < 0){
        // temp below 0
        System.out.println("DANGER temperature is below 0 degrees f");
    }else if (temperature >= 50 && temperature <= 80){
        // temp 50<t<80
        System.out.println("temperature is safe (50f-80f)");
    }else{
        // temp not freezing
        System.out.println("warning temperature at "+temperature+" degrees f");
    }
  }
  public static void main(String[] args)
  {
    // iterate(start,condition,inc)
    int start = 0;
    int end = 150;
    int step = 10;
    /* Add your boolean expressions and System.out.println statements here */
    IntStream.iterate(0,n -> n < 160,n -> n+10).forEach(t -> test_temp(t));
  }
}