// For members (fields, methods, nested classes, and nested interfaces), there are
// four possible access levels, listed here in order of increasing accessibility:
// • private—The member is accessible only from the top-level class where it is
// declared.



class Person {
    private String name = "John";  // Private variable

    private void display() {       // Private method
        System.out.println("Name: " + name);
    }

    public void accessPrivateMethod() {  // Public method to access private method
        display();
    }
}

public class Fields {
    public static void main(String[] args) {
        Person p = new Person();
        System.out.println(p.name); 
        p.display(); 
        p.accessPrivateMethod(); 
    }
}



