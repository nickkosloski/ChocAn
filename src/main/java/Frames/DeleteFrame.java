package Frames;

import Utils.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class DeleteFrame extends JFrame implements ActionListener, BasicFrame
{
    String type = "";

    public DeleteFrame(String type)
    {
        super("ChocAn Data Center");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4,1));

        this.type = type;

        idLabel.setText(type + "ID Number");
        this.add(idLabel);

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

        if(actionCommand.equals("enter"))
        {

            int reply = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete " + idNum + "?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if(reply == 0)
            {
                Connection con = DatabaseHelper.connectToDb();
                try
                {
                    Statement stmt = con.createStatement();

                    String queryStmt = "delete from " + type + " where ";

                    if(type.equalsIgnoreCase("Member"))
                        queryStmt += "MemberId='" + idNum + "';";
                    else
                        queryStmt += "ProviderId='" + idNum + "';";

                    stmt.executeUpdate(queryStmt);
                    JOptionPane.showMessageDialog(this, type + " Successfully Deleted");

                }
                catch(Exception exception)
                {
                    JOptionPane.showMessageDialog(this, "Unable to delete " + type + "\nDue to " + exception.getMessage(), "Error Message", ERROR_MESSAGE);
                }
                finally
                {
                    try
                    {
                        con.close();
                    } catch (SQLException exception)
                    {
                        exception.printStackTrace();
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, type + " NOT Deleted");
            }

            new DataCenterFrame();
        }
        else
            new ModifyFrame(type);
    }
}
