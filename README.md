# FP-Growth

This is an implementation of the FP-Growth Algorithm.  The dataset used for the implementation can be found at:
http://archive.ics.uci.edu/ml/datasets/Adult.

Runtime analysis of the algorithm based on different minimum support thresholds can be found below.  

Support	   FP-Growth (milliseconds)
0.0001	   2465
0.001	     961
0.01	     675
0.025	     624
0.05	     527
0.1	       493
0.2	       438
0.3	       408
0.4	       394
0.5	       390
0.6	       376
0.7	       370
0.8	       366
0.9	       359

I suspect the algorithm could run slightly more efficient if when bringing in the data, the attributes were stored in the same HashMap instead of separately and then iterating through all these HashMaps later.
