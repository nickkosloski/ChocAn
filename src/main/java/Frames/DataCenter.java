package Frames;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DataCenter extends JFrame implements ActionListener, BasicFrame
{
    public DataCenter()
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2));

        modifyMemberBtn.addActionListener(this);
        modifyMemberBtn.setActionCommand("modifyMember");
        add(modifyMemberBtn);

        modifyProviderBtn.addActionListener(this);
        modifyProviderBtn.setActionCommand("modifyProvider");
        add(modifyProviderBtn);


        setSize(500, 500);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));

        try
        {
            Connection con = DriverManager.getConnection(res.getConnection(), res.getUsername(), res.getPassword());
        }
        catch(SQLException e)
        {
            setVisible(false);
            dispose();
            new NoConnectionFrame();
            System.out.println("Test");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        dispose();

        if(actionCommand.equals("modifyMember"))
            new ModifyFrame("Member");
        else if(actionCommand.equals("modifyProvider"))
            new ModifyFrame("Provider");
    }
}
