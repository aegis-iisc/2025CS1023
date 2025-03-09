
// package-private—The member is accessible from any class in the package
// where it is declared. Technically known as default access, this is the access
// level you get if no access modifier is specified (except for interface members,
// which are public by default)

package packageA;

class Animal {  // Default (package-private) class
     String species = "Dog";  // Default variable

    void display() {  // Default method
        System.out.println("Species: " + species);
    }
}

// This class is in the same package, so it can access Animal
public class Default {
    public static void main(String[] args) {
        Animal a = new Animal();
        System.out.println(a.species); // Allowed
        a.display(); // Allowed
    }
}
