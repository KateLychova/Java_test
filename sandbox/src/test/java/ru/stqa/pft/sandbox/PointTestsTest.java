package ru.stqa.pft.sandbox;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PointTestsTest {
   @Test
  public void testPoint() {
     Point p1 = new Point(1, 1);
     Point p2 = new Point(1, 1);
     Assertions.assertEquals(p1.distance(p2), 1);
   }
     @Test
     public void testPoint2() {
       Point p3 = new Point(1, 1);
       Point p4 = new Point(1, 1);
       Assertions.assertEquals(p3.distance(p4),0);
   }
     @Test
  public void testPoint3() {
    Point p5 = new Point(2, 5);
    Point p6 = new Point(3, 1);
    Assertions.assertEquals(p5.distance(p6),-4);
  }

}