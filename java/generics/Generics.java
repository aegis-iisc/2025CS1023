
// A box class without generics
public class Box {
    private Object object;

    public void set(Object object) { this.object = object; }
    public Object get() { return object; }
}




public Class WithoutGenerics {
    public static void main (args []){


        // Problem Failing type safety
        Box box = new Box();
        box.set("Hello");
        Integer num = (Integer) box.get(); // ClassCastException at runtime


        // Problem 2: Explicit type casting is needed
        Box box = new Box();
        box.set("Hello");
        String str = (String) box.get(); // Explicit casting required


        // Code Reusability :If you want to store a specific type (e.g., only Integer values), you have to manually check the type at runtime.
        public void set(Object object) {
            if (object instanceof Integer) {
                this.object = object;
            } else {
                throw new IllegalArgumentException("Only Integers allowed!");
            }
        }

    }


// A typical definition of a generic class
// A generic class is defined with the following format:

class name<T1, T2, ..., Tn> { /* ... */ }



/**
 * Generic version of the Box class.
 * @param <T> the type of the value being boxed
 */


public class Box<T> {
    // T stands for "Type"
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
}


// All occurrences of Object are replaced by T. 
// A type variable can be any non-primitive type you specify: 
// any class type, any interface type, any array type, or even another type variable.
//By convention, type parameter names are single, uppercase letters.


public Class WitGenerics {
    public static void main (args []){


       Box<String> stringBox = new Box<>();
        stringBox.set("Hello");
        // stringBox.set(10); // Compilation error (prevents runtime ClassCastException)


        Box<Integer> intBox = new Box<>();
        intBox.set(100);
        Integer num = intBox.get(); // No explicit casting needed


        // Same box class reused with different types
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello");

        Box<Integer> intBox = new Box<>();
        intBox.set(100);


    }






}
