package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int result = 0;

		if(!isEmpty()){

			SingleLinkedListNode<T> aux = head;

			while(!aux.isNIL()){
				result += 1;
				aux = aux.getNext();
			}

		}

		return result;
	}

	@Override
	public T search(T element) {
		T result = null;

		if(element != null && !isEmpty()){

			SingleLinkedListNode<T> aux = head;

			while(!aux.isNIL()){

				if(aux.getData().equals(element))
					result = aux.getData();

				aux = aux.getNext();
			}

		}

		return result;
	}

	@Override
	public void insert(T element) {
		if(element != null){

			SingleLinkedListNode<T> node = head;

			while(!node.isNIL())
				node = node.getNext();

			node.setData(element);
			node.setNext(new SingleLinkedListNode<>());
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && !isEmpty()){

			if(this.head.getData().equals(element))
				this.head = this.head.getNext();

			else{

				SingleLinkedListNode<T> aux = head;
				SingleLinkedListNode<T> previous = head;

				while(!aux.isNIL() && !aux.getData().equals(element)){

					previous = aux;
					aux = aux.getNext();

				}

				if(!aux.isNIL())
					previous.next = aux.getNext();

			}

		}

	}

	@Override
	public T[] toArray() {
		T[] saida = (T[]) new Comparable[size()];

		int count = 0;

		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			saida[count++] = aux.getData();
			aux = aux.getNext();
		}

		return saida;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
