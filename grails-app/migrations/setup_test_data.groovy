databaseChangeLog = {
    changeSet(author: "Kier", id: "1111111111112-1") {
        sqlFile(path: "sql/initial_test_books_dml.sql", splitStatements: "true")
    }

    changeSet(author: "Kier", id: "1111111111112-2") {
        sqlFile(path: "sql/initial_test_users_dml.sql", splitStatements: "true")
    }
}
