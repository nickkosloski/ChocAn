package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EditFrameGetId extends JFrame implements ActionListener, BasicFrame
{
    String type = "";

    public EditFrameGetId(String type)
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        this.type = type;

        message.setText("Enter " + type + " ID Number");
        message.setEditable(false);
        add(message);

        idNumberTxt.setEditable(true);
        add(idNumberTxt);

        enterBtn.addActionListener(this);
        enterBtn.setActionCommand("enter");
        add(enterBtn);

        backBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        add(backBtn);

        setSize(500, 200);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        String idNum = idNumberTxt.getText();

        if(actionCommand.equals("enter"))
        {
            try
            {
                Connection con = DriverManager.getConnection(res.getConnection(), res.getUsername(), res.getPassword());
                Statement stmt = con.createStatement();
                String queryStmt = "select * from " + type + "where ";

                if(type.equalsIgnoreCase("Member"))
                    queryStmt += "MemberId = " + idNum + ";";
                else
                    queryStmt += "ProviderId = " + idNum + ";";

                ResultSet rs = stmt.executeQuery(queryStmt);

                if(rs.first())
                {
                    con.close();
                    dispose();
                    new EditFrame(type, idNum);
                }
                else
                {
                    con.close();
                    int reply = JOptionPane.showConfirmDialog(this, type + " ID not found. Try again?", "ID not found", JOptionPane.YES_NO_OPTION);

                    if(reply == JOptionPane.YES_OPTION)
                    {
                        dispose();
                        new EditFrameGetId(type);
                    }
                    else
                    {
                        dispose();
                        new DataCenterFrame();
                    }
                }
            }
            catch(Exception exception)
            {
                JOptionPane.showMessageDialog(this, "Unable to access database");
                dispose();
                new DataCenterFrame();
            }
        }
        else
        {
            dispose();
            new ModifyFrame(type);
        }
    }
}
