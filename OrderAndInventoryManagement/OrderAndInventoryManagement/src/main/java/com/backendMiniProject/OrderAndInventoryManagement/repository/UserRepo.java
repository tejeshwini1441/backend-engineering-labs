package com.backendMiniProject.OrderAndInventoryManagement.repository;

import com.backendMiniProject.OrderAndInventoryManagement.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@NonNull @Email @NotBlank String email);
}
