package com.siddu.auth.entity;

import com.siddu.auth.Enums.RoleName;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;



@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(columnDefinition = "uuid", updatable = false, nullable = false)
        private UUID id;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false, unique = true)
        private RoleName name;

        @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
        private Set<UserRoleEntity> users = new HashSet<>();
    }

