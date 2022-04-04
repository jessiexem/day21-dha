package vttp2022.paf.day1.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class Comment {

    private String c_id;
    private String user;
    private Integer rating;
    private String c_text;
    private Integer gid;

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getC_text() {
        return c_text;
    }

    public void setC_text(String c_text) {
        this.c_text = c_text;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public static List<Comment> createComments(SqlRowSet rs) {
        List<Comment> list = new ArrayList<>();

        while (rs.next()) {
            Comment c = new Comment();
            c.setC_id(rs.getString("c_id"));
            c.setUser(rs.getString("user"));
            c.setRating(rs.getInt("rating"));
            c.setC_text(rs.getString("c_text"));
            c.setGid(rs.getInt("gid"));
            list.add(c);
        }
        return list;
    }


}
