package server.side;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServerApplication{
    private static final int PORT = 8192;

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); // менеджер потоков - отвечает за подключающихся клиентов
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // менеджер потоков для работ
        try {
            ServerBootstrap b = new ServerBootstrap(); //для преднастройки сервера
            b.group(bossGroup, workerGroup) //настройка сервера для работы с потоками
                    .channel(NioServerSocketChannel.class) //канал для подкл клиентов
                    .childHandler(new ChannelInitializer<SocketChannel>() { //для настройки общения с клиентом, SocketChannel - инфо о клиенте, его подключении
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new StringDecoder(), new StringEncoder(), new ServerHandler());
                        }
                    });
            ChannelFuture future = b.bind(PORT).sync(); //запуск сервера
            System.out.println("Сервер подключен");
            future.channel().closeFuture().sync(); //ожидание остановки сервера
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully(); //закрытие потоков
            workerGroup.shutdownGracefully(); //закрытие потоков
        }
    }
}
