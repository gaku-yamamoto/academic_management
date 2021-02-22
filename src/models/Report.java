package models;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "reports")
@NamedQueries({
    @NamedQuery(
            name = "getAllReports",
            query = "SELECT r FROM Report AS r ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name = "getReportsCount",
            query = "SELECT COUNT(r) FROM Report AS r"
            ),
    @NamedQuery(
            name = "checkRegisteredName",
            query = "SELECT COUNT(r) FROM Report AS r WHERE r.name = :name"
            ),
    @NamedQuery(
            name = "search",
            query = "SELECT r FROM Report AS r WHERE r.searchdata like :searchdata"
            ),
})
@Entity
public class Report {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 論文名
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    //著者
    @Column(name = "author", nullable = false)
    private String author;

    //発行年
    @Column(name = "year", nullable = false)
    private String year;

    //掲載雑誌
    @Column(name = "magazine", nullable = false)
    private String magazine;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    //各科
    @Column(name = "department", nullable = false)
    private String department;

    //ファイル名
    @Column(name = "filename", nullable = false)
    private String filename;

    //検索用データ
    @Lob
    @Column(name = "searchdata", nullable = false, length = 100000)
    private String searchdata;

    //ラベル
    @Column(name = "labels", nullable = false)
    private String labels;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSearchdata() {
        return searchdata;
    }

    public void setSearchdata(String searchdata) {
        this.searchdata = searchdata;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }

    public List<String> getlabelList(){
        return Arrays.asList(labels.split("\\s*,\\s*"));
    }
}