CREATE TYPE skills AS ENUM('PRODUCT_MANAGER', 'UX_DESIGNER', 'UI_DEVELOPER', 'SOFTWARE_ENGINEER', 'PYTHON_DEVELOPER', 'JAVA_DEVELOPER', 'PROJECT_MANAGER', 'HR_MANAGER', 'JAVASCRIPT_DEVELOPER');

CREATE TYPE job_type AS ENUM('FULL_TIME','INTERNSHIP','CONTRACT','REMOTE');

CREATE TYPE experience_level AS ENUM('FRESHER','MID_LEVEL','EXECUTIVE','DIRECTOR');

CREATE TABLE organisations
(
    id                   uuid   NOT NULL,
    name                 varchar(50) NOT NULL,
    description          text NOT NULL,
    website_url          varchar(50) NOT NULL,
    logo_url             text NOT NULL,
    headquarter_address  text NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE jobs
(
    id                   uuid   NOT NULL,
    title                varchar(50) NOT NULL,
    is_active            boolean NOT NULL,
    date_posted          date NOT NULL,
    description          text NOT NULL,
    skills               skills[] NOT NULL,
    requirements         text[] NOT NULL,
    address              text NOT NULL,
    location             geometry NOT NULL,
    company_id           uuid NOT NULL,
    is_green_commute     boolean NOT NULL,
    job_type             job_type NOT NULL,
    experience_level     experience_level NOT NULL,
    location_point       double precision[] NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES organisations(id)
);

CREATE TABLE user_job_mappings
(
    id                    uuid   NOT NULL,
    job_id                uuid   NOT NULL,
    user_id               uuid   NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (job_id) REFERENCES jobs(id)
);

CREATE INDEX location_geom_idx ON jobs USING GIST (location);

