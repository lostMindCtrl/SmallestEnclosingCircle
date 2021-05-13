import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.awt.Color;

public class BruteForce
{
  private Point[] points;

  public BruteForce(Point[] points)
  {
      this.points = points;
  }

  public Circle sec()
  {
    Circle smallest = new Circle(new Point(0.0, 0.0), 1);
    //Circle circ = new Circle(points[i], points[j]);
    //smallest = circ;
    for(int i = 0; i < points.length; i++)
    {
      for(int j = 0; j < points.length; j++)
      {
        Circle circ = new Circle(points[i], points[j]);
        if(included(this.points, circ) && smallest.radius > circ.radius)smallest = circ;
        for(int y = 0; y < points.length; y++)
        {

          Circle _circ = new Circle(points[i], points[j], points[y]);
          if(!included(this.points, _circ))continue;
          if(smallest.radius > _circ.radius) smallest = _circ;
        }
      }
    }
    return smallest;
  }

  private boolean included(Point[] points, Circle c)
  {
    for(int i =  0; i < points.length; i++)
    {
      if(!c.contains(points[i]))return false;
    }
    return true;
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
    BruteForce bf = new BruteForce(points);
    Circle smallestEnclosingCircle = bf.sec();
    smallestEnclosingCircle.draw();
    StdOut.println("Circumference: " + smallestEnclosingCircle.circumference());
  }

  static double distance(Point p1, Point p2)
  {
    double d = Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y),2);
    return Math.sqrt(d);
  }
}
