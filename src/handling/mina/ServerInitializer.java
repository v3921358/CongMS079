package handling.mina;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import handling.MapleServerHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.ChannelInitializer;

public class ServerInitializer extends ChannelInitializer<SocketChannel>
{
    private int world;
    private int channels;
    
    public ServerInitializer(final int world, final int channels) {
        this.world = world;
        this.channels = channels;
    }
    
    protected void initChannel(final SocketChannel channel) throws Exception {
        final ChannelPipeline pipe = channel.pipeline();
        pipe.addLast(new ChannelHandler[] { (ChannelHandler)new IdleStateHandler(90, 90, 0) });
        pipe.addLast("decoder", (ChannelHandler)new MaplePacketDecoder());
        pipe.addLast("encoder", (ChannelHandler)new MaplePacketEncoder());
        pipe.addLast("handler", (ChannelHandler)new MapleServerHandler(this.world, this.channels));
    }
}
