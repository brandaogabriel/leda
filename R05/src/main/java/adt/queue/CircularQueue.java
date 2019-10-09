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

			if(isEmpty())
				this.head = 0;

			tail = (tail + 1) % array.length;
			array[tail] = element;
			elements++;
		}
		else throw new QueueOverflowException();
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(!isEmpty()) {
			T element = array[head];

//			Se contem apenas 1 elemento
			if(this.head == this.tail){
				this.head = -1;
				this.tail = -1;
			}

			else
				head = (head + 1) % array.length;

			elements--;
			return element;
		}
		throw new QueueUnderflowException();
	}

	@Override
	public T head() {
		T element = null;
		if(!isEmpty())
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
