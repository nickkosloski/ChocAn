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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4,1));

        this.type = type;

        addBtn.setLabel("Add " + type);
        addBtn.addActionListener(this);
        addBtn.setActionCommand("add");
        this.add(addBtn);

        editBtn.setLabel("Edit " + type);
        editBtn.addActionListener(this);
        editBtn.setActionCommand("edit");
        this.add(editBtn);

        deleteBtn.setLabel("Delete " + type);
        deleteBtn.addActionListener(this);
        deleteBtn.setActionCommand("delete");
        this.add(deleteBtn);

        backBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        this.add(backBtn);

        this.setSize(500, 500);
        this.setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((dim.width - getSize().width)/2),((dim.height - getSize().height)/2));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        this.dispose();

        if(actionCommand.equals("add"))
            new AddFrame(type);
        else if(actionCommand.equals("edit"))
            new EditFrameGetId(type);
        else if(actionCommand.equals("delete"))
            new DeleteFrame(type);
        else
            new ModifyMainFrame();
    }
}