package si.iitech.news_parser;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

public class TestObjectRepository extends CouchDbRepositorySupport<TestObject> {

	protected TestObjectRepository(CouchDbConnector db) {
		 super(TestObject.class, db);
	}

}
