package adt.bst;



public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BSTNode<T> node) {
		int height = -1;

		if(!node.isEmpty())
			height = 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));

		return height;
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> result = new BSTNode<>();

		if(element != null){

			if(!isEmpty())
				result = search(element, this.root);

		}

		return result;
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> aux = new BSTNode<>();

		if(!node.isEmpty()){

			if(node.getData().equals(element))
				aux = node;

			else if(node.getData().compareTo(element) > 0)
				aux = search(element, (BSTNode<T>) node.getLeft());

			else
				aux = search(element, (BSTNode<T>) node.getRight());

		}

		return aux;
	}

	@Override
	public void insert(T element) {
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

			if(node.getData().compareTo(element) > 0)
				insert(element, (BSTNode<T>) node.getLeft());

			else
				insert(element, (BSTNode<T>) node.getRight());
		}

	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> max = null;

		if(!isEmpty())
			max = maximum(this.root);

		return max;
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> aux = node;

		if(!node.getRight().isEmpty())
			aux = maximum((BSTNode<T>) node.getRight());

		return aux;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> min = null;

		if(!isEmpty())
			min = minimum(this.root);

		return min;
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> aux = node;

		if(!node.getLeft().isEmpty())
			aux = minimum((BSTNode<T>) node.getLeft());

		return aux;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> sucess = search(element);

		if(element != null && !isEmpty() && !sucess.isEmpty()){

			if(!sucess.getRight().isEmpty())
				sucess = minimum((BSTNode<T>) sucess.getRight());

			else
				sucess = sucessor(sucess);

			}

		else
			sucess = null;

		return sucess;
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		BSTNode<T> aux = (BSTNode<T>) node.getParent();

		if(node.getParent() != null){

			if(!aux.isEmpty() && aux.getRight().equals(node))
				aux = sucessor(aux);

		}

		return aux;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> pred = search(element);

		if(element != null && !isEmpty() && !pred.isEmpty()){

			if(!pred.getLeft().isEmpty())
				pred = maximum((BSTNode<T>) pred.getLeft());

			else
				pred = predecessor((BSTNode<T>) pred);

		}

		else
			pred = null;

		return pred;
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> aux = (BSTNode<T>) node.getParent();

		if(node.getParent() != null){

			if(!aux.isEmpty() && aux.getLeft().equals(node))
				aux = predecessor(aux);

		}

		return aux;
	}

	@Override
	public void remove(T element) {
		if(element != null && !isEmpty()){

			BSTNode<T> node = search(element);

			if(!node.isEmpty()){

				if(node.isLeaf()){

					node.setData(null);
					node.setLeft(null);
					node.setRight(null);

				}

				else if(node.getLeft().isEmpty() || node.getRight().isEmpty()){

					BSTNode<T> parent = (BSTNode<T>) node.getParent();

					if(parent != null){

						if(!parent.getLeft().equals(node)){

							if(!node.getLeft().isEmpty()){

								parent.setRight(node.getLeft());
								node.getLeft().setParent(parent);

							}

							else{

								parent.setRight(node.getRight());
								node.getRight().setParent(parent);

							}

						}

						else{

							if(!node.getLeft().isEmpty()){

								parent.setLeft(node.getLeft());
								node.getLeft().setParent(parent);

							}

							else{

								parent.setLeft(node.getRight());
								node.getRight().setParent(parent);

							}

						}

					}

					else{

						if(node.getLeft().isEmpty())
							this.root = (BSTNode<T>) node.getRight();

						else
							this.root = (BSTNode<T>) node.getLeft();

						this.root.setParent(null);

					}

				}

				else{

					T sucessor = sucessor(node.getData()).getData();
					remove(sucessor);
					node.setData(sucessor);

				}

			}

		}

	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[size()];

		preOrder(array, this.root, 0);

		return array;
	}

	private void preOrder(T[] array, BSTNode<T> node, int index) {
		if (!node.isEmpty()) {

			array[index] = node.getData();
			preOrder(array, (BSTNode<T>) node.getLeft(), index + 1);
			preOrder(array, (BSTNode<T>) node.getRight(), index + 1 + size((BSTNode<T>) node.getLeft()));

		}

	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[size()];

		order(array, this.root, 0);

		return array;
	}

	private int order(T[] array, BSTNode<T> node, int index) {
		if (!node.isEmpty()) {

			index = order(array, (BSTNode<T>) node.getLeft(), index);
			array[index++] = node.getData();
			index = order(array, (BSTNode<T>) node.getRight(), index);

		}

		return index;
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[size()];

		postOrder(array, this.root, 0);

		return array;
	}

	private int postOrder(T[] array, BSTNode<T> node, int index) {
		if (!node.isEmpty()) {

			index = postOrder(array, (BSTNode<T>) node.getLeft(), index);
			index = postOrder(array, (BSTNode<T>) node.getRight(), index);
			array[index++] = node.getData();
		}

		return index;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
