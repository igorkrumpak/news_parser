package si.iitech.parser.repository.impl;

import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import si.iitech.parser.object.impl.Source;

@Component
public class SourceRepository extends CouchDbRepositorySupport<Source> {

	protected SourceRepository(@Qualifier("sources") CouchDbConnector db) {
		super(Source.class, db);
		initStandardDesignDocument();
	}

	@Override
	public void add(Source source) {
		List<Source> retrivedSources = findByName(source.getName());
		if (retrivedSources.isEmpty()) {
			super.add(source);
		} else if (retrivedSources.size() == 1) {
			Source retrivedSource = retrivedSources.get(0);
			if (!retrivedSource.equals(source)) {
				retrivedSource.mergeArticleSource(source);
				super.update(retrivedSource);
			}
		} else {
			new RuntimeException("there should not be more then one source with same title");
		}
	}

	@GenerateView
	public List<Source> findByName(String name) {
		return queryView("by_name", name);
	}

}
