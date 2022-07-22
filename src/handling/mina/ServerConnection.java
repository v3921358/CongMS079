package handling.mina;

import tools.FileoutputUtil;
import io.netty.channel.ChannelHandler;
import constants.WorldConstants;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.bootstrap.ServerBootstrap;

public class ServerConnection
{
    private final int port;
    private int world;
    private int channels;
    private ServerBootstrap boot;
    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;
    private Channel channel;
    
    public ServerConnection(final int port) {
        this.world = -1;
        this.channels = -1;
        this.bossGroup = (EventLoopGroup)new NioEventLoopGroup(1);
        this.workerGroup = (EventLoopGroup)new NioEventLoopGroup();
        this.port = port;
    }
    
    public ServerConnection(final int port, final int world, final int channels) {
        this.world = -1;
        this.channels = -1;
        this.bossGroup = (EventLoopGroup)new NioEventLoopGroup(1);
        this.workerGroup = (EventLoopGroup)new NioEventLoopGroup();
        this.port = port;
        this.world = world;
        this.channels = channels;
    }
    
    public void run() {
        try {
            this.boot = ((ServerBootstrap)((ServerBootstrap)new ServerBootstrap().group(this.bossGroup, this.workerGroup).channel((Class)NioServerSocketChannel.class)).option(ChannelOption.SO_BACKLOG, Integer.valueOf(WorldConstants.USER_LIMIT))).childOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true)).childOption(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(true)).childHandler(new ServerInitializer(this.world, this.channels));
            try {
                this.channel = this.boot.bind(this.port).sync().channel().closeFuture().channel();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e2) {
            System.out.printf("Connection to %s failed.", (this.channel == null) ? e2.toString() : this.channel.remoteAddress());
            FileoutputUtil.outputFileError("logs/異常輸出.txt", (Throwable)e2);
            this.close();
        }
    }
    
    public void close() {
        if (this.channel != null) {
            this.channel.close();
        }
        this.bossGroup.shutdownGracefully();
        this.workerGroup.shutdownGracefully();
    }
    
    public int getPort() {
        return this.port;
    }
}
