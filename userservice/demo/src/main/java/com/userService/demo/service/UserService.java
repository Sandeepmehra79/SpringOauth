package com.userService.demo.service;

import com.userService.demo.dto.ResponseDto;
import com.userService.demo.dto.UserDto.UpdateUserPasswordDto;
import com.userService.demo.exception.RoleNotFoundException;
import com.userService.demo.exception.UserNotFoundException;
import com.userService.demo.mapper.UserToUserResponseDto;
import com.userService.demo.dto.UserDto.UserRequestDto;
import com.userService.demo.dto.UserDto.UserResponseDto;
import com.userService.demo.model.Role;
import com.userService.demo.model.User;
import com.userService.demo.repository.RoleRepository;
import com.userService.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final User user;

    @Autowired
    public UserService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, User user){
        this.roleRepository = roleRepository;
        this.userRepository= userRepository;
        this.passwordEncoder = passwordEncoder;
        this.user = user;
    }
    public UserResponseDto createUser(UserRequestDto userRequestDto) throws RoleNotFoundException{
        // creating a user
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        // checking if the provided roles exist or not
        // adding them to the relevant list of
        Set<String> nonExistingRoles = new HashSet<>();
        Set<Role> existingRoles = new HashSet<>();
        userRequestDto.getRoles()
                .forEach(roleName -> {
                    Optional<Role> optional = roleRepository.findRoleByName(roleName);
                    if (optional.isPresent()) {
                        existingRoles.add(optional.get());
                    } else {
                        nonExistingRoles.add(roleName);
                    }
                }
        );
        // if we have anything in the nonExisting role we will inform the user about the non-existing role/s
        if(!nonExistingRoles.isEmpty()){
            throw new RoleNotFoundException("The role(s) "+ String.join(", ", nonExistingRoles)+" don't exist please verify the list of role(s) and try again");
        }

        user.setRoles(existingRoles);
        // saving the user to db
        // also we are converting the
        UserResponseDto userResponseDto = UserToUserResponseDto
                .convert(userRepository.save(user));
        userRepository.save(user);
        return userResponseDto;
    }

    public UserResponseDto getUserDetails(Long id) throws UserNotFoundException {
        Optional<User> optional =  userRepository.findById(id);
        if(optional.isPresent()){
            return UserToUserResponseDto.convert(optional.get());
        }
        throw new UserNotFoundException("The user with id "+id+" not found.");
    }

    public UserResponseDto setUserRoles(Long userId, List<Long> roleIds) {
        Optional<User> userOptional = userRepository.findById(userId);
        List<Role> roles = roleRepository.findAllByIdIn(roleIds);

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        user.setRoles(Set.copyOf(roles));

        User savedUser = userRepository.save(user);

        return UserToUserResponseDto.convert(savedUser);
    }

    public List<UserResponseDto> findAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserToUserResponseDto::convert).toList();
    }

    public UserResponseDto updatePassword(UpdateUserPasswordDto updateUserPasswordDto){
        User user = userRepository.findByUsername(updateUserPasswordDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("The user with username "+ updateUserPasswordDto.getUsername()+ " not found."));
        System.out.println(user.getUsername());
        user.setPassword(updateUserPasswordDto.getPassword());
        userRepository.save(user);
        return UserToUserResponseDto.convert(user);
    }
    @Transactional
    public ResponseDto deleteUser(String username){
        userRepository.deleteByUsername(username);
        ResponseDto responseDto = new  ResponseDto();
        responseDto.setMessage("Successfully deleted the user with username "+ username);
        return responseDto;
    }


}
