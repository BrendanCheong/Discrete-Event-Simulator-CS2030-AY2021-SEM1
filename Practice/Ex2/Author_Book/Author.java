class Author {

  private String name;
  private String email;
  private char gender;

  public Author(String name, String email, char gender) {
    this.name = name;
    this.email = email;
    this.gender = gender;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public char getGender() {
    return this.gender;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setGendet(char gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "Author[name= " +
      this.name +
      ", email= " +
      this.email +
      ", gender= " +
      this.gender +
      "]";
  }
}
