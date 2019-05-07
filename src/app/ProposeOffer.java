package app;

import java.util.ArrayList;

public class ProposeOffer extends Request {
	
	public ProposeOffer(float value, Product product, boolean isSeller, ArrayList<String> messages) {
		super(value, product, isSeller, messages);
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
