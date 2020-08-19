-- CREATE EXTENSION uuid-ossp;

--############## Category
create table category (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,
    name VARCHAR(100) NOT NULL
);

insert into category (name) values ('Pesquisa de Clima');
insert into category (name) values ('Pesquisa de Treinamentos');
insert into category (name) values ('Refeitório');
insert into category (name) values ('Geral');

alter table category add constraint pk_category_id primary key(id);

--############## Customer
create table customer (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,
    name VARCHAR(255) NOT NULL
);

alter table customer add constraint pk_customer_id primary key(id);

--############## Configuration
create table configuration (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,
    customer_id UUID NOT NULL
);

alter table configuration add constraint pk_configuration_id primary key(id);
alter table configuration add constraint fk_customer_id foreign key (customer_id) references customer (id);

--############## Survey
create table survey (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,
    customer_id UUID NOT NULL,
    name varchar(255) not null,
    anonymous boolean,
    answer_link varchar(255),
    description varchar(255),
    evaluator_name varchar(100),
    expiration_date date,
    final_date date,
    initial_date date,
    instructor_name varchar(100),
    objective varchar(255),
    response_time integer,
    title_icon varchar(45)
);

alter table survey add constraint pk_survey_id primary key(id);
alter table survey add constraint fk_customer_id foreign key (customer_id) references customer (id);

--############## Subject
create table subject (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,
    category_id UUID NOT NULL,
    name varchar(255) NOT NULL
);

alter table subject add constraint pk_subject_id primary key(id);
alter table subject add constraint fk_category_id foreign key (category_id) references category (id);

--############## Survey Subjects
create table survey_subjects (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,
    survey_id UUID NOT NULL,
    subject_id UUID NOT NULL
);

alter table survey_subjects add constraint pk_survey_subjects_id primary key(id);
alter table survey_subjects add constraint fk_survey_id foreign key (survey_id) references survey (id);
alter table survey_subjects add constraint fk_subject_id foreign key (subject_id) references subject (id);

--############## Question
create table question (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,    
    title varchar(255) not null,
    subject_id UUID NOT NULL,
    answer_type integer,
    mandatory boolean
);

alter table question add constraint pk_question_id primary key(id);
alter table question add constraint fk_subject_id foreign key (subject_id) references subject (id);

--############## Answer Option
create table answer_option (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,    
    question_id UUID NOT NULL,
    description varchar(100) not null,
    sequence integer not null
);

alter table answer_option add constraint pk_answer_option_id primary key(id);
alter table answer_option add constraint fk_question_id foreign key (question_id) references question (id);

--############## Respondent
create table respondent (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,        
    admission_date date,
    branch_name varchar(255),
    branch_id integer,
    company_name varchar(255),
    company_id integer,
    cpf varchar(45),
    dismissal_cause varchar(100),
    dismissal_date date,
    education_level varchar(150),
    email varchar(150),
    experience_contract_expiration1 date,
    experience_contract_expiration2 date,
    name varchar(255),
    phone_number varchar(45),
    position_name varchar(150),
    registration integer,
    respondent_identifier varchar(80) not null,
    respondent_type integer not null,
    visit_date date,
    visit_description varchar(255),
    workstation_id varchar(150)
);

alter table respondent add constraint pk_respondent_id primary key(id);

--############## Answer
create table answer (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,     
    answer_nps integer,
    answer_radio integer,
    answer_text varchar(255),
    date date,
    time time,
    question_id uuid,
    respondent_id uuid,
    survey_id uuid
);    

alter table answer add constraint pk_answer_id     primary key (id);
alter table answer add constraint fk_question_id   foreign key (question_id)   references question (id);
alter table answer add constraint fk_respondent_id foreign key (respondent_id) references respondent (id);
alter table answer add constraint fk_survey_id     foreign key (survey_id)     references survey (id);

--############## Answer Checkbox Selected
create table answer_checkbox_selected (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,         
    answer_id uuid,
    answer_option_id uuid    
);    

alter table answer_checkbox_selected add constraint pk_answer_checkbox_selected_id primary key (id);
alter table answer_checkbox_selected add constraint fk_answer_id                   foreign key (answer_id)        references answer (id);
alter table answer_checkbox_selected add constraint fk_answer_option_id            foreign key (answer_option_id) references answer_option (id);

--############## Survey Respondent
create table survey_respondent (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,         
    survey_id uuid,
    respondent_id uuid,
    answer_status integer,
    link varchar(255)
);    

alter table survey_respondent add constraint pk_survey_respondent_id primary key (id);
alter table survey_respondent add constraint fk_respondent_id        foreign key (respondent_id) references respondent (id);
alter table survey_respondent add constraint fk_survey_id            foreign key (survey_id)     references survey (id);

--############## Answer Comment
create table answer_comment (
    id UUID DEFAULT uuid_generate_v4() NOT NULL,         
    survey_id uuid,
    respondent_id uuid,
    positive_comment varchar(255),
    negative_comment varchar(255)
);    

alter table answer_comment add constraint pk_answer_comment_id primary key (id);
alter table answer_comment add constraint fk_respondent_id     foreign key (respondent_id) references respondent (id);
alter table answer_comment add constraint fk_survey_id         foreign key (survey_id)     references survey (id);