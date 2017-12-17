package Terminal;
import Frames.BasicFrame;
import Frames.DataCenterFrame;
import Frames.ModifyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ServiceForm extends JFrame implements ActionListener, BasicFrame
{
    String type = "FORM";
    public ServiceForm(String Date){
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(19,1));

        this.type = type;

        message.setText("All fields are required");
        message.setEditable(false);
        message.setForeground(Color.RED);
        add(message);
/*
        add(currentTimeLabel);
        currentTimeTxt.setEditable(true);
        currentTimeTxt.setText(String.valueOf(System.currentTimeMillis()));     //enter the time when writing to the database
        add(currentTimeTxt);
*/
        add(dateProvided);
        dateProvidedTxt.setEditable(true);
        dateProvidedTxt.setText(Date);
        add(dateProvidedTxt);

        add(providerNumLabel);
        providerNumTxt.setEditable(true);
        add(providerNumTxt);

        add(memberNumLabel);
        memberNumTxt.setEditable(true);
        add(memberNumTxt);

        add(fNameLabel);
        fNameTxt.setEditable(true);
        add(fNameTxt);

        add(lNameLabel);
        lNameTxt.setEditable(true);
        add(lNameTxt);

        add(serviceLabel);
        ServiceTxt.setEditable(true);
        add(ServiceTxt);

        add(feeLabel);
        feeTxt.setEditable(true);
        add(feeTxt);

        add(commentsLabel);
        CommentsTxt.setEditable(true);
        add(CommentsTxt);


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
            new DataCenterFrame();
        }
        else
        {
            dispose();
            new ModifyFrame(type);
        }
    }
}
