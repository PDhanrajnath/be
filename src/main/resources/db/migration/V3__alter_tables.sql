ALTER TABLE organisations
    ALTER COLUMN id
        SET DEFAULT uuid_generate_v4 ();

ALTER TABLE jobs
    ALTER COLUMN id
        SET DEFAULT uuid_generate_v4 ();

ALTER TABLE user_job_mappings
    ALTER COLUMN id
        SET DEFAULT uuid_generate_v4 ();