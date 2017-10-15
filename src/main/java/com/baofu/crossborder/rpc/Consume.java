package com.baofu.crossborder.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class Consume {


    public static void main(String[] args) {
        try {
            String interfaceName = HelloService.class.getName();
            Method method = HelloService.class.getMethod("sayHello", java.lang.String.class);

            Object[] arguments = {"hello"};

            Socket socket = new Socket("127.0.0.1", 8080);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeUTF(interfaceName);
            out.writeUTF(method.getName());

            out.writeObject(method.getParameterTypes());
            out.writeObject(arguments);


            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Object returnObject = ois.readObject();
            System.out.println(returnObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
