package Frames;

import Utils.Resources;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;

public interface BasicFrame
{
    public Button managerBtn = new Button("Manager Tools");
    public Button modifyBtn = new Button("Modify Members and Providers");
    public Button modifyMemberBtn = new Button("Modify Member");
    public Button  modifyProviderBtn = new Button("Modify Provider");
    public Button  backBtn = new Button("Back");
    public Button  enterBtn = new Button("Enter");
    public Button  tryAgainBtn = new Button("Try Again");
    public Button  addBtn = new Button();
    public Button  editBtn = new Button();
    public Button  deleteBtn = new Button();
    public Button  memberRptBtn = new Button("Member Report");
    public Button  providerRptBtn = new Button("Provider Report");
    public Button  weeklyMgrRptBtn = new Button("Weekly Manager Report");

    public TextField   fNameTxt = new TextField("", 10);
    public TextField   lNameTxt = new TextField("", 15);
    public TextField   addressTxt = new TextField("", 25);
    public TextField   cityTxt = new TextField("", 14);
    public TextField   stateTxt = new TextField("", 2);
    public TextField   zipTxt = new TextField("", 5);
    public TextField   idNumberTxt = new TextField("", 9);
    public TextField   message = new TextField();
    public TextField   usernameTxt = new TextField();
    public TextField   passwordTxt = new TextField();

    public JLabel fNameLabel = new JLabel("First Name (10 characters)");
    public JLabel lNameLabel = new JLabel("Last Name (15 characters)");
    public JLabel addressLabel = new JLabel("Address (25 characters)");
    public JLabel cityLabel = new JLabel("City (14 characters)");
    public JLabel stateLabel = new JLabel("State (2 characters)");
    public JLabel zipLabel = new JLabel("Zip Code (5 characters)");
    public JLabel idLabel = new JLabel("ID Number (9 Characters)");
    public JLabel statusLabel = new JLabel("Status (pick one)");
    public JLabel usernameLabel = new JLabel("Username");
    public JLabel passwordLabel = new JLabel("Password");

    public String[] statuses = {"Valid", "Suspended", "Canceled"};

    public Resources res = new Resources();
}
