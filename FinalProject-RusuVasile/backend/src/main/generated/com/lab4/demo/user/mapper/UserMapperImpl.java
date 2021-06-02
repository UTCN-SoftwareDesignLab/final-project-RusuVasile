package com.lab4.demo.user.mapper;

import com.lab4.demo.user.dto.UserListDTO;
import com.lab4.demo.user.dto.UserMinimalDTO;
import com.lab4.demo.user.model.User;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-02T08:57:00+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserMinimalDTO userMinimalFromUser(User user) {
        if ( user == null ) {
            return null;
        }

        UserMinimalDTO userMinimalDTO = new UserMinimalDTO();

        userMinimalDTO.setName( user.getUsername() );
        userMinimalDTO.setId( user.getId() );

        return userMinimalDTO;
    }

    @Override
    public UserListDTO userListDtoFromUser(User user) {
        if ( user == null ) {
            return null;
        }

        String email = null;

        email = user.getEmail();

        Set<String> roles = null;

        UserListDTO userListDTO = new UserListDTO( email, roles );

        userListDTO.setName( user.getUsername() );
        userListDTO.setId( user.getId() );

        populateRoles( user, userListDTO );

        return userListDTO;
    }
}
