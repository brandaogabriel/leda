package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.setHead(new DoubleLinkedListNode<>());
		this.setLast((DoubleLinkedListNode<T>) this.head);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> caixa = new DoubleLinkedListNode();

			caixa.setData(element);
			caixa.setNext(new DoubleLinkedListNode());
			caixa.setPrevious(this.getLast());

			this.getLast().setNext(caixa);

			if (this.getLast().isNIL()) {
				this.setHead(caixa);
			}

			this.setLast(caixa);
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> caixa = new DoubleLinkedListNode();

			caixa.setData(element);
			caixa.setNext(this.getHead());
			caixa.setPrevious(new DoubleLinkedListNode<>());

			((DoubleLinkedListNode<T>) this.head).setPrevious(caixa);

			if (this.head.isNIL()) {
				this.setLast(caixa);
			}

			this.setHead(caixa);
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (this.getHead().getData().equals(element))
				this.removeFirst();
			else if (this.last.getData().equals(element))
				this.removeLast();
			else {
				DoubleLinkedListNode<T> caixa = (DoubleLinkedListNode) this.getHead();

				while (!caixa.isNIL() && !caixa.getData().equals(element)) {
					caixa = (DoubleLinkedListNode<T>) caixa.getNext();
				}

				if (!caixa.isNIL()) {
					caixa.getPrevious().setNext(caixa.getNext());
					((DoubleLinkedListNode<T>) caixa.getNext()).setPrevious(caixa.getPrevious());
				}
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.getHead().isNIL()) {
			this.head = this.head.getNext();
			if (this.head.isNIL()) {
				this.last = (DoubleLinkedListNode<T>) this.head;
			}

			((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<>());
		}
	}

	@Override
	public void removeLast() {
		if (!this.isEmpty()) {
			this.last = this.last.getPrevious();

			if (this.last.isNIL()) {
				this.head = this.last;
			}

			this.last.setNext(new DoubleLinkedListNode<T>());
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
