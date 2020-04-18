package beke.ire.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "loanables", schema = "beke_library")
public class LoanablesEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "author", length = 25)
    private String author;

    @Basic
    @Column(name = "title", length = 25)
    private String title;

    @Basic
    @Column(name = "supplied_date")
    private Timestamp supplied_date;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private LoanableTypeEntity type;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LoanableStatusEntity status;

    public LoanablesEntity() {
    }

    public LoanablesEntity(String author, String title, Timestamp supplied_date, LoanableTypeEntity type, LoanableStatusEntity status) {
        this.author = author;
        this.title = title;
        this.supplied_date = supplied_date;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getSupplied_date() {
        return supplied_date;
    }

    public void setSupplied_date(Timestamp supplied_date) {
        this.supplied_date = supplied_date;
    }

    public LoanableTypeEntity getType() {
        return type;
    }

    public void setType(LoanableTypeEntity type) {
        this.type = type;
    }

    public LoanableStatusEntity getStatus() {
        return status;
    }

    public void setStatus(LoanableStatusEntity status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanablesEntity loanablesEntity = (LoanablesEntity) o;
        return id == loanablesEntity.id &&
                author.equals(loanablesEntity.author) &&
                title.equals(loanablesEntity.title) &&
                supplied_date.equals(loanablesEntity.supplied_date) &&
                type == loanablesEntity.type &&
                status == loanablesEntity.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, supplied_date, type, status);
    }

    @Override
    public String toString() {
        return "Loanables{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", supplied_date=" + supplied_date +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
