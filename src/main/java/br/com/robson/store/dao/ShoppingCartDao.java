package br.com.robson.store.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.robson.store.model.Product;
import br.com.robson.store.model.ShoppingCart;

public class ShoppingCartDao {
	
	private static Map<Long, ShoppingCart> banco = new HashMap<Long, ShoppingCart>();
	private static AtomicLong contador = new AtomicLong(1);
	
	static {
		Product videogame = new Product(6237, "Videogame 4", 4000, 1);
		Product esporte = new Product(3467, "Jogo de esporte", 60, 2);
		ShoppingCart shoppingCart = new ShoppingCart()
								.adiciona(videogame)
								.adiciona(esporte)
								.para("Rua Vergueiro 3185, 8 andar", "Curitiba")
								.setId(1l);
		banco.put(1l, shoppingCart);
	}
	
	public void adiciona(ShoppingCart shoppingCart) {
		long id = contador.incrementAndGet();
		shoppingCart.setId(id);
		banco.put(id, shoppingCart);
	}
	
	public ShoppingCart busca(Long id) {
		return banco.get(id);
	}
	
	public ShoppingCart remove(long id) {
		return banco.remove(id);
	}

}
