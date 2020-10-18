package pl.spring_security_sfa.demo.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.spring_security_sfa.demo.MailService;
import pl.spring_security_sfa.demo.model.Token;
import pl.spring_security_sfa.demo.model.User;
import pl.spring_security_sfa.demo.repo.TokenRepo;
import pl.spring_security_sfa.demo.repo.UserRepo;
import javax.mail.MessagingException;
import java.util.UUID;
@Service
public class UserService {
    private MailService mailService;
    private TokenRepo tokenRepo;
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, TokenRepo tokenRepo, MailService mailService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
        this.mailService = mailService;
    }
    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepo.save(user);
        sendToken(user);
    }
    private void sendToken(User user){
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setUser(user);
        tokenRepo.save(token);
        String link  ="http://localhost:8080/token?value=" + tokenValue;
        try {
            mailService.sendMail(user.getEmail(), "confirm it", link, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
