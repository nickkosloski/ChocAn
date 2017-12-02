package Frames;

import Utils.DatabaseHelper;

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

        Connection con = DatabaseHelper.connectToDb();
        try
        {
            Statement testQuery = con.createStatement();

            ResultSet answer = testQuery.executeQuery("select * from Provider");
            while(answer.next())
            {
                System.out.println(answer.getString("LName"));
            }

        }
        catch(Exception e)
        {
            setVisible(false);
            dispose();
            new NoConnectionFrame();
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

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
