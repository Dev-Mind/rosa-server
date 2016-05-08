package com.devmind.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.devmind.model.session.Session;
import com.devmind.model.session.Vote;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

/**
 * Member DTO for API HATEOAS
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class SessionDto extends ResourceSupport {

    private long idSession;
    public int votes = 0;
    public int positiveVotes = 0;
    public long nbConsults;
    public String lang;
    public String format;
    public String title;
    public String summary;
    public String description;
    public String ideaForNow;
    public String room;
    public String year;
    public String start;
    public String end;
    public List<MemberDto> speakers = new ArrayList<>();

    public static SessionDto convert(Session session) {
        SessionDto dto = new SessionDto()
                .setIdSession(session.getId())
                .setDescription(session.getDescription())
                .setSummary(session.getSummary())
                .setTitle(session.getTitle())
                .setEnd(session.getEnd()==null ? null : session.getEnd().format(DateTimeFormatter.ISO_DATE_TIME))
                .setStart(session.getStart()==null ? null : session.getStart().format(DateTimeFormatter.ISO_DATE_TIME));

        List<Vote> votes = session.getVotes();
        if (!votes.isEmpty()) {
            dto.setVotes(((Long) session.getVotes().stream().distinct().count()).intValue());
            dto.setPositiveVotes(session.getPositiveVotes());
        }
        return dto;
    }

    public Session toSession() {
        return new Session()
                .setId(idSession)
                .setDescription(description)
                .setSummary(summary)
                .setTitle(title)
                .setEnd(end!=null ? LocalDateTime.parse(end, DateTimeFormatter.ISO_DATE_TIME): null)
                .setStart(start!=null ? LocalDateTime.parse(start, DateTimeFormatter.ISO_DATE_TIME) : null);
    }

    public long getIdSession() {
        return idSession;
    }

    public SessionDto setIdSession(long idSession) {
        this.idSession = idSession;
        return this;
    }


    public String getRoom() {
        return room;
    }

    public SessionDto setRoom(String room) {
        this.room = room;
        return this;
    }

    public String getStart() {
        return start;
    }

    public SessionDto setStart(String start) {
        this.start = start;
        return this;
    }

    public String getEnd() {
        return end;
    }

    public SessionDto setEnd(String end) {
        this.end = end;
        return this;
    }

    public int getVotes() {
        return votes;
    }

    public SessionDto setVotes(int votes) {
        this.votes = votes;
        return this;
    }

    public int getPositiveVotes() {
        return positiveVotes;
    }

    public SessionDto setPositiveVotes(int positiveVotes) {
        this.positiveVotes = positiveVotes;
        return this;
    }

    public long getNbConsults() {
        return nbConsults;
    }

    public SessionDto setNbConsults(long nbConsults) {
        this.nbConsults = nbConsults;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public SessionDto setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public SessionDto setFormat(String format) {
        this.format = format;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SessionDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public SessionDto setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SessionDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getIdeaForNow() {
        return ideaForNow;
    }

    public SessionDto setIdeaForNow(String ideaForNow) {
        this.ideaForNow = ideaForNow;
        return this;
    }

    public String getYear() {
        return year;
    }

    public SessionDto setYear(String year) {
        this.year = year;
        return this;
    }

    public SessionDto addSpeaker(MemberDto memberDto) {
        speakers.add(memberDto);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SessionDto that = (SessionDto) o;
        return Objects.equals(idSession, that.idSession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idSession);
    }
}
