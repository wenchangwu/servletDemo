package com.baofu.crossborder.concurrency;

/**
 * Description：
 * <p>
 * #
 * </p >
 * User: qingyunzi Date: 17-9-29 ProjectName:servletDemo Version:
 */
public class FinalReferenceExample {
    final int[] intArray;
    static FinalReferenceExample obj;

    public FinalReferenceExample() {
        intArray = new int[1];
        intArray[0] = 1;
    }

    public static void wirterOne() {
        obj = new FinalReferenceExample();
    }

    public static void writerTwo() {
        obj.intArray[0] = 2;
    }

    public static void reader() {
        if (obj != null) {
            int temp1 = obj.intArray[0];
        }
    }
}