package Utils;

import ReportTools.ReportGeneratorProcessor;
import java.util.TimerTask;

public class ReportGenerator extends TimerTask {

    public void run(){
        ReportGeneratorProcessor processor = new ReportGeneratorProcessor();
        try {
            processor.generateManagerReports();
            processor.generateProviderReports();
            processor.generateMemberReports();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
