import java.io.*;
import java.util.*;
import java.lang.Math;

public class Punto implements Comparable<Punto> {

	private double x; //Variable que representa la coordenada x.
	private double y; //Variable que representa la coordenada y.

	//Constructor de la clase punto.
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}

	//Metodos para acceder a los atributos de los Puntos.

	public double getX() {
		return this.x;
	}


	public boolean equals(Punto a) {
		return this.y== a.y && this.x == a.x;
	}

	public double getY() {
		return this.y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
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
	public double productoCruz(Punto p, Punto q) {
		return (p.x * q.y) - (p.y * q.x);
	}
	
	
	 public int productoCruz(Punto a, Punto b, Punto c) {
        //se  le restan las coordenadas de origen a los puntos para trasladar a y b al origen
        double prod_cruz = ((a.getX() - c.getX())*(b.getY() - c.getY()))  -  ((a.getY() - c.getY())*(b.getX() - c.getX()));
        if(prod_cruz<0) //contra las manecillas, i.e esta a la derecha
            return -1;
        else if (prod_cruz>0) //a favor de las manecillas, i.e esta a la izquierda
            return 1; 
        return 0;  //colineales
    }

}
