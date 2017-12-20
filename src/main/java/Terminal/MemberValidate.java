package Terminal;
import Utils.DatabaseHelper;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;


public class MemberValidate {
    Scanner sys = new Scanner(System.in);
    String type = "MEMBER";
    Connection con = DatabaseHelper.connectToDb();

    public MemberValidate() throws SQLException, IOException {
        boolean run = true;

        while (run) {
            System.out.println("Make a selection:");
            System.out.println("1) Enter a member \n2) Show providers weekly report for validation\n3) Get a list of services");
            int selection = sys.nextInt();
            if (selection == 1) {
                System.out.print("Please Swipe Members Card:");
                String memNum = sys.next();


                try {
                    Statement stmt = con.createStatement();
                    String queryStmt = "select * from " + type + " where MemberId =  '" + memNum + "'";


                    ResultSet rs = stmt.executeQuery(queryStmt);
                    boolean result = rs.next();
                    if (result) {//(rs != null) {

                        String status = rs.getString("Status");
                        if (status.equalsIgnoreCase("Valid     ")) {
                            System.out.println("VALIDATED");
                            System.out.println("Enter Date:");
                            String curDate = sys.next();
                            if (!curDate.equalsIgnoreCase("")) {
                                String memID = rs.getString("MemberId");
                                String code = validateServiceCode();

                                ServiceForm entry = new ServiceForm(curDate, code, memID);
                                run = false;
                            }

                        } else {
                            System.out.println("Members status is " + status);
                            if (status.equals("Suspended ")) {
                                System.out.println("Member owes outstanding fees in the amount of: " + rs.getString("Fees"));
                            }
                        }
                    } else {// if(!rs.next()){
                        System.out.println("Member does not exist");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if (selection == 2) {


                System.out.println("Enter a provider Number");
                String provNum = sys.next();
                Statement stmt = con.createStatement();
                String queryStmt = "select * from PROVIDER where ProviderId =  '" + provNum + "'";
                ResultSet rs = stmt.executeQuery(queryStmt);

                boolean result = rs.next();
                while(!result) {
                    System.out.println("That is not a valid Provider number, try again:");
                    provNum = sys.next();       //broken for some reason
                    result = rs.next();
                }

                providerValidation providerVal = new providerValidation(provNum);
            }
            else if (selection ==3){
                GetList list = new GetList();
            }
            else{
                System.out.println("you done screwed the pooch son");
            }
        }
    }

    public String validateServiceCode(){
        String code = "";
        Boolean loop = true, codeVal = true;
        try {

            System.out.println("Enter the service code");
            code = sys.next();
            Statement stmt = con.createStatement();

            String queryStmt = "select * from SERVICE where ServiceCode =  '" + code + "'";

            ResultSet rs = stmt.executeQuery(queryStmt);
            while(codeVal) {


                if (!rs.next()) {
                    System.out.println("Code is invalid, Try again:");
                    code = sys.next();
                }
                else {

                    System.out.println("Code entered is : " + code + "\nservice is: " + rs.getString("Description"));
                    System.out.println("Is this the correct service?");
                    String yesNo = sys.next();
                    while (loop) {
                        if (!yesNo.equalsIgnoreCase("y")) {
                            System.out.println("This is not a correct choice (try again):");
                            yesNo = sys.next();
                        }
                        else {

                            loop = false;
                            codeVal = false;
                        }
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return code;
    }

}



/*
INSERT INTO TABLE_NAME(
  date_column
)values(
  TO_DATE('06/06/2006', 'mm/dd/yyyy')
)
 */
