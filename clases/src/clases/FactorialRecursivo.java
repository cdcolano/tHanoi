package clases;

public class FactorialRecursivo {
	public static int factorial(int n) {
		int factorial=1;
		if (n<0) return -1 ;
		else {
			if (n!=0) {
				factorial= n* FactorialRecursivo.factorial(n-1);
			}
			return factorial;
		}
	}
	
	public static void main (String[]args) {
		System.out.println(multiplicacion(5, 5));
	}
	
	
	
	public static int multiplicacion(int n, int m) {
		int contador=0;
		int resultado=0;
		if (contador<n) {
			resultado= m +multiplicacion(n-1, m);
			contador++;
		}
		return resultado;
	}
}
