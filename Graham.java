import java.io.*;
import java.util.*;


public class Graham {

	private Punto pivote; //Variable auxiliar que ayuda para obtener el cierre convexo. OPCIONAL.
	protected static enum Turn { CLOCKWISE, COUNTER_CLOCKWISE, COLLINEAR }


	//Metodo que regresa el cierre convexo de una lista de puntos.
	public static Punto puntoMenor(List<Punto> pts) {
		//primero se encuentra el punto de hasta abajo a la derecha
       // Punto lowe
       // for(Punto piv:pts){

        //}
		//return pts;
		Punto lowest = pts.get(0);

        for(int i = 1; i < pts.size(); i++) {

            Punto temp = pts.get(i);

            if(temp.getY() < lowest.getY() || (temp.getY() == lowest.getY() && temp.getX() < lowest.getX())) {
                lowest = temp;
            }
        }

			return lowest;
	}




 public static List<Punto> getConvexHull(List<Punto> points) throws IllegalArgumentException {

        List<Punto> sorted = new ArrayList<Punto>(getSortedPointSet(points));

        if(sorted.size() < 3) {
            throw new IllegalArgumentException("can only create a convex hull of 3 or more unique points");
        }

     //   if(areAllCollinear(sorted)) {
     //       throw new IllegalArgumentException("cannot create a convex hull from collinear points");
     //   }

        Stack<Punto> stack = new Stack<Punto>();
        stack.push(sorted.get(0));
        stack.push(sorted.get(1));

        for (int i = 2; i < sorted.size(); i++) {

            Punto head = sorted.get(i);
            Punto middle = stack.pop();
            Punto tail = stack.peek();

            Turn turn = getTurn(tail, middle, head);

            switch(turn) {
                case COUNTER_CLOCKWISE:
                    stack.push(middle);
                    stack.push(head);
                    break;
                case CLOCKWISE:
                    i--;
                    break;
                case COLLINEAR:
                    stack.push(head);
                    break;
            }
        }

        // close the hull
        stack.push(sorted.get(0));

        return new ArrayList<Punto>(stack);
    }



protected static Set<Punto> getSortedPointSet(List<Punto> points) {

        final Punto lowest = puntoMenor(points);

        TreeSet<Punto> set = new TreeSet<Punto>(new Comparator<Punto>() {
            @Override
            public int compare(Punto a, Punto b) {

                if(a == b || a.equals(b)) {
                    return 0;
                }

                // use longs to guard against int-underflow
                double thetaA = Math.atan2((long)a.getY() - lowest.getY(), (long)a.getX() - lowest.getX());
                double thetaB = Math.atan2((long)b.getY() - lowest.getY(), (long)b.getX() - lowest.getX());

                if(thetaA < thetaB) {
                    return -1;
                }
                else if(thetaA > thetaB) {
                    return 1;
                }
                else {
                    // collinear with the 'lowest' point, let the point closest to it come first

                    // use longs to guard against int-over/underflow
                    double distanceA = Math.sqrt((((long)lowest.getX() - a.getX()) * ((long)lowest.getX() - a.getX())) +
                                                (((long)lowest.getY() - a.getY()) * ((long)lowest.getY() - a.getY())));
                    double distanceB = Math.sqrt((((long)lowest.getX() - b.getX()) * ((long)lowest.getX() - b.getX())) +
                                                (((long)lowest.getY() - b.getY()) * ((long)lowest.getY() - b.getY())));

                    if(distanceA < distanceB) {
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
            }
        });

        set.addAll(points);

        return set;
}


 protected static Turn getTurn(Punto a, Punto b, Punto c) {

        // use longs to guard against int-over/underflow
        long crossProduct = (((long)b.getX() - a.getX()) * ((long)c.getY() - a.getY())) -
                            (((long)b.getY() - a.getY()) * ((long)c.getX() - a.getX()));

        if(crossProduct > 0) {
            return Turn.COUNTER_CLOCKWISE;
        }
        else if(crossProduct < 0) {
            return Turn.CLOCKWISE;
        }
        else {
            return Turn.COLLINEAR;
        }
}









	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        List<Punto> pts = new ArrayList<>();
        //List<Punto> pts;
        for(int i = 0; i < n; i++) {
            String[] linea = sc.nextLine().split(" ");
            int x = Integer.parseInt(linea[0].trim());
            int y = Integer.parseInt(linea[1].trim());
            Punto p = new Punto(x, y);
            pts.add(p);
        }
        pts = getConvexHull(pts);
        System.out.println("Puntos del Cierre Convexo");
        int punto = 1;
        for(Punto p: pts) {
        	System.out.println("Punto " + punto++ + ": (" + p.getX() + "," + p.getY() + ")");
        }
	}
}
