package pl.spring_security_sfa.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.spring_security_sfa.demo.model.Token;
@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {
    Token findByValue(String value);
}