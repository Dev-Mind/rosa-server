package com.devmind.web;

import java.util.List;
import java.util.stream.Collectors;

import com.devmind.dto.SessionDto;
import com.devmind.repository.SessionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * In the public API we expose only accepted sessions
 */
@Api(value = "Conference sessions",
        description = "Session Api helps you to find all the sessions of the conference")
@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    SessionRepository sessionRepository;

    @RequestMapping("/{id}")
    @ApiOperation(value = "Finds one session", httpMethod = "GET")
    public ResponseEntity<SessionDto> getSession(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .body(SessionDto.convert(sessionRepository.findOne(id)));
    }

    @RequestMapping
    @ApiOperation(value = "Finds all sessions", httpMethod = "GET")
    public List<SessionDto> getAllSessions() {
        return sessionRepository.findAllSessions().stream().map(SessionDto::convert).collect(Collectors.toList());
    }

}
