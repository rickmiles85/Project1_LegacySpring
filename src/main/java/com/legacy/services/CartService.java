package com.legacy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.legacy.domain.Cart;
import com.legacy.domain.Item;
import com.legacy.repo.CartRepo;

@Service

public class CartService {

	private CartRepo cartRepo;

	private ArrayList<Item> items = new ArrayList<>();

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

	public ResponseEntity<Cart> getCart(int id) {
		Optional<Cart> found = this.cartRepo.findById(id);
		if (found.isEmpty()) {
			return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
		}
		Cart body = found.get();
		return ResponseEntity.ok(body);
	}

	public boolean deleteCart(int id) {
		this.cartRepo.deleteById(id);
		return !this.cartRepo.existsById(id);
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}
