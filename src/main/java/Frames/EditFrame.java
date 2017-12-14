package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EditFrame extends JFrame implements ActionListener, BasicFrame
{
    String type = "";

    public EditFrame(String type, String idNum)
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(18,1));

        this.type = type;

        message.setText("Only fill out fields that need to be changed.");
        message.setEditable(false);
        message.setForeground(Color.RED);
        add(message);

        TextField blank = new TextField("", 0);
        blank.setEditable(false);
        add(blank);

        add(idLabel);
        idNumberTxt.setText(idNum);
        idNumberTxt.setEditable(false);
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
                String queryStmt = "update " + type + "set";

                if(!fNameTxt.getText().equalsIgnoreCase(""))
                    queryStmt += "FName = " + fNameTxt.getText();

                if(!lNameTxt.getText().equalsIgnoreCase(""))
                    queryStmt += "LName = " + lNameTxt.getText();

                if(!addressTxt.getText().equalsIgnoreCase(""))
                    queryStmt += "Address = " + addressTxt.getText();

                if(!cityTxt.getText().equalsIgnoreCase(""))
                    queryStmt += "City = " + cityTxt.getText();

                if(!stateTxt.getText().equalsIgnoreCase(""))
                    queryStmt += "State = " + stateTxt.getText();

                if(!zipTxt.getText().equalsIgnoreCase(""))
                    queryStmt += "ZipCode = " + zipTxt.getText();

                if(type.equalsIgnoreCase("Member"))
                    queryStmt += "Status = valid";

                queryStmt += ";";

                stmt.executeQuery(queryStmt);
                con.close();

                JOptionPane.showMessageDialog(this, type + " Successfully Added");
            }
            catch(Exception exception)
            {
                JOptionPane.showMessageDialog(this, "Unable to add" + type + "\nDue to" + exception.getMessage());
            }

            dispose();
            new DataCenterFrame();
        }
        else
        {
            dispose();
            new EditFrameGetId(type);
        }
    }
}
