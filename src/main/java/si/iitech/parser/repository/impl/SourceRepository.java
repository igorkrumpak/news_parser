package si.iitech.parser.repository.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.ektorp.AttachmentInputStream;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import si.iitech.parser.object.impl.Source;

@Component
public class SourceRepository extends CouchDbRepositorySupport<Source>
		implements ApplicationListener<ApplicationReadyEvent> {

	protected SourceRepository(@Qualifier("sources") CouchDbConnector db) {
		super(Source.class, db);
		initStandardDesignDocument();
	}

	@GenerateView
	public List<Source> findByName(String name) {
		return queryView("by_name", name);
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// Lets only try to update
		for (Source oldSource : getAll()) {
			this.remove(oldSource);
		}
		Source sloTech = Source.SLO_TECH;
		this.add(sloTech);

		try {
			AttachmentInputStream attachmentInputStream = new AttachmentInputStream("image",
					new FileInputStream(sloTech.getImage()), "image/png");
			db.createAttachment(sloTech.getId(), sloTech.getRevision(), attachmentInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
