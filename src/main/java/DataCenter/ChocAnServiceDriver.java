package DataCenter;

import Frames.DataCenterFrame;
import Terminal.MemberValidate;

import java.io.IOException;
import java.sql.SQLException;
import Utils.ReportGenerator;

import java.util.Calendar;
import java.util.Timer;

public class ChocAnServiceDriver {

    public static void main(String[] args)
    {
        DataCenterFrame chocAnCenter = new DataCenterFrame();

        chocAnCenter.createAndShowGUI();
        try {
            MemberValidate  Terminal = new MemberValidate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scheduleReports();
    }

    private static void scheduleReports(){
        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        date.set(
                Calendar.DAY_OF_WEEK,
                Calendar.FRIDAY
        );
        date.set(Calendar.HOUR, 23);
        date.set(Calendar.MINUTE, 55);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        timer.schedule(
                new ReportGenerator(),
                date.getTime(),
                1000 * 60 * 60 * 24 * 7
        );
    }
}
