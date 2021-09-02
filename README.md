# Average-Term-Length-By-Initial-Character

Program reads the given text file and preprocess the text according to following order: Tokenize the text by whitespace, remove punctuations, and apply the lowercase. Then, it calculates followings: <br>
**Average Term Length By Initial Character:** Finds all words starting with the same letter/number and sums the lengths of these words separately for each letter/word. It divides the results of the addition operation by the number of words starting with that letter. For example, if our inputs are [”apple”,”banana”,”avocado”,”blueberry”], our output would be: <br>
a = 6 <br>
b = 7.5 <br>
**Total Minimum Distance:** For each term pair, program calculates the following formula: ( f(t1)*f(t2) ) / (1+ln∑d(t1,t2) )
where f(t) is the count of the term t in the text and d(t1,t2) gives the minimum distance between t1 and t2 where t1 is followed by t2. For example, If the text is ”aa bb cc aa cc dd bb” and t1 = aa and t2 = bb, then ∑ d(t1,t2) = 1+3 = 4. Program prints only topN pairs according to the score.
Output:<br>

![1](https://user-images.githubusercontent.com/44557162/131872849-cef38a80-9514-412f-91bb-19f592db4220.png) <br>
![2](https://user-images.githubusercontent.com/44557162/131872869-a38acdf2-8eab-459f-bd4d-e689dcc317f0.png)
