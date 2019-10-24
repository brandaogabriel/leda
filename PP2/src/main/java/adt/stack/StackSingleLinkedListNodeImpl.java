package adt.stack;

import adt.linkedList.SingleLinkedListNode;
//import org.graalvm.compiler.nodes.memory.MemoryCheckpoint;

/**
 * Classe que representa um apilha implementada usando-se um noh de uma lista
 * simplesmente ligada, como estrutura sobrejacente. 
 * 
 * Restricoes:
 * - voce DEVE obedecer a politica de acesso e complexidade dos metodos de pilha, ou seja, todos em O(1).
 * - voce NÃO pode usar memoria extra (estrutura auxiliar)
 * - qualquer metodo auxiliar que voce necessite DEVE ser implementado nesta classe
 * - voce NÃO pode modificar a classe SingleLinkedListNode
 * 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class StackSingleLinkedListNodeImpl<T> implements Stack<T> {
	
	private SingleLinkedListNode<T> top;
	private int tamanhoAtual;
	private int tamanhoMaximo;
	/**
	 * A pilha para ser criada precisa ter um tamanho maximo
	 * @param tamanhoMaximo
	 */
	public StackSingleLinkedListNodeImpl(int tamanhoMaximo) {
		this.tamanhoAtual = 0;
		this.tamanhoMaximo = tamanhoMaximo;
		this.top = new SingleLinkedListNode<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull())
			throw new StackOverflowException();

		else{

			SingleLinkedListNode<T> aux = new SingleLinkedListNode<>();
			aux.setData(element);

			if(isEmpty()){
				aux.setNext(new SingleLinkedListNode<>());
				this.top = aux;

			}

			else{

				SingleLinkedListNode<T> previous = this.top;
				aux.setNext(previous);
				this.top = aux;
			}


			this.tamanhoAtual += 1;

		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		T element = null;

		if(isEmpty())
			throw new StackUnderflowException();

		else{

			element = top.getData();

			if(this.tamanhoMaximo == 1)
				this.top = new SingleLinkedListNode<>();

			else{
				SingleLinkedListNode<T> aux = top;
				this.top = aux.getNext();

			}

			this.tamanhoAtual--;
		}

		return element;
	}




	@Override
	public T top() {
		T result = null;

		if(!this.top.isNIL())
			result = top.getData();

		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isNIL();

	}

	@Override
	public boolean isFull() {
		return tamanhoAtual == (tamanhoMaximo);
	}

}
