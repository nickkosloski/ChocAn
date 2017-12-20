package Terminal;

import Utils.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class ServiceFee extends JFrame {
    Connection con = DatabaseHelper.connectToDb();

    TextField commentsTxt = new TextField("", 100);

    JLabel message = new JLabel(" ");




    public ServiceFee(String code) throws SQLException {
        Statement stmt = con.createStatement();
        String queryStmt = "select * from SERVICE where ServiceCode =  '" + code +"'";
        ResultSet rs = stmt.executeQuery(queryStmt);
        rs.next();
       String service = rs.getString("Description");
       String fee = rs.getString("Cost");
        this.setLayout(new GridLayout(19, 1));

        message.setForeground(Color.RED);
        this.add(message);
        commentsTxt.setText("Service:" + service + " has a fee of: " + fee);
        this.add(commentsTxt);
        this.setSize(new Dimension(500, 500));
        this.setVisible(true);




    }


}



