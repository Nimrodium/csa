class StringComparisonDemo {

    public static void main(String[] args) {
        String str1 = new String("Hello");
        String str2 = new String("Hello");
        String str3 = new String("Hello");
        String str4 = str1;
        String[] strs = { str1, str2, str3, str4 };
        for (String string : strs) {
            for (String otherString : strs) {
                System.out.printf(
                    "(%s == %s) = %b\n",
                    string,
                    otherString,
                    string == otherString
                );
            }
        }
        // == compares the literal value of the variables, their pointer, and so only raises true if the two literal values are equal
        // C
        // #include <stdio.h>
        // int a = 1;
        // int b = 2;
        // int* a_ptr = &a;
        // int* b_ptr = &b;
        // if !(a_ptr == b_ptr){
        //  puts("these are not the same pointers");
        // }else {
        //  puts("these are are the same pointer");
        // }
        // // true will be encountered.
        // the same applies to java, it just hides pointer management.
        System.out.println(
            "the only True is from str1 == str4, because those are the same object"
        );
        for (String string : strs) {
            for (String otherString : strs) {
                System.out.printf(
                    "(%s.equals(%s)) = %b\n",
                    string,
                    otherString,
                    string.equals(otherString)
                );
            }
        }
        System.out.println(
            "however these all gave True because of the .equals method,\n which actually evaluates if the objects are logially equal. eg: sharethe same string content"
        );
    }
}
