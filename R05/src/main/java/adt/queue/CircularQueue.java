package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(!isFull()) {
			tail = (tail + 1) % 10;
			array[tail] = element;
			elements++;
		}
		else throw new QueueOverflowException();
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(!isEmpty()) {
			head = (head + 1) % 10;
			T element = array[head];
			elements--;
			return element;
		}
		throw new QueueUnderflowException();
	}

	@Override
	public T head() {
		T element = null;
		if(!isEmpty())
			head = (head + 1) % 10;
			element = array[head];
		return element;
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == array.length;	
	}

}
