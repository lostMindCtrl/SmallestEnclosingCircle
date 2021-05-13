import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.QuickX;
import java.awt.Color;
import java.lang.Math;
import java.lang.InterruptedException;
import java.lang.Thread;

public class Standard
{
  private Point[] points;


  public Standard(Point[] points)
  {
    this.points = points;
  }

  public Circle sec()
  {
    Circle      smallestEnclosingCircle  = null;
    Point       zero                     = new Point(0, 0);
    Point       A                        = furthestPoint(zero);
                smallestEnclosingCircle  = new Circle(zero, distance(zero, A));
                smallestEnclosingCircle  = moveCenter(smallestEnclosingCircle, zero, A, 0.0001);
    Point       B                        = furthestPoint(smallestEnclosingCircle.getCenter());
    ArcInterval arc                      = greatestArcInterval(A, B, smallestEnclosingCircle);
    while(arc.length > smallestEnclosingCircle.circumference()/2 && distance(arc.D,arc.E) != smallestEnclosingCircle.radius*2)
    {
      Point     C                        = centerOfPoints(arc.D, arc.E);
      Point     F                        = reduceDiameter(arc.D, arc.E, C, smallestEnclosingCircle, 0.01);
      if(F.equals(arc.D) || F.equals(arc.E))return new Circle(arc.D, arc.E);
                smallestEnclosingCircle  = new Circle(arc.D, arc.E, F);
                arc                      = greatestArcInterval(arc.D, arc.E, F, smallestEnclosingCircle);
    }
    return smallestEnclosingCircle;
  }

  private Point  reduceDiameter(Point D, Point E, Point A, Circle c, double rate)
  {
    Point center = c.getCenter();
    Point tmp    = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    Point fPoint = furthestPoint(center);
    while(c.contains(fPoint) || (fPoint == D || fPoint == E))
    {
      double  x_center         = center.x + (A.x - center.x)*rate;
      double  y_center         = center.y + (A.y - center.y)*rate;
              center           = new Point(x_center, y_center);
              c                = new Circle(center, distance(center, D));
      if(center.equals(tmp))return fPoint;
              tmp              = center;
              fPoint           = furthestPoint(center);

    }
    return fPoint;
  }

  private Circle moveCenter(Circle c, Point center, Point A, double rate)
  {
        Point fPoint = furthestPoint(center);
        Point U      = fPoint;
        while(c.contains(fPoint))
        {
          U = fPoint;
          double  x_center = center.x + (A.x - center.x)*rate;
          double  y_center = center.y + (A.y - center.y)*rate;
          center           = new Point(x_center, y_center);
          c                = new Circle(center, distance(center, A));
          fPoint           = furthestPoint(center);
        }

        return new Circle(center, distance(center, U));
  }

  private Point centerOfPoints(Point D, Point E)
  {
    double x = (D.x + E.x)/2;
    double y = (D.y + E.y)/2;
    return new Point(x, y);
  }

  private ArcInterval greatestArcInterval(Point A, Point B, Circle c)
  {
    Comparable[] interval = new Comparable[2];
    interval[0]           = new ArcInterval(A, B, computeArcLength(c.getCenter(), A, B, c));
    interval[1]           = new ArcInterval(B, A, (c.circumference() - ((ArcInterval)interval[0]).length));
    QuickX.sort(interval);
    return (ArcInterval)interval[1];
  }

  private ArcInterval greatestArcInterval(Point A, Point B, Point C, Circle c)
  {
    ArcInterval[] arcI        = interval(A, B, C, c);
    int           j           = 0;
    for(int i = 1; i < 3; i++)
      if(arcI[i].length > arcI[j].length)j = i;
    return arcI[j];
  }

  private double computeArcLength(Point center, Point a, Point b, Circle c)
  {
    double center_a_x = a.x - center.x;
    double center_a_y = a.y - center.y;
    double center_b_x = b.x - center.x;
    double center_b_y = b.y - center.y;

    double s          = computeAngle(a,b, center)*(Math.PI/180);
    return s*c.radius;
  }

