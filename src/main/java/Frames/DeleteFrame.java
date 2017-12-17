package Frames;

import Utils.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class DeleteFrame extends JPanel implements ActionListener
{
    String type = "";

    Button  enterBtn = new Button("Enter");

    TextField   idNumberTxt = new TextField("", 9);

    JLabel idLabel = new JLabel("ID Number (9 Characters)");


    public DeleteFrame(String type)
    {
        this.setLayout(new GridLayout(19,1));

        this.type = type;

        idLabel.setText(type + "ID Number");
        this.add(idLabel);

        idNumberTxt.setEditable(true);
        this.add(idNumberTxt);

        enterBtn.addActionListener(this);
        enterBtn.setActionCommand("enter");
        this.add(enterBtn);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
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
                    PreparedStatement stmt;

                    String queryStmt = "delete from " + type + " where ";

                    if(type.equalsIgnoreCase("Member"))
                        queryStmt += "MemberId='" + idNum + "';";
                    else
                        queryStmt += "ProviderId='" + idNum + "';";

                    stmt = con.prepareStatement(queryStmt);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, type + " Successfully Deleted");
                    idNumberTxt.setText("");

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
        }
    }
}
