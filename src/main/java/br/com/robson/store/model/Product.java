package br.com.robson.store.model;

public class Product {
	
	private double price;
	private long id;
	private String name;
	private int amount;
	
	public Product(long id, String name, double price, int amount) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public double getPriceTotal() {
		return amount * price;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
