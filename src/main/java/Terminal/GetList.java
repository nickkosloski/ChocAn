package Terminal;

import Utils.DatabaseHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GetList {
    Connection con = DatabaseHelper.connectToDb();
    Scanner sys = new Scanner(System.in);
    public GetList() throws SQLException, IOException {
        System.out.print("Please enter your Email address(just the beggining, no symbols):");
        String email = sys.next();

        Statement stmt = con.createStatement();
        String queryStmt = "select * from SERVICE";
        ResultSet rs = stmt.executeQuery(queryStmt);
        String report = writeReportString(rs);
        writeReportToFile(report, email);
    }

    private String writeReportString(ResultSet data) throws SQLException {
        String report = "";
        String newLine = System.getProperty("line.separator");
        report = "Current Service Codes" + newLine;
        report += "______________________" + newLine;
        while(data.next()){

            report += "Service Code:" + data.getString("ServiceCode") + newLine;
            report += "Description: " + data.getString("Description") + newLine;
            report += "Cost: " + data.getString("Cost") + newLine;
            report += "______________________" + newLine;
        }
        return report;
    }

    private void writeReportToFile(String data, String email) throws IOException {
        // Create new file

        makeReportDirectory();
        String path="C:\\ChocAn\\ServiceCodes\\" + email + ".txt";
        File file = new File(path);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(data);
        bw.close();
    }

    private void makeReportDirectory() {
        String path = "C:\\ChocAn\\ServiceCodes\\";

        File directory = new File(path);
        if (! directory.exists()){
            directory.mkdirs();
        }
    }
}
