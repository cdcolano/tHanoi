package clases;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Dobleces {
		
	public static int dobleces(double DL, double grosor, int numDobleces) {
		if (grosor>=DL) {
			return numDobleces;
		}else {
			return dobleces(DL, grosor*2, numDobleces+1);
			
		}
	}
	
	
	public static int doblecesIterativo(double DL, double grosor) {
		int numDobleces=0;
		while (grosor<DL) {
			grosor=grosor*2;
			numDobleces++;
		}return numDobleces;
	}
	
	public static int doblecesDevolucion(double DL,double grosor) {
		int numDobleces=0;
		if (grosor>DL) {
			return numDobleces;
		}else {
			 return numDobleces=doblecesDevolucion(DL, grosor*2)+1;
			
		}
	}
	
	
	
	public static void main(String []args) {
		System.out.println("dobleces recur "+ dobleces(384400000, 0.0001, 0));
		System.out.println("dobleces iterativo "+ doblecesIterativo(384400000, 0.0001));
		System.out.println("dobleces devolucion "+ doblecesDevolucion(384400000, 0.0001));
		int []array=new int[100000000];
		for (int i=0;i<100000000;i++) {
			array[i]=i*2;
		}
		Integer []array2= {1,2,3,4,5};
		Stack<Integer>col1= new Stack<Integer>();
		for (int i=4; i>-1;i--) {
			col1.add(array2[i]);
		}
		Stack<Integer>col2= new Stack<Integer>();
		Stack<Integer>col3 = new Stack<Integer>();
		tHanoi(col1.size(), col1, col2, col3);
		System.out.println("COL 3 \n");
		int n=col3.size();
		for (int i=0;i<n;i++) { 
			System.out.println(col3.pop());
		}
		int buscado=20000001;
		long tiempoInicial= System.nanoTime();
		int donde= buscaEnVector(buscado, array,0, array.length-1 );
		long tiempoFinal=System.nanoTime();
		long tiempoInicialLento=System.nanoTime();
		int donde2= buscaEnVectorLento(buscado, array,0, array.length-1 );
		long tiempoFinalLento=System.nanoTime();
	/*	System.out.println("tiempo rapido " + (tiempoFinal-tiempoInicial));
		System.out.println("tiempo lento" + (tiempoFinalLento-tiempoInicialLento));
		System.out.println("Encontrado "+ buscado + " en posicion "+ donde  );
		System.out.println("Encontrado "+ buscado + " en posicion "+ donde2  );
	*/ cadena(3, "A", "B", "");
	}
	
	
	public static int buscaEnVector(int buscado, int[]array, int indiceInf,int indiceSup) {
		int seleccionado=(indiceInf + indiceSup)/2;
		if (buscado<array[indiceInf] || buscado>array[indiceSup]) {
			return -1;
		}
		if (array[seleccionado]==buscado) {
			return seleccionado;
		}else if (array[seleccionado]<buscado) {
			return buscaEnVectorLento(buscado, array, seleccionado+1, indiceSup);
		}
		else {
			return buscaEnVectorLento(buscado, array, indiceInf, seleccionado-1);
		}
	}
	
	
	
	
	public static int buscaEnVectorLento(int buscado, int[] array, int indiceInf, int indiceSup) {
		int seleccionado=(indiceInf + indiceSup)/2;
		if (indiceInf>indiceSup) {
			return -1;
		}
		if (buscado<array[indiceInf] || buscado>array[indiceSup]) {
			return -1;
		}
		if (array[seleccionado]==buscado) {
			return seleccionado;
		}else if (array[seleccionado]<buscado) {
			return buscaEnVectorLento(buscado, array, seleccionado+1, indiceSup);
		}
		else {
			return buscaEnVectorLento(buscado, array, indiceInf, seleccionado-1);
		}
	}
	
	
	
	
	public static void tHanoi(int nDiscos,Stack<Integer>columnaInicio, Stack<Integer>columnaIntermedia, Stack<Integer>columnaDestino ) {
		if (nDiscos==1) {
			int num=columnaInicio.pop();
			columnaDestino.push(num);
		}else {
				tHanoi(nDiscos-1,columnaInicio, columnaDestino, columnaIntermedia);
				int num=columnaInicio.pop();
				columnaDestino.push(num);
				tHanoi(nDiscos-1,columnaIntermedia, columnaInicio, columnaDestino);
			}
		}
	
	
	private static boolean esPar(int num) {
		if (num%2==0)return true;
		else{
			return false;
		}
	}
	
	
	//necesito saber cuantos caracteres
	public static void cadena(int numCar, String i, String b, String cadenaPrevia) {
		if (numCar==0) {
			cadenaPrevia=cadenaPrevia+ "\n";
			System.out.println(cadenaPrevia);
		}else {
			cadena(numCar-1, i, b,cadenaPrevia+ i );
			cadena(numCar-1, i, b, cadenaPrevia+  b) ;
			
		}
	}
	
}
