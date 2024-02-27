package rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Item;
import services.ItemService;

@RestController
@RequestMapping("/item")
@CrossOrigin

public class ItemController {

	private ItemService service;

	public ItemController(ItemService service) {
		super();
		this.service = service;

	}

	@PostMapping("/create")
	public ResponseEntity<Item> createItem(@RequestBody Item newItem) {
		return this.service.createItem(newItem);

	}

	@GetMapping("/get")
	public List<Item> getItems() {
		return this.service.getItem();
	}

	@DeleteMapping("/delete/{id}")
	public boolean deleteItem(@PathVariable int id) {
		return this.service.deleteItem(id);
	}
}
