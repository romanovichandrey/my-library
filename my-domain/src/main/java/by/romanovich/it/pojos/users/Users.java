package by.romanovich.it.pojos.users;

import java.io.Serializable;

/**
 * This is Users pojo
 * @author Romanovich Andrei
 * @version 1.0
 */
public class Users implements Serializable {

    private static final Long serialVersionUID = 48573629864L;

    /**@serial (name = id_user)*/
    private Integer idUser;

    /**@serial (name = u_login)*/
    private String login;

    /**@serial (name = u_password)*/
    private String password;

    /**@serial (Users.id_reader = Readers.id_reader)*/
    private Readers reader;

    public Users() {
    }

    public Users(String login, String password, Readers reader) {
        this.login = login;
        this.password = password;
        this.reader = reader;
    }

    public Users(Integer idUser, String login, String password, Readers reader) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.reader = reader;
    }

    public Users(Integer idUser, String login, String password) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Readers getReader() {
        return reader;
    }

    public void setReader(Readers reader) {
        this.reader = reader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;

        Users users = (Users) o;

        if (idUser != null ? !idUser.equals(users.idUser) : users.idUser != null) return false;
        if (login != null ? !login.equals(users.login) : users.login != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (reader != null ? !reader.equals(users.reader) : users.reader != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser != null ? idUser.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (reader != null ? reader.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Users{" +
                "idUser=" + idUser +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", reader=" + reader +
                '}';
    }
}
