package DataCenter;

import Frames.DataCenterFrame;
import Terminal.MemberValidate;

public class ChocAnServiceDriver {

    public static void main(String[] args)
    {
        DataCenterFrame chocAnCenter = new DataCenterFrame();

        chocAnCenter.createAndShowGUI();
        MemberValidate  Terminal = new MemberValidate();
    }
}
