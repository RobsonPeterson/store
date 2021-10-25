package br.com.robson.store.model;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

//public class ShoppingCart {
public class ShoppingCart {

	private List<Product> products = new ArrayList<Product>();
	private String rua;
	private String cidade;
	private long id;

	public ShoppingCart adiciona(Product product) {
		products.add(product);
		return this;
	}

	public ShoppingCart para(String rua, String cidade) {
		this.rua = rua;
		this.cidade = cidade;
		return this;
	}

	public ShoppingCart setId(long id) {
		this.id = id;
		return this;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;	
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;	
	}
	
	public long getId() {
		return id;
	}
	
	public void remove(long id) {
		for (Iterator iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if(product.getId() == id) {
				iterator.remove();
			}
		}
	}
	
	public void troca(Product product) {
		remove(product.getId());
		adiciona(product);
	}

	public void trocaAmount(Product product) {
		for (Iterator iterator = products.iterator(); iterator.hasNext();) {
			Product p = (Product) iterator.next();
			if(p.getId() == product.getId()) {
				p.setAmount(product.getAmount());
				return;
			}
		}
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public String toXML() {
		return new XStream().toXML(this);
	}

}

