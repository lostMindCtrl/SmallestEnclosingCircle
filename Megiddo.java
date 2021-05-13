// not finished yet

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.MergeX;
import java.util.LinkedList;
import java.lang.Math;
import java.util.List;
import java.awt.Color;
import java.util.Comparator;

public class Megiddo
{
  private Point[] points;
  private final int n;
  //private Line[] pairs;


  public Megiddo(Point[] points)
  {
    this.points = points;
    this.n      = points.length;
  }

  public Circle sec()
  {
    Point[] usePoints = this.points;
    //while(usePoints.length >= 16)
    //{
      usePoints = prune(usePoints);
    //}
    //Circle smallestEnclosingCircle = solveBrute(usePoints);
    //return smallestEnclosingCircle;
    return null;
  }

  private Point[] prune(Point[] points)
  {
    Line p = new Line(new Point(0.1, 0.0), new Point(0.3, 0.2));
    Point willProj =  new Point(0.3, 0.1);
    Point projection  = projectOnLine(willProj, p, null);
    Point secondProjection = projectOnLine(willProj, p);
    p.draw(Color.RED);
    willProj.draw(Color.RED);
    projection.draw(Color.GREEN);
    secondProjection.draw(Color.magenta);
    /*int n               = points.length;
    Line[] bisectors    = pairPointsAndGiveBisector(points);
    double median_slope = medianSlope(bisectors);
    Line median_line    = new Line(median_slope, 0);
    median_line.draw(Color.GREEN);
    int j = n/2;
    LinkedList<Line[]> parallel_pairs          = new LinkedList<Line[]>();
    LinkedList<Line[]> intersecting_pairs       = new LinkedList<Line[]>();
    double[]           y_ij                     = new double[n/2];
    Point[]            inter_computation_point  = new  Point[n/2];
    int index_pl = 0;
    int index_pi = 0;
    for(int i = 0; i < n/4; i++)
    {
      Line bi  = bisectors[i];
      Line bj  = bisectors[j];
      if(bi.angle_x_axis == bj.angle_x_axis)
      {
        Line[] pairs   = new Line[2];
        pairs[0]       = bi;
        pairs[1]       = bj;
        y_ij[index_pl] = (distance(median_line.mid, bi.mid)+distance(median_line.mid, bj.mid))/2;
        parallel_pairs.add(pairs);
        index_pl++;
      }
      else
      {
        Line[] pairs                      = new Line[2];
        pairs[0]                          = bi;
        pairs[1]                          = bj;
        Point inter                       = computeIntersection(bi, bj);
        Point inter_proj                  = projectOnLine(inter, median_line);
        inter_computation_point[index_pi] = new Point(distance(inter_proj, new Point(0.0, 0.0)), distance(inter, inter_proj));
        intersecting_pairs.add(pairs);
        index_pi++;
      }
    }
    //Point[] I     = computeLineInterSections(bisectors, median_slope);
    Point y_mid   = medianPointY(I, Point.yCoordinateOrder());
    Line line_mid = new Line(median_slope, y_mid.y*(Math.sqrt(Math.pow(median_slope, 2) + 1)));
    line_mid.draw(Color.RED);
    Line test_line = new Line(new Point(0.001,-0.99), new Point(0.0, 0.99));
    test_line.draw(Color.PINK);
    Point potential_mid = mec_center(points, test_line);
    potential_mid.draw(Color.BLUE);*/
    return points;
  }

  private Point mec_center(Point[] S, Line l)
  {
    int n = points.length;
    Point    potentialCenter_m = null;
    //while(n >= 3)
    //{
      Line[]   bisector          = computePerpendicularBisectors(n);
      Point[]  intersections_q   = computeBisectorIntersections(bisector, l);
      Point    m                 = medianPointY(intersections_q, Point.yCoordinateOrder());
      StdOut.println("The Median Point m has the Coordinates: " + m.toString());
      if(onOneSide(intersections_q, m))return m;
      else;

    //}
    return potentialCenter_m;
  }

  private boolean onOneSide(Point[] inter, Point m)
  {
    int side = 0;
    for(int i = 0; i < inter.length; i++)
    {
      if(inter[i].y < m.y && side == 1)return true;
      else if(inter[i].y > m.y && side == -1)return true;
      else if(inter[i].y < m.y)side = -1;
      else if(inter[i].y > m.y)side =  1;
    }
    StdOut.println("The solution to the one side check is: false");
    return false;
  }

