package app;

public class ProposeOffer extends Request {
	
	public ProposeOffer(float value, Product product, boolean isSeller, String message) {
		super(value, product, isSeller, message);
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
