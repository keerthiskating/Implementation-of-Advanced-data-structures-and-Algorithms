/*@authors Keerthi Manu,Nithin,Pranathi,Namratha*/

package cs6301.g22;

import java.util.Scanner;

public class TestSort {
	
    public static<T> void printArray(T[] arr, String message) {
	printArray(arr, 0, arr.length-1, message);
    }

    public static<T> void printArray(T[] arr, int from, int to, String message) {
	System.out.print(message);
	for(int i=from; i<=to; i++) {
	    System.out.print(" " + arr[i]);
	}
	System.out.println();
    }
    
    public static void printArray(int arr[], String message) {
	System.out.print(message);
	for(int i=0; i<=arr.length-1; i++) {
	    System.out.print(" " + arr[i]);
	}
	System.out.println();
    }
    	
	public static void main(String args[]) {
		int n = Integer.parseInt(args[0]);
		
       	int[] arr = new int[n];
       	int[] temp = new int[n]; 
       	
       	Integer[] arr1 = new Integer[n];       	
       	Integer[] arr2 = new Integer[n];
       	Integer[] temp2 = new Integer[n]; 

       	for(int i=0; i<n; i++)
       		arr[i] = i;
       	for(int i=0; i<n; i++)
       		arr1[i] = new Integer(i);
       	for(int i=0; i<n; i++)
       		arr2[i] = new Integer(i);       	
       	       	
       	Shuffle.shuffle(arr);       
       	Shuffle.shuffle(arr1);
       	Shuffle.shuffle(arr2);
       	
       	Timer timer = new Timer();
       	       	
        timer.start();
        MergeSort.mergeSort(arr,temp);
        timer.end();                                
        System.out.println("int MergeSort " + timer);	        
    	
      	timer.start();
        GenericMergeSort.mergeSort(arr2,temp2);
        timer.end();                             
        System.out.println("Generic MergeSort " + timer);										
		
	    timer.start();
        InsertionSort.insertionsort(arr1);
        timer.end();                               
        System.out.println("Insertion sort " + timer);
        		
	}
}
