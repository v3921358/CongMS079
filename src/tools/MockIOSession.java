package tools;

import io.netty.channel.ChannelOutboundInvoker;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.RecvByteBufAllocator.Handle;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.Channel.Unsafe;
import io.netty.channel.ChannelFuture;
import java.net.SocketAddress;
import io.netty.channel.ChannelConfig;
import io.netty.channel.EventLoop;
import io.netty.channel.ChannelId;
import io.netty.channel.Channel;

public class MockIOSession implements Channel
{
    public ChannelId id() {
        return null;
    }
    
    public EventLoop eventLoop() {
        return null;
    }
    
    public Channel parent() {
        return null;
    }
    
    public ChannelConfig config() {
        return null;
    }
    
    public boolean isOpen() {
        return false;
    }
    
    public boolean isRegistered() {
        return false;
    }
    
    public boolean isActive() {
        return false;
    }
    
    public SocketAddress localAddress() {
        return null;
    }
    
    public SocketAddress remoteAddress() {
        return null;
    }
    
    public ChannelFuture closeFuture() {
        return null;
    }
    
    public boolean isWritable() {
        return false;
    }
    
    public long bytesBeforeUnwritable() {
        return 0L;
    }
    
    public long bytesBeforeWritable() {
        return 0L;
    }
    
    public Unsafe unsafe() {
        return null;
    }
    
    public ChannelPipeline pipeline() {
        return null;
    }
    
    public ByteBufAllocator alloc() {
        return null;
    }
    
    public Channel read() {
        return null;
    }
    
    public Channel flush() {
        return null;
    }
    
    public Handle recvBufAllocHandle() {
        return null;
    }
    
    public ChannelFuture register(final EventLoop el, final ChannelPromise cp) {
        return null;
    }
    
    public ChannelFuture bind(final SocketAddress sa, final ChannelPromise cp) {
        return null;
    }
    
    public ChannelFuture connect(final SocketAddress sa, final SocketAddress sa1, final ChannelPromise cp) {
        return null;
    }
    
    public ChannelFuture disconnect(final ChannelPromise cp) {
        return null;
    }
    
    public ChannelFuture close(final ChannelPromise cp) {
        return null;
    }
    
    public ChannelFuture closeForcibly() {
        return null;
    }
    
    public ChannelFuture deregister(final ChannelPromise cp) {
        return null;
    }
    
    public ChannelFuture beginRead() {
        return null;
    }
    
    public ChannelFuture write(final Object o, final ChannelPromise cp) {
        return null;
    }
    
    public ChannelPromise voidPromise() {
        return null;
    }
    
    public ChannelOutboundBuffer outboundBuffer() {
        return null;
    }
    
    public ChannelMetadata metadata() {
        return null;
    }
    
    public <T> Attribute<T> attr(final AttributeKey<T> ak) {
        return null;
    }
    
    public <T> boolean hasAttr(final AttributeKey<T> ak) {
        return false;
    }
    
    public ChannelFuture bind(final SocketAddress sa) {
        return null;
    }
    
    public ChannelFuture connect(final SocketAddress sa) {
        return null;
    }
    
    public ChannelFuture connect(final SocketAddress sa, final SocketAddress sa1) {
        return null;
    }
    
    public ChannelFuture disconnect() {
        return null;
    }
    
    public ChannelFuture close() {
        return null;
    }
    
    public ChannelFuture deregister() {
        return null;
    }
    
    public ChannelFuture connect(final SocketAddress sa, final ChannelPromise cp) {
        return null;
    }
    
    public ChannelFuture write(final Object o) {
        return null;
    }
    
    public ChannelFuture writeAndFlush(final Object o, final ChannelPromise cp) {
        return null;
    }
    
    public ChannelFuture writeAndFlush(final Object o) {
        return null;
    }
    
    public ChannelPromise newPromise() {
        return null;
    }
    
    public ChannelProgressivePromise newProgressivePromise() {
        return null;
    }
    
    public ChannelFuture newSucceededFuture() {
        return null;
    }
    
    public ChannelFuture newFailedFuture(final Throwable thrwbl) {
        return null;
    }
    
    public int compareTo(final Channel t) {
        return 0;
    }
}
