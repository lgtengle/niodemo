package com.lg.niodemo;

import java.io.*;
import java.net.Socket;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-02-21 17:04
 */
public class NioDemo {


    public static void main(String[] args) throws Exception {
        NIOServer nioServer = new NIOServer(8080);
        new Thread(nioServer).start();

        Socket socket = new Socket("127.0.0.1", 8080);
        int i = 0;
        String str = "Hello World";
        while (true){
            try {
                /*InputStreamReader stdin = new InputStreamReader(System.in);//键盘输入
                BufferedReader bufin = new BufferedReader(stdin);
                str = bufin.readLine();
                System.out.println(str);*/

                OutputStream os = socket.getOutputStream();
                os.write((str + i++).getBytes());
                os.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
            Thread.sleep(1000);
        }

    }
}
