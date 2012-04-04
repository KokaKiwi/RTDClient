package com.kokakiwi.net.rtd.net;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.kokakiwi.net.rtd.RtdClient;

public class Client
{
    private final RtdClient       main;
    
    private final ClientBootstrap bootstrap;
    private Channel               channel = null;
    
    public Client(RtdClient main)
    {
        this.main = main;
        
        bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()));
        bootstrap.setPipelineFactory(new ClientChannelPipelineFactory(this));
    }
    
    public boolean connect(String host, int port)
    {
        boolean result = false;
        
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(host,
                port));
        try
        {
            future.await(30000L);
            if (future.isSuccess())
            {
                channel = future.getChannel();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public void sendMessage(String message)
    {
        if (channel != null && channel.isWritable())
        {
            channel.write(message);
        }
    }
    
    public Channel getChannel()
    {
        return channel;
    }
    
    public void setChannel(Channel channel)
    {
        this.channel = channel;
    }
    
    public RtdClient getMain()
    {
        return main;
    }
    
    public ClientBootstrap getBootstrap()
    {
        return bootstrap;
    }
}
