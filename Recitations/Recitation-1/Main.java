class Main {

    public static void main(String[] args) {
        Page page1 = new Page("Yes. Some are red. And some are blue.\n" +
            "Some are sad.\n" + "And some are glad."
        );
        Page page2 = new Page("Green eggs and Ham\n" +
            "That I am");
        Page page3 = new Page("Horton hears a who\n" +
            "To which I seem to do\n" +
            "Oh Blimley, what should I do?");
        Page page4 = new Page("Alas m'lady\n" +
            "Whatever shall I do?");
        Page page5 = new Page("Who shall save me from this fate?\n" +
            "Alas, that is to be seen mousieur\n" +
            "---Fin---");
        // new Book with 5 pages
        Book DrSeuss = new Book(5);
        DrSeuss.addPage(page1, 1)
            .addPage(page2, 2)
            .addPage(page3, 3)
            .addPage(page4, 4)
            .addPage(page5, 5);
        DrSeuss.gotoPage(4).nextPage().readPage();
        DrSeuss.gotoPage(5).prevPage().readPage();
        DrSeuss.gotoPage(4).prevPage().readPage();
        DrSeuss.gotoPage(3).prevPage().readPage();
        DrSeuss.gotoPage(2).prevPage().readPage();
        DrSeuss.gotoPage(1).prevPage().readPage();
    }

}
