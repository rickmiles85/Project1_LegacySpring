package services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import domain.Item;
import repo.ItemRepo;

@Service
public class ItemService {

	// Check this works once Constructor is Set

	private ItemRepo itemRepo;

	public ItemService(ItemRepo itemRepo) {
		super();

		this.itemRepo = itemRepo;
	}

	public ResponseEntity<Item> createItem(Item newItem) {

		Item created = this.itemRepo.save(newItem);
		return new ResponseEntity<Item>(created, HttpStatus.CREATED);
	}

	public List<Item> getItem() {
		return this.itemRepo.findAll();

	}
}
