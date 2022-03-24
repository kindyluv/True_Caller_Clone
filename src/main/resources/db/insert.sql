set foreign_key_check = 0

truncate table contact;
truncate table mobile_number;

insert into mobile_number (id, country_code, mobile_number)
values (4, "+234", "09067886544"),
       (5, "+234", "08064980776");

insert into contact (id, name, company_name, email, mobile_number_id, is_blocked)
values (1, "Lois Amara", "semicolon", "loisamara@gmail.com", 5, false),
       (2, "Amara Jesus", "colonial", "amara@gmail.com", 4, false);

set foreign_key_check = 1