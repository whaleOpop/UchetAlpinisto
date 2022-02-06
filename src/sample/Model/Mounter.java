package sample.Model;

import java.sql.Date;

public class Mounter {
    private String Login;

    private String fio;
    private Date date_birth;
    private String adress;
    private String title;
    private String gender;
    private Integer id_group;
    private String Password;

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Mounter(String login,  String fio, Date date_birth, String adress, String title, String gender, Integer id_group, String password) {
        Login = login;

        this.fio = fio;
        this.date_birth = date_birth;
        this.adress = adress;
        this.title = title;
        this.gender = gender;
        this.id_group = id_group;
        Password = password;
    }


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId_group() {
        return id_group;
    }

    public void setId_group(Integer id_group) {
        this.id_group = id_group;
    }
}
