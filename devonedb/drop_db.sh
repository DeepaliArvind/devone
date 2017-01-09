




echo $PATH
DB_PATH=/var/lib/mysql-files/applifire/db/KYIIHJDYQM2G9JMU1SOA
MYSQL=/usr/bin
USER=devone
PASSWORD=devone
PORT=3306
HOST=localhost
echo 'drop db starts at ' $(date)
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends at ' $(date)

