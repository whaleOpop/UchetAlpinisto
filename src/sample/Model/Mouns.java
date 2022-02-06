package sample.Model;

public class Mouns {
    private Integer id;
    private Integer Height;
    private String NameM;
    private String Country;
    private String District;

    public Mouns(Integer id, Integer height, String nameM, String country, String district) {
        this.id = id;
        this.Height = height;
        this.NameM = nameM;
        this.Country = country;
        this.District = district;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(Integer height) {
        Height = height;
    }

    public String getNameM() {
        return NameM;
    }

    public void setNameM(String nameM) {
        NameM = nameM;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }
}
