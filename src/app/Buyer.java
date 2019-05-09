package app;

public class Buyer extends Agent {
	
	public float productKnowledge = -1.0f;
	public float trust = 0.5f;
	
	public Buyer(float riskWillingness, float profitMargin, float offerInflation, float necessity, float concedingFactor) {
		super(riskWillingness, profitMargin, offerInflation, necessity, concedingFactor);
	}

	@Override
	public Request giveResponse(Request request) {
		if (request.messages != null && request.messages.size() > 0) {
			return processLastRequest(request);
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

		if (newValue >= request.value) return new AcceptTrade(false, request.product);

		return new ProposeOffer(newValue, request.product, false, null);
	}

	@Override
	protected float createNextOffer(Request request) {
		if (manager.buyerRequests.size() == 0) {
			return lerp(request.value, perceivedValue, productKnowledge);
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
