package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex && array !=null) {
			int meio = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, meio);
			sort(array, meio + 1, rightIndex);
			merge(array, leftIndex, meio, rightIndex);
		}
	}

	@SuppressWarnings("unchecked")
	private void merge(T[] array, int leftIndex, int meio, int rightIndex) {
		T[] aux= (T[]) new Comparable[array.length];
		for(int i = leftIndex; i <= rightIndex; i++) aux[i] = array[i];
		
		int auxInicio = leftIndex;
		int auxFim = meio + 1;
		int presente = leftIndex;
		
		while(auxInicio <= meio && auxFim <= rightIndex) {
			if(aux[auxInicio].compareTo(aux[auxFim]) > 0) {
				array[presente] = aux[auxFim];
				auxFim++;
			}
			else {
				array[presente] = aux[auxInicio];
				auxInicio++;
			}presente++;
		}
		for(int i = 0; i <= (meio - auxInicio); i++) array[presente + i] = aux[auxInicio + i];
	}
}
