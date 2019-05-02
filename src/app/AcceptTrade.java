package app;

public class AcceptTrade extends Request {
	
	public AcceptTrade(boolean isSeller) {
		super(0.0f, null, isSeller, "");
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
