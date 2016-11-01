
package br.com.brsantiago.sjcnews.load;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brsantiago.sjcnews.model.service.parses.impl.ItemParser;

@Component
public class NewsStarter {

	@Autowired
	private ItemParser service;

	@PostConstruct
	public void run() {
		service.parse();
	}

}
