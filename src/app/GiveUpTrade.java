package app;

//GiveUpTrade: Subclass; Request: Superclass
//Don't accept the deal
public class GiveUpTrade extends Request {
	
	//Product involved in negotiation: Product
	//Is Seller flag: boolean
	public GiveUpTrade(boolean isSeller, Product product) {
		super(0.0f, product, isSeller, null);
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
