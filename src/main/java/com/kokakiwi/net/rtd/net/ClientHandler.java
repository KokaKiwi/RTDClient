package com.kokakiwi.net.rtd.net;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class ClientHandler extends SimpleChannelUpstreamHandler
{
    private final Client client;
    
    public ClientHandler(Client client)
    {
        super();
        this.client = client;
    }
    
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
            throws Exception
    {
        if (e.getMessage() instanceof String)
        {
            String message = (String) e.getMessage();
            String[] split = message.split(" ");
            
            int opcode = Integer.valueOf(split[0]);
            
            switch (opcode)
            {
                case 0:
                {
                    String from = split[1];
                    String msg = concat(split, " ", 2);
                    
                    client.getMain().getFrame().addLine(from + " : " + msg);
                }
                    break;
                
                case 1:
                {
                    String from = split[1];
                    String msg = concat(split, " ", 2);
                    
                    client.getMain().getFrame()
                            .addLine("[Private] " + from + " : " + msg);
                }
                    break;
                
                case 2:
                {
                    String msg = concat(split, " ", 1);
                    
                    client.getMain().getFrame().addLine(msg);
                }
                    break;
            }
        }
    }
    
    public String concat(String[] str, String delimiter, int start)
    {
        StringBuilder sb = new StringBuilder();
        
        for (int i = start; i < str.length; i++)
        {
            sb.append(str[i]);
            if (i < str.length - 1)
            {
                sb.append(' ');
            }
        }
        
        return sb.toString();
    }
}
