/* Stored procedure for retrieving a list of jobs */
CREATE OR REPLACE FUNCTION fn_fetch_jobs(is_green_commute boolean, skills skills, job_type job_type, experience_level experience_level, geography varchar, distance int) RETURNS SETOF jobs AS
$$
DECLARE
query_string varchar;
BEGIN
        query_string := 'SELECT * FROM jobs WHERE 1=1';

        IF is_green_commute is not null and is_green_commute = true
        THEN query_string := query_string || format(' AND is_green_commute = %L', is_green_commute);
        END IF;

        IF skills is not null
        THEN query_string := query_string || format(' AND %L = ANY (skills)', skills);
        END IF;

        IF job_type is not null
        THEN query_string := query_string || format(' AND job_type = %L', job_type);
        END IF;

        IF experience_level is not null
        THEN query_string := query_string || format(' AND experience_level = %L', experience_level);
        END IF;

        IF geography is not null AND distance is not null
        THEN query_string := query_string || format(' AND ST_DWithin(location::geography,ST_GeomFromText(%L,4326)::geography,%L)', geography, distance);
        END IF;

RETURN QUERY EXECUTE query_string;
END
$$ LANGUAGE plpgsql;