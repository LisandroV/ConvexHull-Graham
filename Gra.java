import java.io.*;
import java.util.*;



public class Gra {
    //regresa el punto más abajo y a la izquierda
    public static Punto getMenor(LinkedList<Punto> pts) {
        Punto menor = pts.get(0);
        for(Punto p: pts){
            if(p.getY() < menor.getY() || (p.getY() == menor.getY() && p.getX() < menor.getX()))
                menor = p;
        }
        return menor;
    }

	//metodo que ordena una lista de puntos de acuerdo al comparator, ie de acuerdo al angulo
    public static LinkedList<Punto> ordenaPuntos(LinkedList<Punto> pts, Punto menor) {
        ComparadorPunto comparador = new ComparadorPunto(menor);
        Collections.sort(pts, comparador);
        return pts;
    }

	//Metodo que regresa el cierre convexo de una lista de puntos.
	public static LinkedList<Punto> cierreConvexo(LinkedList<Punto> pts) {
        Punto menor = getMenor(pts);
		LinkedList<Punto> ordenados = ordenaPuntos(pts, menor);
		LinkedList<Punto> pila = new LinkedList<Punto>();
		
		
		//metemos los 2 primeros a la pila
		pila.push(ordenados.get(0));
		pila.push(ordenados.get(1));
	
	
		for (int i =2; i<ordenados.size(); i++){
			
			Punto actual = ordenados.get(i); //punto con el que nos estamos moviendo
			Punto anterior = pila.pop();  //punto padre (anterior)de actual 
			Punto origen = pila.peek();//punto abuelo de actual || (anterior de anterior) 
			
			int giro =origen.productoCruz(anterior,actual,origen); 
			
			switch(giro){
				case 1: //izquierdo 
						pila.push(anterior);
						pila.push(actual);
						break;
				case -1: //giro derecho
						i--;
						break;
				case 0:  //colineales
						pila.push(actual);
						break;
				}
			}
		pila.push(ordenados.get(0)); //cerramos el CH
		return pila;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        LinkedList<Punto> pts = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            String[] linea = sc.nextLine().split(" ");
            double x = Double.parseDouble(linea[0].trim());
            double y = Double.parseDouble(linea[1].trim());
            Punto p = new Punto(x, y);
            pts.add(p);
        }
        pts = cierreConvexo(pts);
        System.out.println("Puntos del Cierre Convexo");
        int punto = 1;
        for(Punto p: pts) {
        	System.out.println("Punto " + punto++ + ": (" + p.getX() + "," + p.getY() + ")");
        }
	}
}
