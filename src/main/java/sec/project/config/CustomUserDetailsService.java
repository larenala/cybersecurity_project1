package sec.project.config;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sec.project.repository.AccountRepository;
import sec.project.domain.Account;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Account account;
    @Autowired
    private AccountRepository accountRepository;
    

    @PostConstruct
    public void init() {
        // Creating a new account with default configuration
        Account account = new Account("First", "Last", "user", "password", "12345678910");
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

    // Flaw 3: Automatically granting admin-rights for all users
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
    }
}
