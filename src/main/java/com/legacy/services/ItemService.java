package com.legacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.legacy.domain.Cart;
import com.legacy.domain.Item;
import com.legacy.repo.CartRepo;
import com.legacy.repo.ItemRepo;

@Service

public class ItemService {

	private ItemRepo itemRepo;
	private CartRepo repo;

	public ItemService(ItemRepo itemRepo, CartRepo repo) {
		super();
		this.repo = repo;
		this.itemRepo = itemRepo;
	}

	public ResponseEntity<Item> createItem(Item newItem) {

		Item created = this.itemRepo.save(newItem);
		return new ResponseEntity<Item>(created, HttpStatus.CREATED);
	}

	public List<Item> getItem() {
		return this.itemRepo.findAll();
	}

	public ResponseEntity<Item> getItem(int id) {
		Optional<Item> found = this.itemRepo.findById(id);
		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Item body = found.get();
		return ResponseEntity.ok(body);
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
		if (updatedItem.getImage() != null) {
			existing.setImage(updatedItem.getImage());
		}
		Item updated = this.itemRepo.save(existing);
		return ResponseEntity.ok(updated);
	}

	public ResponseEntity<Item> addItemToCart(int cartId, Item newItem) {
		Optional<Cart> optionalCart = repo.findById(cartId);
		if (optionalCart.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Cart cart = optionalCart.get();

		if (optionalCart.isEmpty()) {

			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Optional<Item> found = this.itemRepo.findById(newItem.getId());

		Item item = found.get();

		item.setCart(cart);
		Item savedItem = this.itemRepo.save(item);

		return new ResponseEntity<>(savedItem, HttpStatus.CREATED);

	}

}
