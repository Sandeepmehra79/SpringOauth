package com.userService.demo.service;

import com.userService.demo.dto.UserDto.UserResponseDto;
import com.userService.demo.model.User;
import com.userService.demo.repository.UserRepository;
import com.userService.demo.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserInfoService implements UserDetailsService {
    private UserRepository userRepository;
    private UserDetailsImpl userDetailsImpl;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with name "+username+ " not found."));
// converting user to UserDetails
        return userRepository.findByUsername(username).map(UserDetailsImpl:: new ).orElseThrow(() -> new UsernameNotFoundException("The user with username "+username + " not found."));
    }
}
