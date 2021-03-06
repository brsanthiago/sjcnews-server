package br.com.brsantiago.sjcnews.model.service.parses.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.brsantiago.sjcnews.model.document.Item;
import br.com.brsantiago.sjcnews.model.repositories.ItemRepository;
import br.com.brsantiago.sjcnews.model.service.parses.AbstractParser;
import br.com.brsantiago.sjcnews.util.DateUtil;

@Component
public class ItemParser extends AbstractParser {

	private static final Logger LOGGER = LogManager.getLogger(ItemParser.class);

	private static final String AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) "
			+ "Chrome/33.0.1750.152 Safari/537.36";

	private static String TITLE = "title";
	private static String CONTENT = "content";
	private static String PHOTO = "foto";
	private static String AUTOR_PHOTO = "creditofotografo";
	private static String LEGENDA_PHOTO = "legendafoto";
	private static String LINK = "link";
	private static String GUID = "guid";
	private static String PUB_DATE = "pubDate";

	@Value("${url.feed}")
	private String baseUrl;

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public void parse() {

		try {
			Document rssDocument = Jsoup.connect(baseUrl)
					.ignoreContentType(true).parser(Parser.xmlParser())
					.userAgent(AGENT).get();

			final Elements elements = rssDocument.select("item");

			elements.forEach(element -> parseItem(element));

		} catch (IOException e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
		}
	}

	private void parseItem(final Element element) {
		final Item item = new Item();
		parseTitle(element, item);
		parseGuid(element, item);
		parseLink(element, item);
		parseContent(element, item);
		parseFoto(element, item);
		parseCreditoFotografo(element, item);
		parseLegendaFoto(element, item);
		parseData(element, item);
		saveItem(item);
	}

	private void parseTitle(final Element element, final Item item) {
		item.setTitle(getValue(element, TITLE).toString());
	}
	private void parseGuid(final Element element, final Item item) {
		item.setGuid(getValue(element, GUID).toString());
	}
	private void parseLink(final Element element, final Item item) {
		item.setLink(getValue(element, LINK).toString());
	}
	private void parseContent(final Element element, final Item item) {

		String content = "<html><body>";

		List<Element> elements = element.select(CONTENT);

		for (Element el : elements) {
			final List<TextNode> textNodes = el.textNodes();
			for (TextNode node : textNodes) {
				if (node.text() != null && !node.text().trim().isEmpty()) {
					content += node.text();
				}
			}
		}

		content += "</body></html>";

		item.setContent(content);
	}
	private void parseFoto(final Element element, final Item item) {

		String foto = getSubValue(element, CONTENT, PHOTO);

		if (!foto.isEmpty()) {
			foto = foto.split(".jpg")[0] + "g.jpg";
		}

		item.setFoto(foto);
	}
	private void parseCreditoFotografo(final Element element, final Item item) {
		item.setCreditoFotografo(
				getSubValue(element, CONTENT, AUTOR_PHOTO).toString());
	}
	private void parseLegendaFoto(final Element element, final Item item) {
		item.setLegendaFoto(
				getSubValue(element, CONTENT, LEGENDA_PHOTO).toString());
	}
	private void parseData(final Element element, final Item item) {
		try {
			final String data = DateUtil.getDate(getValue(element, PUB_DATE));
			item.setPubDate(data);
		} catch (ParseException e) {
			item.setPubDate("");
		}
	}

	private void saveItem(final Item item) {
		if (!itemRepository.findByGuid(item.getGuid()).isPresent()) {
			itemRepository.save(item);
		}
	}

	private String getValue(final Element element, final String tag) {
		if (tag != null && !tag.isEmpty()) {
			return element.select(tag).text();
		}
		return "";
	}

	private String getSubValue(final Element element, final String parent,
			final String tag) {
		if (element.select(parent).select(tag) != null
				&& !element.select(parent).select(tag).isEmpty()) {
			return element.select(parent).select(tag).first().text();
		}
		return "";
	}
}
