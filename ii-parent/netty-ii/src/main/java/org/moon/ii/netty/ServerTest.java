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
package org.moon.ii.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Scanner;

/**
 * @author wuxii@foxmail.com
 */
public class ServerTest {

    static ChannelFuture channelFuture;

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        new Thread("Channel-Thread") {
            public void run() {
                ServerBootstrap b = new ServerBootstrap();

                b.group(new NioEventLoopGroup(1), new NioEventLoopGroup())//
                        .channel(NioServerSocketChannel.class)//
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline cp = ch.pipeline();
                                cp.addLast(new ChannelHandlerAdapter() {
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(msg);
                                    };
                                });
                            }
                        });

                channelFuture = b.bind(8080);
                try {
                    channelFuture.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                }
                System.out.println(">>>>>>>>>>>>>");
            };
        }.start();

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("please input:");
            String input = scan.nextLine();
            if ("exit".equals(input)) {
                channelFuture.channel().close();
            }
        }
    }
}
