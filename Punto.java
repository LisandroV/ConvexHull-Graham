import java.io.*;
import java.util.*;
import java.lang.Math;

public class Punto implements Comparable<Punto> {

	private int x; //Variable que representa la coordenada x.
	private int y; //Variable que representa la coordenada y.

	//Constructor de la clase punto.
	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//Metodos para acceder a los atributos de los Puntos.

	public int getX() {
		return this.x;
	}


	public boolean equals(Punto a) {
		return this.y== a.y && this.x == a.x;
	}
	
	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	//Metodo que utiliza el metodo sort para poder comparar objetos de la clase Punto.
	public int compareTo(Punto p) {
		//Metodo a implementar
		return 0;
	}

	//Metodo que regresa la distancia entre 2 puntos.
	public double distancia(Punto p, Punto q) {
		return Math.sqrt(Math.pow(q.x - p.x,2) + Math.pow(q.y - p.y,2));
	}

	//Metodo que regresa el producto cruz entre 2 punto.
	public int productoCruz(Punto p, Punto q) {
		return (p.x * q.y) - (p.y * q.x);
	}

}
