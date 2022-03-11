package com.hcl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

//You need to build a menu driven “Create/ Read/ Update/ Delete” aka CRUD application, 
//where you can create, update, delete, view all Products.
//Use collections streams technique to show products, filter products etc.
//For update and delete, you can prompt id that is unique and operate on that.

//declare constructor
class Game
{
	//fields
	int id;
	String title;
	double price;
	
	//methods
	public Game(int id, String name, double price)
	{
		//invoke super() for immediate parent class 
		//constructor meaning it call the parent constructor with no arguments.
		
		super();
		this.id = id;
		this.title = title;
		this.price = price; 
	}
}

public class ProductGame 
{
	//Hamilton Nguyen version 
	
	//global variables, for arraylist using game object as nonprimative datatype.
	static ArrayList<Game> storage = new ArrayList<>();
	static int size = storage.size();
	
	
	//Create method
	public static void Create(Scanner input) throws InputMismatchException
	{
		try 
		{
			System.out.println("Insert title");
			String title = input.nextLine();
			
			System.out.println("Insert price");
			double price = input.nextDouble();
			input.nextLine();
			
			size++;
			
			storage.add(new Game(size, title, price));
			System.out.println("Game added and identified by ID: "+ size);
		}
		
		catch(InputMismatchException e)
		{
			System.out.println("Please enter the value integer for price only");
			System.out.println("Print error :"+e);
			e.getStackTrace();
		}
	}
	
	public static void Read()
	{
		//note: the name gamer object can be any name that classified of an object from game class.
		storage.forEach(gamer -> System.out.println(gamer.id+ ":" +gamer.title+ ":" + gamer.price));
		
		//usage of stream.foreach function
	}
	
	public static void Update(Scanner input) throws InputMismatchException
	{
		try
		{
			System.out.println("Enter Game ID");
			int id = input.nextInt();
			input.nextLine();
			
			System.out.println("Insert new title");
			String title = input.nextLine();
			
			System.out.println("Insert new Price");
			double price = input.nextDouble();
			input.nextLine();
			
			//stream filter method and declare object game from game class
			Stream <Game> fdata = storage.stream().filter(game -> game.id == id);
			
			fdata.forEach(data -> {data.title = title; data.price = price;});
	 		
			System.out.println("Updated title and price for Game ID:"+ id);
		}
		
		catch(InputMismatchException e)
		{
			System.out.println("Please enter the value integer for id or price");
			System.out.println("Print error :"+e);
			e.getStackTrace();
		}
	}
	
	public static void Delete(Scanner input) throws InputMismatchException
	{
		try 
		{
			System.out.println("Enter product ID for deletion.");
			int id = input.nextInt();
			input.nextLine();
			
			storage.removeIf(gamer -> gamer.id ==id);
			System.out.println("deleted product with id: "+ id);
		}
		
		catch(InputMismatchException e)
		{
			System.out.println("Please enter the value integer for id");
			System.out.println("Print error :"+e);
			e.getStackTrace();
		}
	}
	
	public static boolean Prompt(boolean status, Scanner input)
	{
		
		System.out.println("Would you like to repeat the menu?");
		String userinput = input.nextLine();
		userinput= userinput.toLowerCase();
				
			if (userinput.equals("yes"))	
			{
				status = true;		
			}
			
			else if (userinput.equals("no"))
			{
				status =false;
				System.out.println("Have a nice day!");
			}	
			
			else
			{
				System.out.println("Invalid input, please try again.");
				System.out.println(userinput);
				Prompt(status, input);
			}	
			
			return status;
	}
	
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		boolean state = true;
		Scanner userinput = new Scanner(System.in);
		
		
		while(state)
		{
			System.out.println("What operation to perform? : Create, Read, Update, Delete");
			String feed = userinput.nextLine();
			
			switch(feed.toLowerCase())
			{
				case "create":
					Create(userinput);
					break;
					
				case "read":
					Read();
					break;
					
				case "update":
					Update(userinput);
					break;
					
				case "delete":
					Delete(userinput);
					break;
					
				default:
					System.out.println("Invalid input.");
					break;
			}
			
		    state= Prompt(state, userinput);
		}	
	}

}
