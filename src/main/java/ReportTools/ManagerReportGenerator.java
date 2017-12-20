package ReportTools;

import Models.ManagerReport;
import Models.ProviderWeek;
import Utils.DatabaseHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ManagerReportGenerator {

    private DecimalFormat moneyFormat = new DecimalFormat("$##,###.00");

    public void generate() throws SQLException, IOException {

        String managerQuery = "select [ProviderId],"
                + "Count([ProviderId]) as [ProviderConsultationTotal],"
                + "Sum([Cost]) as [ProviderTotalFee]"
                + "FROM [FORM] [F]"
                + "JOIN [Service] [S]"
                + "on [F].[ServiceCode] = [S].[ServiceCode]"
                + "where [ServiceProvidedDate] >= DATEADD (week , -1 , GETDATE() )"
                + "group by [ProviderId]";

        Connection con = DatabaseHelper.connectToDb();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(managerQuery);

        ManagerReport report = new ManagerReport();
        List<ProviderWeek> providerList = new ArrayList<>();

        while (rs.next()) {
            ProviderWeek providerWeek = new ProviderWeek();
            providerWeek.setProviderId(rs.getString("ProviderId"));
            providerWeek.setTotalConsultations(rs.getInt("ProviderConsultationTotal"));
            providerWeek.setTotalFee(rs.getDouble("ProviderTotalFee"));
            providerList.add(providerWeek);
        }
        report.setProviderWeekList(providerList);
        //output report
        writeReportToFile(writeReportString(report));

    }

    private String writeReportString(ManagerReport data){
        String report;
        String newLine = System.getProperty("line.separator");

        report = "Chocoholics Anonymous - Weekly Report" + newLine;
        report += "________________________________________" + newLine;
        report += "Weekly Provider Info:" + newLine + newLine;

        for(ProviderWeek providerWeek : data.getProviderWeekList()) {
            report += "Provider ID:           "
                    + providerWeek.getProviderId()+ newLine;
            report += "Number Consultations:  "
                    + providerWeek.getTotalConsultations().toString().trim() + newLine;
            report += "Total Fee to Provider: "
                    + moneyFormat.format(providerWeek.getTotalFee()) + newLine + newLine;
        }

        report += "________________________________________" + newLine;

        report += "Total Number of Providers:     "
                + data.getProviderTotal().toString().trim()
                + newLine;
        report += "Total Number of Consultations: "
                + data.getTotalWeeklyConsultations().toString().trim()
                + newLine;
        report += "Total Weekly Fee:              "
                + moneyFormat.format(data.getTotalWeeklyFee())
                + newLine;
        report += "________________________________________";

        return report;
    }

    private void writeReportToFile(String data) throws IOException{

        // Create new file
        makeReportDirectory();
        String path="C:\\ChocAn\\ManagerReports\\" + "ManagerReport" + ".txt";
        File file = new File(path);

        // If file doesn't exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        // Write in file
        bw.write(data);

        // Close connection
        bw.close();
    }

    private void makeReportDirectory() {
        String path = "C:\\ChocAn\\ManagerReports\\";

        File directory = new File(path);
        if (! directory.exists()){
            directory.mkdirs();
        }
    }
}
