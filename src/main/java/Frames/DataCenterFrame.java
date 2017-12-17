package Frames;

import Utils.DatabaseHelper;
import DataCenter.ChocAnServiceDriver;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DataCenterFrame extends JFrame implements ActionListener, BasicFrame
{
    public DataCenterFrame()
    {
        super("ChocAn Data Center");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2,1));

        managerBtn.addActionListener(this);
        managerBtn.setActionCommand("manager");
        this.add(managerBtn);

        modifyBtn.addActionListener(this);
        modifyBtn.setActionCommand("modify");
        this.add(modifyBtn);

        this.setSize(500, 250);
        this.setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        this.dispose();

        if(actionCommand.equals("manager"))
            new ManagerLoginFrame();
        else if(actionCommand.equals("modify"))
            new ModifyMainFrame();
    }
}
