import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import java.lang.Math;
import java.awt.Color;

public class Circle
{
  public Point[] boundary;
  public double radius;
  private double x_middle;
  private double y_middle;



  public Circle(Point center, double radius)
  {
    this.x_middle = center.x;
    this.y_middle = center.y;
    this.radius   = radius;

  }

  public Circle(Point p1, Point p2)
  {

    computeMidPoint(p1, p2);
    this.radius = computeRadius(p1, p2);
    Point mid_point =  new Point(x_middle, y_middle);
    boundary = new Point[2];
    boundary[0] = p1;
    boundary[1] = p2;
  }

  public Circle(Point p1, Point p2, Point p3)
  {
    computeMidandRadius(p1, p2, p3);
    boundary = new Point[3];
    boundary[0] = p1;
    boundary[1] = p2;
    boundary[2] = p3;
  }

  public boolean contains(Point p)
  {
    double dist = Math.sqrt(Math.pow((p.x - x_middle), 2) + Math.pow((p.y - y_middle),2));
    if(dist > radius && dist > (radius+0.00001))return false;
    return true;
  }

  public boolean onBoundary(Point p)
  {
    double dist = Math.sqrt(Math.pow((p.x - x_middle), 2) + Math.pow((p.y - y_middle),2));
    if(dist == radius)return true;
    return false;
  }

  public void draw()
  {
    StdOut.println("Drawing circle with middlepoint coordinates: [" + this.x_middle + "," + this.y_middle + "] and Radius " + this.radius);
    StdDraw.setPenColor(Color.BLACK);
    StdDraw.circle(x_middle, y_middle, radius);
  }

  public String toString()
  {
    return "(" + this.x_middle + "," + this.y_middle + ")" + "," + this.radius;
  }

  public Point getCenter()
  {
    return new Point(x_middle, y_middle);
  }

  public double circumference()
  {
    return (2*Math.PI)*this.radius;
  }

  private void computeMidPoint(Point p1, Point p2)
  {
    this.x_middle = (p1.x + p2.x)/2;
    this.y_middle = (p1.y + p2.y)/2;
  }

  private void computeMidandRadius(Point p1, Point p2, Point p3)
  {
    double d = determinant(Math.pow(p1.x, 2) + Math.pow(p1.y, 2), Math.pow(p2.x, 2) + Math.pow(p2.y, 2), Math.pow(p3.x, 2) + Math.pow(p3.y, 2), p1.y, p2.y, p3.y, 1, 1, 1);
    double a = determinant(p1.x, p2.x, p3.x, p1.y, p2.y, p3.y, 1, 1, 1);
    double e = determinant(Math.pow(p1.x, 2) + Math.pow(p1.y, 2), Math.pow(p2.x, 2) + Math.pow(p2.y, 2), Math.pow(p3.x, 2) + Math.pow(p3.y, 2), p1.x, p2.x, p3.x, 1, 1, 1);
    double f = determinant(Math.pow(p1.x, 2) + Math.pow(p1.y, 2), Math.pow(p2.x, 2) + Math.pow(p2.y, 2), Math.pow(p3.x, 2) + Math.pow(p3.y, 2), p1.x, p2.x, p3.x, p1.y, p2.y, p3.y);
    this.x_middle = d/(2*a);
    this.y_middle = (-1*e)/(2*a);
    this.radius   = (Math.pow(d, 2) + Math.pow(e, 2))/(4*Math.pow(a, 2))+(f/a);
    this.radius   =  Math.sqrt(radius);
  }

  private void computeMidPoint(Point p1, Point p2, Point p3)
  {
    double d = determinant(Math.pow(p1.x, 2) + Math.pow(p1.y, 2), Math.pow(p2.x, 2) + Math.pow(p2.y, 2), Math.pow(p3.x, 2) + Math.pow(p3.y, 2), p1.y, p2.y, p3.y, 1, 1, 1);
    double a = determinant(p1.x, p2.x, p3.x, p1.y, p2.y, p3.y, 1, 1, 1);
    double e = determinant(Math.pow(p1.x, 2) + Math.pow(p1.y, 2), Math.pow(p2.x, 2) + Math.pow(p2.y, 2), Math.pow(p3.x, 2) + Math.pow(p3.y, 2), p1.x, p2.x, p3.x, 1, 1, 1);
    this.x_middle = d/(2*a);
    this.y_middle = (-1*e)/(2*a);
  }

  private double computeRadius(Point p1, Point p2)
  {
    double pow_x = Math.pow((p1.x - p2.x),2);
    double pow_y = Math.pow((p1.y - p2.y),2);
    double radius = (pow_x + pow_y)/4;
    return Math.sqrt(radius);
  }

  private double computeRadius(Point p1, Point p2, Point p3)
  {
    double d = determinant(Math.pow(p1.x, 2) + Math.pow(p1.y, 2), Math.pow(p2.x, 2) + Math.pow(p2.y, 2), Math.pow(p3.x, 2) + Math.pow(p3.y, 2), p1.y, p2.y, p3.y, 1, 1, 1);
    double a = determinant(p1.x, p2.x, p3.x, p1.y, p2.y, p3.y, 1, 1, 1);
    double e = determinant(Math.pow(p1.x, 2) + Math.pow(p1.y, 2), Math.pow(p2.x, 2) + Math.pow(p2.y, 2), Math.pow(p3.x, 2) + Math.pow(p3.y, 2), p1.x, p2.x, p3.x, 1, 1, 1);
    double f = determinant(Math.pow(p1.x, 2) + Math.pow(p1.y, 2), Math.pow(p2.x, 2) + Math.pow(p2.y, 2), Math.pow(p3.x, 2) + Math.pow(p3.y, 2), p1.x, p2.x, p3.x, p1.y, p2.y, p3.y);
    this.radius   = (Math.pow(d, 2) + Math.pow(e, 2))/(4*Math.pow(a, 2))+(f/a);
    this.radius   =  Math.sqrt(radius);
    return radius;
  }


  private double determinant(double a_11, double a_12, double a_13, double a_21, double a_22, double a_23, double a_31, double a_32, double a_33)
  {
    double det = a_11*((a_33*a_22) - (a_32*a_23)) - a_12*((a_33*a_21) - (a_31*a_23)) + a_13*((a_32*a_21) - (a_31*a_22));
    return det;
  }
}
