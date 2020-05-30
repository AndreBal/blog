package by.leverx.bean;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "article")
@Data
public class Article extends BaseEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "article")
    public List<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Article_has_Tag",
            joinColumns = {@JoinColumn(name = "Article_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "Tag_id", referencedColumnName = "id")})
    private List<Tag> tags;
}
