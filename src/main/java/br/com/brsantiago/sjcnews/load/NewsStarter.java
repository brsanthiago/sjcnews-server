
package br.com.brsantiago.sjcnews.load;

import javax.annotation.PostConstruct;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brsantiago.sjcnews.model.service.parses.impl.ItemParser;

@Component
public class NewsStarter {
	private final Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ItemParser service;

	@PostConstruct
	public void run() {
		service.parse();
	}

}
