package com.baofu.crossborder.concurrent;

import java.util.concurrent.TimeUnit;

public class Profiler {

    private static final ThreadLocal<Long> TIME_THREADLOCAL=new ThreadLocal<Long>();

    protected Long initValue(){
        return System.currentTimeMillis();
    }

    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final Long end(){
        return System.currentTimeMillis()-TIME_THREADLOCAL.get();
    }

    public static void main(String[] args)throws  Exception{
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("cost:"+Profiler.end()+"mills");
    }
}
