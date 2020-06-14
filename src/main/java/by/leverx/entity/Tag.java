package by.leverx.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
@Data
public class Tag extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Article_has_Tag",
            joinColumns = {@JoinColumn(name = "Tag_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "Article_id", referencedColumnName = "id")})
    private List<Article> articles;

}
