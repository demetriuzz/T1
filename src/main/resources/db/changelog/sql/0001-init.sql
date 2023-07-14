CREATE SCHEMA IF NOT EXISTS t1_schema;

CREATE TABLE IF NOT EXISTS t1_schema.t_note (
    c_id BIGSERIAL,
    c_day DATE,
    c_time TIME,
    c_todo VARCHAR,
    PRIMARY KEY (c_id)
);

COMMENT ON TABLE t1_schema.t_note IS 'Таблица записей - ежедневник';
COMMENT ON COLUMN t1_schema.t_note.c_id IS 'Уникальный идентификатор записи';
COMMENT ON COLUMN t1_schema.t_note.c_day IS 'Когда сделать';
COMMENT ON COLUMN t1_schema.t_note.c_time IS 'Во сколько сделать';
COMMENT ON COLUMN t1_schema.t_note.c_todo IS 'Что сделать';
