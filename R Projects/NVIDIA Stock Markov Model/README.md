NVIDIA Stock Analysis Using Markov Chains

Overview:
This project analyzes historical NVIDIA stock price data using a Markov Chain model. The goal is to categorize trading days based on percentage price changes, compute transition probabilities between different market states, and determine the likelihood 
of a positive trading day given the previous day's state. The project also includes data visualization and simulation to verify the validity of the Markov Chain model.

The dataset contains historical stock data for NVIDIA, including:
Date, Open Price, Close Price, High Price, Low Price, and Volume

The dataset was cleaned in Microsoft Excel before importing into R. Then, Volume values were converted into numeric format by removing commas, and a new variable PERCENT_CHANGE was created to represent close-to-close percent price changes.
Days were categorized into four trading day types based on PERCENT_CHANGE(PC):
Good Trading Day: PC >= 0.75%
Mid Trading Day: -0.75% <= PC < 0.75%
Bad Trading Day: -1.5% < PC < -0.75%
Very Bad Trading Day: PC <= -1.5%

A Markov Chain model was developed with four states:
Good,
Mid,
Bad,
Very Bad

The probability of transitioning from one state to another was computed based on historical occurrences. A transition matrix was constructed and normalized to ensure probabilities sum to 1. The stationary distribution was determined to analyze 
the expected long-term behavior of NVIDIA's stock. A Kullback-Leibler (KL) divergence test was conducted to compare the stationary distribution with a uniform distribution. A chi-square test was performed to check the statistical significance of the transition probabilities.
A Markov Chain simulation was conducted for 200 future trading days, starting from a Mid trading day. Then, the observed frequencies of different states were compared with the predicted stationary distribution.

Several visualizations were generated using ggplot2:
Time Series Plot of NVIDIA's close-to-close percent change,
Heatmap of Transition Probabilities between different market states,
Bar Chart Comparison of observed vs. predicted probabilities for each market state,
Daily Closing Prices Over Time, and
Daily Volume Over Time.

Key Findings:
The transition matrix revealed that, although there are high expected probabilities of good days from all states, certain states have higher probabilities of leading to a positive trading day.
The steady-state probabilities provide insights into the expected long-term distribution of trading days. The KL divergence suggests that NVIDIA's stock movement is not purely random and follows identifiable patterns.
Based on the Markov Chain model, the best days to invest may be after a "Good" or "Very Bad" trading day.

Requirements:
To run this analysis, install the following R packages:
"dplyr",
"markovchain",
"ggplot2",
"lubridate",
"tidyr"
