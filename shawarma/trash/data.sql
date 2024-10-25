delete from Ingredient_Ref;
delete from Shawarma;
delete from Shawarma_Order;

delete from Ingredient;

insert into Ingredient(id,name,type) values('PORK','Свинина','PROTEIN');
insert into Ingredient(id,name,type) values('BEEF','Говядина','PROTEIN');
insert into Ingredient(id,name,type) values('POTA','Картошка','VEGGIES');
insert into Ingredient(id,name,type) values('TMTO','Помидоры','VEGGIES');
insert into Ingredient(id,name,type) values('CUCM','Огурцы','VEGGIES');
insert into Ingredient(id,name,type) values('VITZ','Сыр Витязь','CHEESE');
insert into Ingredient(id,name,type) values('GOLN','Сыр Голандский','CHEESE');
insert into Ingredient(id,name,type) values('GARL','Чесночный','SAUCE');
insert into Ingredient(id,name,type) values('CHES','Сырный','SAUCE');
