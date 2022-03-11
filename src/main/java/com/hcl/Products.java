package com.hcl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


// Hadi Channaa version 

class Product {
	int id;
	String name;
	float price;

	public Product(int id, String name, float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[%d, %s, %d]", id, name, price);
	}
}

public class Products {
	static List<Product> list = new ArrayList<Product>();
	static int lastID = 6;

	public static void main(String[] args) {

		list.add(new Product(1, "Samsung A5", 17000f));
		list.add(new Product(3, "Iphone 6S", 65000f));
		list.add(new Product(2, "Sony Xperia", 25000f));
		list.add(new Product(4, "Nokia Lumia", 15000f));
		list.add(new Product(5, "Redmi4 ", 26000f));
		list.add(new Product(6, "Lenevo Vibe", 19000f));
		
		try (Scanner input = new Scanner(System.in);) {
			// main menu
			boolean menu = true;
			while (menu) {
				System.out.println("What would you like to do: Create, Read, Update, Delete?");
				String option = input.nextLine();
				switch(option.toLowerCase()) {
				case "create":
					listAdd(input);
					break;
				case "read":
					listRead();
					break;
				case "update":
					listUpdate(input);
					break;
				case "delete":
					listDelete(input);
					break;
				default:
					System.out.println("Invalid option");
					break;
				}
				menu = 	menuOperator(input, menu, "Would you like to do anything else? ");
			}
		}
		
		
		/*
		// using lambda to filter data
		Stream<Product> filtered_data = list.stream().filter(p -> p.price > 15000);
		// Stream<Product> filtered_data = list.stream().filter(p -> p.price > 25000);
		// using lambda to iterate through collection
		filtered_data.forEach(product -> System.out.println(product.name + ": " + product.price));
		*/
	}

	public static void listAdd(Scanner input) {
		System.out.println("insert Product Name");
		String name = input.nextLine();
		System.out.println("insert Product Price");
		float price = input.nextFloat();
		input.nextLine();
		
		lastID++;
		list.add(new Product(lastID, name, price));
		System.out.println("Added new product with ID " + lastID);
	}

	public static void listRead() {
		list.forEach(product -> System.out.println(product.name + ": " + product.price));
	}

	public static void listUpdate(Scanner input) {
		System.out.println("insert Product ID");
		int id = input.nextInt();
		input.nextLine();
		System.out.println("insert Product Name");
		String name = input.nextLine();
		System.out.println("insert Product Price");
		float price = input.nextFloat();
		input.nextLine();
		
		
		Stream<Product> filtered_data = list.stream().filter(p -> p.id == id);
		filtered_data.forEach(product -> {
			product.name = name;
			product.price = price;
		});
		System.out.println("Updated product with id: " + id);
	}

	public static void listDelete(Scanner input) {
		System.out.println("insert Product ID to delete");
		int id = input.nextInt();
		input.nextLine();
		if(id == lastID) {
			lastID--;
		}
		list.removeIf(p -> p.id == id);
		System.out.println("deleted product with id: " + id);
	}
	
	private static boolean menuOperator(Scanner input, boolean go, String message) {
		// loops operation menu
		System.out.println(message);
		boolean gogo = true;
		while (gogo) {
			String repeat = input.nextLine();
			if (repeat.toUpperCase().equals("Y")) {
				gogo = false;
			} else if (repeat.toUpperCase().equals("N")) {
				go = false;
				gogo = false;
			} else {
				System.out.println("invalid input, try again. input Y or N: ");
			}
		}
		return go;
	}

}
