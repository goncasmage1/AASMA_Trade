package app;

import java.util.ArrayList;

public class Buyer extends Agent {
	
	public float productKnowledge = -1.0f;
	public float detectionThreshold = 0.65f;
	
	public Buyer(AgentConfig config) {
		super(config);
	}

	@Override
	public Request giveResponse(Request request) {
		ArrayList<String> messages = new ArrayList<String>();
		
		if (manager.isLastRequest()) {
			return processLastRequest(request);
		}
		else if (manager.isBeforeBeforeLastRequest()) {
			if (request.value > request.product.getValue() && manager.rand.nextFloat() < riskWillingness) {
				messages.add(BETTER);
			}
		}
		
		if (productKnowledge == -1.0f) {
			updateProductKnowledge();
			perceivedValue = (request.product.getValue() / (1.0f + profitMargin)) / (1.0f + offerInflation);
		} 
		
		boolean inflate = false;
		if (request.messages.contains(INFLATE)) {
			if (productKnowledge > detectionThreshold) {
				messages.add(DETECTION);
			}
			else inflate = true;
		}
		float newValue = createNextOffer(request, inflate);
		
		if (newValue >= request.value) return new AcceptTrade(false, request.product);

		if (request.messages != null && request.messages.contains(BETTER)) {
			return processBetterOffer(request, newValue);
		}
		return new ProposeOffer(newValue, request.product, false, messages);
	}
	
	@Override
	protected float createNextOffer(Request request, boolean inflate) {
		
		if (manager.buyerRequests.size() == 0) {
			return lerp(perceivedValue, request.value, inflate ? 0.4f * productKnowledge : 0.0f);
		}
		else {
			float lastOfferValue = manager.buyerRequests.get(manager.buyerRequests.size() - 1).value;
			
			float offerValue = request.value;
			
			int numCurrentRequest = manager.buyerRequests.size()-1;
			float concedingFactor = strategy.getConcedingFactor(numCurrentRequest);
			
			float newOfferValue = lerp(lastOfferValue, offerValue, concedingFactor);
			return newOfferValue;
		}
	}

	@Override
	protected Request processLastRequest(Request request) {
		if (request.value <= request.product.getValue()) return new AcceptTrade(false, request.product);
		float discrepancy = request.product.getValue() / request.value;
		return (1.0f - discrepancy) <= necessity ? new AcceptTrade(false, request.product) : new GiveUpTrade(false, request.product);
	}

	@Override
	protected Request processBetterOffer(Request request, float newValue) {
		if (request.value <= request.product.getValue()) return new AcceptTrade(false, request.product);
		return new ProposeOffer(lerp(newValue, request.value, necessity), request.product, false, new ArrayList<String>());
	}

	private void updateProductKnowledge() {
		productKnowledge = manager.rand.nextFloat();
	}
}
