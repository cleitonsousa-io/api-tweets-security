package fcss.dev.security.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_tweets")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tweet_id")
    private Long tweetId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    @CreationTimestamp
    private Instant creationTimestamp;

    // GETTERS
    public Long getTweetId() {
        return tweetId;
    }
    public User getUser() {
        return user;
    }
    public String getContent() {
        return content;
    }
    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    // SETTERS
    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
