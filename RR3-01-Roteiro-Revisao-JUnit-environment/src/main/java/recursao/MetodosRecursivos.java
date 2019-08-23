package recursao;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		return calcularSomaArray(array, 0);
	}
	
	public int calcularSomaArray(int[] array, int i) {
		int result;
		if(i == array.length) {
			result = 0;
		}else {
			result = array[i] + calcularSomaArray(array, i + 1);
		}
		return result;
	}
	
	public int calcularFatorial(int n) {
		int result = 0;
		if(n == 0) {
		  result = 1;
		  System.out.println("0! = 1");
		}
		else {
			result = n * calcularFatorial(n - 1);
			System.out.println(n + "! = " +result);
		}
		return result;
	}

	public int calcularFibonacci(int n) {
		int result = 1;
		// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR E IMPRIMIR
		// O N-ESIMO TERMO
		// DA SEQUENCIA DE FIBONACCI, QUE TEM A SEGUINTE LEI DE FORMACAO: O
		// PRIMEIRO E SEGUNDO NUMEROS
		// DA SEQUENCIA SÃO 1. A PARTIR DO TERCEIRO TERMO, CADA TERMO DA
		// SEQUENCIA É DADO
		// PELA SOMA DOS OUTROS DOIS ANTERIORES. OBSERVE QUE SENDO O METODO
		// RECURSIVO, O FIBONACCI DOS NUMEROS ANTERIORES TAMBEM VAO SER
		// IMPRESSOS
		return result;
	}

	public int countNotNull(Object[] array) {
		return countNotNull(array, 0);
	}
	
	public int countNotNull(Object[] array, int i) {
		int naoNulos = 0;
		if(i == array.length - 1) {
			if(array[i] != null)
				naoNulos += 1;
		}
		else {
			if(array[i] != null)
				naoNulos = 1 + countNotNull(array, i + 1);
			else
				naoNulos = countNotNull(array, i + 1);
		}
		return naoNulos;
	}

	public int potenciaDe2(int expoente) {
		int result = 1;
		if(expoente > 0) {
			result = 2 * potenciaDe2(expoente - 1);
		}
		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = termoInicial;
		if(n == 1) {
			
		}		
		else result = progressaoAritmetica(termoInicial, razao, n - 1) + razao;
		
		return result;
	}


	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = termoInicial;
		if(n == 1) {
			
		}
		else result = progressaoGeometrica(termoInicial, razao, n - 1) * razao;
		
		return result;
	}
	
}
