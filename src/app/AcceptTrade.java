package app;

//AcceptTrade: Subclass; Request: Superclass
//Accept the deal
public class AcceptTrade extends Request {
	
	//Product involved in negotiation: Product
	//Is Seller flag: boolean
	public AcceptTrade(boolean isSeller, Product product) {
		super(0.0f, product, isSeller, null);
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
