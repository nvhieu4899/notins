package go.nvhieucs.notins.controller;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import go.nvhieucs.notins.model.applicationUser.ApplicationUser;
import go.nvhieucs.notins.model.applicationUser.UserRepository;
import go.nvhieucs.notins.model.follow.Follow;
import go.nvhieucs.notins.model.follow.FollowKey;
import go.nvhieucs.notins.model.follow.FollowRepositoryImpl;
import go.nvhieucs.notins.model.photo.PhotoByUser;
import go.nvhieucs.notins.model.photo.PhotoByUserRepository;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@RequestMapping("users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepositoryImpl followRepository;

    @Autowired
    private PhotoByUserRepository photoByUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("signup")
    public ApplicationUser register(@RequestBody ApplicationUser user) throws HttpResponseException {
        boolean isUsernameExists = userRepository.existsByUsername(user.getUsername());
        if (isUsernameExists) {
            throw new HttpResponseException(401, "Username has been used");
        }
        user.setUserId(Uuids.timeBased());
        user.setCreationDate(Calendar.getInstance().getTime());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @GetMapping("newsfeed")
    public Slice<PhotoByUser> newsfeed(Principal principal,
                                       @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ApplicationUser currUser = userRepository.findOneByUsername(principal.getName());
        List<UUID> followingIds = followRepository.findFollowingIdsByFollowerId(currUser.getUserId());
        return photoByUserRepository.findFirst100ByKeyUserIdIsInOrderByKeyCreationDateDesc(followingIds, PageRequest.of(pageIndex - 1, pageSize));
    }

    @GetMapping("{username}")
    public ApplicationUser getUserInfo(@PathVariable("username") String username) throws HttpResponseException {
        ApplicationUser user = userRepository.findOneByUsername(username);
        if (user != null) return user;
        else throw new HttpResponseException(404, "Username not found");
    }


    @PostMapping("{username}/follow")
    public void follow(@PathVariable("username") String username, Principal principal) throws HttpResponseException {
        if (principal == null) {
            throw new HttpResponseException(403, "Forbidden");
        } else {
            String followerName = principal.getName();
            ApplicationUser follower = userRepository.findOneByUsername(followerName);
            ApplicationUser following = userRepository.findOneByUsername(username);

            followRepository.insert(
                    new Follow(
                            new FollowKey(follower.getUserId(), following.getUserId()),
                            follower.getUsername(), following.getUsername()));
        }
    }

}
