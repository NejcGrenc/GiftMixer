
create table giftmixer.admin (
  id bigint NOT NULL  AUTO_INCREMENT  primary key,
  already_sent_all_email_validation     boolean, 
  already_sent_all_target_gift_messages boolean, 
  already_sent_all_wish_links           boolean, 
  email                         varchar(255), 
  email_validation_template     varchar(255), 
  target_gift_message_template  varchar(255), 
  username            varchar(255) not null, 
  wish_link_template  varchar(255)
);

create table giftmixer.chain (
  id        bigint NOT NULL  AUTO_INCREMENT  primary key,
  admin_id  bigint not null
);

create table giftmixer.giver_reciever_pair (
  id bigint NOT NULL  AUTO_INCREMENT  primary key,
  chain_id bigint not null, 
  giver_id bigint not null, 
  receiver_id bigint not null
);

create table giftmixer.participant (
  id bigint NOT NULL  AUTO_INCREMENT  primary key,
  admin_id  bigint not null, 
  code      varchar(255),
  confirmed_confirmation_email boolean, 
  confirmed_recieved_wish_link boolean, 
  email   varchar(255) not null, 
  name    varchar(255) not null, 
  sent_confirmation_email   boolean, 
  sent_target_gift_message  boolean, 
  sent_wish_link        boolean, 
  wish_message_written  boolean
);

create table giftmixer.wish (
  id bigint NOT NULL  AUTO_INCREMENT  primary key,
  participant_id  bigint not null, 
  wish_content    text
);
