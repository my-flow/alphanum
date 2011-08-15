The Alphanum Algorithm
======================

"People sort strings with numbers differently than software does. Most sorting 
algorithms compare ASCII values, which produces an ordering that is 
inconsistent with human logic." David Koelle's approach to this problem is known
as the [Alphanum Algorithm](http://www.davekoelle.com/alphanum.html).

While the sorting algorithm as such works well, the [Java implementation]
(http://www.davekoelle.com/files/AlphanumComparator.java) has some minor, but 
annoying flaws when it comes to static analysis and standard coding guidelines. 
That's why including its source code in professional projects with a focus on 
code quality can easily become troublesome. 

However, [this Java version of the Alphanum Algorithm]
(https://raw.github.com/my-flow/alphanum/master/src/AlphanumComparator.java) is
compatible with the following QA tools:

* [PMD](http://pmd.sourceforge.net): default rules configuration.
* [FindBugs™](http://findbugs.sourceforge.net): all bug categories, low minimum 
priority to report, maximal analysis effort.
* [Checkstyle](http://checkstyle.sourceforge.net): Sun Checks aka [Code 
Conventions for the Java™ Programming Language]
(http://www.oracle.com/technetwork/java/codeconvtoc-136057.html)

Build Prerequisites
-------------------
* J2SE 5.0 or newer

API (Javadoc)
------------------
* [Alphanum Algorithm API]
(http://my-flow.github.com/alphanum/docs/api/index.html) 

License
-------
The Alphanum Algorithm is released under the [GNU Lesser General Public License]
(http://www.gnu.org/licenses/lgpl.html).
