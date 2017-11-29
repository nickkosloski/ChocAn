package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoConnectionFrame extends JFrame implements ActionListener, BasicFrame
{
    public NoConnectionFrame()
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));

        message.setText("Unable to connect to database");
        message.setForeground(Color.RED);
        add(message);

        tryAgainBtn.addActionListener(this);
        tryAgainBtn.setActionCommand("tryAgain");
        add(tryAgainBtn);

        pack();
        setSize(500, 500);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        dispose();
        new DataCenter();
    }
}