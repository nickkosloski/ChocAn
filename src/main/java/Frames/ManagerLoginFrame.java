package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerLoginFrame extends JFrame implements ActionListener, BasicFrame
{
    public ManagerLoginFrame()
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        add(usernameLabel);
        usernameTxt.setEditable(true);
        add(usernameTxt);

        add(passwordLabel);
        passwordTxt.setEditable(true);
        add(passwordTxt);

        enterBtn.addActionListener(this);
        enterBtn.setActionCommand("enter");
        add(enterBtn);

        backBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        add(backBtn);

        setSize(500, 300);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();

        if(actionCommand.equals("enter"))
        {
            //Validate username and password

            //You'll probably want a pop up if username/password is wrong and resend them to this frame.
            //See EditFrameGetId

            dispose();
            new ManagerToolsFrame();
        }
        else if(actionCommand.equals("back"))
        {
            dispose();
            new DataCenterFrame();
        }
    }
}