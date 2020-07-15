package server.side;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static final List<Channel> channels = new ArrayList<>();
    private static int newClientIndex = 1;
    private String clientName;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {//подключение клиента
        System.out.println("Клиент подключился: " + ctx);
        channels.add(ctx.channel());
        clientName = "Клиент #" + newClientIndex;
        newClientIndex++;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {//сервер получил инфо от клиента
        System.out.println("Получено сообщение: " + s);
        if (s.startsWith("copy")) {
            String filename = s.split("\\s", 2)[1];
            System.out.println("Клиент " + clientName + " скопировал файл " + filename);
            return;
        }
        if (s.startsWith("move")) {
            String filename = s.split("\\s", 2)[1];
            System.out.println("Клиент " + clientName + " переместил файл " + filename);
            return;
        }
        if (s.startsWith("delete")) {
            String filename = s.split("\\s", 2)[1];
            System.out.println("Клиент " + clientName + " удалил файл " + filename);
            return;
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Клиент " + clientName + " вышел из сети");
        channels.remove(ctx.channel());
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {//исключение при работе с клиентом
        System.out.println("Клиент " + clientName + " отвалился");
        channels.remove(ctx.channel());
        ctx.close();
    }
}
