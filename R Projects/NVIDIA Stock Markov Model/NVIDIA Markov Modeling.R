library(dplyr)
library(markovchain)
library(ggplot2)
library(lubridate)
library(tidyr)

# The code to import the dataset was here, but I have removed it. For reference,the dataset is available on github under "R Projects/NVIDIA Stock Markov Model".


# Here, I have imported a data set containing the past 1200+ opening, closing,
# highs, lows, volumes, and dates of the NVIDIA stock. I performed data
# cleaning in Microsoft Excel prior to the import.

# Converting the entries in volume to the appropriate format
NVIDIA_data$VOLUME <- as.numeric(gsub(",", "", NVIDIA_data$VOLUME))


# Below, I create a new variable "PERCENT_CHANGE" which represents the
# close-to-close percent price changes. I chose close-to-close as opposed to 
# a typical open-to-close price change, so I could model with all factors
# playing in. This could be overnight changes, new updates, new policies,
# and any other possible influence.


NVIDIA_data$PERCENT_CHANGE <- NA

for (i in 1:nrow(NVIDIA_data)) {
  NVIDIA_data$PERCENT_CHANGE[i] <- (NVIDIA_data$CLOSE[i] - NVIDIA_data$CLOSE[i + 1])/NVIDIA_data$CLOSE[i]
}

# Here, I found and calculated the percent change for the oldest entry in the
# data sheet from external sources.
NVIDIA_data$PERCENT_CHANGE[nrow(NVIDIA_data)] <- -0.0171073095


# I will introduce a new variable which will categorize the data into four
# types of trading days. I have set up the days as below with the
# Percent Change being "PC":
# "Good Trading Day" : PC >= 2%
# "Mid Trading Day" : 0% <= PC < 2%
# "Bad Trading Day" : -1.5% < PC < 0%
# "Very Bad Trading Day" : PC <= -1.5%

# Given these 4 types of days, I can effectively model this data using a
# Markov Chain with 4 states. In the following, I calculate the 
# transition probabilities to each state based on this data.

# Here, I have created a new variable which will record which of the four
# types of trading days a particular date was.

NVIDIA_data$DAY_TYPE <- NA


# Below, I have defined the count for how many of the days are good, mid, 
# bad, and very bad days. Additionally, I have defined counting integers
# for how many of each transition occurs, so I can calculate the 
# transition probabilities.
# I have set m_count to start at -1 because there is one less number of 
# transitions than there are days and the most recent entry is a "mid day".


g_count <- 0
m_count <- 0
b_count <- -1
v_count <- 0
gg_count <- 0
gm_count <- 0
gb_count <- 0
gv_count <- 0
mg_count <- 0
mm_count <- 0
mb_count <- 0
mv_count <- 0
bg_count <- 0
bm_count <- 0
bb_count <- 0
bv_count <- 0
vg_count <- 0
vm_count <- 0
vb_count <- 0
vv_count <- 0



for (i in 1:(nrow(NVIDIA_data) - 1)) {
  if (NVIDIA_data$PERCENT_CHANGE[i] >= 0.0075) {
    NVIDIA_data$DAY_TYPE[i] <- "Good"
    g_count <- g_count + 1
  }
  else if (NVIDIA_data$PERCENT_CHANGE[i] >= -0.0075 && NVIDIA_data$PERCENT_CHANGE[i] < 0.0075) {
    NVIDIA_data$DAY_TYPE[i] <- "Mid"
    m_count <- m_count + 1
  }
  else if (NVIDIA_data$PERCENT_CHANGE[i] > -0.015 && NVIDIA_data$PERCENT_CHANGE[i] < -0.0075) {
    NVIDIA_data$DAY_TYPE[i] <- "Bad"
    b_count <- b_count + 1
  }
  else {
    NVIDIA_data$DAY_TYPE[i] <- "Very Bad"
    v_count <- v_count + 1
  }
}

