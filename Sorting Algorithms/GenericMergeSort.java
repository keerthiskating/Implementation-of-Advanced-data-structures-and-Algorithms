/*@authors Keerthi Manu,Nithin,Pranathi,Namratha*/
package cs6301.g22;
public class GenericMergeSort {
	//Method which implements MergeSort
	static<T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp) {				
		mergeSort(arr, tmp, 0, arr.length-1);		
	}
	
	static<T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp, int start, int end) {		
		if(start >= end)
			return;
		int middle = (start + end)/2;
		mergeSort(arr, tmp, start, middle);
		mergeSort(arr, tmp, middle+1, end);
		merge(arr, tmp, start, end);
		
	}

	static<T extends Comparable<? super T>> void merge(T[] arr, T[] tmp, int start, int end) {
		int size = end-start + 1;
		
		int leftStart = start;
		int leftEnd = (start+end)/2;		
		int rightStart = leftEnd +1;
		int rightEnd = end;
		
		int left = leftStart;
		int right = rightStart;
		int index = leftStart;
		
		while(left <= leftEnd && right <= rightEnd){
			if(arr[left].compareTo(arr[right]) <= 0)
				tmp[index++] = arr[left++];
			else
				tmp[index++] = arr[right++];
		}
		
		while(left <= leftEnd)
			tmp[index++] = arr[left++];
		
		while(right <= rightEnd)
			tmp[index++] = arr[right++];
		
		System.arraycopy(tmp, leftStart, arr, leftStart, size);
	}		
}