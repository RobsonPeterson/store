package br.com.robson.store.resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.robson.store.dao.ShoppingCartDao;
import br.com.robson.store.model.ShoppingCart;

@Path("shoppingcart")
public class ShoppingCartResource {
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") long id) {		
		ShoppingCart shoppingCart = new ShoppingCartDao().busca(id);		
		return shoppingCart.toXML();
	}
	
}

