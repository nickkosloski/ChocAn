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

public class EditFrame extends JFrame implements ActionListener, BasicFrame
{
    String type = "";
    String idNum = "";

    public EditFrame(String type, String idNum)
    {
        super("ChocAn Data Center");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(19,1));

        this.type = type;
        this.idNum = idNum;

        message.setText("Only fill out fields that need to be changed.");
        message.setEditable(false);
        message.setForeground(Color.RED);
        this.add(message);

        this.add(idLabel);
        idNumberTxt.setText(idNum);
        idNumberTxt.setEditable(false);
        this.add(idNumberTxt);

        this.add(fNameLabel);
        fNameTxt.setEditable(true);
        this.add(fNameTxt);

        this.add(lNameLabel);
        lNameTxt.setEditable(true);
        this.add(lNameTxt);

        this.add(addressLabel);
        addressTxt.setEditable(true);
        this.add(addressTxt);

        this.add(cityLabel);
        cityTxt.setEditable(true);
        this.add(cityTxt);

        this.add(stateLabel);
        stateTxt.setEditable(true);
        this.add(stateTxt);

        this.add(zipLabel);
        zipTxt.setEditable(true);
        this.add(zipTxt);

        if(type.equals("Member"))
        {
            this.add(statusLabel);
            statusList.setBackground(Color.WHITE);
            this.add(statusList);
        }

        enterBtn.addActionListener(this);
        enterBtn.setActionCommand("enter");
        this.add(enterBtn);

        backBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        this.add(backBtn);

        this.setSize(500, 500);
        this.setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.dispose();
        String actionCommand = e.getActionCommand();

        if(actionCommand.equals("enter"))
        {
            Connection con = DatabaseHelper.connectToDb();
            try
            {
                Statement stmt = con.createStatement();
                String queryStmt = "update " + type + " set ";

                if(!fNameTxt.getText().equalsIgnoreCase(""))
                    queryStmt += "FName = '" + fNameTxt.getText() + "' ";

                if(!lNameTxt.getText().equalsIgnoreCase(""))
                {
                    if(queryStmt.contains("="))
                        queryStmt += ",";
                    queryStmt += "LName = '" + lNameTxt.getText() + "' ";
                }

                if(!addressTxt.getText().equalsIgnoreCase(""))
                {
                    if (queryStmt.contains("="))
                        queryStmt += ",";
                    queryStmt += "Address = '" + addressTxt.getText() + "' ";
                }

                if(!cityTxt.getText().equalsIgnoreCase(""))
                {
                    if(queryStmt.contains("="))
                        queryStmt += ",";
                    queryStmt += "City = '" + cityTxt.getText() + "' ";
                }

                if(!stateTxt.getText().equalsIgnoreCase(""))
                {
                    if(queryStmt.contains("="))
                        queryStmt += ",";
                    queryStmt += "State = '" + stateTxt.getText() + "' ";
                }

                    if(!zipTxt.getText().equalsIgnoreCase(""))
                {
                    if(queryStmt.contains("="))
                        queryStmt += ",";
                    queryStmt += "ZipCode = " + zipTxt.getText();
                }

                    if(type.equalsIgnoreCase("Member"))
                {
                    if(queryStmt.contains("="))
                        queryStmt += ",";

                    int option = statusList.getSelectedIndex();
                    queryStmt += "Status = '" + statuses[option] + "' ";
                }

                if(type.equalsIgnoreCase("Member"))
                    queryStmt += "where MemberId = " + idNum + ";";
                else
                    queryStmt += " where ProviderId = " + idNum + ";";

                stmt.executeUpdate(queryStmt);

                JOptionPane.showMessageDialog(this, type + " Successfully Edited");
            }
            catch(SQLException exception)
            {
                JOptionPane.showMessageDialog(this, "Unable to edit" + type + "\nDue to" + exception.getMessage(), "Error Message", ERROR_MESSAGE);
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
            new DataCenterFrame();
        }
        else
            new EditFrameGetId(type);
    }
}
