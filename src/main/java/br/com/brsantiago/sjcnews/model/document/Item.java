package br.com.brsantiago.sjcnews.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Item {

	@Id
	private String guid;
	private String link;
	private String title;
	private String content;
	private String foto;
	private String creditoFotografo;
	private String legendaFoto;
	private String pubDate;

	public String getGuid() {
		return this.guid;
	}
	public void setGuid(final String guid) {
		this.guid = guid;
	}
	public String getLink() {
		return this.link;
	}
	public void setLink(final String link) {
		this.link = link;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(final String content) {
		this.content = content;
	}
	public String getFoto() {
		return this.foto;
	}
	public void setFoto(final String foto) {
		this.foto = foto;
	}

	public String getCreditoFotografo() {
		return this.creditoFotografo;
	}
	public void setCreditoFotografo(final String creditoFotografo) {
		this.creditoFotografo = creditoFotografo;
	}
	public String getLegendaFoto() {
		return this.legendaFoto;
	}
	public void setLegendaFoto(final String legendaFoto) {
		this.legendaFoto = legendaFoto;
	}
	public String getPubDate() {
		return this.pubDate;
	}
	public void setPubDate(final String pubDate) {
		this.pubDate = pubDate;
	}

	@Override
	public String toString() {
		return "Title: " + title + "\n Content: " + content;
	}
}
