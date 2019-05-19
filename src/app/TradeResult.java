package app;

public class TradeResult {

    public float productValue;
    public float sellerProfitAmount = 0.0f;
    public float sellerProfitMargin = 0.0f;
    public boolean accepted = false;

    public TradeResult(float productValue, float offerValue, boolean accepted) {
        this.productValue = productValue;
        this.sellerProfitAmount = offerValue - productValue;
        this.sellerProfitMargin = sellerProfitAmount / productValue;
        this.accepted = accepted;
    }
}