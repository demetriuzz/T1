package ru.demetriuzz.t1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.demetriuzz.t1.jooq.notes.t1_schema.tables.pojos.TNote;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
class T1ApplicationTests {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private NoteDao repo;

    @Test
    void contextLoads() {
        TNote note = new TNote(null, LocalDate.of(2023, 7, 1), LocalTime.of(10, 0, 0), "todo 1");
        repo.insert(note);
        assertThat(note.getCId()).isNotZero();

        assertThat(repo.getNotes()).isEqualTo(List.of(note));
    }

}
