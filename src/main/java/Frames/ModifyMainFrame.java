package Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ModifyMainFrame extends JFrame implements ActionListener, BasicFrame
{
    public ModifyMainFrame()
    {
        super("ChocAn Data Center");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3,1));

        modifyMemberBtn.addActionListener(this);
        modifyMemberBtn.setActionCommand("modifyMember");
        this.add(modifyMemberBtn);

        modifyProviderBtn.addActionListener(this);
        modifyProviderBtn.setActionCommand("modifyProvider");
        this.add(modifyProviderBtn);

        backBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        this.add(backBtn);

        this.setSize(500, 375);
        this.setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        this.dispose();

        if(actionCommand.equals("modifyMember"))
            new ModifyFrame("Member");
        else if(actionCommand.equals("modifyProvider"))
            new ModifyFrame("Provider");
        else
            new DataCenterFrame();
    }
}
