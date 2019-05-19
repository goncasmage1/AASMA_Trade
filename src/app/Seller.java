package app;

import java.util.ArrayList;

public class Seller extends Agent {
	
	
	public Seller(AgentConfig config) {
		super(config);
	}

	@Override
	public Request giveResponse(Request request) {
		ArrayList<String> messages = new ArrayList<>();

		if (manager.isLastRequest()) {
			return processLastRequest(request);
		}
		else if (manager.isBeforeBeforeLastRequest()) {
			if (request.value < request.product.getValue() && manager.rand.nextFloat() < config.riskWillingness) {
				messages.add(BETTER);
			}
		}

		//O agente deve sempre oferecer menos do que a sua ultima oferta
		if (request == null) {
			return buildInitialRequest();
		}
		else {
			float newValue = createNextOffer(request, false);

			if (newValue <= request.value) return new AcceptTrade(true, request.product);

			if (request.messages != null && request.messages.contains(BETTER)) {
				return processBetterOffer(request, newValue);
			}
			return new ProposeOffer(newValue, request.product, true, messages);
		}
	}

	@Override
	protected float createNextOffer(Request request, boolean inflate) {

		boolean detected = request.messages.contains(DETECTION);

		float lastOfferValue = manager.sellerRequests.get(manager.sellerRequests.size() - 1).value;
		float offerValue = request.value;
		
		int numCurrentRequest = manager.sellerRequests.size()-1;
		
		float concedingFactor = config.strategy.getConcedingFactor(numCurrentRequest);
		float newOfferValue = lerp(lastOfferValue, offerValue, Math.min(concedingFactor * (detected ? 1.5f : 1.0f), 1.0f));

		return newOfferValue;
	}

	@Override
	protected Request processLastRequest(Request request) {
		if (request.value >= request.product.getValue()) return new AcceptTrade(true, request.product);
		float discrepancy = request.value / request.product.getValue();
		return (1.0f - discrepancy) <= config.necessity ? new AcceptTrade(true, request.product) : new GiveUpTrade(true, request.product);
	}
	
	@Override
	protected Request processBetterOffer(Request request, float newValue) {
		if (request.value >= request.product.getValue()) return new AcceptTrade(true, request.product);
		return new ProposeOffer(lerp(newValue, request.value, config.necessity), request.product, true, new ArrayList<String>());
	}

	private Request buildInitialRequest() {
		Product product = manager.products[manager.rand.nextInt(manager.products.length)];
		//Product product = manager.products[1];
		perceivedValue = product.getValue() * (1.0f + config.profitMargin) * (1.0f + config.offerInflation);
		ArrayList<String> messages = new ArrayList<String>();
		if (manager.rand.nextFloat() < config.riskWillingness) {
			messages.add(INFLATE);
		}
		return new ProposeOffer(perceivedValue, product, true, messages);
	}
}
