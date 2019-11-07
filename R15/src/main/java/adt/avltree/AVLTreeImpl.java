package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int balance = 0;

		if(!node.isEmpty())
			balance = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());

		return balance;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);

		if(balance > 1)
			pesaPraEsquerda(node);

		else if (balance < -1)
			pesaPraDireita(node);

	}

	private void pesaPraDireita(BSTNode<T> node) {
		BSTNode<T> aux;

		if(calculateBalance((BSTNode<T>) node.getRight()) < 0)
			aux = Util.leftRotation(node);

		else {

			Util.rightRotation((BSTNode<T>) node.getRight());
			aux = Util.leftRotation(node);

		}

		if(aux.getParent() == null)
			super.root = aux;

	}

	private void pesaPraEsquerda(BSTNode<T> node) {
		BSTNode<T> aux;

		if(calculateBalance((BSTNode<T>) node.getLeft()) > 0)
			aux = Util.rightRotation(node);

		else{

			Util.leftRotation((BSTNode<T>) node.getLeft());
			aux = Util.rightRotation(node);

		}

		if(aux.getParent() == null)
			super.root = aux;

	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		while(parent != null){

			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();

		}

	}

	@Override
	public void insert(T element){
		if(element != null)
			insert(element, super.root);
	}

	private void insert(T element, BSTNode<T> node){
		if(node.isEmpty()){

			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());

		}

		else{

			if(node.getData().compareTo(element) > 0)
				insert(element, (BSTNode<T>) node.getLeft());

			else
				insert(element, (BSTNode<T>) node.getRight());

			rebalance(node);

		}

	}

	@Override
	public void remove(T element){

		BSTNode<T> aux = super.search(element);
		super.remove(element);
		this.rebalanceUp(aux);

	}
}
