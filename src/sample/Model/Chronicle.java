package sample.Model;

import java.sql.Date;

public class Chronicle {
    private int id;
    private Date date_start;
    private Date date_end;
    private int id_group;
    private int id_mountain;
    private String success;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public int getId_mountain() {
        return id_mountain;
    }

    public void setId_mountain(int id_mountain) {
        this.id_mountain = id_mountain;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Chronicle(int id, Date date_start, Date date_end, int id_group, int id_mountain, String success) {
        this.id = id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.id_group = id_group;
        this.id_mountain = id_mountain;
        this.success = success;
    }
}
