package app;

public class ProposeOffer extends Request {
	
	float newOffer = 0.0f;
	Product product = null;
	
	public ProposeOffer(boolean isSeller, String message, float newOffer, Product product) {
		super(isSeller, message);
		this.newOffer = newOffer;
		this.product = product;
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
