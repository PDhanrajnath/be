INSERT INTO organisations (id, name, description, website_url, logo_url,headquarter_address)
VALUES ('e106083b-6b6f-4fa1-8148-912588bee7f8','Zemoso','Company description','www.zemoso.com','https://assets.website-files.com/5e700f53a406cc1c7cd6a814/5e700f53a406cc2ae8d6a892_zemoso_logo-p-500.png','Raidurg, Hyderabad, Telangana');

INSERT INTO organisations (id, name, description, website_url, logo_url,headquarter_address)
VALUES ('742949f4-1956-4926-a168-738381b1dc6e','BMW','Company description','www.bmw.com','https://m.media-amazon.com/images/I/61b2FkbZsEL._AC_SS350_.jpg','Raidurg, Hyderabad, Telangana');

INSERT INTO organisations (id, name, description, website_url, logo_url,headquarter_address)
VALUES ('b8d91763-03b0-470d-86fe-f50b6aca13fc','HP', 'Company description','www.hp.com','https://upload.wikimedia.org/wikipedia/commons/thumb/a/ad/HP_logo_2012.svg/120px-HP_logo_2012.svg.png','Raidurg, Hyderabad, Telangana');

INSERT INTO organisations (id, name, description, website_url, logo_url,headquarter_address)
VALUES ('33e8bc77-801f-458a-a886-6d4e45375204','Facebook', 'Company description','www.fb.com','https://facebookbrand.com/wp-content/uploads/2019/04/f_logo_RGB-Hex-Blue_512.png?w=512&h=512','Raidurg, Hyderabad, Telangana');

