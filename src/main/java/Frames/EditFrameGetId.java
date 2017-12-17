package Frames;

import Utils.DatabaseHelper;

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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 1));

        this.type = type;

        message.setText("Enter " + type + " ID Number");
        message.setEditable(false);
        this.add(message);

        idNumberTxt.setEditable(true);
        this.add(idNumberTxt);

        enterBtn.addActionListener(this);
        enterBtn.setActionCommand("enter");
        this.add(enterBtn);

        backBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        this.add(backBtn);

        this.setSize(500, 200);
        this.setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        this.dispose();
        String idNum = idNumberTxt.getText();
        Boolean isValid = false;

        if(actionCommand.equals("enter"))
        {
            Connection con = DatabaseHelper.connectToDb();
            try
            {
                Statement stmt = con.createStatement();
                String queryStmt = "select * from " + type + " where ";

                if(type.equalsIgnoreCase("Member"))
                    queryStmt += "MemberId = " + idNum + ";";
                else
                    queryStmt += "ProviderId = " + idNum + ";";

                ResultSet rs = stmt.executeQuery(queryStmt);

                isValid = rs.next();

                if(isValid)
                {
                    con.close();
                    new EditFrame(type, idNum);
                }
                else
                {
                    con.close();
                    int reply = JOptionPane.showConfirmDialog(this, type + " ID not found. Try again?", "ID not found", JOptionPane.YES_NO_OPTION);

                    if(reply == JOptionPane.YES_OPTION)
                        new EditFrameGetId(type);
                    else
                        new DataCenterFrame();
                }
            }
            catch(Exception exception)
            {
                JOptionPane.showMessageDialog(this, exception);
                new DataCenterFrame();
            }
        }
        else
        {
            new ModifyFrame(type);
        }
    }
}
