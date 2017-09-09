Sort:
	MergeSort.java
	GenericMergeSort.java
	InsertionSort.java
	TestSort.java
	Timer.java
	Shuffle.java

MergeSort is implemented for Generic Type arrays and int arrays.
InsertionSort is implemented for Generic Type arrays.

MergeSort.java - Sorts int type arrays using Divide & Conquer technique.
GenericMergeSort.java - Sorts Generic Type arrays using Divide & Conquer technique.
InsertionSort.java - Sorts Generic Type arrays using insertion sort technique.
TestSort.java - Driver class.	
Timer.java - To calculate the time taken for each type of sort.
Shuffle.java -  To shuffle the array before sorting.

run.sh - It is a shell script to run TestSort.java. This shell script accepts size of the array as input. If the execution time goes beyond 2minutes the program terminates.

Execution :
	1) Compile all the .java files
	2) chmod +x run.sh
	3) ./run.sh
	4) Enter input size of the array

Results:
			                 Size of input

Type			  1M		5M            10M	   16M

GenericInsertionSort    infinity     infinity       infinity     infinity

int MergeSort		150msec      847msec	    1711msec     2836msec

GenericMergeSort	482msec	     2666msec	    5804msec	 9915msec