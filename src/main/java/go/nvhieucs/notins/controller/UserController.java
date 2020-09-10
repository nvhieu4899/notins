package go.nvhieucs.notins.controller;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import go.nvhieucs.notins.model.applicationUser.ApplicationUser;
import go.nvhieucs.notins.model.applicationUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("users/signup")
    public ApplicationUser register(@RequestBody ApplicationUser user) {
        user.setUserId(Uuids.timeBased());
        user.setCreationDate(Calendar.getInstance().getTime());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
