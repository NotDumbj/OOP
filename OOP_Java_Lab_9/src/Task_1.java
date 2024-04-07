import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.Iterator;
//import java.util.HashMap;

public class Task_1 {
	private ArrayList<Book> inventory;
	public Task_1() {
		inventory = new ArrayList<>();
	}
	public void addBook(Book book) {
		inventory.add(book);
	}
	
	public void updateBook(String bookn)
	{
		for(Book BoOk : inventory)
		{
			if(BoOk.getTitle().equalsIgnoreCase(bookn))
			{
				Scanner updbok = new Scanner(System.in);
				System.out.println("Enter New Price: ");
				double p = BoOk.getPrice(); // if there is a error down below, it will set the previous value
				int q = BoOk.getQuantity();
				try{
					p = updbok.nextDouble(); 
				} catch(Exception e) {
					e.printStackTrace();					
				}
				System.out.println("Enter New Quantity: ");
				try {
					q = updbok.nextInt(); 					
				} catch(Exception e) {
					e.printStackTrace();
				}
				BoOk.setPrice(p);
				BoOk.setQuantity(q);
				System.out.println("Price and Quantity Updated for Book " + BoOk.getTitle());
				updbok.close();
			}
		}
	}
	
	public void removeBook(String bookn) {
	    Book booktoremove = new Book();
	    for (Book book : inventory) {
	        if (book.getTitle().equalsIgnoreCase(bookn)) {
	        	booktoremove = book;
	        }
	    }
	    inventory.remove(booktoremove);
	}

	
	public void searchBook(String bookn) {
		for(Book BoOk : inventory)
		{
			if(BoOk.getTitle().equalsIgnoreCase(bookn))
			{
				BoOk.displayDetails();
			}
		}		
	}
	
	public void displayallBooks() {
		Iterator<Book> iterator = inventory.iterator();
		int i = 0;
		while(iterator.hasNext()) {
			Book book = iterator.next();
			i++;
			System.out.println("Book " + i + ": ");
			book.displayDetails();
			System.out.println();
		}
	}
	
	public void SavetoFile() {
		try {
			FileWriter fileexpander = new FileWriter("Inventory.txt");
			Iterator<Book> iter = inventory.iterator();
			while(iter.hasNext())
			{
				Book book = iter.next();
				fileexpander.write(book.getTitle() + ", ");
				fileexpander.write(book.getAuthor() + ", ");
				fileexpander.write(Double.toString(book.getPrice()) + ", ");
				fileexpander.write(Integer.toString(book.getQuantity()) + ", " );
				fileexpander.write("\n");
			}
			System.out.println("File Save Succeeded. Thumbs Up!");
			fileexpander.close();
		} catch (IOException e) {
			System.out.println("File cannot Create. Error : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void LoadfromFile() {
		try {
			FileReader fileReader = new FileReader("Inventory.txt");
			Scanner filescan = new Scanner(fileReader);
			String line;
			int objcounter = 0;
			while(filescan.hasNext())
			{
				line = filescan.nextLine();
				String[] parts = line.split(", "); // creating an array of part that are store in line and splitting them //Method learned from Chat GPT
				if(parts.length == 4) {
					String title = parts[0];
					String author = parts[1];
					double price = Double.parseDouble(parts[2]);
					int quantity = Integer.parseInt(parts[3]);
					
					Book book = new Book(title, author, price, quantity);
					inventory.add(book);
					objcounter++;
				}
			}
			System.out.println("File Scan Completed. Total Object Scanned: " + objcounter);
			filescan.close();
			fileReader.close();
		} catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	} 
	class Book {
		private String title;
		private String author;
		private double price;
		private int quantity;
		
		//Constructors
		
		public Book() {}
		
		public Book(String t, String a, double p, int q) {
			this.title = t;
			this.author = a;
			this.price = p;
			this.quantity = q;
		}
		
		//getters
		
		public String getTitle() {
			return title;
		}
		
		public String getAuthor() {
			return author;
		}
		
		public double getPrice() {
			return price;
		}
		
		public int getQuantity() {
			return quantity;
		}
		
		//setters
		
		public void setTitle(String n) {
			this.title = n;
		}
		
		public void setAuthor(String a) {
			this.author = a;
		}
		
		public void setPrice(double p) {
			this.price = p;	
		}
		
		public void setQuantity(int q) {
			this.quantity = q;
		}
		
		//Methods
		
		public void displayDetails() {
			System.out.println("Book Title: " + this.title);
			System.out.println("Book Author: " + this.author);
			System.out.println("Book Price: Rs " + this.price);
			System.out.println("Book Quantity: " + this.quantity);
		}
	}
	
	
	public static void main(String[] args)
	{
		//Commented the Manual Implementation
		
		Task_1 task = new Task_1();
		Task_1.Book book1 = task.new Book("Maths", "Prof. Hafeez", 99.9 , 5);
		//Task_1.Book book2 = task.new Book("Physics", "Sir Rizwan", 132 , 6);
		//Task_1.Book book3 = task.new Book("English", "Sir Shan Masood", 35.2 , 5);
		//Task_1.Book book4 = task.new Book("Magic", "M. Asif", 399.9 , 8);
		
		task.LoadfromFile();
	
		task.addBook(book1);
		//task.addBook(book2);
		//task.addBook(book3);
		//task.addBook(book4);
		
		task.displayallBooks();
		
		task.removeBook("Maths");
		
		task.displayallBooks();
		
		task.updateBook("Physics");
		
		task.displayallBooks();
		
		task.searchBook("Magic");
		//book1.displayDetails();
		//System.out.println("Hello Sir");
		
		task.SavetoFile();
		
	}
}
