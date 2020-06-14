package by.leverx.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@Data
public class Comment extends BaseEntity{
    @Column(name = "message")
    private String text;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;
}
