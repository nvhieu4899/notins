package go.nvhieucs.notins.controller;


import com.amazonaws.services.appconfig.model.Application;
import com.amazonaws.services.fms.model.App;
import go.nvhieucs.notins.model.applicationUser.ApplicationUser;
import go.nvhieucs.notins.model.applicationUser.UserRepository;
import go.nvhieucs.notins.model.follow.FollowRepository;
import go.nvhieucs.notins.model.photo.PhotoByUser;
import go.nvhieucs.notins.model.photo.PhotoByUserRepository;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("people/{username}")
public class PeopleController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhotoByUserRepository photoByUserRepository;

    @Autowired
    private FollowRepository followRepository;

    @GetMapping("info")
    public ApplicationUser getUserInfo(@PathVariable("username") String username) throws HttpResponseException {
        ApplicationUser user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new HttpResponseException(404, "Username not found");
        } else return user;
    }

    @GetMapping("photo")
    public Slice<PhotoByUser> getUserPhotos(@PathVariable("username") String username) throws HttpResponseException {
        ApplicationUser user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new HttpResponseException(404, "Username not found");
        } else {
            return photoByUserRepository.findByKeyUserId(user.getUserId(), PageRequest.of(0, 20));
        }
    }

}
