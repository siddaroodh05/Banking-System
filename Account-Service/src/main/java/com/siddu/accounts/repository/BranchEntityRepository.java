package com.siddu.accounts.repository;

import com.siddu.accounts.Entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BranchEntityRepository extends JpaRepository<BranchEntity, UUID> {
    Optional<BranchEntity> findByIfscCode(String ifscCode);
    boolean existsByBranchName(String branchName);
    boolean existsByIfscCode(String ifscCode);
    boolean existsByAddressLineAndPincode(String addressLine, String pincode);


}
