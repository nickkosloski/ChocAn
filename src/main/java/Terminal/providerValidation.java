package Terminal;

import Utils.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class providerValidation extends JFrame{

    Connection con = DatabaseHelper.connectToDb();

    JTextArea commentsTxt = new JTextArea(500,800);

    JLabel message = new JLabel(" ");


    public providerValidation(String provNum) throws SQLException {
        String queryLine = "";

        //this.setLayout(new GridLayout(19, 1));



        queryLine = formatQueryLine(provNum);



        message.setForeground(Color.RED);
        this.add(message);
        commentsTxt.setText(queryLine);
        this.add(commentsTxt);
        this.setSize(new Dimension(500, 500));
        this.setVisible(true);


            }


    public String formatQueryLine(String provNum) throws SQLException {
        String queryLine = "";
        Statement stmt = con.createStatement();
        String queryStmt = "select * from FORM where ProviderId =  '" + provNum + "'";
        ResultSet rs = stmt.executeQuery(queryStmt);

        while(rs.next())
        {
            queryLine += "Date Provided: " + rs.getString("ServiceProvidedDate") + " Member ID: " + rs.getString("MemberId") + " Service Code: " + rs.getString("ServiceCode") + "\n";

        }

        return queryLine;
    }
        }
