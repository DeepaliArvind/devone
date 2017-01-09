




echo $PATH
OSNAME=`uname -s`
ART_DATA_PATH=/var/lib/mysql-files/applifire/db/KYIIHJDYQM2G9JMU1SOA/art/data
AST_DATA_PATH=/var/lib/mysql-files/applifire/db/KYIIHJDYQM2G9JMU1SOA/ast/data
MYSQL=/usr/bin


if [ $OSNAME = "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi

DB_NAME=devone
USER=devone
PASSWORD=devone
PORT=3306
HOST=localhost


echo ' load ART data starts at ' $(date)
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_password_algorithm.csv' into table art_password_algorithm FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_password_policy.csv' into table art_password_policy FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/cloud_drive_file_content_type.csv' into table cloud_drive_file_content_type FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/cloud_drive_tags.csv' into table cloud_drive_tags FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_query.csv' into table art_query FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_report_ui.csv' into table art_report_ui FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_severity_m.csv' into table art_log_severity_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_config_m.csv' into table art_log_config_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_config_attributes_m.csv' into table art_log_config_attributes_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_connector_m.csv' into table art_log_connector_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_connector_attributes_m.csv' into table art_log_connector_attributes_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_health_scheduler_config_m.csv' into table art_health_scheduler_config_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_health_status_config_m.csv' into table art_health_status_config_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_job_details.csv' into table art_job_details FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_scheduler_details.csv' into table art_scheduler_details FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_schedule_config.csv' into table art_schedule_config FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_bounded_context.csv' into table art_bounded_context FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_domain.csv' into table art_domain FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_alarm.csv' into table art_log_alarm FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_architecture_layer_m.csv' into table art_log_architecture_layer_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_status_m.csv' into table art_log_status_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_event_action_m.csv' into table art_log_event_action_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

echo 'load ART data ends at ' $(date)

