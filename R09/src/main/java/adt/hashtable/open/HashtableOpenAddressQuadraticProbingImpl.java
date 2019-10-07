package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	private int getHash(T element, int probe){
		return  Math.abs((((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe)));
	}


	@Override
	public void insert(T element) {
		if (element != null) {

			if (!this.isFull() && indexOf(element) == -1) {

				int i = 0;
				boolean insert = false;

				while (!insert) {

					int hash = getHash(element, i++);
					if (this.table[hash] == null || this.table[hash].equals(this.deletedElement)) {

						insert = true;
						this.table[hash] = element;
						this.elements++;


					} else
						this.COLLISIONS++;

				}

			}

			else throw new HashtableOverflowException();

		}

	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()){

			int hash = indexOf(element);

			if(hash != -1){
				this.table[hash] = this.deletedElement;
				this.elements--;
			}

		}
	}

	@Override
	public T search(T element) {
		T result = null;

		if(element != null){

			if(!this.isEmpty()){

				int hash = indexOf(element);

				if(hash != -1)
					result = (T) this.table[hash];

			}

		}

		return result;
	}

	@Override
	public int indexOf(T element) {
		int result = -1;

		if(element != null && !isEmpty()){

			int i = 0;
			int hash;
			boolean find = false;

			while(!find && i < size()){

				hash = getHash(element, i++);

				if(this.table[hash] == null || this.table[hash].equals(deletedElement))
					find = true;

				else if(this.table[hash].equals(element)){
					result = hash;
					find = true;
				}

			}

		}

		return result;
	}
}
