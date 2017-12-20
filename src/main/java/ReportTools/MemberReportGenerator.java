package ReportTools;

import Models.MemberReport;
import Models.ServiceEntry;
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

public class MemberReportGenerator {

    public void generate() throws SQLException, IOException {
        boolean newMember = true;

        String memberQuery = "select [mem].[MemberId], " +
                "[mem].[FName] as MFName, " +
                "[mem].[LName] as MLName, " +
                "[mem].[Address], " +
                "[mem].[City], " +
                "[mem].[State], " +
                "[mem].[ZipCode], " +
                "CONVERT(VARCHAR(10),[fm].[ServiceProvidedDate],110) as [ServiceProvidedDate], " +
                "[prov].[FName] as PFName, " +
                "[prov].[LName] as PLName, " +
                "[serv].[Description] " +
                "from [FORM] [fm] " +
                "join [Member] [mem] " +
                "on [fm].[MemberId] = [mem].[MemberId] " +
                "join [Provider] [prov] " +
                "on [fm].[ProviderId] = [prov].[ProviderId] " +
                "join [Service] [serv] " +
                "on [fm].[ServiceCode] = [serv].[ServiceCode] " +
                "where [fm].[ServiceProvidedDate] >= DATEADD (week , -1 , GETDATE() ) " +
                "order by [mem].[MemberId], [fm].[ServiceProvidedDate]";

        Connection con = DatabaseHelper.connectToDb();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(memberQuery);
        if(!rs.next()) return;
        while (newMember) {
            String memberId;
            MemberReport report = new MemberReport();
            List<ServiceEntry> serviceList = new ArrayList<>();
            // do the member info
            report.setNumber(rs.getString("MemberId"));
            report.setfName(rs.getString("MFName"));
            report.setlName(rs.getString("MLName"));
            report.setAddress(rs.getString("Address"));
            report.setCity(rs.getString("City"));
            report.setState(rs.getString("State"));
            report.setZipCode(rs.getString("ZipCode"));

            memberId = rs.getString("MemberId");
            while (rs.getString("MemberId").equalsIgnoreCase(memberId)) {
                // iterates all services until change in member id
                ServiceEntry entry = new ServiceEntry();
                entry.setServiceDate(rs.getString("ServiceProvidedDate"));
                entry.setProviderFName(rs.getString("PFName"));
                entry.setProviderLName(rs.getString("PLName"));
                entry.setServiceName(rs.getString("Description"));
                serviceList.add(entry);

                if(!rs.next()) {
                    newMember = false;
                    break;
                }
            }
            report.setServices(serviceList);
            //output report
            writeReportToFile(writeReportString(report), memberId);
        }

    }

    private String writeReportString(MemberReport data){
        String report;
        String newLine = System.getProperty("line.separator");

        report = "Chocoholics Anonymous" + newLine;
        report += "______________________" + newLine;
        report += "Member Info:" + newLine;
        report += "Name:    "
                + data.getfName().trim() + " "
                + data.getlName().trim() + newLine;
        report += "ID:      "
                + data.getNumber().trim() + newLine;
        report += "Address: "
                + data.getAddress().trim() + newLine;
        report += "City:    "
                + data.getCity().trim() + newLine;
        report += "State:   "
                + data.getState().trim() + newLine;
        report += "ZipCode: "
                + data.getZipCode().trim() + newLine;
        report += "______________________" + newLine;
        report += "Services Provided: " + newLine;

        //Services
        for(ServiceEntry service : data.getServices()) {
            report += newLine;
            report += "Date of Service: "
                    + service.getServiceDate().trim()
                    + newLine;
            report += "Provider: "
                    + service.getProviderFName().trim() + " " + service.getProviderLName().trim()
                    + newLine;
            report += "Service: "
                    + service.getServiceName().trim();
            report += newLine;
        }
        report += "______________________";
        return report;
    }

    private void writeReportToFile(String data, String memberId) throws IOException {
        // Create new file
        memberId = memberId.trim();
        makeReportDirectory();
        String path="C:\\ChocAn\\MemberReports\\" + memberId + ".txt";
        File file = new File(path);

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
        String path = "C:\\ChocAn\\MemberReports\\";

        File directory = new File(path);
        if (! directory.exists()){
            directory.mkdirs();
        }
    }
}
