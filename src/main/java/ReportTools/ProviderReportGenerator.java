package ReportTools;

import Models.MemberReport;
import Models.ProviderReport;
import Models.ProviderService;
import Models.ServiceEntry;
import Utils.DatabaseHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProviderReportGenerator {
    public ProviderReportGenerator(){

    }
    public void generateProviderReports(){
        generate();
    }

    private void generate() {
        boolean newMember = true;

        String providerQuery = "select [prov].[FName] as [PFName]," +
                "[prov].[LName] as [PLName]," +
                "[prov].[ProviderId]," +
                "[prov].[Address]," +
                "[prov].[City]," +
                "[prov].[State]," +
                "[prov].[ZipCode]," +
                "CONVERT(varchar(10),[fm].[ServiceProvidedDate],110) + ' '" +
                        " + CONVERT(varchar(8), [fm].[ServiceProvidedDate], 108)" +
                " AS [ServiceProvidedDate]," +
                "[fm].[CurrentDate]," +
                "[mem].[FName] AS [MFName]," +
                "[mem].[LName] AS [MLName]," +
                "[mem].[MemberId]," +
                "[serv].[ServiceCode]" +
                "from [FORM] as [fm]" +
                "join [Member] [mem]" +
                "on [fm].[MemberId] = [mem].[MemberId]" +
                "join [Provider] [prov]" +
                "on [fm].[ProviderId] = [prov].[ProviderId]" +
                "join [Service] [serv]" +
                "on [fm].[ServiceCode] = [serv].[ServiceCode]" +
                "where [fm].[ServiceProvidedDate] >= DATEADD (week , -1 , GETDATE() )" +
                "order by [fm].[ServiceProvidedDate]";

        try {
            Connection con = DatabaseHelper.connectToDb();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(providerQuery);
            if(!rs.next()) return;
            while (newMember) {
                String providerId = rs.getString("ProviderId");
                ProviderReport report = new ProviderReport();
                List<ProviderService> serviceList = new ArrayList<ProviderService>();
                // do the member info
                report.setNumber(providerId);
                report.setFName(rs.getString("PFName"));
                report.setLName(rs.getString("PLName"));
                report.setAddress(rs.getString("Address"));
                report.setCity(rs.getString("City"));
                report.setState(rs.getString("State"));
                report.setZipCode(rs.getString("ZipCode"));

                while (rs.getString("ProviderId").equalsIgnoreCase(providerId)) {
                    // iterates all services until change in member id
                    ProviderService entry = new ProviderService();
                    entry.setServiceDate(rs.getString("ServiceProvidedDate"));
                    entry.setReceivedDateTime(rs.getString("CurrentDate"));
                    entry.setMemberFName(rs.getString("MFName"));
                    entry.setMemberLName(rs.getString("MLName"));
                    entry.setServiceCode(rs.getString("ServiceCode"));
                    //entry.setServiceFee(rs.getDouble("ServiceFee"));
                    serviceList.add(entry);

                    if(!rs.next()) {
                        newMember = false;
                        break;
                    }
                }
                report.setServiceList(serviceList);
                //output report
                writeReportToFile(writeReportString(report), providerId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String writeReportString(ProviderReport data){
        String report;
        String newLine = System.getProperty("line.separator");

        report = "Chocoholics Anonymous" + newLine;
        report += "______________________" + newLine;
        report += "Provider Info:" + newLine;
        report += "Name:    "
                + data.getFName().trim() + " "
                + data.getLName().trim() + newLine;
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
        for(ProviderService service : data.getServiceList()) {
            report += newLine;
            report += "Date of Service:      "
                    + service.getServiceDate().trim()
                    + newLine;
            report += "System Received Date: "
                    + service.getReceivedDateTime().trim()
                    + newLine;
            report += "Member Name:          "
                    + service.getMemberFName().trim() + " "
                    + service.getMemberLName().trim()
                    + newLine;
            report += "Service Code:         "
                    + service.getServiceCode().trim()
                    + newLine;
//            report += "Service Fee:          "
//                    + service.getServiceFee().toString().trim();
            report += newLine;
        }
        report += " _______________________" + newLine;
        report += "|Total Consultations: "
                + data.getNumConsultations() + " |"
                + newLine;
//        report += "|Total Fee: "
//                + data.getTotalFee() + " |"
//                + newLine;
        report += " _______________________";

        return report;
    }

    private void writeReportToFile(String data, String providerId){
        try{
            // Create new file
            providerId = providerId.trim();
            makeReportDirectory();
            String path="C:\\ChocAn\\ProviderReports\\" + providerId + ".txt";
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
        catch(Exception e){
            // TODO: Handle
            System.out.println(e);
        }

    }

    private void makeReportDirectory() {
        String path = "C:\\ChocAn\\ProviderReports\\";

        File directory = new File(path);
        if (! directory.exists()){
            directory.mkdirs();
        }
    }
}
