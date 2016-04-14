package by.romanovich.it.pojos.users;

import java.io.Serializable;

/**
 * This is Readers pojo
 * @author Romanovich Andrei
 * @version 1.0
 */
public class Readers implements Serializable {

    private static final Long serialVersionUID = 38462958103L;

    /**@serial (name = id_reader)*/
    private Integer idReader;

    /**@serial (name = r_name)*/
    private String name;

    /**@serial (name = r_surname)*/
    private String surname;

    /**@serial (name = r_tel)*/
    private String userTel;

    /**@serial (Readers.id_city = Citys.id_city)*/
    private Citys city;

    /**@serial (Readers.id_street = Streets.id_street)*/
    private Streets street;

    public Readers() {
    }

    public Readers(String name, String surname, String userTel, Citys city, Streets street) {
        this.name = name;
        this.surname = surname;
        this.userTel = userTel;
        this.city = city;
        this.street = street;
    }

    public Readers(Integer idReader, String name, String surname, String userTel, Citys city, Streets street) {
        this.idReader = idReader;
        this.name = name;
        this.surname = surname;
        this.userTel = userTel;
        this.city = city;
        this.street = street;
    }

    public Integer getIdReader() {
        return idReader;
    }

    public void setIdReader(Integer idReader) {
        this.idReader = idReader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Citys getCity() {
        return city;
    }

    public void setCity(Citys city) {
        this.city = city;
    }

    public Streets getStreet() {
        return street;
    }

    public void setStreet(Streets street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Readers)) return false;

        Readers readers = (Readers) o;

        if (city != null ? !city.equals(readers.city) : readers.city != null) return false;
        if (idReader != null ? !idReader.equals(readers.idReader) : readers.idReader != null) return false;
        if (name != null ? !name.equals(readers.name) : readers.name != null) return false;
        if (street != null ? !street.equals(readers.street) : readers.street != null) return false;
        if (surname != null ? !surname.equals(readers.surname) : readers.surname != null) return false;
        if (userTel != null ? !userTel.equals(readers.userTel) : readers.userTel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReader != null ? idReader.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (userTel != null ? userTel.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Readers{" +
                "idReader=" + idReader +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", userTel='" + userTel + '\'' +
                ", city=" + city +
                ", street=" + street +
                '}';
    }
}
