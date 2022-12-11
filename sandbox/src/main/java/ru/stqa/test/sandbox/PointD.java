package ru.stqa.test.sandbox;

public class PointD {
  public static void main(String[] args) {
    Point p1 = new Point();
    Point p2 = new Point();
    p1.x1 = 7;
    p1.y1 = 7;
    p2.x2 = 5;
    p2.y2 = 5;
    System.out.println("Координаты точки 1 = " + p1.x1 + ";" + p1.y1);
    System.out.println("Координаты точки 2 = " + p2.x2 + ";" + p2.y2);

    System.out.println("Расстояние между точками - " + p1.distance(p2));
  }
}
