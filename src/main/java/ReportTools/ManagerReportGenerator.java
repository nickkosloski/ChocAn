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
import java.util.ArrayList;
import java.util.List;

public class ManagerReportGenerator {

    public void generate() throws SQLException, IOException {

        String managerQuery = "select [ProviderId],"
                + "Count([ProviderId]) as [ProviderConsultationTotal]"
                //+ "--Sum([Fee]) as [ProviderTotalFee],"
                + "FROM [FORM]"
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
//                providerWeek.setTotalFee(rs.getDouble("ProviderTotalFee"));
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
                    + data.getTotalWeeklyConsultations().toString().trim() + newLine;
            report += "Total Fee to Provider: " + "$" + "2.00"
                    + newLine + newLine;
                    // TODO: Implement Fee
                    // + data.getTotalWeeklyFee().toString().trim() + newLine;
        }

        report += "________________________________________" + newLine;

        report += "Total Number of Providers:     "
                + data.getProviderTotal().toString().trim()
                + newLine;
        report += "Total Number of Consultations: "
                + data.getTotalWeeklyConsultations().toString().trim()
                + newLine;
        report += "Total Weekly Fee:              " + "$" + "2"
                //+ data.getTotalWeeklyFee().toString().trim() //TODO: Implement Fee
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
