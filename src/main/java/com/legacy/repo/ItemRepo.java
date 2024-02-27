package com.legacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.legacy.domain.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
