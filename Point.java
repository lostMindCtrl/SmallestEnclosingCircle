import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;
import java.lang.Math;
import java.awt.Color;

public class Point implements Comparable<Point>
{
  public final double radius;
  public final double x;
  public final double y;

  private static final Comparator<Point> BY_YCOORDINATE = new byYCoordinate();
  private static final Comparator<Point> BY_XCOORDINATE = new byXCoordinate();

  private final Comparator<Point> BY_POLARCOORDINATE    = new byPolarCoordinate();

  public Point(double x, double y)
  {
    this.x = x;
    this.y = y;
    this.radius = 0.01;
  }

  public void draw()
  {
    StdOut.println("--> printing Point coordinates: [" + this.x + " , " + this.y + "]");
    StdDraw.setPenColor(Color.BLACK);
    StdDraw.filledCircle(this.x, this.y, this.radius);
  }
  public void draw(Color c)
  {
    StdOut.println("--> printing Point coordinates: [" + this.x + " , " + this.y + "]");
    StdDraw.setPenColor(c);
    StdDraw.filledCircle(this.x, this.y, this.radius);
  }

  public boolean equals(Object o)
  {
    if(this == o)return true;
    else if(o == null)return false;
    else if(this.getClass() != o.getClass())return false;
    Point point = (Point) o;
    return this.x == point.x && this.y == point.y;
  }

  public String toString()
  {
    return "(" + this.x + "," + this.y + ")";
  }

  public static Comparator<Point> yCoordinateOrder()
  {
    return BY_YCOORDINATE;
  }

  public static Comparator<Point> xCoordinateOrder()
  {
    return BY_XCOORDINATE;
  }

  public Comparator<Point> polarCoordinateOrder()
  {
  return BY_POLARCOORDINATE;
  }

  public int compareTo(Point that)
  {
    double len_this = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    double len_that = Math.sqrt(Math.pow(that.x, 2) + Math.pow(that.y, 2));
    if(len_this > len_that) return 1;
    else if(len_this < len_that) return -1;
    else return 0;
  }

  private static class byYCoordinate implements Comparator<Point>
  {
    public int compare(Point w, Point v)
    {
      if(w.y > v.y)return 1;
      else if(w.y < v.y)return -1;
      else return w.compareTo(v);
    }
  }

  private static class byXCoordinate implements Comparator<Point>
  {
    public int compare(Point w, Point v)
    {
      if(w.x > v.x)return 1;
      else if(w.x < v.x)return -1;
      else return w.compareTo(v);
    }
  }

  private class byPolarCoordinate implements Comparator<Point>
  {
    public int compare(Point A, Point B)
    {
        double dx1 = A.x - x;
        double dy1 = A.y - y;
        double dx2 = B.x - x;
        double dy2 = B.y - y;

        if      (dy1 >= 0 && dy2 < 0) return -1;
        else if (dy2 >= 0 && dy1 < 0) return  1;
        else if (dy1 == 0 && dy2 == 0)
        {
            if      (dx1 >= 0 && dx2 < 0) return -1;
            else if (dx2 >= 0 && dx1 < 0) return  1;
            else                          return  0;
        }
        else return -ccw(Point.this, A, B);
    }

    private  int ccw(Point a, Point b, Point c)
    {
        double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
        if      (area2 < 0) return -1;
        else if (area2 > 0) return  1;
        else                return  0;
    }

  }

}
