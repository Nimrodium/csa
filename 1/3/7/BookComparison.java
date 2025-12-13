class BookComparison {

    public static void main(String[] args) {
        Book b1 = new Book("10 new ways to glorp", "glorpman", "12323434");
        Book b2 = new Book("the only guide on seepgo", "him", "9746823123");
        Book b3 = new Book("i am the true glorpman", "glorpman", "13278768");
        Book b4 = new Book(
            "who is glorpman? intro to the top new author",
            "anthony williams",
            "126777432"
        );
        var b5 = b4;
        Book b6 = new Book("i am the true glorpman", "glorpman", "13278768");
        Book b7 = null;
        if (b5 == b4) {
            System.out.println("book 5 and book 4 are the same pointer");
        }
        if (b3.equals(b6)) {
            System.out.println("book 3 and book 6 are equal");
        }
        if (!(b3 == b6)) {
            System.out.println(
                "but book 3 and book 6 are not literally the same object (ptr)"
            );
        }
        if (b1 != b7) {
            System.out.println("book 1 is not book 7, which is null.");
        }
        if (b7 == null) {
            System.out.println("however book 7 is equal to null (zero ptr)");
        }
    }
}
