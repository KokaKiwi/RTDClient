package com.kokakiwi.net.rtd.ui;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import com.kokakiwi.net.rtd.RtdClient;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientFrame extends JFrame
{
    private static final long serialVersionUID = 5021779514875055435L;
    
    private final RtdClient   main;
    private final JTextField  message;
    
    private final JTextPane   messages;
    
    public ClientFrame(final RtdClient main) throws HeadlessException
    {
        super("RTD::Client");
        this.main = main;
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        JScrollPane scrollPane = new JScrollPane();
        tabbedPane
                .addTab("Main",
                        new ImageIcon(
                                ClientFrame.class
                                        .getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")),
                        scrollPane, null);
        
        messages = new JTextPane();
        messages.setEditable(false);
        scrollPane.setViewportView(messages);
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(panel, BorderLayout.SOUTH);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_panel.rowHeights = new int[] { 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);
        
        JLabel lblMessage = new JLabel("Message :");
        GridBagConstraints gbc_lblMessage = new GridBagConstraints();
        gbc_lblMessage.insets = new Insets(0, 0, 0, 5);
        gbc_lblMessage.gridx = 0;
        gbc_lblMessage.gridy = 0;
        panel.add(lblMessage, gbc_lblMessage);
        
        message = new JTextField();
        message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e)
            {
                if (e.getKeyChar() == '\n')
                {
                    sendMessage();
                }
            }
        });
        GridBagConstraints gbc_message = new GridBagConstraints();
        gbc_message.gridwidth = 18;
        gbc_message.insets = new Insets(0, 0, 0, 5);
        gbc_message.fill = GridBagConstraints.HORIZONTAL;
        gbc_message.gridx = 1;
        gbc_message.gridy = 0;
        panel.add(message, gbc_message);
        message.setColumns(10);
        
        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0)
            {
                sendMessage();
            }
        });
        GridBagConstraints gbc_btnSend = new GridBagConstraints();
        gbc_btnSend.insets = new Insets(0, 0, 0, 5);
        gbc_btnSend.gridx = 19;
        gbc_btnSend.gridy = 0;
        panel.add(btnSend, gbc_btnSend);
        
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
    
    public RtdClient getMain()
    {
        return main;
    }
    
    public JTextField getMessage()
    {
        return message;
    }
    
    public JTextPane getMessages()
    {
        return messages;
    }
    
    public void addLine(String line)
    {
        messages.setText(messages.getText() + line + "\n");
    }
    
    public void sendMessage()
    {
        if (!message.getText().isEmpty())
        {
            main.getClient().sendMessage(message.getText());
            message.setText(null);
        }
    }
}
