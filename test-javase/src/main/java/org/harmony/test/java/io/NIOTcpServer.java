/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.harmony.test.java.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuxii@foxmail.com
 */
public class NIOTcpServer implements Runnable {

    private static final Logger serverLog = LoggerFactory.getLogger("Server");

    private Handler handler;

    public NIOTcpServer(Handler handler) {
        this.handler = handler;
    }

    public void run() {

        try {
            Selector selector = Selector.open();// 打开选择器
            ServerSocketChannel ssc = ServerSocketChannel.open();// 打开通道

            ssc.configureBlocking(false);// 非阻塞
            ssc.socket().bind(new InetSocketAddress("localhost", 8080));// 绑定地址
            ssc.register(selector, SelectionKey.OP_ACCEPT);// 向通道注册选择器和对应的时间

            while (true) {
                int selects = selector.select();
                if (selects > 0) {
                    Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        if (key.isAcceptable()) {
                            serverLog.info("selection acceptable");
                            handler.handleAccept(key);
                        } else if (key.isReadable()) {
                            serverLog.info("selection readable");
                            handler.handleRead(key);
                        } else if (key.isWritable()) {
                            serverLog.info("selection writable");
                            handler.handleWrite(key);
                        }
                        it.remove();
                    }
                }
            }
        } catch (IOException e) {
        }

    }

    public interface Handler {
        /**
         * 处理{@link SelectionKey#OP_ACCEPT}事件
         * 
         * @param key
         * @throws IOException
         */
        void handleAccept(SelectionKey key) throws IOException;

        /**
         * 处理{@link SelectionKey#OP_READ}事件
         * 
         * @param key
         * @throws IOException
         */
        void handleRead(SelectionKey key) throws IOException;

        /**
         * 处理{@link SelectionKey#OP_WRITE}事件
         * 
         * @param key
         * @throws IOException
         */
        void handleWrite(SelectionKey key) throws IOException;

    }

}
