package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyFrame extends JFrame implements ActionListener, BasicFrame
{
    String type = "";

    public ModifyFrame(String type)
    {
        super("ChocAn Data Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,2));

        this.type = type;

        addBtn.setLabel("Add " + type);
        addBtn.addActionListener(this);
        addBtn.setActionCommand("add");
        add(addBtn);

        editBtn.setLabel("Edit " + type);
        editBtn.addActionListener(this);
        editBtn.setActionCommand("edit");
        add(editBtn);

        deleteBtn.setLabel("Delete " + type);
        deleteBtn.addActionListener(this);
        deleteBtn.setActionCommand("delete");
        add(deleteBtn);

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
        dispose();

        if(actionCommand.equals("add"))
            new AddFrame(type);
        else if(actionCommand.equals("edit"))
            new EditFrameGetId(type);
        else if(actionCommand.equals("delete"))
            new DeleteFrame(type);
        else
            new DataCenter();
    }
}