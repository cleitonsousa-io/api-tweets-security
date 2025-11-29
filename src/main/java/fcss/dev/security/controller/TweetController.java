package fcss.dev.security.controller;

import fcss.dev.security.controller.dto.CreateTweetDTO;
import fcss.dev.security.controller.dto.FeedDTO;
import fcss.dev.security.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class TweetController {

    private final TweetService tweetService;

    @PostMapping("/tweets")
    public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDTO dto, JwtAuthenticationToken token){
        tweetService.createTweet(dto, UUID.fromString(token.getName()));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") long tweetId, JwtAuthenticationToken token){
        tweetService.deleteTweet(tweetId, UUID.fromString(token.getName()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/feed")
    public ResponseEntity<FeedDTO> getFeed(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        FeedDTO feed = tweetService.getFeed(page, pageSize);
        return ResponseEntity.ok(feed);
    }
}
