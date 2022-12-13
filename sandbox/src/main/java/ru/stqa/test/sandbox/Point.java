package ru.stqa.test.sandbox;

public class Point {
  double x1, y1;
  double x2, y2;
  public void p1(double x1, double y1) {
    this.x1 = x1;
    this.y1 = y1;
  }
  public void p2(double x2, double y2) {
    this.x2 = x2;
    this.y2 = y2;
  }

  double distance(Point p) {
    return Math.sqrt((this.x2 - this.x1) * (this.x2 - this.x1) + (this.y2 - this.y1) * (this.y2 - this.y1));

  }

}


