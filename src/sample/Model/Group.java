package sample.Model;

public class Group {
private Integer id;
private String name_g;
private Integer count_p;
private String Login;

    public Group(Integer id, String name_g, Integer count_p, String login) {
        this.id = id;
        this.name_g = name_g;
        this.count_p = count_p;
        this.Login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_g() {
        return name_g;
    }

    public void setName_g(String name_g) {
        this.name_g = name_g;
    }

    public Integer getCount_p() {
        return count_p;
    }

    public void setCount_p(Integer count_p) {
        this.count_p = count_p;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }
}
