import java.util.ArrayList;

class Main {
    void num10To20(){
        for (int i=10;i<=20;i++){
            System.out.println(i);
        }
    }
    //
    double div(int n,int d) throws ArithmeticException{
        if (d==0){
            throw new ArithmeticException("cannot divide by zero");
        }
        int q = 0;
        int r = n;
        while (r>=d){
            r-=d;
            q++;
        }
        return (r!=0) ? (q + div(r,d)) : q; // recursion!
    }
    void itoa(int i){
        // ive only ever had to implement itoa in raw asm before, so i would implement this as a fixed buffer 
        // and iterate through each index in reverse and then just return the pointer 
        // of the last indice written and total indices written, but this is java.

        // divide i by radix, add asciiZero to remainder, store remainder in buf, loop until quotient is zero
        // turns out i dont even need to return the values
        // var buf = new ArrayList<Character>();
        final int radix = 10;
        // get initial values
        int quotient = i / radix;
        int remainder = i % radix;
        while (quotient != 0){
            quotient /= radix;
            remainder %= radix;
            System.out.print(remainder);
            // buf.add((char) remainder);    
        }
        // return buf.reversed();
    }
    public static void main(){

    }
}