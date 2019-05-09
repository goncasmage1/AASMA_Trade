package app;

import java.util.ArrayList;
import java.util.Random;

public class TradeManager {
	
	public static Buyer buyer;
	public static Seller seller;
	
	private static TradeManager tradeManager;

	public static Random rand = new Random();
	
	public static Product[] products = {
		new Product("Ferrari Sabrini", 10000, 0.7f),
		new Product("Asus Laptop", 800, 0.9f),
		new Product("MAC XPTO", 2000, 0.4f),
	};
	
	public ArrayList<Request> requests = new ArrayList<Request>();
	public ArrayList<Request> buyerRequests = new ArrayList<Request>();
	public ArrayList<Request> sellerRequests = new ArrayList<Request>();

	public static int maxRequests = 0;
	public static int requestCount = 0;
	
	public TradeManager() {
		maxRequests = rand.nextInt((10 - 4) + 1) + 4;
	}
	
	public static TradeManager get() {
		if (tradeManager == null) {
			tradeManager = new TradeManager();
			tradeManager.seller = new Seller(0.8f, 0.3f, 0.1f, 0.15f, 0.25f, false);
			tradeManager.buyer = new Buyer(0.0f, 0.2f, 0.1f, 0.15f, 0.25f);
		}
		return tradeManager;
	}
	
	public void startTrade() {
		Request nextRequest = null;

		System.out.println("Max: " + maxRequests);

		while(true) {
			nextRequest = seller.giveResponse(nextRequest);
			requestCount++;
			nextRequest.accept(this);
			
			if (nextRequest instanceof AcceptTrade ||
				nextRequest instanceof GiveUpTrade ||
				requestCount >= maxRequests) break;

			System.out.println("Seller offer: " + nextRequest.value + "$");
			
			nextRequest = buyer.giveResponse(nextRequest);
			requestCount++;
			nextRequest.accept(this);
			
			if (nextRequest instanceof AcceptTrade ||
				nextRequest instanceof GiveUpTrade ||
				requestCount >= maxRequests) break;
				
			System.out.println("Buyer offer: " + nextRequest.value + "$");
		}

		if (nextRequest instanceof AcceptTrade) System.out.println("Trade accepted");
		else if (nextRequest instanceof GiveUpTrade) System.out.println("Trade cancelled");
		System.out.println(nextRequest.product.name + ": " + nextRequest.product.marketValue * nextRequest.product.quality + "$");
	}

	public static boolean isLastRequest() {
		return requestCount >= (maxRequests-1);
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
}
