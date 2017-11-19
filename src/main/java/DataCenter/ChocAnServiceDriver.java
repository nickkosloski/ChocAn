package DataCenter;

import Utils.Resources;

public class ChocAnServiceDriver {

    public static void main(String[] args)
    {

        //DataCenter.DataCenter chocAnCenter = new DataCenter.DataCenter();

        Resources res = new Resources();
        System.out.println(res.getUsername());
        System.out.println(res.getPassword());
        System.out.println(res.getConnection());
    }
}
