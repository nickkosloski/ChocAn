package Frames;

import ReportTools.ReportGeneratorProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class ManagerToolsFrame extends JFrame implements ActionListener
{
    Button  memberRptBtn = new Button("Member Report");
    Button  providerRptBtn = new Button("Provider Report");
    Button  weeklyMgrRptBtn = new Button("Weekly Manager Report");

    public ManagerToolsFrame()
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        ReportGeneratorProcessor processor = new ReportGeneratorProcessor();
        dispose();

        if(actionCommand.equals("member")) {
            try {
                processor.generateMemberReports();
            } catch (IOException | SQLException e1) {
                e1.printStackTrace();
            }
        }
        else if(actionCommand.equals("provider")) {
            try {
                processor.generateProviderReports();
            } catch (IOException | SQLException e1) {
                e1.printStackTrace();
            }
        }
        else if(actionCommand.equals("manager")) {
            try {
                processor.generateManagerReports();
            } catch (IOException | SQLException e1) {
                e1.printStackTrace();
            }
        }
        else
            new DataCenterFrame();
    }
}
