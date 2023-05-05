create database if not exists cooking_app;

use cooking_app;

create table if not exists dish (
  dish_id bigint(20) unsigned not null auto_increment comment '料理ID',
  user_id bigint(20) unsigned not null comment 'ユーザーID',
  dish_name varchar(255) not null comment '料理名',
  dish_create_required_time bigint(20) unsigned not null comment '所要時間',
  create_timestamp timestamp default current_timestamp,
  update_timestamp timestamp default current_timestamp on update current_timestamp,
  primary key (`dish_id`)
);