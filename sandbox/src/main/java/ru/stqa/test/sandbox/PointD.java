package ru.stqa.test.sandbox;

public class PointD {
  public static void main(String[] args) {
    Point point_1 = new Point(5, 5);
    Point point_2 = new Point(1, 1);

    point_1.distance(point_2);
    System.out.println("Точка 1: " + point_1.x + ";" + point_1.y);
    System.out.println("Точка 2: " + point_2.x + ";" + point_2.y);
    System.out.print("Расстояние между точками = " + point_1.distance(point_2));

  }
}
