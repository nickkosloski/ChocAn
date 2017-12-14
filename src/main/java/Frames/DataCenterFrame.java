package Frames;

import Utils.DatabaseHelper;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DataCenterFrame extends JFrame implements ActionListener, BasicFrame
{
    public DataCenterFrame()
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));

        managerBtn.addActionListener(this);
        managerBtn.setActionCommand("manager");
        add(managerBtn);

        modifyBtn.addActionListener(this);
        modifyBtn.setActionCommand("modify");
        add(modifyBtn);

        setSize(500, 250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        dispose();

        if(actionCommand.equals("manager"))
            new ManagerLoginFrame();
        else if(actionCommand.equals("modify"))
            new ModifyMainFrame();
    }
}
