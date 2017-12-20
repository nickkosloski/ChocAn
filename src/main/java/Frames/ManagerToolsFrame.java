package Frames;

import ReportTools.ReportGeneratorProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class ManagerToolsFrame extends JPanel implements ActionListener
{
    Button  memberRptBtn = new Button("Member Report");
    Button  providerRptBtn = new Button("Provider Report");
    Button  weeklyMgrRptBtn = new Button("Weekly Manager Report");

    public ManagerToolsFrame()
    {
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

        if(actionCommand.equals("member")) {
            try {
                processor.generateMemberReports();
                JOptionPane.showMessageDialog(this, "Completed member reports. Check C:\\ChocAn\\MemberReports for files.");
            } catch (IOException | SQLException e1) {
                JOptionPane.showMessageDialog(this, "Error in member report:  " +e1.getMessage(),"Error Message", ERROR_MESSAGE);
            }
        }
        else if(actionCommand.equals("provider")) {
            try {
                processor.generateProviderReports();
                JOptionPane.showMessageDialog(this, "Completed provider reports. Check C:\\ChocAn\\ProviderReports for files.");
            } catch (IOException | SQLException e1) {
                JOptionPane.showMessageDialog(this, "Error in provider report:  " +e1.getMessage(),"Error Message", ERROR_MESSAGE);
            }
        }
        else if(actionCommand.equals("manager")) {
            try {
                processor.generateManagerReports();
                JOptionPane.showMessageDialog(this, "Completed manager reports. Check C:\\ChocAn\\ManagerReports for files.");
            } catch (IOException | SQLException e1) {
                JOptionPane.showMessageDialog(this, "Error in manager report:  " +e1.getMessage(),"Error Message", ERROR_MESSAGE);
            }
        }
        else
            new DataCenterFrame();
    }
}
