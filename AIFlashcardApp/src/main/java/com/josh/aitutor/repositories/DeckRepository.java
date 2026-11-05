package com.josh.aitutor.repositories;

import com.josh.aitutor.models.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeckRepository extends JpaRepository<Deck,Long>{
    List<Deck> findByIsDefault(boolean isDefault);

    // uses built in JpaRepository methods
    // finds one deck by topic name, excludes decks already studied, and orders by lowest id
    // this means the user will find the next deck they have not studied for that topic
    Optional<Deck> findFirstByTopicAndIdNotInOrderByIdAsc(String topic, List<Long> exclusionList);

    @Query("SELECT DISTINCT d.topic FROM Deck d") // manual query to return Strings not decks
    List <String> findDistinctTopics();
}
