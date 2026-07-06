package com.siddu.auth.service;

import com.siddu.auth.Enums.RoleName;
import com.siddu.auth.dto.Requests.RoleAssignRequest;
import com.siddu.auth.dto.Response.RolesResponse;
import com.siddu.auth.dto.Response.UserStatusResponse;
import com.siddu.auth.entity.RoleEntity;
import com.siddu.auth.entity.UserEntity;
import com.siddu.auth.entity.UserRoleEntity;
import com.siddu.auth.exception.ResourceNotFoundException;
import com.siddu.auth.repository.RoleRepository;
import com.siddu.auth.repository.UserRepository;
import com.siddu.auth.repository.UserRoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserAccessManagementService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    @PersistenceContext
    private EntityManager entityManager;


    public UserAccessManagementService(UserRepository userRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;


    }


    @Transactional(readOnly = true)
    public Page<UserStatusResponse> getUsers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<UserEntity> usersPage = userRepository.findAll(pageable);

        List<UUID> userIds = usersPage.stream()
                .map(UserEntity::getId)
                .toList();

        List<UserRoleEntity> userRoles =
                userRoleRepository.findByUser_IdIn(userIds);

        Map<UUID, List<String>> rolesMap =
                userRoles.stream()
                        .collect(Collectors.groupingBy(
                                ur -> ur.getUser().getId(),
                                Collectors.mapping(
                                        ur -> ur.getRole().getName().name(),
                                        Collectors.toList()
                                )
                        ));

        return usersPage.map(user -> UserStatusResponse.builder()
                .email(user.getEmail())
                .status(String.valueOf(user.getStatus()))
                .isEmailVerified(user.isEmailVerified())
                .roles(rolesMap.getOrDefault(user.getId(), List.of())).build());
    }

    public static RoleName fromString(String role) {
        try {
            return RoleName.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Role Not Found: " + role);
        }
    }


    public RolesResponse assignRole(@RequestBody RoleAssignRequest request) {


        UUID userId=userRepository.findIdByEmail(request.getEmail()).orElseThrow(()->new ResourceNotFoundException("User not found"));

        RoleName rolename=fromString(request.getRoles());
        long roleId = roleRepository.findIdByName(rolename)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        if (userRoleRepository.existsByUserIdAndRoleId(userId, roleId)) {
            throw new IllegalStateException("Role already assigned to user");
        }
        UserEntity userRef = entityManager.getReference(UserEntity.class, userId);
        RoleEntity roleRef = entityManager.getReference(RoleEntity.class, roleId);

        UserRoleEntity userRole = UserRoleEntity.builder()
                .user(userRef)
                .role(roleRef)
                .build();
        userRoleRepository.save(userRole);
        return new RolesResponse("role assigned successfully",rolename.name());

    }
}
