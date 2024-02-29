package com.legacy.services;

import com.legacy.domain.Cart;

public class App {

	public static void main(String[] args) {

		Cart myCart = new Cart("Amazon");

		mycart.add(new Phone("Nokia", "555.00", 1));

		System.out.println(myCart.toString());
		System.out.println("Bill: " + myCart.calcBill());

	}

}