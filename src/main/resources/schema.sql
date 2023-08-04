create database if not exists cook_pro;

use cook_pro;

create table if not exists user (
  user_id varchar(36) not null primary key comment 'ユーザーID',
  user_name varchar(36) not null comment 'ユーザー名',
  display_user_name varchar(36) comment '表示用ユーザー名',
  email varchar(255) not null comment 'Eメール',
  tel_number varchar(255) not null comment '電話番号',
  user_image_key varchar(255) comment 'ユーザー画像',
  create_timestamp timestamp default current_timestamp comment '作成日時',
  update_timestamp timestamp default current_timestamp on update current_timestamp comment '更新日時'
);

create table if not exists dish (
  dish_id varchar(36) not null primary key comment '料理ID',
  user_id varchar(36) not null comment 'ユーザーID',
  dish_name varchar(255) not null comment '料理名',
  dish_create_required_time bigint(20) unsigned not null comment '所要時間',
  create_timestamp timestamp default current_timestamp comment '作成日時',
  update_timestamp timestamp default current_timestamp on update current_timestamp comment '更新日時',
  foreign key (user_id) references user (user_id) on delete cascade
);

create table if not exists material (
  id varchar(36) not null primary key comment '材料ID',
  dish_id varchar(36) not null comment '料理ID',
  name varchar(36) not null comment '材料名',
  quantity bigint not null comment '個数',
  unit varchar(36) not null comment '単位',
  create_timestamp timestamp default current_timestamp comment '作成日時',
  update_timestamp timestamp default current_timestamp on update current_timestamp comment '更新日時'
);

create table if not exists dish_process (
  dish_process_id varchar(36) not null primary key comment '料理工程ID',
  dish_id varchar(36) not null comment '料理ID',
  dish_process_text varchar(255) not null comment '説明文',
  create_timestamp timestamp default current_timestamp comment '作成日時',
  update_timestamp timestamp default current_timestamp on update current_timestamp comment '更新日時'
);

create table if not exists dish_image (
  dish_image_id varchar(36) not null primary key comment '料理画像ID',
  dish_id varchar(36) not null comment '料理ID',
  dish_image_key varchar(255) not null comment '画像を一意に識別するキー',
  create_timestamp timestamp default current_timestamp comment '作成日時',
  update_timestamp timestamp default current_timestamp on update current_timestamp comment '更新日時'
);

create table if not exists category (
  category_id varchar(36) not null primary key comment 'カテゴリーID',
  category_type varchar(36) not null comment 'カテゴリー種別',
  create_timestamp timestamp default current_timestamp comment '作成日時',
  update_timestamp timestamp default current_timestamp on update current_timestamp comment '更新日時'
);

create table if not exists dish_category (
  dish_category_id varchar(36) not null primary key comment '料理とカテゴリーの中間ID',
  dish_id varchar(36) not null comment '料理ID',
  category_id varchar(255) not null comment 'カテゴリーID',
  create_timestamp timestamp default current_timestamp comment '作成日時',
  update_timestamp timestamp default current_timestamp on update current_timestamp comment '更新日時',
  foreign key (dish_id) references dish (dish_id) on delete cascade,
  foreign key (category_id) references category (category_id) on delete cascade
);
