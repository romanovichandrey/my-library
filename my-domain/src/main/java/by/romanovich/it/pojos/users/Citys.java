package by.romanovich.it.pojos.users;

import java.io.Serializable;

/**
 * This is Citys pojo
 * @author Romanovich Andrei
 * @version 1.0
 */
public class Citys implements Serializable {

    private static final Long serialVersionUID = 13409278437L;

    /**@serial (name = id_city)*/
    private Integer idCity;

    /**@serial (name = c_name)*/
    private String name;

    public Citys() {
    }

    public Citys(Integer idCity, String name) {
        this.idCity = idCity;
        this.name = name;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
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
        if (!(o instanceof Citys)) return false;

        Citys citys = (Citys) o;

        if (idCity != null ? !idCity.equals(citys.idCity) : citys.idCity != null) return false;
        if (name != null ? !name.equals(citys.name) : citys.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCity != null ? idCity.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Citys{" +
                "idCity=" + idCity +
                ", name='" + name + '\'' +
                '}';
    }
}