for (i in 1:(nrow(NVIDIA_data) - 2)) {
    if (NVIDIA_data$DAY_TYPE[i] == "Good") {
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Good") {
        gg_count <- gg_count + 1
      }
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Mid") {
        mg_count <- mg_count + 1
      }
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Bad") {
        bg_count <- bg_count + 1
      }
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Very Bad") {
        vg_count <- vg_count + 1
      }
    } 
    else if (NVIDIA_data$DAY_TYPE[i] == "Mid") {
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Good") {
        gm_count <- gm_count + 1
      }
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Mid") {
        mm_count <- mm_count + 1
      }
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Bad") {
        bm_count <- bm_count + 1
      }
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Very Bad") {
        vm_count <- vm_count + 1
      }
    } 
    else if (NVIDIA_data$DAY_TYPE[i] == "Bad") {
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Good") {
        gb_count <- gb_count + 1
      }
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Mid") {
        mb_count <- mb_count + 1
      }
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Bad") {
        bb_count <- bb_count + 1
      }
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Very Bad") {
        vb_count <- vb_count + 1
      }
    } 
    else if (NVIDIA_data$DAY_TYPE[i] == "Very Bad") {
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Good") {
        gv_count <- gv_count + 1
      }
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Mid") {
        mv_count <- mv_count + 1
      }
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Bad") {
        bv_count <- bv_count + 1
      }
      if (NVIDIA_data$DAY_TYPE[i + 1] == "Very Bad") {
        vv_count <- vv_count + 1
      }
    }
}

NVIDIA_data$DAY_TYPE[nrow(NVIDIA_data)] <- "Bad"

gg_prob <- ifelse(g_count > 0, gg_count / g_count, 0)
gm_prob <- ifelse(g_count > 0, gm_count / g_count, 0)
gb_prob <- ifelse(g_count > 0, gb_count / g_count, 0)
gv_prob <- ifelse(g_count > 0, gv_count / g_count, 0)

mg_prob <- ifelse(m_count > 0, mg_count / m_count, 0)
mm_prob <- ifelse(m_count > 0, mm_count / m_count, 0)
mb_prob <- ifelse(m_count > 0, mb_count / m_count, 0)
mv_prob <- ifelse(m_count > 0, mv_count / m_count, 0)

bg_prob <- ifelse(b_count > 0, bg_count / b_count, 0)
bm_prob <- ifelse(b_count > 0, bm_count / b_count, 0)
bb_prob <- ifelse(b_count > 0, bb_count / b_count, 0)
bv_prob <- ifelse(b_count > 0, bv_count / b_count, 0)

vg_prob <- ifelse(v_count > 0, vg_count / v_count, 0)
vm_prob <- ifelse(v_count > 0, vm_count / v_count, 0)
vb_prob <- ifelse(v_count > 0, vb_count / v_count, 0)
vv_prob <- ifelse(v_count > 0, vv_count / v_count, 0)

transition_matrix <- matrix(
  c(gg_prob, gm_prob, gb_prob, gv_prob,
    mg_prob, mm_prob, mb_prob, mv_prob,
    bg_prob, bm_prob, bb_prob, bv_prob,
    vg_prob, vm_prob, vb_prob, vv_prob), 
  nrow = 4, ncol = 4, byrow = TRUE)

# Normalizing the matrix in case there are any small rounding errors
transition_matrix <- transition_matrix / rowSums(transition_matrix)

print(transition_matrix)


# My goal, of course, is to find the most optimal times to invest in the
# NVIDIA stock, or in other words, find when it is most likely that the 
# next trading day is either a "good day" or a "mid day", as mid days still 
# include gain (0% < PC < 2%).

# Below I will calculate the probabilities of a having a positive day tomorrow,
# given the current day.
# I will denote positive given good as PG, positive given mid as PM, et.


PG <- transition_matrix[1,1] + transition_matrix[1,2]
PM <- transition_matrix[2,1] + transition_matrix[2,2]
PB <- transition_matrix[3,1] + transition_matrix[3,2]
PV <- transition_matrix[4,1] + transition_matrix[4,2]


print(PG)
print(PM)
print(PB)
print(PV)

# Now, I will use R's built in solve function to find the stationary 
# distribution of this markov chain. Then I can get some perspective
# as to what the expected long term outcome of NVIDIA stock will look like.

NVIDIA_chain <- new("markovchain", transitionMatrix = transition_matrix)
print(steadyStates(NVIDIA_chain))

# Up until now, I have developed the markov chain which represents the stock
# moves, however, in order to truly confirm that these moves follow a pattern
# and are not random, I will conduct a chi-square test of significance.

# Assuming uniform transitions:
uniform <- rep(1/4, 4)

