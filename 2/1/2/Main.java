public class Main {

    static void for1to10(){
        for (int i=1;i<=10;i++){
            System.out.println(i);
        }
    }
    static void customInc(){
        for (int i=2;i<=20;i+=2){
            System.out.println(i);
        }
    }

    static void countdown(){
        for (int i=10;i>0;i--){
            System.out.println(i);
        }
        System.out.println("Blastoff!");
    }

    static void sumOf100(){
        int sum = 0;
        for (int i=1;i<=100;i++){
            sum+=i;
        }
        System.out.printf("sum of 1..100 = %d",sum);
    }

    static void colorIterator(){
        String str = "Red Green Blue";
        for (int i = 0; i < str.length();i++){
            System.out.println(str.substring(i,i+1));
        }
    }

    public static void main(String[] args) {
        for1to10();
        customInc();
        countdown();
        sumOf100();
        colorIterator();
    }
}