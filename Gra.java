import java.io.*;
import java.util.*;



public class Gra {
    //regresa el punto que está más a la izquierda
    public static Punto getMenor(ArrayList<Punto> pts) {
        Punto menor = pts.get(0);
        for(Punto p: pts){
            if(p.getX() < menor.getX() || (p.getX() == menor.getX() && p.getY() < menor.getY()))
                menor = p;
        }
        return menor;
    }

    public static ArrayList<Punto> ordenaPuntos(ArrayList<Punto> pts, Punto menor) {
        ComparadorPunto comparador = new ComparadorPunto(menor);
        Collections.sort(pts, comparador);
        return pts;
    }

	//Metodo que regresa el cierre convexo de una lista de puntos.
	public static ArrayList<Punto> cierreConvexo(ArrayList<Punto> pts) {
        Punto menor = getMenor(pts);
        pts.remove(menor);
		ArrayList<Punto> ordenados = ordenaPuntos(pts, menor);
		return pts;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        ArrayList<Punto> pts = new ArrayList<>();
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
