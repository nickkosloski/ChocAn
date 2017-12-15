package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteFrame extends JFrame implements ActionListener, BasicFrame
{
    String type = "";

    public DeleteFrame(String type)
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,1));

        this.type = type;

        idLabel.setText(type + "ID Number");
        add(idLabel);

        idNumberTxt.setEditable(true);
        add(idNumberTxt);

        enterBtn.addActionListener(this);
        enterBtn.setActionCommand("enter");
        add(enterBtn);

        backBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        add(backBtn);

        setSize(500, 200);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));
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
                try
                {
                    Connection con = DriverManager.getConnection(res.getConnection(), res.getUsername(), res.getPassword());
                    Statement stmt = con.createStatement();

                    String queryStmt = "delete from " + type + "where ";

                    if(type.equalsIgnoreCase("Member"))
                        queryStmt += "MemberId = " + idNum + ";";
                    else
                        queryStmt += "ProviderId = " + idNum + ";";

                    stmt.executeQuery(queryStmt);
                    con.close();
                    JOptionPane.showMessageDialog(this, type + " Successfully Deleted");

                }
                catch(Exception exception)
                {
                    JOptionPane.showMessageDialog(this, "Unable to access database");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, type + " NOT Deleted");
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
