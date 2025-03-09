
// Example protected fileds accessed from a subclass in a different package
package packageB;
import packageA.Animal;

public class Dog { 

public void accessProtected() {
        Animal an = new Animal();
        System.out.println("Species: " + an.species); 
        an.display(); 
    }


   public static void main(String[] args) {
       Dog mytestdog = new Dog();
       mytestdog.accessProtected ();

    } 

}
