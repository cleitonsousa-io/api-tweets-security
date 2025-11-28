package fcss.dev.security.controller;


import fcss.dev.security.controller.dto.CreateTweetDTO;
import fcss.dev.security.controller.dto.FeedDTO;
import fcss.dev.security.controller.dto.FeedItensDTO;
import fcss.dev.security.entities.Role;
import fcss.dev.security.entities.Tweet;
import fcss.dev.security.repository.TweetRepository;
import fcss.dev.security.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class TweetController {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;


    public TweetController(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/tweets")
    public ResponseEntity<Void> createdTweet(@RequestBody CreateTweetDTO dto,
                                             JwtAuthenticationToken token){

        var user = userRepository.findById(UUID.fromString(token.getName()));
        var tweet = new Tweet();
        tweet.setUser(user.get());
        tweet.setContent(dto.content());

        tweetRepository.save(tweet);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") long tweetId, JwtAuthenticationToken token){

        var user = userRepository.findById(UUID.fromString(token.getName()));
        var tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var isAdmin = user.get().getRoles()
                .stream().anyMatch(role -> role.getName()
                        .equalsIgnoreCase(Role.Values.ADMIN.name()));

        if (isAdmin || tweet.getUser().getUserId().equals(UUID.fromString(token.getName()))){
            tweetRepository.deleteById(tweetId);
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/feed")
    public ResponseEntity<FeedDTO> feed(@RequestParam(value = "page", defaultValue = "0")int page,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

        var tweets = tweetRepository.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "creationTimesStamp"))
                .map(tweet -> new FeedItensDTO(tweet.getTweetId(), tweet.getContent(), tweet.getUser().getUsername()));

        return ResponseEntity.ok(new FeedDTO(tweets.getContent(), page, pageSize, tweets.getTotalPages(), tweets.getTotalElements()));
    }












}
