<a href="http://alvarogarcia7.github.io/">Álvaro</a> and I have been pairing lately to do a subset of the Bank Account kata that <a href="http://codurance.com/blog/author/sandro-mancuso/">Sandro Mancuso</a> proposed as an exercise in his <a href="http://www.meetup.com/Barcelona-Software-Craftsmanship/messages/boards/thread/48519810">Crafting Code course</a>.
<br><br>
We had to write and make the following acceptance test pass using <a href="http://coding-is-like-cooking.info/2013/04/the-london-school-of-test-driven-development/">Outside-In TDD</a>:

<pre>
Given a client makes a deposit of 1000.00 on 01/04/2014
And a withdrawal of 100.00 on 02/04/2014
And a deposit of 500.00 on 10/04/2014
When she prints her bank statement
Then she would see

DATE | AMOUNT | BALANCE
10/04/2014 | 500.00 | 1400.00
02/04/2014 | -100.00 | 900.00
01/04/2014 | 1000.00 | 1000.00
</pre>

The goal of the exercise was to understand the outside-in or London-school TDD style.
