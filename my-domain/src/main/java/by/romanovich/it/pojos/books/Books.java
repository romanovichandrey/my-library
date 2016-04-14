package by.romanovich.it.pojos.books;

import by.romanovich.it.pojos.users.Users;

import java.io.Serializable;

public class Books implements Serializable
{

    private static final long serialVersionUID = 1L;
    private Integer id_book;
    private String name;
    private String description;
    private String author;
    private String book_date;
    private float price;
    private Users user;
    private Categories category;

    public Books() {
    }

    public Books(String name, String description, String author, String book_date, float price, Users user, Categories category) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.book_date = book_date;
        this.price = price;
        this.user = user;
        this.category = category;
    }

    public Books(Integer id_book, String name, String description, String author, String book_date, float price,
                 Users user, Categories category) {
        this.id_book = id_book;
        this.name = name;
        this.description = description;
        this.author = author;
        this.book_date = book_date;
        this.price = price;
        this.user = user;
        this.category = category;

    }




    public Books(int id_book) {
        this.id_book = id_book;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook_date() {
        return book_date;
    }

    public void setBook_date(String book_date) {
        this.book_date = book_date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Books)) return false;

        Books books = (Books) o;

        if (id_book != books.id_book) return false;
        if (Float.compare(books.price, price) != 0) return false;
        if (author != null ? !author.equals(books.author) : books.author != null) return false;
        if (book_date != null ? !book_date.equals(books.book_date) : books.book_date != null) return false;
        if (category != null ? !category.equals(books.category) : books.category != null) return false;
        if (description != null ? !description.equals(books.description) : books.description != null) return false;
        if (name != null ? !name.equals(books.name) : books.name != null) return false;
        if (user != null ? !user.equals(books.user) : books.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id_book;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (book_date != null ? book_date.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id_book=" + id_book +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", book_date='" + book_date + '\'' +
                ", price=" + price +
                ", user=" + user +
                ", category=" + category +
                '}';
    }
}
