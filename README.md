# Document-Distance
A course project in Algorithms and Data Structure to measure document similarities based on the document distance problem
The Algorithm used is cosine similarity - a vector based similarity measure.

The cosine distance of two documents is defined by the angle between their feature vectors , which in our case are word frequency vectors. https://en.wikipedia.org/wiki/Cosine_similarity

We start by reading the text in the files and counting frequency for each word.
The word frequency distribution for the text D in Java is a Map from words to their frequency counts (<word><freq(word)>) .
We need a method to calculate the inner product(dot product) of the frequency vectors such as: X*Y = x1y1 + x2y2 + ... + xnyn.

After having those values we can calculate the angle cosƟ.

