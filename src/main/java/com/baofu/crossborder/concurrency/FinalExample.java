package com.baofu.crossborder.concurrency;

/**
 * Description：
 * <p>
 * #
 * </p >
 * User: qingyunzi Date: 17-9-29 ProjectName:servletDemo Version:
 */
public class FinalExample {
    int i;
    final int j;
    static FinalExample obj;

    public FinalExample(){
        i=1;
        j=2;
    }

    public static void writer(){
        obj=new FinalExample();
    }

    public static void reader(){
        FinalExample object=new FinalExample();
        int a=obj.i;
        int b=obj.j;
    }

}