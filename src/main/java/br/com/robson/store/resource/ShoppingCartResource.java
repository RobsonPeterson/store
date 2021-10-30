package br.com.robson.store.resource;


import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

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
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response addShoppingCart(String content) {
		ShoppingCart shoppingCart =  (ShoppingCart) new XStream().fromXML(content);
		new ShoppingCartDao().adiciona(shoppingCart);
		URI uri = URI.create("/shoppingcart/" + shoppingCart.getId());
		return Response.created(uri).build();
		
	}
	
	@Path("{id}/product/{productId}")
	@DELETE
	public Response removeProduct(@PathParam("id") long id, @PathParam("productId") long productId) {
		ShoppingCart shoppingCart = new ShoppingCartDao().busca(id);
		shoppingCart.remove(productId);
		return Response.ok().build();
	}
	
}

