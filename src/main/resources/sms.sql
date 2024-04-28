create table sms
(
id int primary key auto_increment,
BarCode char(255) not null,
Callno char(255) not null,
LoanDate char(255) not null,
Phone char(255) not null,
ReturnTime char(255) not null,
Title char(255) not null,
LoanCount char(255) not null,
Cardno char(255) not null,
readerInfo char(255) not null,
readerInfoResponse char(255) not null,
readerInfo2 char(255) not null,
readerName char(255) not null
)engine=Innodb default charset=utf8 COMMENT='信息发送表';

create table sms1
(
    id int primary key auto_increment,
    BarCode char(255) not null,
    Callno char(255) not null,
    LoanDate char(255) not null,
    Phone char(255) not null,
    ReturnTime char(255) not null,
    Title char(255) not null,
    LoanCount char(255) not null,
    Cardno char(255) not null,
    readerInfo char(255) not null,
    readerInfoResponse char(255) not null,
    readerInfo2 char(255) not null,
    readerName char(255) not null,
    default_time  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)engine=Innodb default charset=utf8 COMMENT='信息发送表1';


DROP TABLE IF EXISTS sended_message;
create table sended_message(
    id int primary key auto_increment,
    openid varchar(255),
    card_no varchar(255),
    LoanDate varchar(255),
    true_name varchar(255),
    return_time varchar(255),
    title varchar(255),
    default_time  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)engine=Innodb default charset=utf8 COMMENT='文献催还发送成功给读者的消息明细';
