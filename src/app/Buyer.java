package app;

public class Buyer extends Agent {
	
	public float productKnowledge = -1.0f;
	public float trust = 0.5f;
	
	public Buyer(float riskWillingness, float profitMargin, float offerInflation) {
		super(riskWillingness, profitMargin, offerInflation);
	}

	@Override
	public Request giveResponse(Request request) {
		if (request.messages != null && request.messages.size() > 0) {
			
		}
		if (manager.isLastRequest()) {
			//TODO: Nao randomizar!!
			float giveUpProbability = manager.rand.nextFloat();
			if (giveUpProbability >= 0.5f) return new GiveUpTrade(false, request.product);
			else return new AcceptTrade(false, request.product);
		}

		if (productKnowledge == -1.0f) updateProductKnowledge();
		//Se valor se aproximar ou baixar da margem de lucro, indicar ultima oferta
		float newValue = createNextOffer(request);

		if (newValue >= request.value) return new AcceptTrade(false, request.product);

		return new ProposeOffer(newValue, request.product, false, null);
	}

	@Override
	protected float createNextOffer(Request request) {
		if (manager.buyerRequests.size() == 0) {
			return lerp(request.value, (request.product.marketValue * request.product.quality / (1.0f + profitMargin)) / (1.0f + offerInflation), productKnowledge);
		}
		else {
			float lastOfferValue = manager.buyerRequests.get(manager.buyerRequests.size() - 1).value;

			float offerValue = request.value;
			float newOfferValue = lerp(lastOfferValue, offerValue, 0.2f);
			return newOfferValue;
		}
	}

	private void updateProductKnowledge() {
		productKnowledge = manager.rand.nextFloat();
	}
}
