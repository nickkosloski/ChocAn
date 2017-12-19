package ReportTools;

public class ReportGeneratorProcessor  {
    private MemberReportGenerator memberReporter;
    private ProviderReportGenerator providerReporter;
    private ManagerReportGenerator managerReporter;

    public ReportGeneratorProcessor(){
        memberReporter = new MemberReportGenerator();
        providerReporter = new ProviderReportGenerator();
        managerReporter = new ManagerReportGenerator();
    }

    public void generateMemberReports() {
        memberReporter.generateMemberReports();
    }

    public void generateProviderReports(){
        providerReporter.generateProviderReports();
    }

    public void generateManagerReports() {
        managerReporter.generateManagerReports();
    }



}
