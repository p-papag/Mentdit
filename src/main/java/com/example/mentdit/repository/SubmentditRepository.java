package com.example.mentdit.repository;

import com.example.mentdit.model.Submentdit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubmentditRepository extends JpaRepository<Submentdit, Long> {
    Optional<Submentdit> findByName(String submentditName);
}
