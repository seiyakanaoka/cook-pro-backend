create database if not exists cooking_app;

use cooking_app;

create table if not exists dish (
  dish_id varchar(36) not null primary key comment '料理ID',
  user_id varchar(36) not null comment 'ユーザーID',
  dish_name varchar(255) not null comment '料理名',
  dish_create_required_time bigint(20) unsigned not null comment '所要時間',
  create_timestamp timestamp default current_timestamp comment '作成日時',
  update_timestamp timestamp default current_timestamp on update current_timestamp comment '更新日時',
);