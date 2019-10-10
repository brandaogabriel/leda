package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	private boolean contains(T element){
		boolean result = false;

		for(T value : super.toArray()){

			if(element.equals(value)){
				result = true;
				break;
			}

		}
		return result;
	}

	@Override
	public void removeDuplicates() {
		T[] array = super.toArray();
		SingleLinkedListImpl<T> newList = new SingleLinkedListImpl<>();

		for(int i = 0; i < array.length; i++){

			if(newList.search(array[i]) == null)
				newList.insert(array[i]);

		}

		this.setHead(newList.getHead());

	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		SetLinkedList<T> newSet = new SetLinkedListImpl<>();

		for(T value : this.toArray())
			newSet.insert(value);

		for(T value : otherSet.toArray())
			newSet.insert(value);

		newSet.removeDuplicates();
		return newSet;
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		SetLinkedList<T> newSet = new SetLinkedListImpl<>();

		for(T value : otherSet.toArray()){

			if(this.contains(value))
				newSet.insert(value);

		}

		newSet.removeDuplicates();
		return newSet;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		SingleLinkedListNode<T> auxNode = this.getHead();

		while(!auxNode.isNIL())
			auxNode = auxNode.getNext();

		auxNode.setData(((SingleLinkedListImpl<T>) otherSet).getHead().getData());
		auxNode.setNext(((SingleLinkedListImpl<T>) otherSet).getHead().getNext());

	}

}
