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
        //pts.remove(menor);
		LinkedList<Punto> ordenados = ordenaPuntos(pts, menor);
		
		LinkedList<Punto> pila = new LinkedList<Punto>();
		
		for(int j = 0; j<ordenados.size(); j++){
			System.out.print(ordenados.get(j).getX()+","+ordenados.get(j).getY());
		}
		
		pila.push(ordenados.get(0));
		pila.push(ordenados.get(1));
		ordenados.remove(0);
		ordenados.remove(1);
		
		
		for (int i =0; i<ordenados.size(); i++){
			Punto actual = ordenados.get(i);
			Punto anterior = pila.pop();
			Punto origen = pila.peek();
			//Punto actual = ordenados.get(i);
			comparador = new ComparadorPunto(origen);
			int giro =comparador.compare(anterior,actual);
			System.out.println("giro: "+giro);
			System.out.println("origen: "+origen.getX()+","+origen.getY() );
						System.out.println("anterior: "+anterior.getX()+","+anterior.getY());
						System.out.println("actual: "+actual.getX()+","+actual.getY());
			switch(giro){
				case -1: //giro izquierdo
						pila.push(anterior);
						pila.push(actual);
						break;
				case 1: //giro derecho
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
