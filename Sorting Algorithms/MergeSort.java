/*@authors Keerthi Manu,Nithin,Pranathi,Namratha*/

package cs6301.g22;

public class MergeSort {
//Method which implements the Merge Sort Algorithm
	static void mergeSort(int[] arr, int[] temp)
	{
		mergeSort(arr, temp, 0, arr.length-1);
	}
	static void mergeSort(int[] arr, int[] temp,int start, int end)
	{
		
		if(start >= end)
			return;
		int middle = (start + end)/2;
		mergeSort(arr, temp, start, middle);
		mergeSort(arr, temp, middle+1, end);
		merge(arr, temp, start, end);
	}
	static void merge(int[] arr, int[] temp, int start, int end)
	{
		int size = end-start + 1;
		
		int leftStart = start;
		int leftEnd = (start+end)/2;
		
		int rightStart = leftEnd +1;
		int rightEnd = end;
				
		int left = leftStart;
		int right = rightStart;
		int index = leftStart;
		
		while(left <= leftEnd && right <= rightEnd)
		{
			
			if(arr[left] <= arr[right])			
				temp[index++] = arr[left++];
			else			
				temp[index++] = arr[right++];				
			
		}
		
		while(left <= leftEnd)
			temp[index++] = arr[left++];
		while(right <= rightEnd)
			temp[index++] = arr[right++];
		
		System.arraycopy(temp, leftStart, arr, leftStart, size);
	}	
}