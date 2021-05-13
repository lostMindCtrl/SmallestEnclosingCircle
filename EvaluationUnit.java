import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

public class EvaluationUnit
{
  private final int start;
  private final int end;

  public EvaluationUnit(int start, int end)
  {
    this.start = start;
    this.end   = end;
  }

  public boolean startInputTestingStandard()
  {
    boolean test = true;
    for(int i = start; i < end+1; i++)
    {
      try
      {

          String inputFile = "data/circle/input"  + i + ".txt";
          In input  = new In(inputFile);
          int size  = input.readInt();
          Point[] points = new Point[size];
          for(int j = 0; j < size; j++)
          {
            points[j] = new Point(input.readDouble(), input.readDouble());
          }
          BruteForce  bf                      = new BruteForce(points);
          Standard    b                       = new Standard(points);
          Stopwatch   stopwatch_bruteForce    = new Stopwatch();
          Circle      bf_sec                  = bf.sec();
          double      bf_elapsedTime          = stopwatch_bruteForce.elapsedTime();
          try
          {
            Stopwatch stopwatch_brute  = new Stopwatch();
            Circle b_sec               = b.sec();
            double b_elapsedTime       = stopwatch_brute.elapsedTime();
            if(Math.round(bf_sec.circumference()*100000) != Math.round(b_sec.circumference()*100000))
            {
              StdOut.println("\n\u001B[33m/!!!/ The Smallest Enclosing Circle with input Points " + i + " was not computed  correctly! /!!!/ Circumference(Bf - Std) " + bf_sec.circumference() + " != " + b_sec.circumference() + " \u001B[0m");
            }
            else
            {
            StdOut.println("\n\u001B[0mFor an input with "+ i +" Points: The BruteForce algorithm took: " + bf_elapsedTime + "s and the Brute algorithm (Brute means  not BruteForce) took: " + b_elapsedTime + "s - Brute was " + (b_elapsedTime-bf_elapsedTime) + "s faster!(a negative number is positive for Brute)\u001B[0m");
            }
          }
          catch(Exception e)
          {
            StdOut.println("\n\u001B[31m/!!!/ Brute class has issues computing the Input with " + i + " Points! /!!!/\u001B[0m");
            test = false;
          }

      }
      catch(Exception e){continue;}

    }
    return test;
  }

  public boolean startInputTestingChrystal()
  {
    boolean test = true;
    for(int i = start; i < end+1; i++)
    {
      try
      {

          String inputFile = "data/circle/input"  + i + ".txt";
          In input  = new In(inputFile);
          int size  = input.readInt();
          Point[] points = new Point[size];
          for(int j = 0; j < size; j++)
          {
            points[j] = new Point(input.readDouble(), input.readDouble());
          }
          BruteForce  bf                      = new BruteForce(points);
          Chrystal    b                       = new Chrystal(points);
          Stopwatch   stopwatch_bruteForce    = new Stopwatch();
          Circle      bf_sec                  = bf.sec();
          double      bf_elapsedTime          = stopwatch_bruteForce.elapsedTime();
          try
          {
            Stopwatch stopwatch_brute  = new Stopwatch();
            Circle b_sec               = b.sec();
            double b_elapsedTime       = stopwatch_brute.elapsedTime();
            if(bf_sec.circumference() != b_sec.circumference())
            {
              StdOut.println("\n\u001B[33m/!!!/ The Smallest Enclosing Circle with input Points " + i + " was not computed  correctly! /!!!/\u001B[0m");
            }
            else
            {
            StdOut.println("\n\u001B[0mFor an input with "+ i +" Points: The BruteForce algorithm took: " + bf_elapsedTime + "s and the Chrystal-Pierce algorithm  took: " + b_elapsedTime + "s - Chrystal was " + (b_elapsedTime-bf_elapsedTime) + "s faster!(a negative number is positive for Chrystal)\u001B[0m");
            }
          }
          catch(Exception e)
          {
            StdOut.println("\n\u001B[31m/!!!/ Chrystal class has issues computing the Input with " + i + " Points! /!!!/\u001B[0m");
            test = false;
          }

      }
      catch(Exception e){continue;}

    }
    return test;
  }

