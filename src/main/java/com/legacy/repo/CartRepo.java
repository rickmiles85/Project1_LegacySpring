package com.legacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.legacy.domain.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}
