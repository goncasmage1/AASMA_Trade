package app;

import java.util.ArrayList;
import java.util.Random;

public class App {

    public static ArrayList<TradeResult> trades = new ArrayList<>();
    public static int numOfSimulations = 50;

    public static int maxTrades = 15;
    public static int minTrades = 10;
    public static Random rand = new Random();
    public static void main(String[] args) throws Exception {
        TradeManager tradeManager = TradeManager.get();

        int maxRequests = rand.nextInt((maxTrades - minTrades) + 1) + minTrades;
        AgentConfig sellerConfig = new AgentConfig(0.8f, 0.3f, 0.1f, 0.1f, new Strategy(maxRequests, 2.0f, 0.1f));
        AgentConfig buyerConfig = new AgentConfig(0.6f, 0.4f, 0.1f, 0.1f, new Strategy (maxRequests, 0.3f, 0.1f));

        for (int i = 0; i < numOfSimulations; i++) {
            System.out.println("TRADE " + (i+1));
            trades.add(tradeManager.startTrade(maxRequests, sellerConfig, buyerConfig));
            System.out.println();
        }
    }
}