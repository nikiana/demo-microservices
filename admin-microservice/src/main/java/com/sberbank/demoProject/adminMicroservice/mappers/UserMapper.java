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

/**
 * Маппинг ДТО классов в entity и обратно с помощью mapstruct (автогенерирует реализацию на основе описанного интерфейса)
 * Добавляем (componentModel = "spring"), чтобы сгенерированный класс имел аннотацию @Component и автоматически подцеплялся
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Маппинг из User в UserResponse
     */
    @Mapping(target = "roles", expression = "java(rolesToEnum(user.getRoles()))")
    UserResponse toDto(User user);

    /**
     * Отдельно прописывается маппинг для коллекций сущностей
     */
    List<UserResponse> toDtos(List<User> users);

    /**
     * Маппинг из UserRequest в User
     */
    @Mapping(target = "roles", source = "roles")
    User toEntity(UserRequest userRequest, Set<Role> roles);

    /**
     * Вспомогательный класс, который маппит сет ролей-entity в сет ролей-enum
     */
    default Set<RoleEnum> rolesToEnum(Set<Role> roles) {
        return roles.stream().map(r -> RoleEnum.valueOf(r.getName())).collect(Collectors.toSet());
    }
}
