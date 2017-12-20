package Terminal;

import Utils.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class ServiceFee extends JFrame implements ActionListener {

    String[] statuses = {"Valid", "Suspended", "Canceled"};

    Button enterBtn = new Button("Enter");

    TextField dateTxt = new TextField("", 9);
    TextField providerNumTxt = new TextField("", 9);
    TextField memNumTxt = new TextField("", 9);
    TextField serviceCodeTxt = new TextField("", 6);
    TextField commentsTxt = new TextField("", 100);

    JLabel message = new JLabel("All fields are required");

    JLabel dateLabel = new JLabel("Date of Service (MM-DD-YYYY)");
    JLabel providerNumLabel = new JLabel("Enter Provider Number");
    JLabel memNumLabel = new JLabel("Enter Member Number");
    JLabel serviceCodeLabel = new JLabel("Service Code");
    JLabel commentsLabel = new JLabel("Comments (Optional)");

    public JComboBox statusList = new JComboBox(statuses);

    public ServiceFee(String Date, String code, String memID) {

            this.setLayout(new GridLayout(19, 1));


            message.setForeground(Color.RED);
            this.add(message);




            enterBtn.addActionListener(this);
            enterBtn.setActionCommand("enter");
            this.add(enterBtn);
            this.setSize(new Dimension(500, 500));
            this.setVisible(true);

        }

        public void actionPerformed( ActionEvent e)
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String actionCommand = e.getActionCommand();

            if (actionCommand.equals("enter")) {
                Connection con = DatabaseHelper.connectToDb();
                try {
                    PreparedStatement stmt;
                    String queryStmt = "insert into FORM (";


                    Date date = new Date();
                    System.out.println(); //2016/11/16 12:08:43

                    queryStmt += "CurrentDate, ServiceProvidedDate, ProviderId, MemberId, ServiceCode, Comments)";

                    queryStmt += "values ('" + dateFormat.format(date) ;
                    queryStmt += "','" + dateTxt.getText();
                    queryStmt += "','" + providerNumTxt.getText();
                    queryStmt += "','" + memNumTxt.getText();
                    queryStmt += "','" + serviceCodeTxt.getText();
                    queryStmt += "','" + commentsTxt.getText();
                    queryStmt += "');";

                    stmt = con.prepareStatement(queryStmt);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(this, " Successfully Added");

                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(this, "Unable to add " + "\nDue to " + exception.getMessage(), "Error Message", ERROR_MESSAGE);
                } finally {
                    try {
                        con.close();
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }
                dispose();
            }
        }
    }


