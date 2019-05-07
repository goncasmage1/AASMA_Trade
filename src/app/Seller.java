package app;

import java.util.ArrayList;

public class Seller extends Agent {
	
	public boolean useBoulware = false;
	
	public Seller(float riskWillingness, float profitMargin, float offerInflation, boolean useBoulware) {
		super(riskWillingness, profitMargin, offerInflation);
		this.useBoulware = useBoulware;
	}

	@Override
	public Request giveResponse(Request request) {
		if (request != null && request.messages != null && request.messages.size() > 0) {
			
		}
		if (manager.isLastRequest()) {
			float giveUpProbability = manager.rand.nextFloat();
			if (giveUpProbability >= 0.5f) return new GiveUpTrade(false, request.product);
			else return new AcceptTrade(false, request.product);
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
		float newOfferValue = lerp(lastOfferValue, offerValue, 0.2f);

		return newOfferValue;
	}

	private Request buildInitialRequest() {
		Product product = manager.products[manager.rand.nextInt(manager.products.length)];
		float desiredValue = (product.marketValue * product.quality) * (1.0f + profitMargin) * (1.0f + offerInflation);
		ArrayList<String> messages = new ArrayList<String>();
		if (manager.rand.nextFloat() < riskWillingness) {
			messages.add(INFLATE);
		}
		return new ProposeOffer(desiredValue, product, true, messages);
	}
}
