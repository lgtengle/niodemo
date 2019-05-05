package com.lg.niodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-02-21 17:05
 */
public class NIOServer implements Runnable{

    Selector selector;

    ServerSocketChannel serverSocket;

    public NIOServer(int port) throws Exception {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                selector.select();
                selector.selectedKeys().forEach(key -> {
                    try {
                        dispatch(key);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatch(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            register(key);
        } else if (key.isReadable()){
            read(key);
        } else if (key.isWritable()){
            write(key);
        }
    }

    private void read(SelectionKey key) throws IOException {
        System.out.println("read-------------");
        SocketChannel server = (SocketChannel) key.channel();
        ByteBuffer allocate = ByteBuffer.allocate(256);
        int read = server.read(allocate);
        if (read == -1) {
            //System.out.println("closed.......");
            //server.close();
        }else if (read > 0) {
            System.out.println(new String(allocate.array()));

        }

    }

    private void write(SelectionKey key) throws IOException {
        System.out.println("-------------write");

        SocketChannel clientChannel = (SocketChannel) key.channel();
        // get content from attachment
        String content = (String) key.attachment();
        // write content to socket channel
        clientChannel.write(ByteBuffer.wrap(content.getBytes()));
        key.interestOps(SelectionKey.OP_READ);


    }

    public void register(SelectionKey key) throws IOException {
        System.out.println("----register--------");
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel channel = server.accept();
        if (channel != null) {
            channel.configureBlocking(false);
            channel.register(this.selector, SelectionKey.OP_READ);
        }
    }
}