INSERT INTO organisations (id, name, description, website_url, logo_url,headquarter_address)
VALUES ('3667a2b1-0024-4226-a671-7bd659e42db1','Google', 'Company description','www.google.com','https://www.freepnglogos.com/uploads/google-logo-png/google-logo-icon-png-transparent-background-osteopathy-16.png','Raidurg, Hyderabad, Telangana');


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('f74cdff8-34d9-4d00-aaa3-eee4e99cd225', 'User Interface Designer', true, '2021-01-01',
        'Zemoso is seeking a talented, personable interaction designer who can assist the User Experience Design team by working with other designers and development teams on a variety of projects. Zemosos User Experience Design group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['UX_DESIGNER']::skills[],ARRAY['High level of proficiency with leading UX Design software packages, such as Axure, Sketch, InVision", " Experience Design including the core Adobe Creative Suite products.'],
        'Raidurg, Hyderabad, Telangana', POINT(17.4178,78.3890)::geometry, 'e106083b-6b6f-4fa1-8148-912588bee7f8', true, 'FULL_TIME', 'MID_LEVEL',ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('e127b816-0c07-4821-b0d7-87a9029e45b7', 'Product Manager', true, '2021-01-01',
        'BMW is seeking a talented, personable interaction manager who can assist the team by working with other designers and development teams on a variety of projects. BMW development group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['PRODUCT_MANAGER','PROJECT_MANAGER']::skills[],ARRAY['High level of proficiency with leading methodologies knowledge, such as agile", " Experience product managing including the knowledge of scrum ceremonies.'],
        'Hitech, Banglore, Karnatka', POINT(12.9716,77.5946)::geometry, '742949f4-1956-4926-a168-738381b1dc6e', true, 'FULL_TIME', 'EXECUTIVE',ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('91a9213b-9f70-44ae-9e76-8fa9891e215d', 'Software Engineer 1', true, '2021-01-01',
        'HP is seeking a talented, personable software engineer who can assist the team by working with other designers and development teams on a variety of projects. HP development group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['SOFTWARE_ENGINEER']::skills[], ARRAY['High level of proficiency with leading methodologies knowledge, such as react, spring", " Experience software development including the knowledge of database.'],
        'Sector 14, Gurugram, Haryana', POINT(28.4595,77.0266)::geometry, 'b8d91763-03b0-470d-86fe-f50b6aca13fc', true, 'FULL_TIME', 'EXECUTIVE', ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('8e962635-1a01-48b8-9f7c-d77c6679f6a8', 'UI/UX Designer', true, '2021-01-01',
        'Facebook is seeking a talented, personable senior software engineer who can assist the team by working with other designers and development teams on a variety of projects. Facebook development group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['UX_DESIGNER']::skills[], ARRAY['High level of proficiency with leading methodologies knowledge, such as agile", " Experience product managing including the knowledge of scrum ceremonies.'],
        'Hitech, Hyderabad, Telangana', POINT(17.4435,78.3772)::geometry, '33e8bc77-801f-458a-a886-6d4e45375204', true, 'FULL_TIME', 'EXECUTIVE', ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('46d0dbb0-f554-4bff-b227-2bce9ddce751', 'Senior Software Engineer', true, '2021-01-01',
        'Google is seeking a talented, personable senior software engineer who can assist the team by working with other designers and development teams on a variety of projects. Google development group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['SOFTWARE_ENGINEER','JAVA_DEVELOPER']::skills[], ARRAY['High level of proficiency with leading methodologies knowledge, such as spring, react, postgres", " Experience software development and team lead including the knowledge of devops.'],
        'Nanakramguda, Hyderabad, Telangana', POINT(17.4169,78.3424)::geometry, '3667a2b1-0024-4226-a671-7bd659e42db1', true, 'FULL_TIME', 'EXECUTIVE', ARRAY[17.4178,78.3890]);

INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('f5fb8657-7ef7-4982-b38b-6fecd89ffc4f', 'Python Developer', true, '2021-01-01',
        'Zemoso is seeking a talented, personable interaction designer who can assist the User Experience Design team by working with other designers and development teams on a variety of projects. Zemosos User Experience Design group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['PYTHON_DEVELOPER']::skills[],ARRAY['High level of proficiency with leading UX Design software packages, such as Axure, Sketch, InVision", " Experience Design including the core Adobe Creative Suite products.'],
        'Raidurg, Hyderabad, Telangana', POINT(17.4178,78.3890)::geometry, 'e106083b-6b6f-4fa1-8148-912588bee7f8', true, 'FULL_TIME', 'MID_LEVEL',ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('7caad339-8f4a-4ec8-ae11-78c7a3ccd441', 'Senior Software Engineer', true, '2021-01-01',
        'BMW is seeking a talented, personable interaction manager who can assist the team by working with other designers and development teams on a variety of projects. BMW development group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['PRODUCT_MANAGER','PROJECT_MANAGER']::skills[],ARRAY['High level of proficiency with leading methodologies knowledge, such as agile", " Experience product managing including the knowledge of scrum ceremonies.'],
        'Hitech, Banglore, Karnatka', POINT(12.9716,77.5946)::geometry, '742949f4-1956-4926-a168-738381b1dc6e', true, 'FULL_TIME', 'EXECUTIVE',ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('451b1231-52f3-4efe-be4e-4342599a19bf', 'Software Engineer 1', true, '2021-01-01',
        'HP is seeking a talented, personable software engineer who can assist the team by working with other designers and development teams on a variety of projects. HP development group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['SOFTWARE_ENGINEER','PYTHON_DEVELOPER']::skills[], ARRAY['High level of proficiency with leading methodologies knowledge, such as react, spring", " Experience software development including the knowledge of database.'],
        'Sector 14, Gurugram, Haryana', POINT(28.4595,77.0266)::geometry, 'b8d91763-03b0-470d-86fe-f50b6aca13fc', true, 'FULL_TIME', 'EXECUTIVE', ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('720a32f4-355e-48a4-96c8-15bc3feb2d00', 'Product Manager', true, '2021-01-01',
        'Facebook is seeking a talented, personable senior software engineer who can assist the team by working with other designers and development teams on a variety of projects. Facebook development group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['UX_DESIGNER','UX_DESIGNER']::skills[], ARRAY['High level of proficiency with leading methodologies knowledge, such as agile", " Experience product managing including the knowledge of scrum ceremonies.'],
        'Hitech, Hyderabad, Telangana', POINT(17.4435,78.3772)::geometry, '33e8bc77-801f-458a-a886-6d4e45375204', true, 'FULL_TIME', 'EXECUTIVE', ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('9beb4917-0f4d-4d16-8204-35f943362434', 'UI/UX Designer', true, '2021-01-01',
        'Google is seeking a talented, personable senior software engineer who can assist the team by working with other designers and development teams on a variety of projects. Google development group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['UI_DEVELOPER','JAVA_DEVELOPER']::skills[], ARRAY['High level of proficiency with leading methodologies knowledge, such as spring, react, postgres", " Experience software development and team lead including the knowledge of devops.'],
        'Nanakramguda, Hyderabad, Telangana', POINT(17.4169,78.3424)::geometry, '3667a2b1-0024-4226-a671-7bd659e42db1', true, 'FULL_TIME', 'EXECUTIVE', ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('5951d160-9c8d-4796-8aba-e138d7a91524', 'Java Developer', true, '2021-01-01',
        'Zemoso is seeking a talented, personable interaction designer who can assist the User Experience Design team by working with other designers and development teams on a variety of projects. Zemosos User Experience Design group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['JAVA_DEVELOPER']::skills[],ARRAY['High level of proficiency with leading UX Design software packages, such as Axure, Sketch, InVision", " Experience Design including the core Adobe Creative Suite products.'],
        'Raidurg, Hyderabad, Telangana', POINT(17.4178,78.3890)::geometry, 'e106083b-6b6f-4fa1-8148-912588bee7f8', true, 'FULL_TIME', 'MID_LEVEL',ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('df53c29e-ac70-4859-97b1-f1e0caa1800c', 'Python Developer', true, '2021-01-01',
        'BMW is seeking a talented, personable interaction manager who can assist the team by working with other designers and development teams on a variety of projects. BMW development group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['PYTHON_DEVELOPER']::skills[],ARRAY['High level of proficiency with leading methodologies knowledge, such as agile", " Experience product managing including the knowledge of scrum ceremonies.'],
        'Hitech, Banglore, Karnatka', POINT(12.9716,77.5946)::geometry, '742949f4-1956-4926-a168-738381b1dc6e', true, 'FULL_TIME', 'EXECUTIVE',ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('1a6e80d5-1af5-402c-be66-c2aaa1b0f902', 'UI/UX Designer', true, '2021-01-01',
        'HP is seeking a talented, personable software engineer who can assist the team by working with other designers and development teams on a variety of projects. HP development group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['UX_DESIGNER','UI_DEVELOPER']::skills[], ARRAY['High level of proficiency with leading methodologies knowledge, such as react, spring", " Experience software development including the knowledge of database.'],
        'Sector 14, Gurugram, Haryana', POINT(28.4595,77.0266)::geometry, 'b8d91763-03b0-470d-86fe-f50b6aca13fc', true, 'FULL_TIME', 'EXECUTIVE', ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('7bb4c397-5ee4-4225-af2f-b988dbc31509', 'HR Manager', true, '2021-01-01',
        'Facebook is seeking a talented, personable senior software engineer who can assist the team by working with other designers and development teams on a variety of projects. Facebook development group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['UX_DESIGNER']::skills[], ARRAY['High level of proficiency with leading methodologies knowledge, such as agile", " Experience product managing including the knowledge of scrum ceremonies.'],
        'Hitech, Hyderabad, Telangana', POINT(17.4435,78.3772)::geometry, '33e8bc77-801f-458a-a886-6d4e45375204', true, 'FULL_TIME', 'EXECUTIVE', ARRAY[17.4178,78.3890]);


INSERT INTO jobs (id, title, is_active, date_posted, description, skills, requirements, address, location, company_id, is_green_commute, job_type, experience_level, location_point)
VALUES ('76275576-40dc-4691-b2ea-52d22c89fb6d', 'Senior Designer', true, '2021-01-01',
        'Google is seeking a talented, personable senior software engineer who can assist the team by working with other designers and development teams on a variety of projects. Google development group is a distributed multi-disciplinary team of professionals that are responsible for enhancing the UX of the company’s collective product suites worldwide.',
        ARRAY['JAVA_DEVELOPER']::skills[], ARRAY['High level of proficiency with leading methodologies knowledge, such as spring, react, postgres", " Experience software development and team lead including the knowledge of devops.'],
        'Nanakramguda, Hyderabad, Telangana', POINT(17.4169,78.3424)::geometry, '3667a2b1-0024-4226-a671-7bd659e42db1', true, 'FULL_TIME', 'EXECUTIVE', ARRAY[17.4178,78.3890]);