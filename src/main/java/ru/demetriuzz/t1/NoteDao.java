package ru.demetriuzz.t1;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.demetriuzz.t1.jooq.notes.t1_schema.tables.pojos.TNote;

import java.util.List;

import static ru.demetriuzz.t1.jooq.notes.t1_schema.tables.TNote.T_NOTE;

@Repository
@AllArgsConstructor
public class NoteDao {

    private final DSLContext context;

    public void insert(TNote note) {
        var id = context.insertInto(T_NOTE)
                .set(T_NOTE.C_DAY, note.getCDay())
                .set(T_NOTE.C_TIME, note.getCTime())
                .set(T_NOTE.C_TODO, note.getCTodo())
                .returningResult(T_NOTE.C_ID)
                .fetchOne(T_NOTE.C_ID);
        note.setCId(id);
    }

    public void update(TNote note) {
        context.update(T_NOTE)
                .set(T_NOTE.C_DAY, note.getCDay())
                .set(T_NOTE.C_TIME, note.getCTime())
                .set(T_NOTE.C_TODO, note.getCTodo())
                .where(T_NOTE.C_ID.eq(note.getCId()))
                .execute();
    }

    public void delete(Long id) {
        context.delete(T_NOTE)
                .where(T_NOTE.C_ID.eq(id))
                .execute();
    }

    public List<TNote> getNotes() {
        return context.select()
                .from(T_NOTE)
                .fetchInto(TNote.class);
    }

}
