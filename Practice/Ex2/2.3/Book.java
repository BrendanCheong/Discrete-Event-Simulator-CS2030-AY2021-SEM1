class Book {

  private String isbn;
  private String name;
  private Author author;
  private double price;
  private int qty;

  public Book(String isbn, String name, Author author, double price) {
    this.isbn = isbn;
    this.name = name;
    this.author = author;
    this.price = price;
  }

  public Book(String isbn, String name, Author author, double price, int qty) {
    this.isbn = isbn;
    this.name = name;
    this.author = author;
    this.price = price;
    this.qty = qty;
  }

  public String getISBN() {
    return this.isbn;
  }

  public String getName() {
    return this.name;
  }

  public Author getAuthor() {
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

  public String getAuthorName() {
    return this.author.getName();
  }

  @Override
  public String toString() {
    return "Book[isbn=" +
      this.getISBN() +
      ", name=" +
      this.getName() +
      ", Author[name=" +
      this.getAuthorName() +
      ", email=" +
      this.author.getEmail() +
      "], price=" +
      this.getPrice() +
      ",qty=" +
      this.getQty() +
      "]";
  }
}
