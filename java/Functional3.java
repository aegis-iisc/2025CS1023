

/*

Java provides several predefined functional interfaces in java.util.function package:
Interface   	Method	            Purpose
Predicate<T>	boolean test(T t)	Returns true or false based on a condition
Function<T, R>	R apply(T t)	Takes one input and returns one output
Consumer<T>	void accept(T t)	Takes input but does not return anything
Supplier<T>	T get()	Takes no input and returns a value
*/



import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Functional3 {
    public static void main(String[] args) {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println(isEven.test(10));  // true
        System.out.println(isEven.test(7));   // false


         Function<String, Integer> stringLength = str -> str.length();
        System.out.println(stringLength.apply("Hello"));  


        Consumer<String> printMessage = message -> System.out.println("Message: " + message);
        printMessage.accept("Hello, Functional Interface!");


        Supplier<Double> getRandom = () -> Math.random();
        System.out.println(getRandom.get());
    }
}


