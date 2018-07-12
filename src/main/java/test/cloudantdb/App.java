package test.cloudantdb;

import java.io.IOException;

import com.cloudant.client.api.CloudantClient;

public class App 
{
    public static void main( String[] args )
    {
        cloudantconnection cloudantcon = new cloudantconnection();
        CloudantClient cloudantCon = cloudantcon.createConncetion();
        
        CloudantOperations insertData = new CloudantOperations();
        insertData.startOperations(cloudantCon);
        
        
    }
}
