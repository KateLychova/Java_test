package ru.stqa.pft.sandbox;

 public class MyFirstApplication {
    public static void main(String[] args){

      System.out.println("Hello, world !");

      Point p1 = new Point(3,4);

      Point p2 = new Point(9,7);

      System.out.println("Расстояние между координатами " + p1.x + p1.y + "и" + p2.x + p2.y + "=" + distance(p1, p2));
    }



   public static double distance(Point p1, Point p2){
      return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
 }


}
