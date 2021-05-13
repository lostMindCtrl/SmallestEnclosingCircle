import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.awt.Color;

public class Chrystal
{
  private Point[] points;

  public Chrystal(Point[] points)
  {
    this.points = points;
  }

  public Circle sec()
  {
    if(this.points.length > 10)
    {
      Circle smallestEnclosingCircle = chrystal();
      if(!containsAll(points, smallestEnclosingCircle))return this.sec();
      else return smallestEnclosingCircle;
    }
    else
    {
    Standard s = new Standard(this.points);
    return s.sec();
    }
  }

  private Circle chrystal()
  {
    Point[] convex_hull             = convexHull(this.points);
    int     size_convexHull         = convex_hull.length;
    Circle  smallestEnclosingCircle = new Circle(new Point(0.0, 0.0), 0.0);
    int     ind                     = 0;
    int     one_ind                 = StdRandom.uniform(size_convexHull);
    int     two_ind                 = next(one_ind, size_convexHull);
    Point   one                     = convex_hull[one_ind];
    Point   two                     = convex_hull[two_ind];
    while(size_convexHull > 0)
    {
      int    v_ind         = next(one_ind, two_ind, size_convexHull);
      Point  v             = convex_hull[v_ind];
      double a             = computeAngle(one, two, v);
      for(int i = 0; i < size_convexHull; i++)
      {
        if(i == one_ind || i == two_ind || i == v_ind);
        double tmp = computeAngle(one, two, convex_hull[i]);
        if(a > tmp)
        {
          a     = tmp;
          v_ind = i;
        }
      }
      v = convex_hull[v_ind];
      double alpha = computeAngle(one, v, two);
      double beta  = computeAngle(v, two, one);
      if(a >= 90)return new Circle(one, two);
      else if((a <= 90) && (alpha <= 90) && (beta <= 90))return new Circle(one, two, v);
      else
      {
        if    (alpha < 180  && alpha > 90 )
        {
          swap(two_ind, size_convexHull, convex_hull);
          if(v_ind != size_convexHull-1)two_ind = v_ind;
          size_convexHull--;
          one_ind = next(two_ind, size_convexHull);
          one     = convex_hull[one_ind];
          two     = convex_hull[two_ind];
        }
        else
        {
          swap(one_ind, size_convexHull, convex_hull);
          if(v_ind != size_convexHull-1)one_ind = v_ind;
          size_convexHull--;
          two_ind = next(two_ind, size_convexHull);
          one     = convex_hull[one_ind];
          two     = convex_hull[two_ind];
        }
      }
      ind++;
    }

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

  private static void swap(int index, int current_length, Point[] c)
  {
    c[index] = c[current_length-1];
  }

  private int getIndex(int current, int size)
  {
    StdOut.println("Size of the current convex hill: " + size);
    StdOut.println("current: " + current);
    if(current >= size/2)return current - size/2;
    else return current + size/2;
  }

  private int getStart(Point a, Point b, Point[] con)
  {
    int i = StdRandom.uniform(con.length);
    while(con[i].equals(a) || con[i].equals(b))
    {
    if(i+1 < con.length)i++;
    else i = 0;
    }
    return i;
  }

  private int next(int p, int length)
  {
    int i = StdRandom.uniform(length);
    while(i == p) i = StdRandom.uniform(length);
    return i;
  }

  private int next(int A, int B, int length)
  {
    int i = StdRandom.uniform(length);
    while(i == A || i == B)i = StdRandom.uniform(length);
    return i;
  }

  private Point next(Point p, Point[] a)
  {
    int i = StdRandom.uniform(a.length);
    while(a[i] == p)
    {
      if(i+1 < a.length)i++;
      else i = 0;
    }
    return a[i];
  }

  private Point next(Point A, Point B, Point[] a)
  {
    int j = StdRandom.uniform(a.length);
    if(!a[j].equals(A) && !a[j].equals(B))return a[j];
    for(int i = 0; i < a.length; i++)
    {
      if(!a[i].equals(A) && !a[i].equals(B))return a[i];
    }
    return null;
  }

  private Point furthestPoint(Point p, Point[] points)
  {
    int furthest    = 0;
    double dist     = distance(p, points[0]);
    for(int i = 0; i < points.length; i++)
    {
      if(dist > distance(p, points[i]))
      {
        dist     = distance(p, points[i]);
        furthest = i;
      }
    }

    return points[furthest];
  }

  private double distance(Point a, Point b)
  {
    return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
  }

  private void createTriangle(Point a, Point b, Point c)
  {
    StdDraw.line(a.x, a.y, b.x, b.y);
    StdDraw.line(b.x, b.y, c.x, c.y);
    StdDraw.line(a.x, a.y, c.x, c.y);
  }

  private double computeAngle(Point a, Point b, Point c)
  {
    double abx   = b.x-a.x;
    double aby   = b.y-a.y;
    double acx   = c.x-a.x;
    double acy   = c.y-a.y;
    double bcx   = b.x-c.x;
    double bcy   = b.y-c.y;
    double alpha = Math.acos((abx*acx + aby*acy)/(Math.sqrt(Math.pow(abx, 2) + Math.pow(aby, 2))* Math.sqrt(Math.pow(acx, 2) + Math.pow(acy, 2))));
    double beta  = Math.acos((abx*bcx + aby*bcy)/(Math.sqrt(Math.pow(abx, 2) + Math.pow(aby, 2))* Math.sqrt(Math.pow(bcx, 2) + Math.pow(bcy, 2))));
    double gamma = 180-Math.toDegrees(alpha+beta);
    return gamma;
  }

  private Point[] convexHull(Point[] points)
  {
    Stack<Point> convex_hull = GrahamScan.convexHull(points);
    Point[] ch               = new Point[convex_hull.size()];
    Iterator iter_ch         = convex_hull.iterator();
    for(int i = 0; iter_ch.hasNext(); i++)ch[i] = (Point)iter_ch.next();
    return ch;
  }

  public static void main(String[] args)
  {
      In input       = new In(args[0]);
      int size       = input.readInt();
      Point[] points = new Point[size];
      StdDraw.setXscale(-1.2, 1.2);
      StdDraw.setYscale(-1.2, 1.2);
      for(int i = 0; i < size; i++)
      {
        points[i] = new Point(input.readDouble(), input.readDouble());
        points[i].draw();
      }
      Chrystal test = new Chrystal(points);
      Circle smallestEnclosingCircle = test.sec();
      smallestEnclosingCircle.draw();
  }

  private static class GrahamScan
  {
    public static Stack<Point> convexHull(Point[] points)
    {
      Stack<Point> convex_hull = new Stack<Point>();
      int n                    = points.length;
      points                   = lowestYCoordinate(points);

      Arrays.sort(points, 1, n, points[0].polarCoordinateOrder());
      convex_hull.push(points[0]);

      int j, c;
      for (j = 1; j < n; j++)
          if (!points[0].equals(points[j])) break;
      if (j == n) return null;

      for (c = j+1; c < n; c++)
          if (ccw(points[0], points[j], points[c]) != 0) break;
      convex_hull.push(points[c-1]);

      for (int i = c; i < n; i++)
      {
          Point tmp = convex_hull.pop();
          while (ccw(convex_hull.peek(), tmp, points[i]) <= 0)tmp = convex_hull.pop();
          convex_hull.push(tmp);
          convex_hull.push(points[i]);
      }

      return convex_hull;
    }

    private static Point[] lowestYCoordinate(Point[] points)
    {
      int smallest = 0;
      for(int i = 1; i < points.length; i++)
        if(points[smallest].y > points[i].y)smallest = i;

      Point tmp         = points[0];
      points[0]         = points[smallest];
      points[smallest]  =  tmp;
      return points;
    }

    private static int ccw(Point a, Point b, Point c)
    {
      double ccw = ((c.y - a.y)*(b.x - a.x)) - ((b.y - a.y)*(c.x - a.x));
      if(ccw > 0) return 1;
      else if(ccw < 0)return -1;
      else return 0;
    }

  }
}
