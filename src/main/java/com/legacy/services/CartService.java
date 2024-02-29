package com.legacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.legacy.domain.Cart;
import com.legacy.repo.CartRepo;

@Service

public class CartService {

	private CartRepo cartRepo;
	private Cart updatedName;
	private Cart updatedPrice;
	private Cart updatedQuantity;

	public CartService(CartRepo cartRepo) {
		super();

		this.cartRepo = cartRepo;
	}

	public ResponseEntity<Cart> createCart(Cart newCart) {

		Cart created = this.cartRepo.save(newCart);
		return new ResponseEntity<Cart>(created, HttpStatus.CREATED);
	}

	public List<Cart> getCart() {
		return this.cartRepo.findAll();

	}

	public boolean deleteCart(int id) {
		this.cartRepo.deleteById(id);
		return !this.cartRepo.existsById(id);
	}

	public ResponseEntity<Cart> updateCart(int id, Cart updatedCart) {

		Optional<Cart> found = this.cartRepo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
		}

		Cart existing = found.get();

		if (updatedCart.getName() != null) {
			existing.setName(updatedName.getName());
		}

		if (updatedCart.getPrice() != null) {
			existing.setPrice(updatedPrice.getPrice());
		}

		if (updatedCart.getQuantity() != 0) {
			existing.setQuantity(updatedQuantity.getQuantity());
		}

		Cart updated = this.cartRepo.save(existing);

		return ResponseEntity.ok(updated);
	}

}
