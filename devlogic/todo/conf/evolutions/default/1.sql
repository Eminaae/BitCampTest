# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_category primary key (id))
;

create table task (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  is_completed              tinyint(1) default 0,
  date                      datetime(6),
  category_id               bigint,
  constraint pk_task primary key (id))
;

alter table task add constraint fk_task_category_1 foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_task_category_1 on task (category_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table category;

drop table task;

SET FOREIGN_KEY_CHECKS=1;

