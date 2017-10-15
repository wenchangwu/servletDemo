package com.baofu.crossborder.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ServiceProvider {

    public static void main(String[] args) {
        ServerSocket server = null;
        ObjectOutputStream out = null;

        try {
            server = new ServerSocket(8080);
            Socket socket = null;
            while (true) {
                System.out.println("-----listen on port 8080------");
                socket = server.accept();
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                String interfaceName = input.readUTF();
                String methodName = input.readUTF();
                Class<?>[] paramersType = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                System.out.println("parameter received:" + Arrays.toString(arguments));

                Class<?> serviceInterfaceClass = Class.forName(interfaceName);
                Method method = serviceInterfaceClass.getMethod(methodName, paramersType);

                HelloService service = new HelloServiceImpl();

                Object result = method.invoke(service, arguments);

                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
