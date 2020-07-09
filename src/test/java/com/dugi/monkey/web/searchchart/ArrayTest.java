package com.dugi.monkey.web.searchchart;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayTest {

    @Test
    public void test() {
        int [] a = {1, 2};

        System.out.println(a.toString());

        a = new int[]{2, 3};

        System.out.println(a.toString());

        List<String> str = Arrays.asList("s", "t", "r");
        List<String> str1 = Arrays.asList("s", "t", "r");

        System.out.println(str.hashCode());
        System.out.println(str1.hashCode());

        str = Arrays.asList("s", "t", "r");

        System.out.println(str.hashCode());
    }
}
