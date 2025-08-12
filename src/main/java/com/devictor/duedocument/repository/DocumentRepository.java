package com.devictor.duedocument.repository;

import com.devictor.duedocument.entity.Document;
import com.devictor.duedocument.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    void deleteByUser(User user);
    Page<Document> findByUser(User user, Pageable pageable);
}
