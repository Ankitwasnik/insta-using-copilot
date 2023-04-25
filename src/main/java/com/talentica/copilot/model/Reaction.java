package com.talentica.copilot.model;

import com.talentica.copilot.enums.ReactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

// entity to store reactions on a post
@Entity(name = "Reaction")
@Table(name = "reactions")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reaction extends BaseEntity{

  // id of the post on which reaction is made
  @ManyToOne(targetEntity = Post.class, optional = false)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  // id of the user who made the reaction
  @ManyToOne(targetEntity = User.class, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  // type of the reaction
  @Enumerated(jakarta.persistence.EnumType.STRING)
  @Column(name = "type", nullable = false)
  private ReactionType type;

}
