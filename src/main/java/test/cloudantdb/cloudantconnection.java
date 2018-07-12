package test.cloudantdb;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

public class cloudantconnection {
	
	public CloudantClient createConncetion(){
		CloudantClient client = ClientBuilder.account("cludant_username")
	            .username("cludant_username")
	            .password("cloudant_password")
	            .build();
		
		return client;
	}
	
}
