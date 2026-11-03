package com.josh.aitutor.repositories;

import com.josh.aitutor.models.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DeckRepository extends JpaRepository<Deck,Long>{
    List<Deck> findByIsDefault(boolean isDefault);

    Optional<Deck> findFirstByTopicAndIdNotInOrderByIdAsc(String topic, List<Long> exclusionList);
}
