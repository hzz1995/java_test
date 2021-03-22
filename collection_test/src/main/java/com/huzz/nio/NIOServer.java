package com.huzz.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Davie on 2018/8/23.
 */
public class NIOServer {
    public static void main(String[] args) throws IOException
    {
        //创建选择器
        Selector selector = Selector.open();

        //将通道注册到选择器上
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        /**
         * 注意：
         * （1）通道必须配置为 非阻塞 模式，否则使用选择器没有任何意义
         * 因为如果通道在某个事件上被阻塞，那么服务器就不能响应其他事件，必须等待这个事件处理完毕才能去处理其他事件
         * （2）通道主要分为两大类：文件（File）通道 和 套接字（Socket）通道
         *   涉及的类主要有FileChannel 类 和 三个Socket通道类：SocketChannel、ServerSocketChannel和DatagramChannel
         *   应该注意的是：只有套接字Channel才能配置为 非阻塞，而FileChannel不能，为FileChannel配置为 非阻塞没有意义
         *
         */
        ssChannel.configureBlocking(false);
        /**
         * 注册的具体事件：主要有一下几类：
         *      SelectionKey.OP_CONNECT  --- 连接就绪事件，表示客户端与服务器的连接已经建立成功
         *      SelectionKey.OP_ACCEPT   --- 接收连接事件，表示服务器监听到了客户连接，服务器可以接收这个连接了
         *      SelectionKey.OP_READ     --- 读 就绪事件，表示通道中已经有了可读的数据，可以执行读操作了
         *      SelectionKey.OP_WRITE    --- 写 就绪事件，表示已经可以向通道写数据了
         */
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //创建服务器对象
        ServerSocket serverSocket = ssChannel.socket();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        serverSocket.bind(address);

        while (true) {
            //阻塞到至少有一个通道在你注册的选择器上就绪了
            selector.select();
            /**
             *    selector.keys():当前所有向Selector注册的SelectionKey集合
             *    selector.selectedKeys()：相关事件已经被Selector捕获的SelectionKey集合
             */
            Set selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                //服务器监听到了一个客户端连接
                if (key.isAcceptable()) {
                   ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    // 服务器会为每个新的客户端连接创建一个 SocketChannel
                    SocketChannel sChannel = serverSocketChannel.accept();
                    //配置为 非阻塞 模式
                    sChannel.configureBlocking(false);
                    // 这个新连接主要用于从客户端读取数据
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    System.out.println(readDataFromSocketChannel(socketChannel));
                    socketChannel.close();
                }
                /**
                 * 每次迭代末尾的remove()调用，Selector不会自己从已选择的SelectionKey集合中
                 * 移除SelectionKey实例的，必须在处理完通道时自己移除
                 */
                keyIterator.remove();
            }
        }
    }

    private static String readDataFromSocketChannel(SocketChannel sChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder data = new StringBuilder();
        while (true) {
            buffer.clear();
            int n = sChannel.read(buffer);
            if (n == -1) {
                break;
            }
            buffer.flip();
            int limit = buffer.limit();
            char[] dst = new char[limit];
            for (int i = 0; i < limit; i++) {
                dst[i] = (char) buffer.get(i);
            }
            data.append(dst);
            buffer.clear();
        }
        return data.toString();
    }
}