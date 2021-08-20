public class Book {

    private final Page[] pages;
    private final int currentPageNumber;

    public Book(int pageAmt) {
        this.pages = new Page[pageAmt];
        this.currentPageNumber = 0;
    }

    private Book(int currentPageNumber, Page[] pages) {
        this.pages = pages;
        this.currentPageNumber = currentPageNumber;
    }

    public Book addPage(Page page, int pageNumber) {
        if (pageNumber <= this.pages.length || pageNumber > 0) {
            Book newBook = new Book(this.currentPageNumber, this.pages);
            newBook.pages[pageNumber - 1] = page;
            return newBook;
        } else {
            return this;
        }
    }

    public Book nextPage() {
        if (this.currentPageNumber + 1 <= this.pages.length) {
            return new Book(this.currentPageNumber + 1, this.pages);
        } else {
            return this;
        }
    }

    public Book prevPage() {
        if (this.currentPageNumber - 1 > 0) {
            return new Book(this.currentPageNumber - 1, this.pages);
        } else {
            return this;
        }
    }

    public Book gotoPage(int pageNumber) {
        if (pageNumber > 0 && pageNumber <= this.pages.length) {
            return new Book(pageNumber, this.pages);
        } else {
            return this;
        }
    }

    public void readPage() {
        if (currentPageNumber == 0 || this.pages[currentPageNumber - 1] == null) {
            System.out.println(String.format("Page %d is currently blank", currentPageNumber));
        } else {
            System.out.println(String.format("---- Page %d ----\n", currentPageNumber) +
                this.pages[currentPageNumber - 1].toString());
        }
    }
}
