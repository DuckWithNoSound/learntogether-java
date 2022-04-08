package learntogether.Service;

import learntogether.DTO.MyUser;
import learntogether.Entity.UserEnity;
import learntogether.Repository.UserRepository;
import learntogether.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
  Created by Luvbert
*/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEnity userEnity = userRepository.findOneByUsernameAndStatus(username, SystemConstant.ACTIVE_STATUS);

        if(userEnity == null){
            throw new UsernameNotFoundException("User with username: \'" + username + "\' not found!");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userEnity.getRole().getId() + ""));
        // Put user's information to spring security store
        MyUser user = new MyUser(userEnity.getUsername(),userEnity.getPassword(), true, true, true, true, authorities);
        user.setFullname(userEnity.getFullname());
        user.setAvatar(userEnity.getAvatar());
        user.setUserQuote(userEnity.getUserQuote());
        user.setPhoneNumber(userEnity.getPhoneNumber());
        user.setEmail(userEnity.getEmail());
        return user;
    }
}