  private static double computeAngle(Point a, Point b, Point c)
  {
    double abx   = b.x-a.x;
    double aby   = b.y-a.y;
    double acx   = c.x-a.x;
    double acy   = c.y-a.y;
    double bcx   = b.x-c.x;
    double bcy   = b.y-c.y;
    double gamma = Math.acos((acx*bcx + acy*bcy)/(Math.sqrt(Math.pow(acx, 2) + Math.pow(acy, 2))* Math.sqrt(Math.pow(bcx, 2) + Math.pow(bcy, 2))));
    return gamma;
  }

  private Point furthestPoint(Point center)
  {
    Point smallest            = points[0];
    double currentLargestDist = distance(center, smallest);
    for(int i = 1; i < points.length; i++)
    {
      double d                = distance(center, points[i]);
      if(d > currentLargestDist)
      {
        currentLargestDist    = d;
        smallest              = points[i];
      }
    }
    return smallest;
  }

  public double distance(Point p1, Point p2)
  {
    double d = Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y),2);
    return Math.sqrt(d);
  }

  private double computeArcAngle(Point a, Point b, double r)
  {
    double length = Math.sqrt(Math.pow(b.x-a.x, 2) + Math.pow(b.y-a.y, 2));
    return Math.toDegrees(2*Math.asin(length/(2*r)));
  }

  private ArcInterval[] interval(Point A, Point B, Point C, Circle c)
  {
    ArcInterval[] arcI = new ArcInterval[3];
    int i = 0;
    if(PointAngle(c.getCenter(), A, B, C) < 0)arcI[i++]     = new ArcInterval(A, B, (computeArcAngle(A, B, c.radius)/360) * c.circumference());
    else arcI[i++]     = new ArcInterval(A, B, (360 - computeArcAngle(A, B, c.radius))/360 * c.circumference());
    if(PointAngle(c.getCenter(), B, C, A) < 0)arcI[i++]     = new ArcInterval(B, C, computeArcAngle(B, C, c.radius)/360 * c.circumference());
    else arcI[i++]     = new ArcInterval(B, C, (360 - computeArcAngle(B, C, c.radius))/360 * c.circumference());
    if(PointAngle(c.getCenter(), C, A, B) < 0)arcI[i++]     = new ArcInterval(C, A, computeArcAngle(C, A, c.radius)/360 * c.circumference());
    else arcI[i++]     = new ArcInterval(C, A, (360 - computeArcAngle(A, B, c.radius))/360 * c.circumference());

    return arcI;
  }

  private static int PointAngle(Point Center, Point A, Point B, Point C)
  {
    double dxa =  A.x - Center.x;
    double dya =  A.y - Center.y;
    double dxb =  B.x - Center.x;
    double dyb =  B.y - Center.y;
    double dxc =  C.x - Center.x;
    double dyc =  C.y - Center.y;

    double angleA = Math.toDegrees(Math.atan2(dya, dxa));
    double angleB = Math.toDegrees(Math.atan2(dyb, dxb));
    double angleC = Math.toDegrees(Math.atan2(dyc, dxc));
    if(angleA < 0) angleA = 360 + angleA;
    if(angleB < 0) angleB = 360 + angleB;
    if(angleC < 0) angleC = 360 + angleC;

    if(angleA < angleB)
    {
      if(angleA < angleC && angleC < angleB)
      {
        if(angleB - angleA < 180)return 1;
        else return -1;
      }
      else
      {
        if(angleB - angleA < 180)return -1;
        else return 1;
      }
    }
    else if(angleA > angleB)
    {
      if(angleC < angleA && angleC > angleB)
      {
        if(angleA - angleB < 180)return 1;
        else return -1;
      }
      else
      {
        if(angleA - angleB < 180)return -1;
        else return 1;
      }
    }
    else return 1;
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
      points[i]   = new Point(input.readDouble(), input.readDouble());
      points[i].draw();
    }
    Standard test = new Standard(points);
    Circle c      = test.sec();
    c.draw();
  }

  private class ArcInterval implements Comparable<ArcInterval>
  {
    public Point D;
    public Point E;
    public double length;

    public ArcInterval(Point D, Point E, double length)
    {
      this.D      = D;
      this.E      = E;
      this.length = length;
    }

    public int compareTo(ArcInterval that)
    {
      if(this.length > that.length)return 1;
      else if(this.length < that.length)return -1;
      else return 0;
    }
  }
}
