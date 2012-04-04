package com.kokakiwi.net.rtd;

import com.kokakiwi.net.rtd.net.Client;
import com.kokakiwi.net.rtd.ui.ClientFrame;

public class RtdClient
{
    private final ClientFrame frame;
    private final Client      client;
    
    public RtdClient()
    {
        frame = new ClientFrame(this);
        client = new Client(this);
        
        client.connect("opex-mc.servegame.com", 1337);
        
        frame.setVisible(true);
    }
    
    public ClientFrame getFrame()
    {
        return frame;
    }
    
    public Client getClient()
    {
        return client;
    }
    
    public static void main(String[] args)
    {
        new RtdClient();
    }
    
}
