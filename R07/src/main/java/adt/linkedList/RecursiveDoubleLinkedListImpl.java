package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insert(T element){
		if(element != null){

			if(isEmpty()){

				setData(element);
				setNext(new RecursiveDoubleLinkedListImpl<>());
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);

			}

			else
				this.getNext().insert(element);
		}
	}

	@Override
	public void remove(T element){
		if(element != null){

			if(this.getData().equals(element)){

				if(this.getPrevious() == null && this.getNext().isEmpty()){
					removeFirst();
				}

				else {

					setData(this.next.getData());
					setNext(this.getNext().getNext());

					if(this.getNext() != null)
						((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(this);
				}
			}

			else
				this.next.remove(element);

		}
	}



	@Override
	public void insertFirst(T element) {
		if(element != null){

			if(isEmpty()){
				insert(element);
			}

			else{

				RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<>();
				node.setData(this.getData());
				node.setNext(this.getNext());

				this.setData(element);
				this.setNext(node);

				node.setPrevious(this);

				((RecursiveDoubleLinkedListImpl<T>) node.getNext()).setPrevious(node);

			}

		}
	}

	@Override
	public void removeFirst() {
		if(!isEmpty()){

			if(this.getPrevious() == null && this.getNext().isEmpty()){

				setData(null);
				setNext(new RecursiveSingleLinkedListImpl<>());
				setPrevious(new RecursiveDoubleLinkedListImpl<>());

			}

			else {

				this.data = this.getNext().getData();
				this.next = this.getNext().getNext();
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);

			}
		}
	}

	@Override
	public void removeLast() {

		if(!isEmpty()){

			if(this.getNext().isEmpty()){

				setData(null);
				setNext(new RecursiveDoubleLinkedListImpl<>());

			}

			else
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).removeLast();
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
