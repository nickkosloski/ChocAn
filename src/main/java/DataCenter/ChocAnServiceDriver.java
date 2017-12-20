package DataCenter;

import Frames.DataCenterFrame;
import Terminal.MemberValidate;

public class ChocAnServiceDriver {

    public static void main(String[] args)
    {
        DataCenterFrame chocAnCenter = new DataCenterFrame();
        MemberValidate  Terminal = new MemberValidate();
        chocAnCenter.createAndShowGUI();
    }
}
