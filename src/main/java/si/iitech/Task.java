package si.iitech;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {

	// @Autowired
	// private ArticleRepository articleRepository;

	@Scheduled(fixedRate = 5000)
	public void parseArticles() {

		System.out.println("TEST");

	}
}
