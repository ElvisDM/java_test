package ru.stqa.test.sandbox;

public class Point {
  double x;
  double y;

  public Point (double x, double y) {
    this.x = x;
    this.y = y;
  }


  public void distance(Point p2) {
    System.out.println("Точка 1: " + this.x + ";" + this.y);
    System.out.println("Точка 2: " + p2.x + ";" + p2.y);
    System.out.printf("Расстояние между точками = %s%n", Math.sqrt((this.x - p2.x) * (this.x - p2.x) + (this.y - p2.y) * (this.y - p2.y)));


  }

}


