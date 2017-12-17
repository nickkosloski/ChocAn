package Frames;

import Utils.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class AddFrame extends JPanel implements ActionListener
{
    String type = "";
    String[] statuses = {"Valid", "Suspended", "Canceled"};

    Button  enterBtn = new Button("Enter");

    TextField   fNameTxt = new TextField("", 10);
    TextField   lNameTxt = new TextField("", 15);
    TextField   addressTxt = new TextField("", 25);
    TextField   cityTxt = new TextField("", 14);
    TextField   stateTxt = new TextField("", 2);
    TextField   zipTxt = new TextField("", 5);
    TextField   idNumberTxt = new TextField("", 9);

    JLabel   message = new JLabel("All fields are required");
    JLabel fNameLabel = new JLabel("First Name (10 characters)");
    JLabel lNameLabel = new JLabel("Last Name (15 characters)");
    JLabel addressLabel = new JLabel("Address (25 characters)");
    JLabel cityLabel = new JLabel("City (14 characters)");
    JLabel stateLabel = new JLabel("State (2 characters)");
    JLabel zipLabel = new JLabel("Zip Code (5 characters)");
    JLabel idLabel = new JLabel("ID Number (9 Characters)");
    JLabel statusLabel = new JLabel("Status (pick one)");

    public JComboBox statusList = new JComboBox(statuses);

    public AddFrame(String type)
    {
        this.setLayout(new GridLayout(19,1));

        this.type = type;

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
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();

        if(actionCommand.equals("enter"))
        {
            Connection con = DatabaseHelper.connectToDb();
            try
            {
                PreparedStatement stmt;
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

                stmt = con.prepareStatement(queryStmt);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, type + " Successfully Added");

                idNumberTxt.setText("");
                fNameTxt.setText("");
                lNameTxt.setText("");
                addressTxt.setText("");
                cityTxt.setText("");
                stateTxt.setText("");
                zipTxt.setText("");
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
        }
    }
}
