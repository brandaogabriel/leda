package adt.stack;

public class program {

    public static void main(String[] args) throws StackOverflowException, StackUnderflowException {

        StackSingleLinkedListNodeImpl<Integer> pilha = new StackSingleLinkedListNodeImpl<>(4);

        pilha.push(1);
        pilha.push(2);
        pilha.push(3);
        pilha.push(4);


        System.out.println(pilha.top());
        pilha.pop();
        System.out.println(pilha.top());

        pilha.pop();
        System.out.println(pilha.top());

        pilha.pop();
        System.out.println(pilha.top());


    }
}