stationary_distribution <- steadyStates(NVIDIA_chain)

KL_divergence <- sum(stationary_distribution * log(stationary_distribution / uniform))
print(KL_divergence)

# When run, I observed a KL_divergence of 0.1145055, leading me to believe
# there is definitely some significance concerning the steady states. 
# Given the transition matrix, the highest probability of a good stock day 
# tomorrow comes after a "good" or "very bad" day.



NVIDIA_data$DATE <- as.character(NVIDIA_data$DATE)

NVIDIA_data$DATE <- dmy(NVIDIA_data$DATE)

# Creating a time series plot for percent change.
ggplot(NVIDIA_data, aes(x = DATE, y = PERCENT_CHANGE)) +
  geom_line(color = "blue") +
  geom_hline(yintercept = 0, linetype="dashed", color="red") +
  labs(title = "NVIDIA Stock Percent Change Over Time",
       x = "Date", 
       y = "Close-to-Close Percent Change") +
  theme_minimal()

transition_df <- as.data.frame(as.table(transition_matrix))
colnames(transition_df) <- c("From", "To", "Probability")

# Heat map visualization of the transition probabilities between the types of days
ggplot(transition_df, aes(x = From, y = To, fill = Probability)) +
  geom_tile() +
  scale_fill_gradient(low = "white", high = "blue") +
  scale_x_discrete(labels = c("Good", "Mid", "Bad", "Very Bad")) +
  scale_y_discrete(labels = c("Good", "Mid", "Bad", "Very Bad")) +
  labs(title = "Transition Probability Heatmap",
       x = "Current State",
       y = "Next State",
       fill = "Probability") +
  theme_minimal()

set.seed(123)
simulate_markov <- function(n, initial_state) {
  states <- c("Good", "Mid", "Bad", "Very Bad")
  state_index <- match(initial_state, states)
  simulated_days <- c(initial_state)
  
  for (i in 1:n) {
    next_state <- sample(states, size = 1, prob = transition_matrix[state_index, ])
    simulated_days <- c(simulated_days, next_state)
    state_index <- match(next_state, states)
  }
  
  return(simulated_days)
}

# Simulating 50 days into the future starting from a Mid day, the "average" day for the stock.
simulated_future <- simulate_markov(200, "Mid")
print(simulated_future)

simulated_table <- table(simulated_future)

# Below are the predicted "Good", "Mid", "Bad", and "Very Bad" day counts from the simulation denoted og, om, ob, and ov.
og_count = simulated_table["Good"]
om_count = simulated_table["Mid"]
ob_count = simulated_table["Bad"]
ov_count = simulated_table["Very Bad"]

# The observed probabilities and predicted probabilities of each state
observed_probs <- c(og_count, om_count, ob_count, ov_count) / 200
predicted_probs <- steadyStates(NVIDIA_chain)

comparison_df <- data.frame(
  State = c("Good", "Mid", "Bad", "Very Bad"),
  Observed = observed_probs,
  Predicted = as.numeric(predicted_probs)
)


comparison_long <- comparison_df %>%
  pivot_longer(cols = c(Observed, Predicted),
               names_to = "Type", 
               values_to = "Probability")


# Bar chart comparing observed probabilities in the simulation vs the predicted
# from the markov chain
ggplot(comparison_long, aes(x = State, y = Probability, fill = Type, group = Type)) + 
  geom_bar(stat = "identity", position = "dodge", width = 0.7) + 
  labs(title = "Observed vs Predicted Probabilities for Each State", 
       x = "State",
       y = "Probability") +
  scale_fill_manual(values = c("green", "purple")) +
  theme_minimal()

# Plot of NVIDIA's closing prices over the time frame of the data.
ggplot(NVIDIA_data, aes(x = DATE)) +
  geom_line(aes(y = CLOSE), color = "blue") + 
  labs(title = "Daily Closing Prices Over Time", 
       x = "Date", 
       y = "Closing Price (USD)") +
  theme_minimal()
  
# Plot of NVIDIA's volume for the day over the time frame of the data.
ggplot(NVIDIA_data, aes(x = DATE)) +
  geom_line(aes(y = VOLUME/1000000), color = "orange") + 
  labs(title = "Daily Volume(in Millions) Over Time", 
       x = "Date", 
       y = "Volume") +
  theme_minimal()
