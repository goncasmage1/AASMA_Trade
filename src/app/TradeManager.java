package app;

import java.util.ArrayList;
import java.util.Random;

public class TradeManager {
	
	public static Buyer buyer;
	public static Seller seller;

	public float lastOfferValue = 0.0f;
	
	private static TradeManager tradeManager;

	public static Random rand = new Random();
	
	public static Product[] products = {
		new Product("Ferrari Sabrini", 10000, 0.7f),
		new Product("Asus Laptop", 800, 0.9f),
		new Product("MAC XPTO", 2000, 0.4f),
		new Product("Casaco da Zara", 30, 1.0f),
	};
	
	public ArrayList<Request> requests = new ArrayList<Request>();
	public ArrayList<Request> buyerRequests = new ArrayList<Request>();
	public ArrayList<Request> sellerRequests = new ArrayList<Request>();

	public static int maxRequests = 0;
	public static int requestCount = 0;
	
	public TradeManager() {
	}
	
	public static TradeManager get() {
		if (tradeManager == null) tradeManager = new TradeManager();
		return tradeManager;
	}

	private void init(int maxRequests, AgentConfig buyerConfig, AgentConfig sellerConfig) {
		tradeManager.seller = new Seller(sellerConfig);
		tradeManager.buyer = new Buyer(buyerConfig);
		requests.clear();
		buyerRequests.clear();
		sellerRequests.clear();

		this.maxRequests = maxRequests;
		requestCount = 0;
		buyerConfig.strategy.reset();
		sellerConfig.strategy.reset();
	}
	
	public TradeResult startTrade(int maxRequests, AgentConfig buyerConfig, AgentConfig sellerConfig) {
		init(maxRequests, buyerConfig, sellerConfig);

		Request nextRequest = null;

		//System.out.println("Max: " + maxRequests);

		while(true) {
			nextRequest = seller.giveResponse(nextRequest);
			requestCount++;
			//if (nextRequest.messages != null) for (int i = 0; i < nextRequest.messages.size(); i++) System.out.println(nextRequest.messages.get(i));
			nextRequest.accept(this);
			
			if (nextRequest instanceof AcceptTrade ||
				nextRequest instanceof GiveUpTrade ||
				requestCount >= maxRequests) break;

			//System.out.println("Seller offer: " + nextRequest.value + "$");
			lastOfferValue = nextRequest.value;
			
			nextRequest = buyer.giveResponse(nextRequest);
			requestCount++;
			//if (nextRequest.messages != null) for (int i = 0; i < nextRequest.messages.size(); i++) System.out.println(nextRequest.messages.get(i));
			nextRequest.accept(this);
			
			if (nextRequest instanceof AcceptTrade ||
				nextRequest instanceof GiveUpTrade ||
				requestCount >= maxRequests) break;
				
			//System.out.println("Buyer offer: " + nextRequest.value + "$");
			lastOfferValue = nextRequest.value;
		}

		//System.out.println();
		//if (nextRequest instanceof AcceptTrade) System.out.println("Trade accepted");
		//else if (nextRequest instanceof GiveUpTrade) System.out.println("Trade cancelled");
		//System.out.println(nextRequest.product.name + ": " + nextRequest.product.getValue() + "$");

		return new TradeResult(nextRequest.product.getValue(), lastOfferValue, nextRequest instanceof AcceptTrade);
	}

	public static boolean isLastRequest() {
		return requestCount == (maxRequests-1);
	}

	public static boolean isBeforeLastRequest() {
		return requestCount == (maxRequests-2);
	}

	public static boolean isBeforeBeforeLastRequest() {
		return requestCount == (maxRequests-3);
	}

	public static float tradeProgress() {
		return (float)requestCount / (float)maxRequests;
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
