package Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DataCenterFrame implements ItemListener
{
    JPanel cards;
    String comboBoxItems[] = {"Add Member", "Edit Member", "Delete Member", "Add Provider", "Edit Provider", "Delete Provider", "Manager Login"};

    public void addComponentToPane(Container pane)
    {
        JPanel comboBoxPane = new JPanel();
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        JPanel addMember = new AddFrame("Member");
        JPanel editMember = new EditFrameGetId("Member");
        JPanel deleteMember = new DeleteFrame("Member");
        JPanel addProvider = new AddFrame("Provider");
        JPanel editProvider = new EditFrameGetId("Provider");
        JPanel deleteProvider = new DeleteFrame("Provider");
        JPanel managerLogin = new ManagerLoginFrame();

        cards = new JPanel(new CardLayout());
        cards.add(addMember, "Add Member");
        cards.add(editMember, "Edit Member");
        cards.add(deleteMember, "Delete Member");
        cards.add(addProvider, "Add Provider");
        cards.add(editProvider, "Edit Provider");
        cards.add(deleteProvider, "Delete Provider");
        cards.add(managerLogin, "Manager Login");

        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }

    public void itemStateChanged(ItemEvent event)
    {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)event.getItem());
    }

    public void createAndShowGUI()
    {
        JFrame frame = new JFrame("ChocAn Data Center");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DataCenterFrame dataCenter = new DataCenterFrame();
        dataCenter.addComponentToPane(frame.getContentPane());

        frame.setSize(new Dimension(500, 500));
        frame.setVisible(true);

    }

}
