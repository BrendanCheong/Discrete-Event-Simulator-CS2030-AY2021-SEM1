class Employee {

  private int id;
  private String firstName;
  private String lastName;
  private int salary;

  public Employee(int id, String firstName, String lastName, int salary) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.salary = salary;
  }

  public int getID() {
    return this.id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getName() {
    return this.firstName + " " + this.lastName;
  }

  public int getSalary() {
    return this.salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public int getAnnualSalary() {
    return this.salary * 12;
  }

  public int raiseSalary(int percent) {
   /* Due to Java FloatingPoint Weirdness
    * We must use a (type) cast literal
    * to convert an Int into a (double)
    * as percent is originally Int -> cast to Double
    * and Return as (int) to prevent errors
    */
    double increment = (double) percent / 100;
    System.out.println(increment);
    this.salary *= (1 + increment);
    return (int) this.salary;
  }

  public String toString() {
    return "Employee[id= " +
      this.id +
      ", name= " +
      (this.firstName + " " + this.lastName) +
      ", salary= " +
      this.salary +
      "]";
  }
}
