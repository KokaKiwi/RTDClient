package com.kokakiwi.net.rtd.net;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class ClientChannelPipelineFactory implements ChannelPipelineFactory
{
    private final Client client;
    
    public ClientChannelPipelineFactory(Client client)
    {
        this.client = client;
    }
    
    public ChannelPipeline getPipeline() throws Exception
    {
        ChannelPipeline pipeline = Channels.pipeline();
        
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        
        pipeline.addLast("handler", new ClientHandler(client));
        
        return pipeline;
    }
    
}
