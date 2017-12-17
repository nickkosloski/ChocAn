package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerToolsFrame extends JFrame implements ActionListener
{
    Button  memberRptBtn = new Button("Member Report");
    Button  providerRptBtn = new Button("Provider Report");
    Button  weeklyMgrRptBtn = new Button("Weekly Manager Report");

    public ManagerToolsFrame()
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(19,1));

        memberRptBtn.addActionListener(this);
        memberRptBtn.setActionCommand("member");
        add(memberRptBtn);

        providerRptBtn.addActionListener(this);
        providerRptBtn.setActionCommand("provider");
        add(providerRptBtn);

        weeklyMgrRptBtn.addActionListener(this);
        weeklyMgrRptBtn.setActionCommand("manager");
        add(weeklyMgrRptBtn);

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
