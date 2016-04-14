package by.romanovich.it.pojos.users;

import java.io.Serializable;

/**
 * This is Streets pojo
 * @author Romanovich Andrei
 * @version 1.0
 */
public class Streets implements Serializable {

    private static final Long serialVersionUID = 25467879401L;

    /**@serial (name = id_street)*/
    private Integer idStreet;

    /**@serial (name = s_name)*/
    private String name;

    public Streets() {
    }

    public Streets(String name) {
        this.name = name;
    }

    public Streets(Integer idStreet, String name) {
        this.idStreet = idStreet;
        this.name = name;
    }

    public Integer getIdStreet() {
        return idStreet;
    }

    public void setIdStreet(Integer idStreet) {
        this.idStreet = idStreet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Streets)) return false;

        Streets streets = (Streets) o;

        if (idStreet != null ? !idStreet.equals(streets.idStreet) : streets.idStreet != null) return false;
        if (name != null ? !name.equals(streets.name) : streets.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStreet != null ? idStreet.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Steerts{" +
                "idStreet=" + idStreet +
                ", name='" + name + '\'' +
                '}';
    }
}