  public boolean startInputTestingWelzl()
  {
    boolean test = true;
    for(int i = start; i < end+1; i++)
    {
      try
      {

          String inputFile = "data/circle/input"  + i + ".txt";
          In input  = new In(inputFile);
          int size  = input.readInt();
          Point[] points = new Point[size];
          for(int j = 0; j < size; j++)
          {
            points[j] = new Point(input.readDouble(), input.readDouble());
          }
          BruteForce  bf                      = new BruteForce(points);
          Welzl    b                          = new Welzl(points);
          Stopwatch   stopwatch_bruteForce    = new Stopwatch();
          Circle      bf_sec                  = bf.sec();
          double      bf_elapsedTime          = stopwatch_bruteForce.elapsedTime();
          try
          {
            Stopwatch stopwatch_brute  = new Stopwatch();
            Circle b_sec               = b.sec();
            double b_elapsedTime       = stopwatch_brute.elapsedTime();
            if(bf_sec.circumference() != b_sec.circumference())
            {
              StdOut.println("\n\u001B[33m/!!!/ The Smallest Enclosing Circle with input Points " + i + " was not computed  correctly! /!!!/\u001B[0m");
            }
            else
            {
            StdOut.println("\n\u001B[0mFor an input with "+ i +" Points: The BruteForce algorithm took: " + bf_elapsedTime + "s and the Welzl algorithm  took: " + b_elapsedTime + "s - Welzl was " + (b_elapsedTime-bf_elapsedTime) + "s faster!(a negative number is positive for Welzl)\u001B[0m");
            }
          }
          catch(Exception e)
          {
            StdOut.println("\n\u001B[31m/!!!/ Welzl class has issues computing the Input with " + i + " Points! /!!!/\u001B[0m");
            test = false;
          }

      }
      catch(Exception e){continue;}

    }
    return test;
  }

  public static void main(String[] args)
  {

    String inputFile = "data/circle/input"  + 226 + ".txt";
    In input  = new In(inputFile);
    int size  = input.readInt();
    Point[] points = new Point[size];
    for(int j = 0; j < size; j++)
    {
      points[j] = new Point(input.readDouble(), input.readDouble());
    }
    BruteForce b = new BruteForce(points);
    StdOut.println(b.sec().circumference());
    Standard   s = new Standard(points);
    StdOut.println(s.sec().circumference());
    StdOut.println("Starting testing in Range: " + args[1] + " - " + args[2] + " !");
    EvaluationUnit unit = new EvaluationUnit(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    if(args[0].equalsIgnoreCase("-c") || args[0].equalsIgnoreCase("-chrystal"))
    {
      if(unit.startInputTestingChrystal())StdOut.println("\nNo issues were detected!\u001B[0m");
      else StdOut.println("\n\u001BThe EvaluationUnit found some severe issues!\u001B[0m");
    }
    else if(args[0].equalsIgnoreCase("-s") || args[0].equalsIgnoreCase("-standard"))
    {
      if(unit.startInputTestingStandard())StdOut.println("\nNo issues were detected!\u001B[0m");
      else StdOut.println("\n\u001BThe EvaluationUnit found some severe issues!\u001B[0m");
    }
    else if(args[0].equalsIgnoreCase("-w") || args[0].equalsIgnoreCase("-welzl"))
    {
      if(unit.startInputTestingWelzl())StdOut.println("\nNo issues were detected!\u001B[0m");
      else StdOut.println("\n\u001BThe EvaluationUnit found some severe issues!\u001B[0m");
    }
    else
    {
      StdOut.println("The Usage for the evalution Unit is: -<algorithm> <startValue> <endValue> e.g. -s (Standard algorithm) 20 1000 // The algorithm are Standard -s, Chrystal -c, Welzl -w and Megiddo -m (under construction)");
    }


  }
}
