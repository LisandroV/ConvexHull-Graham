import java.util.Comparator;
public class ComparadorPunto implements Comparator<Punto>{
    private static Punto origen;

    public ComparadorPunto(Punto origen) {
        this.origen = origen;
    }

    public int compare(Punto a, Punto b) {
        //se  le restan las coordenadas de origen a los puntos para trasladar a y b al origen
        double prod_cruz = ((a.getX() - origen.getX())*(b.getY() - origen.getY()))  -  ((a.getY() - origen.getY())*(b.getX() - origen.getX()));
        if(prod_cruz<0) //contra las manecillas der
            return -1;//significa que el punto a está antes que el punto b
        else if (prod_cruz>0) //a favor izq
            return 1;//a está después que b
        return 0;
    }
}
