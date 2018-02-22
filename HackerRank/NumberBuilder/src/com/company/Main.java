package com.company;

import java.util.ArrayList;

public class Main {

    public int reverse(int a) {
        Boolean negative = false;
        ArrayList<Integer> integers = new ArrayList<>();
        //if(a < 0) negative = true;
        Integer count = 0;
        Integer sum = 0;
        while(((int)a / ((int)Math.pow(10,count)))  != 0) {
            Integer digit = ((int)a % ((int)Math.pow(10,count + 1))) / ((int)Math.pow(10, count));
            integers.add(digit);
            a = a - digit;
            count++;
        }
        int position = 0;
        for(int i = integers.size() - 1; i >= 0;i-- ) {
            if(sum + integers.get(position) * ((int)Math.pow(10, i)) > Integer.MAX_VALUE || sum + integers.get(position) * ((int)Math.pow(10, i)) < Integer.MIN_VALUE) return 0;
            sum += integers.get(position) * ((int)Math.pow(10, i));
            position++;
        }

        return sum;
    }

    public static void main(String[] args) {
	      Main main = new Main();
        main.reverse(-1146467285);
    }
}
