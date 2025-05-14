create table push_token
(
    id         varchar(255) not null,
    member_id  varchar(255),
    token      varchar(255) unique,
    version    integer,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    created_by varchar(255),
    updated_by varchar(255),
    primary key (id)
);

alter table if exists push_token
    add constraint fk_push_token_member
        foreign key (member_id)
            references member;
