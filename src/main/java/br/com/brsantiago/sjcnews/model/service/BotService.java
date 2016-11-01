/**
 *
 */
package br.com.brsantiago.sjcnews.model.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.brsantiago.sjcnews.model.service.parses.impl.ItemParser;

@Service
public class BotService {

	private final Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ItemParser itemParser;

	@Async
	public void run() {
		itemParser.parse();
	}

}
