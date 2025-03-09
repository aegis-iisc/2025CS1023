//Concept: Hiding implementation details while exposing only the necessary functionalities.
//both abstract classes and interfaces are used to achieve abstraction and define blueprints for other classes

abstract class Shape {
    abstract void draw(); // Abstract method
}




class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a circle...");
    }
}


class Rectangle extends Shape, Circle {
    @Override
    void draw() {
        System.out.println("Drawing a rectangle...");
    }
}

public class Abstraction {
    public static void main(String[] args) {
        Shape myShape = new Circle();
        Shape myotherShape = new Shape ();
        myShape.draw(); // Output: Drawing a circle...
    }
}
