import java.io.*;
import java.util.*;


public class Graham {

	private Punto pivote; //Variable auxiliar que ayuda para obtener el cierre convexo. OPCIONAL.

	//Metodo que regresa el cierre convexo de una lista de puntos.
	public static ArrayList<Punto> cierreConvexo(ArrayList<Punto> pts) {
		//primero se encuentra el punto de hasta abajo a la derecha
        Punto lowe
        for(Punto piv:pts){

        }
		return pts;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        ArrayList<Punto> pts = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            String[] linea = sc.nextLine().split(" ");
            int x = Integer.parseInt(linea[0].trim());
            int y = Integer.parseInt(linea[1].trim());
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