  private Line[] pairPointsAndGiveBisector(Point[] points)
  {
    Point[] lo_points_clone = points.clone();
    Point[] hi_points_clone = points.clone();
    StdRandom.shuffle(lo_points_clone, 0, n/2 + (n%2));
    StdRandom.shuffle(hi_points_clone, n/2 + (n%2), points.length);
    Line[] bisector = new Line[n/2];
    int i = 0;
    for(int j = n/2 + (n%2); j < n; j++)
    {
      Point a = lo_points_clone[i];
      Point b = hi_points_clone[j];
      double slope = computeSlope(a, b);
      bisector[i] = new Line(new Point((a.x+b.x)/2, (a.y+b.y)/2), (-1)*Math.pow(slope, -1));
      i++;
    }
    return bisector;
  }

  private Point[] computeLineInterSections(Line[] pairs, double m_mid)
  {

    //n =  pairs.length;
    int i = 0;
    Point[] I = new Point[n/4];
    for(int j = n/4; j < n; j++)
    {
      if(!(pairs[i].slope < m_mid && pairs[j].slope >= m_mid))break;
      I[i] = computeIntersection(pairs[i].a, pairs[i].b, pairs[j].a, pairs[j].b);
      i++;
    }
    return I;
  }

  private Point computeIntersection(Line inputLine, Line line)
  {
    if((inputLine.slope - line.slope) == 0.0) return new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    double inter_x = (inputLine.c - line.c) / (line.slope - inputLine.slope);
    double inter_y = line.slope * inter_x + line.c;
    return new Point(inter_x, inter_y);
  }

  private static Point computeIntersection(Point a, Point b, Point c, Point d)
  {
    double inter_x = ((a.x*b.y - a.y*b.x)*(c.x - d.x) - (a.x - b.x)*(c.x*d.y - c.y*d.x))/((a.x - b.x)*(c.y - d.y) - (a.y - b.y)*(c.x - d.x));
    double inter_y = ((a.x*b.y - a.y*b.x)*(c.y - d.y) - (a.y - b.y)*(c.x*d.y - c.y*d.x))/((a.x - b.x)*(c.y - d.y) - (a.y - b.y)*(c.x - d.x));
    return new Point(inter_x, inter_y);
  }

  private Line[] computePerpendicularBisectors(int n)
  {
    Point[] lo_points_clone = points.clone();
    Point[] hi_points_clone = points.clone();
    StdRandom.shuffle(lo_points_clone, 0, n/2 + (n%2));
    StdRandom.shuffle(hi_points_clone, n/2 + (n%2), points.length);
    Line[] bisector = new Line[n/2];
    int i = 0;
    for(int j = n/2 + (n%2); j < n; j++)
    {
      Point a = lo_points_clone[i];
      Point b = hi_points_clone[j];
      double slope = computeSlope(a, b);
      bisector[i] = new Line(new Point((a.x+b.x)/2, (a.y+b.y)/2), (-1)*Math.pow(slope, -1));
      i++;
    }
    return bisector;
  }

  private Point[] computeBisectorIntersections(Line[] bisector, Line l)
  {
    Point[] intersections_q = new Point[bisector.length];
    StdOut.println("Number of bisectors: " + bisector.length);
    for(int i = 0; i < bisector.length; i++)
    {
      intersections_q[i] = computeIntersection(bisector[i].a, bisector[i].b, l.a, l.b);
    }
    return intersections_q;
  }

  private Point medianPointY(Comparable[] m, Comparator c)
  {
    for(int i = 0; i < m.length; i++)StdOut.println("Current - Intersection[" + i + "]: " + ((Point)m[i]).toString());
    MergeX.sort(m, c);
    int n = m.length;
    if(n % 2 == 0)return new Point(((((Point)m[((n-1)/2)]).x)+(((Point)m[n/2]).x))/2, ((((Point)m[((n-1)/2)]).y)+(((Point)m[n/2]).y))/2);
    else return (((Point)m[n/2]));
  }

  private double medianSlope(Comparable[] paired_points)
  {
    MergeX.sort(paired_points);
    int n = paired_points.length;
    if(n % 2 == 0)return (((Line)paired_points[((n-1)/2)]).slope+((Line)paired_points[n/2]).slope)/2;
    else return ((Line)paired_points[n/2]).slope;
  }

  private double medianAngleX(Comparable[] bisectors)
  {
    MergeX.sort(bisectors, ((Line)bisectors[0]).angleXOrder());
    int n = bisectors.length;
    if(n % 2 == 0)return (((Line)bisectors[((n-1)/2)]).slope+((Line)bisectors[n/2]).slope)/2;
    else return ((Line)bisectors[n/2]).slope;
  }

  private double computeSlope(Point p1, Point p2)
  {
    return (p1.y - p2.y)/(p1.x - p2.x);
  }

