package com.devmind.repository;

import java.util.List;

import com.devmind.model.member.Speaker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * {@link com.devmind.model.member.Member}
 */
public interface SpeakerRepository extends CrudRepository<Speaker, Long> {

    @Query(value = "SELECT DISTINCT m FROM Speaker m inner join m.sessions s")
    List<Speaker> findAllSpeakers();

}
