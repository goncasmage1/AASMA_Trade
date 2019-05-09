package app;

import java.util.ArrayList;

public class Seller extends Agent {
	
	public boolean useBoulware = false;
	
	public Seller(float riskWillingness, float profitMargin, float offerInflation, float necessity, float concedingFactor, boolean useBoulware) {
		super(riskWillingness, profitMargin, offerInflation, necessity, concedingFactor);
		this.useBoulware = useBoulware;
	}

	@Override
	public Request giveResponse(Request request) {
		if (request != null && request.messages != null && request.messages.size() > 0) {
			return processLastRequest(request);
		}
		if (manager.isLastRequest()) {
			return processLastRequest(request);
		}

		//O agente deve sempre oferecer menos do que a sua ultima oferta
		if (request == null) {
			return buildInitialRequest();
		}
		else {
			//Se valor se aproximar ou baixar da margem de lucro, indicar ultima oferta
			float newValue = createNextOffer(request);

			if (newValue <= request.value) return new AcceptTrade(true, request.product);

			return new ProposeOffer(newValue, request.product, true, null);
		}
	}

	@Override
	protected float createNextOffer(Request request) {
		float lastOfferValue = manager.sellerRequests.get(manager.sellerRequests.size() - 1).value;

		float offerValue = request.value;
		float newOfferValue = lerp(lastOfferValue, offerValue, concedingFactor);

		return newOfferValue;
	}

	@Override
	protected Request processLastRequest(Request request) {
		if (request.value >= request.product.getValue()) return new AcceptTrade(true, request.product);
		float discrepancy = request.value / request.product.getValue();
		return (1.0f - discrepancy) <= necessity ? new AcceptTrade(true, request.product) : new GiveUpTrade(true, request.product);
	}

	private Request buildInitialRequest() {
		//Product product = manager.products[manager.rand.nextInt(manager.products.length)];
		Product product = manager.products[0];
		perceivedValue = product.getValue() * (1.0f + profitMargin) * (1.0f + offerInflation);
		ArrayList<String> messages = new ArrayList<String>();
		if (manager.rand.nextFloat() < riskWillingness) {
			messages.add(INFLATE);
		}
		return new ProposeOffer(perceivedValue, product, true, messages);
	}
}
