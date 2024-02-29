package com.legacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.legacy.domain.Item;
import com.legacy.repo.ItemRepo;

@Service

public class ItemService {

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

	public boolean deleteItem(int id) {
		this.itemRepo.deleteById(id);
		return !this.itemRepo.existsById(id);
	}

	public ResponseEntity<Item> updateItem(int id, Item updatedItem) {
		Optional<Item> found = this.itemRepo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Item existing = found.get();
		if (updatedItem.getName() != null) {
			existing.setName(updatedItem.getName());
		}
		if (updatedItem.getPrice() != null) {
			existing.setPrice(updatedItem.getPrice());
		}
		if (updatedItem.getQuantity() != 0) {
			existing.setQuantity(updatedItem.getQuantity());
		}
		Item updated = this.itemRepo.save(existing);
		return ResponseEntity.ok(updated);
	}

}
