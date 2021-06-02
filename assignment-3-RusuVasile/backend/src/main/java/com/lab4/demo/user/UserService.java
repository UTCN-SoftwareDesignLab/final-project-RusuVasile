package com.lab4.demo.user;

import com.lab4.demo.user.dto.UserDTO;
import com.lab4.demo.user.dto.UserListDTO;
import com.lab4.demo.user.dto.UserMinimalDTO;
import com.lab4.demo.user.mapper.UserMapper;
import com.lab4.demo.user.model.ERole;
import com.lab4.demo.user.model.Role;
import com.lab4.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    public User findByName(String name){
        User doctor= userRepository.findByUsername(name)
                .orElseThrow(() -> new RuntimeException("Not found"));
        for (Role role : doctor.getRoles())
            if (role.getName() == ERole.DOCTOR)
                return doctor;

        throw new RuntimeException("No doctor found");
    }


    public UserDTO create(UserDTO userDTO){
        if(!userRepository.existsByEmail(userDTO.getEmail()) || !userRepository.existsByUsername(userDTO.getUsername())) {
            Set<Role> roles = new HashSet<>();
            Role defaultRole = roleRepository.findByName(ERole.valueOf(userDTO.getRole())).orElseThrow(() -> new RuntimeException("Cannot find " + userDTO.getRole().toString() + " role"));
            roles.add(defaultRole);
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .email(userDTO.getEmail())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .roles(roles)
                    .build();
            userRepository.save(user);
            return userDTO;
        }
        else{

            throw new RuntimeException("User already exists");
        }

    }

    public UserDTO edit (UserDTO userDTO){
        if(!userRepository.existsByEmail(userDTO.getEmail()) || !userRepository.existsByUsername(userDTO.getUsername())) {
            Set<Role> roles = new HashSet<>();
            Role defaultRole = roleRepository.findByName(ERole.valueOf(userDTO.getRole())).orElseThrow(() -> new RuntimeException("Cannot find " + userDTO.getRole().toString() + " role"));
            roles.add(defaultRole);
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .email(userDTO.getEmail())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .roles(roles)
                    .build();
            userRepository.save(user);
            return userDTO;
        }
        else{

            throw new RuntimeException("User already exists");
        }

    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

}
