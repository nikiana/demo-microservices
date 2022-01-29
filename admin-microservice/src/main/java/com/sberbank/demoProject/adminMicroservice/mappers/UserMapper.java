package com.sberbank.demoProject.adminMicroservice.mappers;

import com.sberbank.demoProject.adminMicroservice.enums.RoleEnum;
import com.sberbank.demoProject.adminMicroservice.models.entities.Role;
import com.sberbank.demoProject.adminMicroservice.models.entities.User;
import com.sberbank.demoProject.adminMicroservice.models.requests.UserRequest;
import com.sberbank.demoProject.adminMicroservice.models.responces.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(rolesToEnum(user.getRoles()))")
    UserResponse toDto(User user);

    List<UserResponse> toDtos(List<User> users);

    @Mapping(target = "roles", source = "roles")
    User toEntity(UserRequest userRequest, Set<Role> roles);

    default Set<RoleEnum> rolesToEnum(Set<Role> roles) {
        return roles.stream().map(r -> RoleEnum.valueOf(r.getName())).collect(Collectors.toSet());
    }
}
