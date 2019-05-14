package app;

import java.util.ArrayList;

//Request: Superclass
//Proposal/Request by agent
public class Request { 
	
	public float value = 0.0f;
	public Product product = null;
	public ArrayList<String> messages;
	public boolean isSeller = false;
	
	//Value proposed: float
	//Product involved in negotiation: Product
	//Is Seller flag: boolean
	//Messages sent: ArrayList<String>
	public Request(float value, Product product, boolean isSeller, ArrayList<String> messages) {
		this.value = value;
		this.product = product;
		this.isSeller = isSeller;
		this.messages = messages;
	}
	
	public void accept(TradeManager visitor) {}
}
