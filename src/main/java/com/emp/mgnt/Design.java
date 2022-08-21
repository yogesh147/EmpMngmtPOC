package com.emp.mgnt;

import java.util.Optional;

public class Design {

	public static void main(String[] args) {

		String name = Optional.of("").orElse("s");

		System.out.println(name);

	}
}
