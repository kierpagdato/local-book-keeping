databaseChangeLog = {
    changeSet(author: "Kier", id: "1111111111113-1") {
        sqlFile(path: "sql/test_users_dml.sql", splitStatements: "true")
    }

    changeSet(author: "Kier", id: "1111111111113-2") {
        sqlFile(path: "sql/test_books_dml.sql", splitStatements: "true")
    }

    changeSet(author: "Kier", id: "1111111111113-3") {
        sqlFile(path: "sql/test_transactions_1_dml.sql", splitStatements: "true")
    }

    changeSet(author: "Kier", id: "1111111111113-4") {
        sqlFile(path: "sql/test_transactions_2_dml.sql", splitStatements: "true")
    }

    changeSet(author: "Kier", id: "1111111111113-5") {
        sqlFile(path: "sql/test_transactions_3_dml.sql", splitStatements: "true")
    }
}
