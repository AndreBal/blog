package by.leverx.bean;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "user")
    public List<Article> articles;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "user")
    public List<Comment> comments;
}
