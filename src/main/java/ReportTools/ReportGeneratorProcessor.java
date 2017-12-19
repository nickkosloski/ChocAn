package ReportTools;

public class ReportGeneratorProcessor  {
    private MemberReportGenerator memberReporter;
    private ProviderReportGenerator providerReporter;

    public ReportGeneratorProcessor(){
        memberReporter = new MemberReportGenerator();
        providerReporter = new ProviderReportGenerator();
    }

    public void generateMemberReports() {
        memberReporter.generateMemberReports();
    }

    public void generateProviderReports(){
        providerReporter.generateProviderReports();
    }



}
