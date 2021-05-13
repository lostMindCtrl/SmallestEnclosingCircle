import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;

public class Welzl
{
  private Point[] points;

  public Welzl(Point[] points)
  {
      this.points = points;
  }

  public Circle sec()
  {
    StdRandom.shuffle(points);
    Circle smallestEnclosingCircle = sec(points, new Point[3], 0, 0);
    if(!containsAll(points, smallestEnclosingCircle))smallestEnclosingCircle = this.sec();
    return smallestEnclosingCircle;
  }

  private boolean containsAll(Point[] points, Circle c)
  {
    for(int i = 0; i < points.length; i++)
    {
      if(!c.contains(points[i]))return false;
    }
    return true;
  }

  private Circle sec(Point[] points, Point[] boundary, int index, int boundarySize)
  {
    Circle c;
    if(!(index < points.length-1) || boundarySize > 2)
    {
      if(boundarySize == 1)return new Circle(boundary[0], 0);
      else if (boundarySize == 2)return new Circle(boundary[0], boundary[1]);
      else if (boundarySize == 3)return new Circle(boundary[0], boundary[1], boundary[2]);
      else return new Circle(new Point(0, 0), 0);

    }
    else
    {
      Point p = points[index];
      c = sec(points, boundary, index+1, boundarySize);
      if(!c.contains(p))
      {
        boundary[boundarySize] = p;
        return sec(points, boundary, index+1, boundarySize+1);
      }
    }
    return c;
  }

  public static void main(String[] args)
  {
    In input  = new In(args[0]);
    int size  = input.readInt();
    Point[] points = new Point[size];
    StdDraw.setXscale(-1.2, 1.2);
    StdDraw.setYscale(-1.2, 1.2);
    for(int i = 0; i < size; i++)
    {
      points[i] = new Point(input.readDouble(), input.readDouble());
      points[i].draw();
    }
    Welzl w = new Welzl(points);
    w.sec().draw();
  }
}
