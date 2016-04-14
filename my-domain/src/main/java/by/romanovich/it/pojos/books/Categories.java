package by.romanovich.it.pojos.books;

import java.io.Serializable;

public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id_cat;
    private String name_cat;

    public Categories() {

    }

    public Categories(Integer id_cat, String name_cat) {
        this.id_cat = id_cat;
        this.name_cat = name_cat;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getName_cat() {
        return name_cat;
    }

    public void setName_cat(String name_cat) {
        this.name_cat = name_cat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categories)) return false;

        Categories that = (Categories) o;

        if (id_cat != that.id_cat) return false;
        if (name_cat != null ? !name_cat.equals(that.name_cat) : that.name_cat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id_cat;
        result = 31 * result + (name_cat != null ? name_cat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id_cat=" + id_cat +
                ", name_cat='" + name_cat + '\'' +
                '}';
    }
}
