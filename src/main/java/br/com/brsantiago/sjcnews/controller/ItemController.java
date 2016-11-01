package br.com.brsantiago.sjcnews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brsantiago.sjcnews.model.document.Item;
import br.com.brsantiago.sjcnews.model.repositories.ItemRepository;

@RestController
@RequestMapping("/api/item")
@ExposesResourceFor(Item.class)
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;

	@GetMapping
	public List<Item> getAll() {
		return itemRepository.findAll();
	}
}
