# AASMA_Trade

The goal of this project is to simulate a situation where 2 agents are trying to negotiate a product with the possibility of deception.

Agents have several properties including:

- Risk Willingness
- Desired Profit Margin
- Offer Inflation (Percentage of initial inflation, in order to drop to their desired profit margin)
- Necessity
- Strategy (Boulware, Conceder, Linear, Constant)

The Buyer agent also has these properties:

- Product Knowledge
- Detection Threshold

Whenever a new trade starts, the Seller agent will make an initial offer, and the Buyer agent will respond with its desired value.
From there, each agent will make subsequent offers closer to the adversary's desired value, where the amount they concede depends on their
chosen strategy.

When you run the program, several trades with different parameters will be executed, and their results will be saved in .csv files.
You can then open TradeResults.xlsx, update all the tables (by going to Data -> Connections) and check the results of each group of simulations.

In order to run the program, simply open the project in Eclipse, IntelliJ, or another Java-capable IDE.