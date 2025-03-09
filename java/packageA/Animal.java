
// A class definition as part of the protected fields
// protected—The member is accessible from subclasses of the class where it is
// declared (subject to a few restrictions [JLS, 6.6.2]) and from any class in the
// package where it is declared.


package packageA;

public class Animal {  
    protected String species = "Dog";  // protected variable

    protected void display() {  // protected method
        System.out.println("Species: " + species);
    }
}