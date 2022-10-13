public class Dog {
    //blueprint that describes all attributes/properties of the object 
    private int ID;
    private int weight ;
    private String name;
    private int age;

    //default constructor
    public Dog() {
        ID = 0;
        name = "NA";
        weight = 0;
        age = 0;
    }

    // overloaded constructor to instantiate object 
    public Dog(String name, int ID, int weight, int age) {
        this.name = name;
        this.ID = ID;
        this.weight = weight;
        this.age = age;
    }


    //setter
    public void setName(String name) {
        this.name = name; 
    }

    //getter
    public String getName() {
        return name; 
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public double getWeight () {
        return weight;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

//Instance method because there is no static keyword 
//static methods belong to the class, instance methods belong to the object 
// working method because it uses the data in the class 
    public String outputInfo() {
    return "Name: " + name + "\nID: " + ID + "\nAge: " + age + "\nWeight: " + weight;
    }

}
