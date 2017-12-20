package DataCenter;

import Frames.DataCenterFrame;
import Utils.ReportGenerator;

import java.util.Calendar;
import java.util.Timer;

public class ChocAnServiceDriver {

    public static void main(String[] args)
    {
        DataCenterFrame chocAnCenter = new DataCenterFrame();
        chocAnCenter.createAndShowGUI();
        scheduleReports();
    }

    private static void scheduleReports(){
        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        date.set(
                Calendar.DAY_OF_WEEK,
                Calendar.FRIDAY
        );
        date.set(Calendar.HOUR, 21);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        // Schedule to run every Sunday in midnight
        timer.schedule(
                new ReportGenerator(),
                date.getTime(),
                1000 * 60 * 60 * 24 * 7
        );
    }
}
