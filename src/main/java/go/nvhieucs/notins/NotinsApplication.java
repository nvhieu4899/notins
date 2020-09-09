package go.nvhieucs.notins;

import go.nvhieucs.notins.model.ApplicationUser;
import go.nvhieucs.notins.model.Follow;
import go.nvhieucs.notins.repository.FollowRepository;
import go.nvhieucs.notins.repository.FollowerByFollowingRepository;
import go.nvhieucs.notins.repository.PhotoRepository;
import go.nvhieucs.notins.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotinsApplication implements CommandLineRunner {


	@Autowired
	private FollowRepository followRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FollowerByFollowingRepository followingRepository;

	@Autowired
	private PhotoRepository photoRepository;


	@Override
	public void run(String... args) throws Exception {
		//ApplicationUser user1 = userRepository.insert(new ApplicationUser("Nguyen Van Hieu", "nvhieu4899@gmail.com", null,null));
		//ApplicationUser user2 = userRepository.insert(new ApplicationUser("Nguyen Van Hieu Dep Trai", "elpulga246@gmail.com", null,null));


		//followRepository.insert(new Follow(user1.getUserId(),user2.getUserId(),user1.getUsername(),user2.getUsername()));

		//System.out.println(followingRepository.findAll());
	}

	public static void main(String[] args) {
		SpringApplication.run(NotinsApplication.class, args);
	}

}
