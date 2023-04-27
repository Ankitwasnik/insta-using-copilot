package com.talentica.copilot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// entity to store comments on a post
@Entity(name = "Comment")
@Table(name = "comments")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

  // id of the post on which comment is made
  @ManyToOne(targetEntity = Post.class, optional = false)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  // id of the user who made the comment
  @ManyToOne(targetEntity = User.class, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  // comment text
  @Column(name = "text", nullable = false)
  private String text;

}
