package fcss.dev.security.service;

import fcss.dev.security.controller.dto.CreateTweetDTO;
import fcss.dev.security.controller.dto.FeedDTO;
import fcss.dev.security.controller.dto.FeedItensDTO;
import fcss.dev.security.entities.Role;
import fcss.dev.security.entities.Tweet;
import fcss.dev.security.entities.User;
import fcss.dev.security.repository.TweetRepository;
import fcss.dev.security.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public void createTweet(CreateTweetDTO dto, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Tweet tweet = new Tweet();
        tweet.setUser(user);
        tweet.setContent(dto.content());
        tweetRepository.save(tweet);
    }

    public void deleteTweet(long tweetId, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tweet not found"));

        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));

        if (isAdmin || tweet.getUser().getUserId().equals(userId)) {
            tweetRepository.delete(tweet);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not allowed to delete this tweet");
        }
    }

    public FeedDTO getFeed(int page, int pageSize) {
        var tweetsPage = tweetRepository.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "creationTimestamp"))
                .map(tweet -> new FeedItensDTO(
                        tweet.getTweetId(),
                        tweet.getContent(),
                        tweet.getUser().getUsername())
                );

        return new FeedDTO(
                tweetsPage.getContent(),
                page,
                pageSize,
                tweetsPage.getTotalPages(),
                tweetsPage.getTotalElements()
        );
    }
}
