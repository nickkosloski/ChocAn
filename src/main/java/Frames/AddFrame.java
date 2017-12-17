package Frames;

import Utils.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class AddFrame extends JFrame implements ActionListener, BasicFrame
{
    String type = "";

    public AddFrame(String type)
    {
        super("ChocAn Data Center");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(19,1));

        this.type = type;

        message.setText("All fields are required");
        message.setEditable(false);
        message.setForeground(Color.RED);
        this.add(message);

        this.add(idLabel);
        idNumberTxt.setEditable(true);
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
                String queryStmt = "insert into " + type + " (";

                if(type.equalsIgnoreCase("Member"))
                    queryStmt += "MemberID, ";
                else
                    queryStmt += "ProviderId, ";

                queryStmt += "FName, LName, Address, City, State, ZipCode";

                if(type.equalsIgnoreCase("Member"))
                    queryStmt += ", Status)";
                else
                    queryStmt += ")";

                queryStmt += "values (" + idNumberTxt.getText();
                queryStmt += ",'" + fNameTxt.getText();
                queryStmt += "','" + lNameTxt.getText();
                queryStmt += "','" + addressTxt.getText();
                queryStmt += "','" + cityTxt.getText();
                queryStmt += "','" + stateTxt.getText();
                queryStmt += "'," + zipTxt.getText();

                if(type.equalsIgnoreCase("Member"))
                {
                    int option = statusList.getSelectedIndex();
                    queryStmt += ", '" + statuses[option] + "');";
                }
                else
                    queryStmt += ");";

                stmt.executeUpdate(queryStmt);

                JOptionPane.showMessageDialog(this, type + " Successfully Added");
            }
            catch(SQLException exception)
            {
                JOptionPane.showMessageDialog(this, "Unable to add " + type + "\nDue to " + exception.getMessage(),"Error Message", ERROR_MESSAGE);
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
            new ModifyFrame(type);
    }
}
