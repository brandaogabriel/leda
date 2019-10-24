package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public void insert(T element){
		if(element != null)
			insert(element, this.root);

	}

	private void insert(T element, BSTNode<T> node) {
		if(node.isEmpty()){

			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());

		}

		else{

			if(this.comparator.compare(node.getData(), element) > 0)
				insert(element, (BSTNode<T>) node.getLeft());

			else
				insert(element, (BSTNode<T>) node.getRight());

		}

	}


	@Override
	public BSTNode<T> search(T element){
		BSTNode<T> result = new BSTNode<>();

		if(element != null)
			result = search(element, this.root);

		return result;
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> aux = new BSTNode<>();

		if(!node.isEmpty()){

			if(node.getData().equals(element))
				aux = node;

			else if(this.comparator.compare(node.getData(), element) > 0)
				aux = search(element, (BSTNode<T>) node.getLeft());

			else
				aux = search(element, (BSTNode<T>) node.getRight());

		}

		return aux;
	}


	@Override
	public T[] sort(T[] array) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}


	@Override
	public T[] reverseOrder() {

		T[] result = (T[]) new Comparable[size()];

		if(!isEmpty())

			reverse(result, 0, super.root);

		return result;
	}

	private int reverse(T[] result, int index, BSTNode<T> node) {
		if(!node.isEmpty()){

			index = reverse(result, index, (BSTNode<T>) node.getRight());
			result[index++] = node.getData();
			index = reverse(result, index, (BSTNode<T>) node.getLeft());

		}

		return index;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
