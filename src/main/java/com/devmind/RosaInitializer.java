package com.devmind;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;

import com.devmind.dto.MemberDto;
import com.devmind.dto.SessionDto;
import com.devmind.model.member.Member;
import com.devmind.model.session.Session;
import com.devmind.repository.SessionRepository;
import com.devmind.repository.SpeakerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dev-Mind <guillaume@dev-mind.fr>
 * @since 03/04/16.
 */
@Component
@Transactional
public class RosaInitializer {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SpeakerRepository speakerRepository;

    protected <T> List<T> fromJson(String filename, Class<T> className) {
        Resource file = resourceLoader.getResource("classpath:raw/" + filename);

        try {
            return objectMapper.readValue(file.getFile(), objectMapper.getTypeFactory().constructCollectionType(List.class, className));
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostConstruct
    public void init() {
        List<MemberDto> members = fromJson("speakers.json", MemberDto.class);

        members
                .stream()
                .map(MemberDto::toSpeaker)
                .forEach(speaker -> speakerRepository.save(speaker));

        List<SessionDto> sessions = fromJson("sessions.json", SessionDto.class);

        sessions
                .stream()
                .forEach(s -> {
                    //A session is created
                    Session persistedSession = sessionRepository.save(s.toSession());
                    //We add the links (many to many so after the first save)
                    s.speakers.forEach(sp ->
                            persistedSession.addSpeaker(speakerRepository.findOne(sp.getIdMember()))
                    );
                    sessionRepository.save(persistedSession);
                });
    }
}
