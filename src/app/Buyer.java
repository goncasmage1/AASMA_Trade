package app;

import java.util.ArrayList;

public class Buyer extends Agent {
	
	public float productKnowledge = -1.0f;
	public float detectionThreshold = 0.65f;
	//public float trust = 0.5f;
	
	public Buyer(float riskWillingness, float profitMargin, float offerInflation, float necessity, Strategy strategy) {
		super(riskWillingness, profitMargin, offerInflation, necessity, strategy);
	}

	@Override
	public Request giveResponse(Request request) {
		ArrayList<String> messages = new ArrayList<>();

		if (request.messages != null && request.messages.size() > 0) {
			if (request.messages.contains(LAST)) {
				return processLastRequest(request);
			}
		}
		if (manager.isLastRequest()) {
			return processLastRequest(request);
		}

		if (productKnowledge == -1.0f) {
			updateProductKnowledge();
			perceivedValue = (request.product.getValue() / (1.0f + profitMargin)) / (1.0f + offerInflation);
		} 

		//Se valor se aproximar ou baixar da margem de lucro, indicar ultima oferta
		float newValue = createNextOffer(request);
		if (request.messages.contains(INFLATE) && productKnowledge > detectionThreshold) {
			messages.add(DETECTION);
		}

		if (newValue >= request.value) return new AcceptTrade(false, request.product);

		return new ProposeOffer(newValue, request.product, false, messages);
	}

	@Override
	protected float createNextOffer(Request request) {
		
		int numCurrentRequest = manager.buyerRequests.size()+1;
		float concedingFactor = strategy.getConcedingFactor(numCurrentRequest);
		
		if (manager.buyerRequests.size() == 0) {
			//TODO: MUDEI ISTO!!
			//return lerp(request.value, perceivedValue, productKnowledge);
			return perceivedValue;
		}
		else {
			float lastOfferValue = manager.buyerRequests.get(manager.buyerRequests.size() - 1).value;

			float offerValue = request.value;
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

	private void updateProductKnowledge() {
		productKnowledge = manager.rand.nextFloat();
	}
}
