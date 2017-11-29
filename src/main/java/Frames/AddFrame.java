package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddFrame extends JFrame implements ActionListener, BasicFrame
{
    String type = "";

    public AddFrame(String type)
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(19,1));

        this.type = type;

        message.setText("All fields are required");
        message.setEditable(false);
        message.setForeground(Color.RED);
        add(message);

        add(idLabel);
        idNumberTxt.setEditable(true);
        add(idNumberTxt);

        add(fNameLabel);
        fNameTxt.setEditable(true);
        add(fNameTxt);

        add(lNameLabel);
        lNameTxt.setEditable(true);
        add(lNameTxt);

        add(addressLabel);
        addressTxt.setEditable(true);
        add(addressTxt);

        add(cityLabel);
        cityTxt.setEditable(true);
        add(cityTxt);

        add(stateLabel);
        stateTxt.setEditable(true);
        add(stateTxt);

        add(zipLabel);
        zipTxt.setEditable(true);
        add(zipTxt);

        if(type.equals("Member"))
        {
            add(statusLabel);
            JComboBox statusList = new JComboBox(statuses);
            statusList.setBackground(Color.WHITE);
            add(statusList);
        }

        enterBtn.addActionListener(this);
        enterBtn.setActionCommand("enter");
        add(enterBtn);

        backBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        add(backBtn);

        setSize(500, 500);
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
            try
            {
                Connection con = DriverManager.getConnection(res.getConnection(), res.getUsername(), res.getPassword());
                Statement stmt = con.createStatement();
                String queryStmt = "insert into " + type + "(";

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
                queryStmt += "'" + fNameTxt.getText();
                queryStmt += "," + lNameTxt.getText();
                queryStmt += "," + addressTxt.getText();
                queryStmt += "," + cityTxt.getText();
                queryStmt += "," + stateTxt.getText();
                queryStmt += "," + zipTxt.getText();

                if(type.equalsIgnoreCase("Member"))
                    queryStmt += ", valid);";
                else
                    queryStmt += ");";

                stmt.executeQuery(queryStmt);
                con.close();

                JOptionPane.showMessageDialog(this, type + " Successfully Added");
            }
            catch(Exception exception)
            {
                JOptionPane.showMessageDialog(this, "Unable to add" + type + "\nDue to" + exception.getMessage());
            }

            dispose();
            new DataCenter();
        }
        else
        {
            dispose();
            new ModifyFrame(type);
        }
    }
}
