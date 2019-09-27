package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(element != null)

			if(!isFull()) this.top.insert(element);

		else throw new StackOverflowException();
	}

	@Override
	public T pop() throws StackUnderflowException {
		T element = null;

		if(isEmpty()) throw new StackUnderflowException();

		else {
			element = this.top();
			this.top.removeLast();
		}

		return element;
	}

	@Override
	public T top() {
		T result = null;

		if(!isEmpty()){
			T[] array = top.toArray();
			result = array[array.length  - 1];
		}

		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size;
	}

}
