package DataCenter;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;

public class DataCenter extends JFrame implements ActionListener
{
    String host = "jdbc:sqlserver://chocan.database.windows.net:1433;database=ChocAnDatabase;user=shyde@chocan;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
    String uName = "shyde";
    String uPass = "ILoveChoc1";

    private Button  modifyMemberBtn,
                    modifyProviderBtn,
                    addBtn,
                    editBtn,
                    deleteBtn,
                    backBtn,
                    enterBtn;

    private TextField   fNameTxt,
                        lNameTxt,
                        addressTxt,
                        cityTxt,
                        stateTxt,
                        zipTxt,
                        idNumberTxt,
                        message;

    private String[] statuses = {"Valid", "Suspended", "Canceled"};

    public DataCenter()
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2));

        modifyMemberBtn = new Button("Modify Model.Member");
        modifyMemberBtn.addActionListener(this);
        modifyMemberBtn.setActionCommand("modifyMember");
        add(modifyMemberBtn);

        modifyProviderBtn = new Button("Modify Model.Provider");
        modifyProviderBtn.addActionListener(this);
        modifyProviderBtn.setActionCommand("modifyProvider");
        add(modifyProviderBtn);

        setSize(500, 500);
        setVisible(true);

        try
        {
            Connection con = DriverManager.getConnection(host, uName, uPass);
        }
        catch(SQLException e)
        {
            System.out.println("Test");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        dispose();

        if(actionCommand.equals("modifyMember"))
            new ModifyFrame("Model.Member");
        else if(actionCommand.equals("modifyProvider"))
            new ModifyFrame("Model.Provider");
    }

    public class ModifyFrame extends JFrame implements ActionListener
    {
        String type = "";

        public ModifyFrame(String type)
        {
            super("ChocAn Data Center");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(2,2));

            this.type = type;

            addBtn = new Button("Add " + type);
            addBtn.addActionListener(this);
            addBtn.setActionCommand("add");

            add(addBtn);

            editBtn = new Button("Edit " + type);
            editBtn.addActionListener(this);
            editBtn.setActionCommand("edit");
            add(editBtn);

            deleteBtn = new Button("Delete " + type);
            deleteBtn.addActionListener(this);
            deleteBtn.setActionCommand("delete");
            add(deleteBtn);

            backBtn = new Button("Back");
            backBtn.addActionListener(this);
            backBtn.setActionCommand("back");
            add(backBtn);

            setSize(500, 500);
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String actionCommand = e.getActionCommand();
            dispose();

            if(actionCommand.equals("add"))
                new AddFrame(type);
            else if(actionCommand.equals("edit"))
                new EditFrame(type);
            else if(actionCommand.equals("delete"))
                new DeleteFrame(type);
            else
                new DataCenter();
        }
    }

    public class AddFrame extends JFrame implements ActionListener
    {
        String type = "";

        public AddFrame(String type)
        {
            super("ChocAn Data Center");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(10,1));

            this.type = type;

            message = new TextField("All fields are required");
            message.setEditable(false);
            message.setForeground(Color.RED);
            message.setBackground(Color.GRAY);
            add(message);

            fNameTxt = new TextField("First Name", 10);
            fNameTxt.setEditable(true);
            add(fNameTxt);

            lNameTxt = new TextField("Last Name", 15);
            lNameTxt.setEditable(true);
            add(lNameTxt);

            addressTxt = new TextField("Address", 25);
            addressTxt.setEditable(true);
            add(addressTxt);

            cityTxt = new TextField("City", 14);
            cityTxt.setEditable(true);
            add(cityTxt);

            stateTxt = new TextField("State", 2);
            stateTxt.setEditable(true);
            add(stateTxt);

            zipTxt = new TextField("Zip Code", 5);
            zipTxt.setEditable(true);
            add(zipTxt);

            if(type.equals("Model.Member"))
            {
                JComboBox statusList = new JComboBox(statuses);
                add(statusList);
            }

            enterBtn = new Button("Enter");
            enterBtn.addActionListener(this);
            enterBtn.setActionCommand("enter");
            add(enterBtn);

            backBtn = new Button("Back");
            backBtn.addActionListener(this);
            backBtn.setActionCommand("back");
            add(backBtn);

            setSize(500, 500);
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String actionCommand = e.getActionCommand();
            dispose();

            if(actionCommand.equals("enter"))
            {
                JOptionPane.showMessageDialog(this, type + " Successfully Added");
                new DataCenter();
            }
            else
            {
                new ModifyFrame(type);
            }
        }
    }

    public class EditFrame extends JFrame implements ActionListener
    {
        String type = "";

        public EditFrame(String type)
        {
            super("ChocAn Data Center");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(10,1));

            this.type = type;

            message = new TextField("Only fill out fields that need to be changed.");
            message.setEditable(false);
            message.setForeground(Color.RED);
            message.setBackground(Color.GRAY);
            add(message);

            idNumberTxt = new TextField(type + "ID Number");
            add(idNumberTxt);

            fNameTxt = new TextField("First Name", 10);
            fNameTxt.setEditable(true);
            add(fNameTxt);

            lNameTxt = new TextField("Last Name", 15);
            lNameTxt.setEditable(true);
            add(lNameTxt);

            addressTxt = new TextField("Address", 25);
            addressTxt.setEditable(true);
            add(addressTxt);

            cityTxt = new TextField("City", 14);
            cityTxt.setEditable(true);
            add(cityTxt);

            stateTxt = new TextField("State", 2);
            stateTxt.setEditable(true);
            add(stateTxt);

            zipTxt = new TextField("Zip Code", 5);
            zipTxt.setEditable(true);
            add(zipTxt);

            if(type.equals("Model.Member"))
            {
                JComboBox statusList = new JComboBox(statuses);
                add(statusList);
            }

            enterBtn = new Button("Enter");
            enterBtn.addActionListener(this);
            enterBtn.setActionCommand("enter");
            add(enterBtn);

            backBtn = new Button("Back");
            backBtn.addActionListener(this);
            backBtn.setActionCommand("back");
            add(backBtn);

            setSize(500, 500);
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String actionCommand = e.getActionCommand();
            dispose();

            if(actionCommand.equals("enter"))
            {
                JOptionPane.showMessageDialog(this, type + " Successfully Edited");
                new DataCenter();
            }
            else
            {
                new ModifyFrame(type);
            }
        }
    }

    public class DeleteFrame extends JFrame implements ActionListener
    {
        String type = "";

        public DeleteFrame(String type)
        {
            super("ChocAn Data Center");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(3,1));

            this.type = type;

            idNumberTxt = new TextField(type + "ID Number");
            add(idNumberTxt);

            enterBtn = new Button("Enter");
            enterBtn.addActionListener(this);
            enterBtn.setActionCommand("enter");
            add(enterBtn);

            backBtn = new Button("Back");
            backBtn.addActionListener(this);
            backBtn.setActionCommand("back");
            add(backBtn);

            setSize(500, 500);
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String actionCommand = e.getActionCommand();
            dispose();

            if(actionCommand.equals("enter"))
            {
                int n = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete " + idNumberTxt.getText() + "?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if(n == 0)
                    JOptionPane.showMessageDialog(this, type + " Successfully Deleted");
                else
                    JOptionPane.showMessageDialog(this, type + " NOT Deleted");

                new DataCenter();
            }
            else
            {
                new ModifyFrame(type);
            }
        }
    }
}