  private Point projectOnLine(Point a, Line l)
  {
    Point  v = l.span;
    StdOut.println(v.toString());
    v.draw(Color.BLUE);
    double c = ((v.x*a.x)+(v.y*a.y))/((v.x*v.x)+(v.y*v.y));
    double x = c*v.x;
    double y = c*v.y;
    StdOut.println("The projected Point: " + new Point(x, y).toString());
    return new Point(x, y);
  }

  private Point  projectOnLine(Point a, Line l, Point c)
  {
    Line projectLine = new Line(a, Math.pow(l.slope, -1));
    projectLine.draw(Color.GREEN);
    return computeIntersection(projectLine, l);
  }

  private double distance(Point p1, Point p2)
  {
    double d = Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y),2);
    return Math.sqrt(d);
  }

  private Circle solveBrute(Point[] points)
  {
    Standard b = new Standard(points);
    return b.sec();
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
    Megiddo test = new Megiddo(points);
    Circle sec = test.sec();
    //sec.draw();
  }



  private class Line implements Comparable<Line>
  {
    public final Point a;
    public final Point b;
    public final Point mid;
    public final double slope;
    public final double c;
    public       Point span;
    public final double angle_x_axis;

    private final Comparator<Line> BY_ANGLEWITHXAXIS = new byAngleWithXAxis();

    public Line(Point a, Point b)
    {
      this.a = a;
      this.b = b;
      this.mid = new Point((a.x+b.x)/2, (a.y+b.y)/2);
      this.slope = Math.abs((b.y-a.y)/(b.x-a.x));
      this.c     = computeB(this.a.y, this.a.x, this.slope);
      this.angle_x_axis = (Math.atan(this.slope)*180)/Math.PI;
      this.span = new Point(this.b.x-this.a.x, this.b.y-this.a.y);
      StdOut.println("Line Equation: " + this.toString());
    }

    public Line(Point a, Point b, double slope)
    {
      this.a = a;
      this.b = b;
      this.mid = new Point((a.x+b.x)/2, (a.y+b.y)/2);
      this.slope = Math.abs(slope);
      this.c     = computeB(this.a.y, this.a.x, this.slope);
      this.angle_x_axis = (Math.atan(this.slope)*180)/Math.PI;
      StdOut.println("Line Equation: " + this.toString());
    }

    public Line(Point mid, double slope)
    {
      this.mid    = mid;
      this.slope  = slope;
      this.c      = this.computeB(this.mid.y, this.mid.x, this.slope);
      this.a      = this.computeRandomPoint(this.mid.x+0.3, this.slope, this.c);
      this.b      = this.computeRandomPoint(this.mid.x-0.3, this.slope, this.c);
      this.angle_x_axis = (Math.atan(this.slope)*180)/Math.PI;
    }

    public Line(double slope, double b)
    {
      this.slope  = Math.abs(slope);
      double rand = StdRandom.uniform()*10.0;
      this.a      = this.computeRandomPoint(0.85, this.slope, b);
      StdOut.println(this.a.toString());
      this.b      = this.computeRandomPoint(-0.95, this.slope, b);
      StdOut.println(this.b.toString());
      this.mid    = new Point((this.a.x+this.b.x)/2, (this.a.y+this.b.y)/2);
      StdOut.println(this.mid.toString() + " ZZZ " + (double)(this.a.y+this.b.y)/2);
      this.c      = b;
      this.angle_x_axis = (Math.atan(this.slope)*180)/Math.PI;
    }

    public int compareTo(Line that)
    {
      if(this.slope > that.slope)return 1;
      else if(this.slope < that.slope)return -1;
      else return 0;
    }

    public void draw()
    {
      StdDraw.setPenColor(Color.BLACK);
      StdDraw.line(this.a.x, this.a.y, this.b.x, this.b.y);
    }

    public void draw(Color c)
    {
      StdDraw.setPenColor(c);
      StdDraw.line(this.a.x, this.a.y, this.b.x, this.b.y);
    }

    public Comparator<Line> angleXOrder()
    {
      return BY_ANGLEWITHXAXIS;
    }

    public String toString()
    {
        return "(y = " + slope + "*x + " + this.c + ")";
    }

    private Point computeRandomPoint(double x, double slope, double b)
    {
      double y = (slope*x) + b;
      return new Point(x, y);
    }

    private double computeB(double y, double x, double m)
    {
      return y - (m*(x));
    }

    private class byAngleWithXAxis implements Comparator<Line>
    {
      public int compare(Line w, Line v)
      {
        if(w.angle_x_axis > v.angle_x_axis)return 1;
        else if(w.angle_x_axis > v.angle_x_axis)return -1;
        else return 0;
      }
    }

  }


}
