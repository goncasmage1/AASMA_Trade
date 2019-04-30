package app;

import java.util.ArrayList;

public class TradeManager {
	
	public static Buyer buyer;
	public static Seller seller;
	
	private static TradeManager tradeManager;
	
	public Product[] products = {
		new Product("Whiskas Saquetas", 5, 0.2f),
		new Product("Ferrari Sabrini", 10000, 0.7f),
		new Product("Asus Laptop", 800, 0.9f),
		new Product("MAC XPTO", 2000, 0.4f),
	};
	
	public ArrayList<Request> requests = new ArrayList<Request>();
	public ArrayList<Request> buyerRequests = new ArrayList<Request>();
	public ArrayList<Request> sellerRequests = new ArrayList<Request>();
	
	public TradeManager() {
	}
	
	public static TradeManager get() {
		if (tradeManager == null) {
			tradeManager = new TradeManager();
			tradeManager.buyer = new Buyer(0.0f, false);
			tradeManager.seller = new Seller(0.0f);
		}
		return tradeManager;
	}
	
	public void startTrade() {
		Request nextRequest = null;

		while(true) {
			nextRequest = seller.giveResponse(nextRequest);
			nextRequest.accept(this);
			
			if (nextRequest instanceof AcceptTrade ||
				nextRequest instanceof GiveUpTrade) break;
			
			nextRequest = buyer.giveResponse(nextRequest);
			nextRequest.accept(this);
			
			if (nextRequest instanceof AcceptTrade ||
				nextRequest instanceof GiveUpTrade) break;
		}
	}
	
	public void processRequestAbstract(Request request) {
		if (request.isSeller) {
			sellerRequests.add(request);
		}
		else {
			buyerRequests.add(request);
		}
		requests.add(request);
	}
	
	public void processRequest(AcceptTrade request) {
		processRequestAbstract(request);
		
	}
	
	public void processRequest(GiveUpTrade request) {
		processRequestAbstract(request);
	}

	public void processRequest(ProposeOffer request) {
		processRequestAbstract(request);
	}	

	public void processRequest(InitialOffer request) {
		processRequestAbstract(request);
	}
}
