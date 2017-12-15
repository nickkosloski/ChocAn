package Frames;

import Utils.DatabaseHelper;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ModifyMainFrame extends JFrame implements ActionListener, BasicFrame
{
    public ModifyMainFrame()
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));

        modifyMemberBtn.addActionListener(this);
        modifyMemberBtn.setActionCommand("modifyMember");
        add(modifyMemberBtn);

        modifyProviderBtn.addActionListener(this);
        modifyProviderBtn.setActionCommand("modifyProvider");
        add(modifyProviderBtn);

        backBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        add(backBtn);

        setSize(500, 375);
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
        else
            new DataCenterFrame();
    }
}
