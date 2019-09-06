package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(validacao(array, leftIndex, rightIndex)) {
			Integer[] menorEmaior = procura(array, leftIndex, rightIndex);
			Integer[] contador = new Integer[menorEmaior[1] - menorEmaior[0] + 1];
			Integer[] vetorAux = new Integer[rightIndex - leftIndex + 1];
			
			Arrays.fill(contador, 0);
			
			for(int i = 0; i <= rightIndex; i++) {
				contador[array[i] - menorEmaior[0]]++;
			}
			for(int i = 0; i < contador.length - 1; i ++) {
				contador[i + 1] += contador[i];
			}
			
			for(int i = rightIndex; i >= leftIndex; i--) {
				Integer elemento = array[i];
				vetorAux[--contador[elemento - menorEmaior[0]]] = array[i];
			}
			
			for(int i = leftIndex; i <= rightIndex; i++) {
				array[i] = vetorAux[i - leftIndex];
			}
		}
	}

	private Integer[] procura(Integer[] array, int leftIndex, int rightIndex) {
		Integer menor = array[leftIndex];
		Integer maior = array[leftIndex];
		for(int i = leftIndex; i <= rightIndex; i++) {
			if(array[i].compareTo(menor) < 0) {
				menor = array[i];
			}
			if(array[i].compareTo(maior) > 0) {
				maior = array[i];
			}
		}
		Integer[] saida = {menor, maior};
		return saida;
	}

	
	private boolean validacao(Integer[] array, int leftIndex, int rightIndex) {
		boolean valido = true;
		if (array == null || array.length == 0) valido = false;
		else if((leftIndex >= rightIndex) || (leftIndex < 0) || (rightIndex <= 0)) valido = false;
		else if((rightIndex > array.length - 1) || (leftIndex > array.length)) valido = false;
		return valido;
	}
}
