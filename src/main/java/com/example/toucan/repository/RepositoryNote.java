package com.example.toucan.repository;

import com.example.toucan.model.entity.EntityNote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositoryNote extends JpaRepository<EntityNote, UUID> {

    EntityNote findByUuid(String uuid);

    @Query("SELECT u FROM EntityNote u WHERE u.owner = :userid ORDER BY u.creationTimestamp DESC")
    List<EntityNote> takeForShortNotes(@Param("userid") String userId,
                                       Pageable pageable);

}