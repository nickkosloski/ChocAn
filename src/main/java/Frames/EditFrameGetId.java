package Frames;

import Utils.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class EditFrameGetId extends JPanel implements ActionListener
{
    String type = "";

    Button  enterBtn = new Button("Enter");

    TextField   idNumberTxt = new TextField("", 9);

    JLabel idLabel = new JLabel("ID Number (9 Characters)");

    public EditFrameGetId(String type)
    {
        this.setLayout(new GridLayout(19,1));

        this.type = type;

        this.add(idLabel);

        idNumberTxt.setEditable(true);
        this.add(idNumberTxt);

        enterBtn.addActionListener(this);
        enterBtn.setActionCommand("enter");
        this.add(enterBtn);

        this.setSize(500, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        String idNum = idNumberTxt.getText();
        Boolean isValid;

        if(actionCommand.equals("enter"))
        {
            Connection con = DatabaseHelper.connectToDb();
            try
            {
                PreparedStatement stmt;
                String queryStmt = "select * from " + type + " where ";

                if(type.equalsIgnoreCase("Member"))
                    queryStmt += "MemberId = " + idNum + ";";
                else
                    queryStmt += "ProviderId = " + idNum + ";";

                stmt = con.prepareStatement(queryStmt);
                ResultSet rs = stmt.executeQuery();

                isValid = rs.next();

                if(isValid)
                    new EditFrame(type, idNum);
                else
                    JOptionPane.showMessageDialog(this, "ID Not found", "Error Message", ERROR_MESSAGE);
            }
            catch(Exception exception)
            {
                JOptionPane.showMessageDialog(this, exception);
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
