/*This class created for basic operations in cloudant.
 * Insert a doc, Retrieved doc and Delete a doc
 * */

package test.cloudantdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;

public class CloudantOperations {
	
	String name = "John";
	int age = 19;
	String address = "12/2,6th lane,Australia";
	String id = "John_id";
	
	CloudantClient cloudantClientConnection = null;
	Database db = null;
	String dbDocRevID = null;
	public void startOperations(CloudantClient conn) {
		cloudantClientConnection = conn;
		db = conn.database("tetsdb", true);
		insertDatatoCloudant();
		getDatafromDb(id);
		deleteDocfromDb(id);
	}
	
	public void insertDatatoCloudant(){
		JSONObject createdDoc = createSampleDoc();
		Response dbResponse = db.save(createdDoc);
		System.out.println("Inserted " + createdDoc + " to database and db response :" + dbResponse );
	}
	
	public void getDatafromDb(String id){
		InputStream returnedDoc = db.find(id);
		String returnedDocString = convertInputStreamtoString(returnedDoc);
		JSONParser parser = new JSONParser(); 
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(returnedDocString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbDocRevID = (String) json.get("_rev");
		System.out.println("Retrived document : " +json);
		
	}
	
	public void deleteDocfromDb(String id){
		db.remove(id, dbDocRevID);
		System.out.println("Deleted a doc with id :" + id);
	}
	public JSONObject createSampleDoc(){
		//This document contain very basic details
		JSONObject  cloudantDoc = new JSONObject ();
		
		cloudantDoc.put("_id", id);
		cloudantDoc.put("name", name);
		cloudantDoc.put("age", age);
		cloudantDoc.put("address", address);
		
		return cloudantDoc;
	}
	
	public String convertInputStreamtoString(InputStream is){
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}
}
