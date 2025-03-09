/*
A functional interface in Java is an interface that has exactly one abstract method. 
It can have multiple default or static methods, but only one abstract method.

Functional interfaces are useful in lambda expressions, method references, and functional programming.

*/


@FunctionalInterface
interface Calculator {
    int add(int a, int b);  // Single abstract method
}


// Implementing the Interface in a Class
class MathOperation implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}

public class Functional {
    public static void main(String[] args) {
        Calculator calc = new MathOperation();
        System.out.println("Sum: " + calc.add(5, 10));
    }
}



