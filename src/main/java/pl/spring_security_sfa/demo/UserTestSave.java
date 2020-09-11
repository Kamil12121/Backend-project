//package pl.spring_security_sfa.demo;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//@Configuration
//public class UserTestSave {
//
//    public UserTestSave(UserRepo userRepo, PasswordEncoder passwordEncoder) {
//        User userMaciek = new User();
//        userMaciek.setUsername("Maciek");
//        userMaciek.setPassword(passwordEncoder.encode("Maciek123"));
//        userMaciek.setRole("ROLE_ADMIN");
//
//        User userKarmel  = new User();;
//        userKarmel.setUsername("Karmel");
//            userKarmel.setPassword(passwordEncoder.encode("Karmel123"));
//            userKarmel.setRole("ROLE_USER");
//
//      userRepo.save(userKarmel);
//        userRepo.save(userMaciek);
//    }
//}
