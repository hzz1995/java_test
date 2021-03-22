package com.huzz.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest {
    @Test
    public void testNoi() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"),8123));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        int select = selector.select();
        Set<SelectionKey> set = selector.selectedKeys();
        Iterator<SelectionKey> iterator = set.iterator();
        while (iterator.hasNext()) {
            SelectionKey next = iterator.next();
        }


    }
}
