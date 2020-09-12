package go.nvhieucs.notins.controller;

import com.amazonaws.services.fms.model.App;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import go.nvhieucs.notins.model.applicationUser.ApplicationUser;
import go.nvhieucs.notins.model.applicationUser.UserRepository;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RequestMapping("users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("signup")
    public ApplicationUser register(@RequestBody ApplicationUser user) {
        user.setUserId(Uuids.timeBased());
        user.setCreationDate(Calendar.getInstance().getTime());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @GetMapping("{username}")
    public ApplicationUser getUserInfo(@PathVariable("username") String username) throws HttpResponseException {
        ApplicationUser user = userRepository.findOneByUsername(username);
        if (user != null) return user;
        else throw new HttpResponseException(404, "Username not found");
    }


}
