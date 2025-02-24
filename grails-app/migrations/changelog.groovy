databaseChangeLog = {
    include file: 'setup_tables.groovy'
    include file: 'setup_test_data.groovy'
    include file: 'setup_load_test_data.groovy'
    include file: 'add_2_more_user_role.groovy'
}