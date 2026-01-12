class Main {
    static void num10To20(){
        // for (int i=10;i<=20;i++){
        //     System.out.println(i);
        // }
        int i = 10;
        while (i <= 20){
            System.out.println(i);
            i++;
        }
    }
    //
    static double div(int n,int d) throws ArithmeticException{
        if (d==0){
            throw new ArithmeticException("cannot divide by zero");
        }
        int q = 0;
        int r = n;
        while (r>=d){
            r-=d;
            q++;
        }
        return (r!=0) ? (q + div(r,d)) : q;
    }
    static void itoa(int i){
        // ive only ever had to implement itoa in raw asm before, so i would implement this as a fixed buffer 
        // and iterate through each index in reverse and then just return the pointer 
        // of the last indice written and total indices written, but this is java.

        // divide i by radix, add asciiZero to remainder, store remainder in buf, loop until quotient is zero
        // turns out i dont even need to return the values
        // var buf = new ArrayList<Character>();
        final int radix = 10;
        int quotient = i;
        while (true){
            int remainder = quotient % radix;
            quotient /= radix;
            System.out.printf("digit: %d \n",remainder);
            if (quotient == 0) break;
        }
        // return buf.reversed();
    }
    static void minMax(int[] seq){
        Integer min = null;
        Integer max = null;
        int n = 0;
        while (n < seq.length){
            int i = seq[n];
            if (min != null && i < min){
                min=i;
            } else if (max != null && i > max) {
                max = i;
            } else if (min == null && max == null){
                min=i;
                max=i;
            }
            n++;
        }
        System.out.printf("min = %d, max = %d\n",min,max);
    }

    static void frequencyCount(String s, String crit){
        // known content and length, iterate over string until find char of start of string, 
        // test if i+n == s then count++ and skip, else continue
        // int length = s.length();
        if (s.length() == 0){
            System.out.println("s is an empty string");
        }
        int count = 0;
        
        int firstChar = s.charAt(0);
        int i = 0;
        
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c != firstChar){
                i++;
                continue;
            }
            else if (s.substring(i,i+(crit.length())).equals(crit)){
                    count++;
                    i+=crit.length();
                    } 
                else i++;
                
        }
        System.out.printf("the provided string has %d occurances of the substring %s\n",count,crit);
    }
    static void sumAndAverage(int[] seq){
        if (seq.length == 0){
            return;
        }
        // i would do it like this, but the assn is about manual iteration..
        // Stream<Integer> seqStream = IntStream.of(seq).boxed();
        // if (seqStream.anyMatch(i -> i == 0)) return; // NO ZEROS!
        // seqStream.reduce(0,(acc,n) -> acc+n);
        int sum = 0;
        int i = 0;
        while (i < seq.length){
            int n = seq[i];
            if (n == 0){
                System.out.println("input array cannot include a zero");
                return; // NO ZEROS!
            }
            sum+=n;
            i++;
        }
        var avg = sum / seq.length;
        System.out.printf("sum=%d , avg=%d\n",sum,avg); 
    }
    public static void main(String[] args){
        num10To20();
        System.out.println("10/2 = "+div(10, 2));
        itoa(875);
        minMax(new int[]{5,2,9,1,7});
        frequencyCount("gleep glorp glorp teep", "glorp");
        sumAndAverage(new int[]{5,2,9,1,7});
    }
}