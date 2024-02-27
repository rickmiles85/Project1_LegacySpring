package repo;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
