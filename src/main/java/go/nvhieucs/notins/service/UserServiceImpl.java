package go.nvhieucs.notins.service;

import go.nvhieucs.notins.model.applicationUser.ApplicationUser;
import go.nvhieucs.notins.model.applicationUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = userRepository.findOneByUsername(username);
        if (applicationUser != null) {
            return new User(applicationUser.getUsername(), applicationUser.getPassword(), new ArrayList<>());
        } else throw new UsernameNotFoundException("Username not found");
    }
}
