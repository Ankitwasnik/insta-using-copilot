package com.talentica.copilot.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

//entity for post of a social media app
@Entity(name = "Post")
@Table(name = "posts")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {

  //user who created the post
  @ManyToOne(targetEntity = User.class, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  //caption of the post
  @Column(name = "caption", length = 100)
  private String caption;

  //media url of the post
  @Column(name = "media_url")
  private String mediaUrl;

  //comments on the post
  @OneToMany(targetEntity = Comment.class, mappedBy = "post", cascade = CascadeType.ALL)
  private List<Comment> comments;

  // reactions on the post
  @OneToMany(targetEntity = Reaction.class, mappedBy = "post", cascade = CascadeType.ALL)
  private List<Reaction> reactions;


}
