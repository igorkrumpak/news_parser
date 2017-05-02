package si.iitech.news_parser;

import java.net.MalformedURLException;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

public class Main {

	public static void main(String[] args) {
		
		try {
			HttpClient httpClient = new StdHttpClient.Builder()
			        .url("https://couchdb-3594d0.smileupps.com")
			        .username("admin")
			        .password("4c0879193246")
			        .build();
			CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
			CouchDbConnector db = new StdCouchDbConnector("mydatabase", dbInstance);

			db.createDatabaseIfNotExists();
			
			TestObjectRepository repo = new TestObjectRepository(db);
			repo.add(new TestObject("rdeèa"));
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("test");
		
	}
}
