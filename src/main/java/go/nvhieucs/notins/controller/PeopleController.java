package go.nvhieucs.notins.controller;


import com.amazonaws.services.appconfig.model.Application;
import com.amazonaws.services.fms.model.App;
import go.nvhieucs.notins.model.applicationUser.ApplicationUser;
import go.nvhieucs.notins.model.applicationUser.UserRepository;
import go.nvhieucs.notins.model.follow.Follow;
import go.nvhieucs.notins.model.follow.FollowKey;
import go.nvhieucs.notins.model.follow.FollowRepository;
import go.nvhieucs.notins.model.follow.FollowRepositoryImpl;
import go.nvhieucs.notins.model.photo.PhotoByUser;
import go.nvhieucs.notins.model.photo.PhotoByUserRepository;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("people/{username}")
public class PeopleController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhotoByUserRepository photoByUserRepository;

    @Autowired
    private FollowRepositoryImpl followRepository;

    @GetMapping("")
    public ApplicationUser getUserInfo(@PathVariable("username") String username) throws HttpResponseException {
        ApplicationUser user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new HttpResponseException(404, "Username not found");
        } else return user;
    }

    @GetMapping("photo")
    public Slice<PhotoByUser> getUserPhotos(@PathVariable("username") String username,
                                            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws HttpResponseException {
        ApplicationUser user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new HttpResponseException(404, "Username not found");
        } else {
            return photoByUserRepository.findByKeyUserId(user.getUserId(), PageRequest.of(pageIndex - 1, pageSize));
        }
    }

    @PostMapping("follow")
    public void followUser(@PathVariable("username") String username, Principal principal) {
        ApplicationUser followUser = userRepository.findOneByUsername(username);
        ApplicationUser follower = userRepository.findOneByUsername(principal.getName());
        Follow follow = new Follow(new FollowKey(follower.getUserId(), followUser.getUserId()), follower.getUsername(), followUser.getUsername());
        followRepository.insert(follow);
    }

    @PostMapping("unfollow")
    public void unfollowUser(@PathVariable("username") String username, Principal principal) throws HttpResponseException {
        ApplicationUser followUser = userRepository.findOneByUsername(username);
        ApplicationUser follower = userRepository.findOneByUsername(principal.getName());
        Optional<Follow> follow = followRepository.findById(new FollowKey(follower.getUserId(), followUser.getUserId()));
        if (follow.isPresent()) {
            followRepository.delete(follow.get());
        } else {
            throw new HttpResponseException(404, "Follow didn't exists");
        }
    }

}
