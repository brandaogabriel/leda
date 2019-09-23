package adt.linkedList;

import java.util.Arrays;

public class Lista {

	public static void main(String[] args) {
		SingleLinkedListImpl<Integer> lista = new SingleLinkedListImpl<>();
		lista.insert(321);
		lista.insert(32);
		lista.insert(434);
		lista.insert(765);
		lista.insert(24);
		
		System.out.println(Arrays.toString(lista.toArray()));
	}

}
