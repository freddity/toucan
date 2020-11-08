package com.example.toucan.repository;

import com.example.toucan.model.entity.EntityNote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface RepositoryNote extends JpaRepository<EntityNote, UUID> {

    EntityNote findByUuid(UUID uuid);

    @Query("SELECT u FROM EntityNote u WHERE 'owner' = :userid ORDER BY 'creation_date' DESC")
    ArrayList<EntityNote> takeForShortNotes(@Param("userid") String userId,
                                            Pageable pageable);

}