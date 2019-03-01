import java.util.Comparator;
public class ComparadorPunto implements Comparator<Punto>{
    private static Punto origen;

    public ComparadorPunto(Punto origen) {
        this.origen = origen;
    }
    
    
	//Metodo para ordenar de acuerdo al angulo en contra de las manecillas del reloj
    public int compare(Punto a, Punto b) {

                if(a == b || a.equals(b)) {
                    return 0;
                }

                // vamos al contrario de las manecillas del reloj
                double tanA = Math.atan2(a.getY() - origen.getY(), a.getX() - origen.getX());
                double tanB = Math.atan2(b.getY() - origen.getY(), b.getX() - origen.getX());

                if(tanA < tanB) {
                    return -1;
                }
                else if(tanA > tanB) {
                    return 1;
                }
                else {
					//se elige el colineal con la menor x, la menor distancia
                 
                    double distA = origen.distancia(origen,a);
                    double distB = origen.distancia(origen,b);

                    if(distA < distB) {
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
            }
    
  
}
