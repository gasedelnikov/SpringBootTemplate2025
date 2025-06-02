--   password = hash("password")
INSERT INTO ${defaultSchema}.usr (login, password, description, status, created_user_id, updated_user_id)
SELECT 'admin', '$2a$10$GLuIgQXoSMST1LecVfQ9HePcoFENt30J3L6axd7fiE8Fk.7NALmrW', 'admin', 'registered', null, null
ON CONFLICT (login) DO UPDATE SET description     = EXCLUDED.description,
                                  updated_user_id = EXCLUDED.updated_user_id;

DO
$$
    DECLARE
        v_user_admin_id ${defaultSchema}.usr.id%TYPE;
    BEGIN
        SELECT id INTO v_user_admin_id FROM ${defaultSchema}.usr WHERE login = 'admin';

--   password = hash("password")
        INSERT INTO ${defaultSchema}.usr (login, password, description, status, created_user_id, updated_user_id)
        SELECT 'username',
               '$2a$10$GLuIgQXoSMST1LecVfQ9HePcoFENt30J3L6axd7fiE8Fk.7NALmrW',
               'username',
               'registered',
               v_user_admin_id,
               v_user_admin_id
        ON CONFLICT (login) DO UPDATE SET description     = EXCLUDED.description,
                                          updated_user_id = EXCLUDED.updated_user_id;


--   *********************************************************8
        INSERT INTO ${defaultSchema}.author (type, fio, created_user_id, updated_user_id)
        VALUES ('a1', 'author a1', v_user_admin_id, v_user_admin_id);

        INSERT INTO ${defaultSchema}.author (type, fio, created_user_id, updated_user_id)
        VALUES ('b2', 'author b2', v_user_admin_id, v_user_admin_id);

        INSERT INTO ${defaultSchema}.author (type, fio, created_user_id, updated_user_id)
        VALUES ('b1', 'author b1', v_user_admin_id, v_user_admin_id);

--   *********************************************************8

        INSERT INTO ${defaultSchema}.book (author_id, name, description, created_user_id, updated_user_id)
        SELECT id,
               'book_' || id || '_' || (random() * 100000)::int,
               'description_' || gen_random_uuid(),
               v_user_admin_id,
               v_user_admin_id
        FROM author a, generate_series(1, id * 10);


    END
$$;
