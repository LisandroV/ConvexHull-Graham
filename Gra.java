import java.io.*;
import java.util.*;



public class Gra {
    //regresa el punto que está más a la izquierda
    public static Punto getMenor(LinkedList<Punto> pts) {
        Punto menor = pts.get(0);
        for(Punto p: pts){
            if(p.getY() < menor.getY() || (p.getY() == menor.getY() && p.getX() < menor.getX()))
                menor = p;
        }
        return menor;
    }

    public static LinkedList<Punto> ordenaPuntos(LinkedList<Punto> pts, Punto menor) {
        ComparadorPunto comparador = new ComparadorPunto(menor);
        Collections.sort(pts, comparador);
        return pts;
    }

	//Metodo que regresa el cierre convexo de una lista de puntos.
	public static LinkedList<Punto> cierreConvexo(LinkedList<Punto> pts) {
        Punto menor = getMenor(pts);
        System.out.println("menor: "+menor.getX()+","+menor.getY());
        ComparadorPunto comparador ;
		LinkedList<Punto> ordenados = ordenaPuntos(pts, menor);
		//LinkedList<Punto> ordenados = new LinkedList<Punto>();
		/**Punto p1 = new Punto(7,8);
		Punto p2 = new Punto(4,2);
		Punto p3 = new Punto(10,2);
		Punto p4 = new Punto(3,5);
		Punto p5 = new Punto(6,4);
		ordenados.add(p2);
		ordenados.add(p3);
		ordenados.add(p5);
		ordenados.add(p1);
		ordenados.add(p4);*/
		LinkedList<Punto> pila = new LinkedList<Punto>();
		
		for(int j = 0; j<ordenados.size(); j++){
			System.out.print(ordenados.get(j).getX()+","+ordenados.get(j).getY());
		}
		
		pila.push(ordenados.get(0));
		pila.push(ordenados.get(1));
	
	
		for (int i =2; i<ordenados.size(); i++){
			Punto actual = ordenados.get(i);
			Punto anterior = pila.pop();
			Punto origen = pila.peek();
			comparador = new ComparadorPunto(origen);
			int giro =comparador.compare(anterior,actual);
			
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
		pila.push(ordenados.get(0));
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
