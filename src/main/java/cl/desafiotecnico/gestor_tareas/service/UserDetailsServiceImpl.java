package cl.desafiotecnico.gestor_tareas.service;

import cl.desafiotecnico.gestor_tareas.entity.User;
import cl.desafiotecnico.gestor_tareas.repository.UserRepository;
import cl.desafiotecnico.gestor_tareas.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads the user by email and returns a Spring Security User with the BCrypt-hashed password.
     *
     * @param email the email used as username.
     * @return UserDetails containing the user data.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new UserPrincipal(user);
    }

}
