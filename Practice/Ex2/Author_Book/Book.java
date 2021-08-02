import java.util.ArrayList;

class Book {

  private String name;
  private Author[] author;
  private double price;
  private int qty = 0;

  public Book(String name, Author[] author, double price) {
    this.name = name;
    this.author = author;
    this.price = price;
  }

  public Book(String name, Author[] author, double price, int qty) {
    this.name = name;
    this.author = author;
    this.price = price;
    this.qty = qty;
  }

  public String getName() {
    return this.name;
  }

  public Author[] getAuthors() {
    return this.author;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQty() {
    return this.qty;
  }

  public void setQty(int qty) {
    this.qty = qty;
  }

  public ArrayList<String> getAuthorNames() {
    ArrayList<String> authorNameList = new ArrayList<String>();
    for (Author person: this.author) {
      authorNameList.add(person.getName());
    }
    return authorNameList;
  }

  @Override
  public String toString() {
    return "Book [name=" +
      this.name +
      ", authors=" +
      this.getAuthorNames() +
      ", price=" +
      this.price
      + ", qty=" +
      this.qty +
      "]";
  }
}
