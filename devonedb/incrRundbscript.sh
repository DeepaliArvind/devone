




echo $PATH
OSNAME=`uname -s`
FILE_PATH=/var/lib/mysql-files/applifire/db/KYIIHJDYQM2G9JMU1SOA
MYSQL=/usr/bin





DB_PATH=/var/lib/mysql-files/applifire/db/KYIIHJDYQM2G9JMU1SOA
DB_NAME=devone
USER=devone
PASSWORD=devone
PORT=3306
HOST=localhost


echo 'create incremental tables starts at '$(date)
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $DB_PATH/incrddl.sql;"
echo 'create incremental tables ends at '$(date)


echo 'delete data from incremental tables starts at '$(date)
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $DB_PATH/incrdelete.sql;"
echo 'delete data from incremental tables ends at '$(date)


echo 'loading data to incremental table starts at '$(date)
sh $DB_PATH/incrloaddata.sh
echo 'loading data to incremental tables ends at '$(date)