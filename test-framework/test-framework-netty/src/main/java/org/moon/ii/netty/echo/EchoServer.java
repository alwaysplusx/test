/*
 * Copyright 2013-2015 wuxii@foxmail.com.
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
package org.moon.ii.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author wuxii@foxmail.com
 */
public class EchoServer {

    private int port = 8080;

    public static void main(String[] args) throws Exception {
        new EchoServer().run();
    }

    // use telnet localhost 8080 and input char. that server will return same
    // char
    public void run() throws Exception {

        // 是用来处理I/O操作的多线程事件循环器
        // 第一个经常被叫做‘boss’，用来接收进来的连接。
        // 第二个经常被叫做‘worker’，用来处理已经被接收的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap(); // (2) 是一个启动NIO服务的辅助启动类
            b.group(bossGroup, workerGroup)//
                    .channel(NioServerSocketChannel.class) // (3)  使用NioServerSocketChannel类来举例说明一个新的Channel如何接收进来的连接。
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                                                                            // 帮助使用者配置一个新的Channel
                                @Override
                                public void initChannel(SocketChannel ch) throws Exception {
                                    ch.pipeline().addLast(new EchoServerHandler());
                                }
                            })//
                    .option(ChannelOption.SO_BACKLOG, 100); // (5) 通道实现的配置参数
                    //.childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
            
            // option()是提供给NioServerSocketChannel用来接收进来的连接
            // childOption()是提供给由父管道ServerChannel接收到的连接

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7) 绑定端口然后启动服务

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to
            // gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
            // sync() method wait
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
