package clases;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recursividad{
	public static String invierteFrase(String frase) {
		if (frase.length()==1) {
			return ""+ frase.charAt(0) ;
		}else {
			return  invierteFrase(frase.substring(1)) + frase.charAt(0);
		}
		
	}
	
	
	public static String inviertePalabras(String frase) {
		Pattern p= Pattern.compile( "\\p{Punct}");
		frase=frase.replace("\n", "!");
		frase=frase.replace(" ", "!");
		frase=frase.replace("\t", "!");
		Matcher m=p.matcher(frase);
		if (m.find()) {
			int indice=m.start();
			if (indice==frase.length()-1) {
				return frase;
			}else {	
				String palabra=frase.substring(0, indice) + frase.charAt(indice) ;
				
				return (inviertePalabras(frase.substring(indice + 1))+  palabra).replaceAll("\\p{Punct}", " ");
			}
		}else {
			return frase + " ";
		}
	}
	
	
//	public static String invierteFrase(String frase) {
//		return invierteFrase(frase, "");
//	}
	
	public static String longHexa(long l) {
		String binario=Long.toBinaryString(l);
		int num= Integer.parseInt(binario, 2);
		if  (num<=15) {
			String letra="";
			if (num<10) {
				letra=letra +num;
			}else if (num==10) {
				letra="A";
			}else if (num==11) {
				letra="B";
			}else if (num==12) {
				letra="C";
			}else if(num==13) {
				letra="D";
			}else if(num==14) {
				letra="E";
			}else if(num==15) {
				letra="F";
			}
			return letra;
		
		}else {
			String letraBinario=binario.substring(binario.length()-4);
			String letra="";
			int num2= Integer.parseInt(letraBinario, 2);
			if (num2<=15) {
				if (num2<10) {
					letra=letra +num2;
				}else if (num2==10) {
					letra="A";
				}else if (num2==11) {
					letra="B";
				}else if (num2==12) {
					letra="C";
				}else if(num2==13) {
					letra="D";
				}else if(num2==14) {
					letra="E";
				}else if(num2==15) {
					letra="F";
				}
			}
			return  longHexa(Long.parseLong(binario.substring(0,binario.length()-4), 2)) +letra;
		}
	}
	
	public static int buscaPalabra(ArrayList<String> lista, String palabra, int indiceInf, int indiceSup) {
		int indice=(indiceInf+ indiceSup)/2;
		String palabraSel= lista.get(indice);
		if (indiceInf>indiceSup) {
			return -1;
		}
		if (palabra.contentEquals(palabraSel)) {
			boolean siguiente=true;
			if (indice<lista.size()-1 && lista.get(indice+1).contentEquals(palabra)) {
				return buscaPalabra(lista, palabra, indice+1, indiceSup);
			}
			else {
				return indice;
			}
		}
		else {
			if (palabra.compareTo(palabraSel)>0) {
				return buscaPalabra(lista, palabra, indice, lista.size()-1);
			}else
				return buscaPalabra(lista, palabra, 0, indice);
		}
		
	}
	
	public static void main(String[]args) {
		System.out.println(Recursividad.invierteFrase("Hola Javieg"));
		System.out.println(Recursividad.inviertePalabras("HOLA ADIOS\nHastaLuego.lol "));
		System.out.println(Long.toHexString(832729281));
		System.out.println("hexa "+ Recursividad.longHexa( 832729281));
		ArrayList<String>e= new ArrayList<String>();
		e.add("a");
		e.add("c");
		e.add("b");
		e.add("s");
		e.add("x");
		e.add("w");
		e.add("e");
		e.add("b");
		quickSort(e, 0, e.size()-1);
		for (String s: e) {
			System.out.println(s);
		}
		System.out.println(buscaPalabra(e, "b", 0, e.size()-1));
		ArrayList<String>listaOrdenada=ordenaAlReves("Hola me llamo Jose", new ArrayList<String>());
		for (String s: listaOrdenada) {
			System.out.println(s);
		}
		File arch=new File("C:\\Users\\cdcol\\git\\clases\\clases\\src\\clases\\prueba.txt");
		ArrayList<String> lista;
		
		lista = sacaPalabras(arch);
		for (String s:lista) {	
			System.out.println(s);
		}
		
		
	}
	
	public static ArrayList<String> quickSort(ArrayList<String> lista, int inferior, int superior) {
		String mitad= lista.get((inferior + superior) /2 );
		int indice=-1;
		String x;
		int izquierda=inferior;
		int derecha=superior;
		do {
			while (lista.get(izquierda).compareTo(mitad)<0 && izquierda<superior) {
				izquierda++;
			}
			while (mitad.compareTo(lista.get(derecha))<0 && derecha>inferior) {
				derecha--;
			}
		if (izquierda<derecha) {
			x=lista.get(izquierda);
			lista.set(izquierda, lista.get(derecha));
			lista.set(derecha, x);
			izquierda++;
			derecha--;
		}else {
			break;
		}
		
		}while (true);
		if (inferior<derecha) {
			quickSort(lista, inferior, derecha);
			quickSort(lista, derecha +1 , superior);
			
		}
		return lista;
	}
	
	public static ArrayList<String> sacaPalabras(File archivo){
		try {
			return sacaPalabras(archivo, new ArrayList<String>(), new Scanner(archivo));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("archivo no encontrado");
			return null;
		}
	}
	
	
	public static ArrayList<String> sacaPalabras(File archivo, ArrayList<String>listaAlReves, Scanner scan){
		

		if (!scan.hasNextLine()) {
			return listaAlReves;
		}else {
			String linea= scan.nextLine(); //scan next line tine que estar delante de la llamada para que llegue al caso base
			sacaPalabras(archivo, listaAlReves,scan);
			if (linea!=null) {
				ordenaAlReves(linea, listaAlReves);
				
			}
			return listaAlReves;
		}
		
	}
	
	
	public static ArrayList<String> ordenaAlReves(String linea, ArrayList<String> lista){
			String invertido=inviertePalabras(linea);
			StringTokenizer st= new StringTokenizer(invertido);
			while (st.hasMoreElements()){
				lista.add(st.nextToken());
			}
			return lista;
		}
		
		
	
}
