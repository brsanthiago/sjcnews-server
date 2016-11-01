
package br.com.brsantiago.sjcnews.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.brsantiago.sjcnews.model.service.parses.impl.ItemParser;

@Component
public class NewsCron {

	@Autowired
	private ItemParser service;

	@Scheduled(cron = "0 * 7,9 * * *")
	@Scheduled(cron = "0 * 11,12 * * *")
	@Scheduled(cron = "0 * 16,17 * * *")
	public void run() {
		service.parse();
	}
}
