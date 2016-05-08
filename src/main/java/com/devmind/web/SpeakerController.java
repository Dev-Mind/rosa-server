package com.devmind.web;

import java.util.List;
import java.util.stream.Collectors;

import com.devmind.dto.MemberDto;
import com.devmind.repository.SpeakerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Conference speakers",
        description = "Speaker Api helps you to find all the conference speakers")
@RestController
@RequestMapping("/api/speaker")
public class SpeakerController {

    @Autowired
    SpeakerRepository speakerRepository;

    @RequestMapping("/{id}")
    @ApiOperation(value = "Finds one member", httpMethod = "GET")
    public ResponseEntity<MemberDto> getMember(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .body(MemberDto.convert(speakerRepository.findOne(id)));
    }

    @RequestMapping
    @ApiOperation(value = "Finds all speakers", httpMethod = "GET")
    public List<MemberDto> getAllMembers() {
        return speakerRepository.findAllSpeakers().stream().map(MemberDto::convert).collect(Collectors.toList());
    }
}