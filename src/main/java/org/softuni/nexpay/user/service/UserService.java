package org.softuni.nexpay.user.service;

import jakarta.transaction.Transactional;
import org.softuni.nexpay.exception.EmailAlreadyExistException;
import org.softuni.nexpay.exception.UsernameAlreadyExistException;
import org.softuni.nexpay.security.AuthenticationMetadata;
import org.softuni.nexpay.subscription.service.SubscriptionService;
import org.softuni.nexpay.user.entity.User;
import org.softuni.nexpay.user.entity.UserRole;
import org.softuni.nexpay.user.repository.UserRepository;
import org.softuni.nexpay.wallet.service.WalletService;
import org.softuni.nexpay.web.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final WalletService walletService;
    private final SubscriptionService subscriptionService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       WalletService walletService,
                       SubscriptionService subscriptionService,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.walletService = walletService;
        this.subscriptionService = subscriptionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegisterRequest registerRequest) {

        User registerUser = userRepository.save(InitializeUser(registerRequest));

        registerUser.setWallets(List.of(walletService.createNewWallet(registerUser)));
        registerUser.setSubscriptions(List.of(subscriptionService.createNewSubscription(registerUser)));
    }

    private User InitializeUser(RegisterRequest registerRequest) {

        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistException("User with this [%s] username already exists.".formatted(registerRequest.getUsername()));
        } else if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException("User with this [%s] email already exists.".formatted(registerRequest.getEmail()));
        }

        return User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .isActive(true)
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .userRole(UserRole.USER)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return new AuthenticationMetadata(user.getId(), user.getUsername(), user.getPassword(), UserRole.USER, user.isActive());
    }

    public User getById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User with id [%s] not found".formatted(userId)));
    }
}
