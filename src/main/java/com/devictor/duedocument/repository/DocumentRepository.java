package com.devictor.duedocument.repository;

import com.devictor.duedocument.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
