package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerToolsFrame extends JFrame implements ActionListener, BasicFrame
{
    public ManagerToolsFrame()
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,1));

        memberRptBtn.addActionListener(this);
        memberRptBtn.setActionCommand("member");
        add(memberRptBtn);

        providerRptBtn.addActionListener(this);
        providerRptBtn.setActionCommand("provider");
        add(providerRptBtn);

        weeklyMgrRptBtn.addActionListener(this);
        weeklyMgrRptBtn.setActionCommand("manager");
        add(weeklyMgrRptBtn);

        backBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        add(backBtn);

        setSize(500, 500);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        dispose();

        if(actionCommand.equals("member"))
            //Do something for member Reports, you can take out println
            System.out.println("Member");
        else if(actionCommand.equals("provider"))
            //Do something for provider Reports, you can take out println
            System.out.println("Provider");
        else if(actionCommand.equals("manager"))
            //Do something for manager Reports, you can take out println
            System.out.println("Manager");
        else
            new DataCenterFrame();
    }
}
