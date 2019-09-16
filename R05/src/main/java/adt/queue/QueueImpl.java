package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T element = null;
		if(!isEmpty())
			element = array[0];
		return element;
	}

	@Override
	public boolean isEmpty() {
		return tail == -1;
	}

	@Override
	public boolean isFull() {
		return tail == array.length - 1;
	}

	private void shiftLeft() {
		for(int i = 0; i < tail; i++)
			array[i] = array[i + 1];
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(!isFull()) {
			array[++tail] = element;
		}
		else throw new QueueOverflowException();
	}
//	[1, 2]
	@Override
	public T dequeue() throws QueueUnderflowException {
		if(!isEmpty()) {
			T element = array[0];
			tail--;
			shiftLeft();
			return element;
		}
		throw new QueueUnderflowException();
	}

}
