package DataCenter;

import Frames.DataCenterFrame;
import Terminal.MemberValidate;

import java.sql.SQLException;

public class ChocAnServiceDriver {

    public static void main(String[] args)
    {
        DataCenterFrame chocAnCenter = new DataCenterFrame();

        chocAnCenter.createAndShowGUI();
        try {
            MemberValidate  Terminal = new MemberValidate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
