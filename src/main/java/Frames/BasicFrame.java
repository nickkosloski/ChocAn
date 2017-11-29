package Frames;

import Utils.Resources;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;

public interface BasicFrame
{
    public Button modifyMemberBtn = new Button("Modify Member");
    public Button  modifyProviderBtn = new Button("Modify Provider");
    public Button  backBtn = new Button("Back");
    public Button  enterBtn = new Button("Enter");
    public Button  tryAgainBtn = new Button("Try Again");
    public Button  addBtn = new Button();
    public Button  editBtn = new Button();
    public Button  deleteBtn = new Button();

    public TextField   fNameTxt = new TextField("", 10);
    public TextField   lNameTxt = new TextField("", 15);
    public TextField   addressTxt = new TextField("", 25);
    public TextField   cityTxt = new TextField("", 14);
    public TextField   stateTxt = new TextField("", 2);
    public TextField   zipTxt = new TextField("", 5);
    public TextField   idNumberTxt = new TextField("", 9);
    public TextField   message = new TextField();

    public JLabel fNameLabel = new JLabel("First Name (10 characters)");
    public JLabel lNameLabel = new JLabel("Last Name (15 characters)");
    public JLabel addressLabel = new JLabel("Address (25 characters)");
    public JLabel cityLabel = new JLabel("City (14 characters)");
    public JLabel stateLabel = new JLabel("State (2 characters)");
    public JLabel zipLabel = new JLabel("Zip Code (5 characters)");
    public JLabel idLabel = new JLabel("ID Number (9 Characters)");
    public JLabel statusLabel = new JLabel("Status (pick one)");

    public String[] statuses = {"Valid", "Suspended", "Canceled"};

    public Resources res = new Resources();
}
