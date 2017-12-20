package ReportTools;

import java.io.IOException;
import java.sql.SQLException;

public class ReportGeneratorProcessor  {
    private MemberReportGenerator memberReporter;
    private ProviderReportGenerator providerReporter;
    private ManagerReportGenerator managerReporter;

    public ReportGeneratorProcessor(){
        memberReporter = new MemberReportGenerator();
        providerReporter = new ProviderReportGenerator();
        managerReporter = new ManagerReportGenerator();
    }

    public void generateMemberReports() throws IOException, SQLException {
        memberReporter.generate();
    }

    public void generateProviderReports() throws IOException, SQLException {
        providerReporter.generate();
    }

    public void generateManagerReports() throws IOException, SQLException {
        managerReporter.generate();
    }



}
