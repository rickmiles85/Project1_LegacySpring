package com.legacy.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legacy.domain.Cart;
import com.legacy.services.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin

public class CartController {

	private CartService service;

	public CartController(CartService service) {
		super();
		this.service = service;

	}

	@PostMapping("/create")
	public ResponseEntity<Cart> createCart(@RequestBody Cart newCart) {
		return this.service.createCart(newCart);

	}

	@GetMapping("/get")
	public List<Cart> getCart() {
		return this.service.getCart();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Cart> getCart(@PathVariable int id) {
		return this.service.getCart(id);
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<Cart> update(@PathVariable int id, @RequestBody Cart newCart) {
		return this.service.updateCart(id, newCart);
	}

	@DeleteMapping("/delete/{id}")
	public boolean deleteCart(@PathVariable int id) {
		return this.service.deleteCart(id);
	}
}
