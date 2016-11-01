
package br.com.brsantiago.sjcnews.model.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.brsantiago.sjcnews.model.document.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

	Optional<Item> findByGuid(final String guid);
	List<Item> findAll();
}
