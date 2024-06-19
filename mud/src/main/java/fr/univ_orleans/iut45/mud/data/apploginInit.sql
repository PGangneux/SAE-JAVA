-- executer ce script avec un compte disposant des droit
drop user 'applogin'@'localhost';
drop user 'applogin'@'%';
create user 'applogin'@'localhost';
create user 'applogin'@'%';
grant select on SAEACCOUNT.* to applogin@'localhost';
grant select on SAEACCOUNT.* to applogin@'%';