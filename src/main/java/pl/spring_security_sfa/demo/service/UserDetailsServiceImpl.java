package pl.spring_security_sfa.demo.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.spring_security_sfa.demo.repo.UserRepo;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepo userRepo;
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s).get();
    }
}