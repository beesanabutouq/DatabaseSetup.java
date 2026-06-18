package supermarket_db;

public class Employee {

    private int id;
    private String name;
    private String position;
    private double salary;
    private String hireDate;
    private int managerId;

    public Employee(int id,
                    String name,
                    String position,
                    double salary,
                    String hireDate,
                    int managerId) {

        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
        this.managerId = managerId;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getPosition() { return position; }

    public double getSalary() { return salary; }

    public String getHireDate() { return hireDate; }

    public int getManagerId() { return managerId; }
}