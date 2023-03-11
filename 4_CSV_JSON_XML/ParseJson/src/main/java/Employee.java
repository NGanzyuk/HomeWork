public class Employee {
    public long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public Employee() {

    }

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    @Override
    public String toString() {
        return "id: "+id+" firstName: "+firstName+" lastName: "+lastName+" country: "+country+" age: "+age;
    }

    public static boolean ArraysEquals(Employee[] e1, Employee[] e2) {
        if (e1.length == e2.length) {
            for (int i = 0; i < e1.length; i++) {
                if (e1[i].id == e2[i].id && e1[i].firstName.equals(e2[i].firstName)
                        && e1[i].lastName.equals(e2[i].lastName) && e1[i].country.equals(e2[i].country)
                        && e1[i].age == e2[i].age) {
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        else {
            return false;
        }
        return false;
    }
}
