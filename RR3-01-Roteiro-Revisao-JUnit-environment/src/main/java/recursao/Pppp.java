package recursao;

public class Pppp {

	public static void main(String[] args) {
		
		
		MetodosRecursivos m = new MetodosRecursivos();
		System.out.println(m.potenciaDe2(4));
		
		m.calcularFatorial(2);
		
		int[] valores = {1, 2, 3, 4, 5};
		System.out.println(m.calcularSomaArray(valores));
		
		Object[] value = {1, 2, null, null, 5, 7, 8};
		System.out.println(m.countNotNull(value));
		
		
		System.out.println(m.progressaoAritmetica(1, 3, 6));
		
		System.out.println(m.progressaoGeometrica(2, 2, 3));
	}

}
