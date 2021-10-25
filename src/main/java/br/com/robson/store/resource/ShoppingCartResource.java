package br.com.robson.store.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.robson.store.dao.ShoppingCartDao;
import br.com.robson.store.model.ShoppingCart;

@Path("shoppingcart")
public class ShoppingCartResource {
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca() {		
		ShoppingCart shoppingCart = new ShoppingCartDao().busca(1l);		
		return shoppingCart.toXML();
	}
}

