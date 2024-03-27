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

	private ItemRepo repo;
	private CartRepo cartRepo;

	public ItemService(ItemRepo repo, CartRepo cartRepo) {
		super();
		this.repo = repo;
		this.setCartRepo(cartRepo);
	}

	public ResponseEntity<Item> createItem(Item newItem) {

		Item created = this.repo.save(newItem);
		return new ResponseEntity<Item>(created, HttpStatus.CREATED);
	}

	public List<Item> getItem() {
		return this.repo.findAll();
	}

	public ResponseEntity<Item> getItem(int id) {
		Optional<Item> found = this.repo.findById(id);
		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Item body = found.get();
		return ResponseEntity.ok(body);
	}

	public boolean deleteItem(int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public ResponseEntity<Item> updateItem(int id, Item updatedItem) {
		Optional<Item> found = this.repo.findById(id);

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
		Item updated = this.repo.save(existing);
		return ResponseEntity.ok(updated);
	}

	public ResponseEntity<Item> addItemToCart(int cartId, Item newItem) {
		Optional<Cart> optionalCart = cartRepo.findById(cartId);
		if (optionalCart.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Cart cart = optionalCart.get();

		if (optionalCart.isEmpty()) {

			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Optional<Item> found = this.repo.findById(newItem.getId());

		Item item = found.get();

		item.setCart(cart);
		Item savedItem = this.repo.save(item);

		return new ResponseEntity<>(savedItem, HttpStatus.CREATED);

	}

	public CartRepo getCartRepo() {
		return cartRepo;
	}

	public void setCartRepo(CartRepo cartRepo) {
		this.cartRepo = cartRepo;
	}

}
