package org.harmony.test.netty;

import java.util.Scanner;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

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
                                cp.addLast(new ChannelInboundHandlerAdapter() {

                                    @Override
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
