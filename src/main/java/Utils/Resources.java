package Utils;

import DataCenter.ChocAnServiceDriver;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Resources
{
     private String connString,
            uName,
            uPass;
    private InputStream input;

    public Resources()
    {
       try {
           Properties prop = new Properties();
           input = ChocAnServiceDriver.class.getResourceAsStream("/Config.properties");

           prop.load(input);

           connString = prop.getProperty("databaseConnectionString");
           uName = prop.getProperty("dbusername");
           uPass = prop.getProperty("dbpassword");
       }
       catch (IOException ex){
           ex.printStackTrace();
       }
       finally
       {
            if(input != null)
            {
                try
                {
                    input.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
       }
    }

    public String getUsername()
    {
        return uName;
    }

    public String getPassword()
    {
        return uPass;
    }

    public String getConnection()
    {
        return connString;
    }
}
