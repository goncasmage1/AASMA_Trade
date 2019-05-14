package app;

import java.util.ArrayList;

//ProposeOffer: Subclass; Request: Superclass
//Propose new offer
public class ProposeOffer extends Request {
	
	//Value proposed: float
	//Product involved in negotiation: Product
	//Is Seller flag: boolean
	//Messages sent: ArrayList<String>
	public ProposeOffer(float value, Product product, boolean isSeller, ArrayList<String> messages) {
		super(value, product, isSeller, messages);
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
