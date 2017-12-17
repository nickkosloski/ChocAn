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

public class EditFrame extends JFrame implements ActionListener
{
    String type = "";
    String idNum = "";
    String[] statuses = {"Valid", "Suspended", "Canceled"};

    Button  enterBtn = new Button("Enter");

    TextField   fNameTxt = new TextField("", 10);
    TextField   lNameTxt = new TextField("", 15);
    TextField   addressTxt = new TextField("", 25);
    TextField   cityTxt = new TextField("", 14);
    TextField   stateTxt = new TextField("", 2);
    TextField   zipTxt = new TextField("", 5);
    TextField   idNumberTxt = new TextField("", 9);

    JLabel   message = new JLabel("Only fill out fields that need to be changed.");
    JLabel fNameLabel = new JLabel("First Name (10 characters)");
    JLabel lNameLabel = new JLabel("Last Name (15 characters)");
    JLabel addressLabel = new JLabel("Address (25 characters)");
    JLabel cityLabel = new JLabel("City (14 characters)");
    JLabel stateLabel = new JLabel("State (2 characters)");
    JLabel zipLabel = new JLabel("Zip Code (5 characters)");
    JLabel idLabel = new JLabel("ID Number (9 Characters)");
    JLabel statusLabel = new JLabel("Status (pick one)");

    public JComboBox statusList = new JComboBox(statuses);

    public EditFrame(String type, String idNum)
    {
        super("ChocAn Data Center");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(19,1));

        this.type = type;
        this.idNum = idNum;

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

        this.setSize(500, 500);
        this.setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));
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

                stmt = con.prepareStatement(queryStmt);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, type + " Successfully Edited");

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
        }
    }
}
