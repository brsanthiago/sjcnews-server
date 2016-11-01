
package br.com.brsantiago.sjcnews.starter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brsantiago.sjcnews.model.service.BotService;

@Component
public class StartNews {

	@Autowired
	private BotService service;

	@PostConstruct
	public void run() {
		service.run();
	}

}
