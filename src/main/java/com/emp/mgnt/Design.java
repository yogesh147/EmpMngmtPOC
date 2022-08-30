package com.emp.mgnt;

import java.util.Optional;

abstract class Employee {
	Employee() {
	}
}

public class Design extends Employee {

	public static void main(String[] args) {

		String name = Optional.of("").orElse("s");

		System.out.println(name);

	}
}
