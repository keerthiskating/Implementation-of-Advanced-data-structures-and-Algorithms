/*@authors Keerthi Manu,Nithin,Pranathi,Namratha*/

package cs6301.g22;

public class InsertionSort {
	static<T extends Comparable<? super T>> void insertionsort(T[] arr)
	{		
		int n = arr.length;
		/*from the position i,the array is unsorted*/
		for(int i=1; i<n;i++)
		{
			T value = arr[i];
			int j=i-1;
			/*looping through the sorted part of array to insert the value in the correct position*/
			while(j>=0 && value.compareTo(arr[j])<0)
			{
				arr[j+1] = arr[j];
				j = j-1;
				
			}
			
			arr[j+1] = value;
		}		
	}	
}