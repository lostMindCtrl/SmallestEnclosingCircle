# SmallestEnclosingCircle
The following project presents multiple solutions to the Smallest Enclosing Circle problem. 
The problem was suggested by https://algs4.cs.princeton.edu and is described on https://introcs.cs.princeton.edu/java/assignments/circle.html .In Brief, the smallest enclosing circle problem takes a specific set of points and finds the smallest circle, which includes every point of the set. Hence, the center point of the "smallest enclosing circle" represents a point with the shortest distance considering the furthest point in the given set. Therefore, the center point has meaningful applications in the physical world, such as defining the place to build a hospital or school, which is reachable from all considered points when the points are representing houses.  The Aforementioned Problem is referred to as a part of computational geometry and exists in 2d space as the "smallest enclosing circle" and in 3d space as the "smallest enclosing sphere" problem. Notably, the algorithms solving the matters find also applications in Machine Learning dilemmas.

<h2>Content of the project:</h2>
data/ <br>
algs4.jar <br>
BruteForce.java <br>
Chrystal.java <br>
Circle.java <br>
EvaluationUnit.java <br>
Megiddo.java <br>
Point.java <br>
Standard.java <br>
Visualize.java<br>
Welzl.java<br>
all classes depend on algs4.jar, which https://algs4.cs.princeton.edu provides.<br>

<h2>Usage and description:</h2>

<h3>BruteForce.java: </h3>
The BruteForce class solves the smallest enclosing circle problem in a brute force manner by trying every combination of circles, which include all points. It outputs the circle having the least circumference. The brute force approach terminates in O(n^3).
<br><br><b>Usage:</b><br>
BruteForce &ltsource_file&gt <br>
e.g. BruteForce data/circle/input10.txt<br>


<h3>Chrystal.java:</h3>
The Chrystal class solves the problem more efficiently. The implemented algorithm is based on the idea of P. Chrystal proposed at the third meeting of the Edinburgh Mathematical Society. His approach utilizes specific geometric properties of a circle by constructing a triangle linking the points on the circle's boundary and checking the angles of the points. While only considering the points describing the convex hull of the point set. The algorithm used to compute the Convex Hull in the Chrystal class is the GrahamScan to figure the convex hull efficiently. 
<br><br><b>Usage:</b><br>
Chrystal &ltsource_file&gt <br>
e.g.  Chrystal data/circle/input10.txt

<h3>Circle.java:</h3>
The Circle class represents the circle and calculates the circumference, midpoint, and radius. 

<h3>EvaluationUnit.java: </h3>
The EvaluationUnit checks whether an algorithm is implemented correctly and further compares two of the implemented algorithms displaying the time usage comparison. The class test for all files in data/circle/*. 
<br><br><b>Usage:</b><br>
EvaluationUnit &ltalgorithm&gt &ltstart_index&gt &ltend_index&gt <br>
Chrystal: -c , Standard: -s , Welzl: -w <br>
e.g. EvaluationUnit -s 0 500

<h3>Megiddo.java: </h3>
The Megiddo class describes the linear time solution proposed by Megiddo. The Megiddo class is not finished yet and will be finished in the future.

<h3>Point.java: </h3>
The Point class represents a point with x and y coordinates. The class presents different sorting opportunities, e.g., by x,y coordinate, or polar coordinate order(beneficial for  Graham Scan).

<h3>Standard.java: </h3>
Represents an intuitive approach to the problem. Shrinking the point until the algorithm converges onto a circle which arc length within the points on the boundary is smaller than half the circumference. Otherwise, shrinking the circle further in respective to another point. 
<br><br><b>Usage:</b><br>
Standard &ltsource_file&gt <br>
e.g.  Standard data/circle/input10.txt

<h3>Visualize.java: </h3>
A visualization of the Standard algorithm.
<br><br><b>Usage:</b><br>
Visualize &ltsource_file&gt <br>
e.g.  Visualize data/circle/input10.txt

<h3>Welzl.java: </h3>
Welzl describes a randomized algorithm that converges in linear time.
<br><br><b>Usage:</b><br>
Welzl &ltsource_file&gt <br>
e.g.  Welzl data/circle/input10.txt

<h4>Disclaimer: </h4>
The usage syntax is just the syntax for the correct execution of the different classes. The standard java execution has still to be used:
<br>e.g.,  java -cp ".;alg4.jar" Standard data/circle/input226.txt<br>
Test files are provieded in data/circle/*

<h2>Examples: </h2><br>


