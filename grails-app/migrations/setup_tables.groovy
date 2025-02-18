databaseChangeLog = {

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-1") {
        createTable(tableName: "book") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "bookPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "title", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "isbn", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "CLOB") {
                constraints(nullable: "false")
            }

            column(name: "author", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-2") {
        createTable(tableName: "borrow") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "borrowPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "borrower_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "processor_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "date_borrowed", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "book_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "transaction_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "date_returned", type: "datetime")

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-3") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "text", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-4") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "first_name", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "last_name", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-5") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "user_rolePK")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "user_rolePK")
            }
        }
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-6") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLEAUTHORITY_COL", tableName: "role")
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-7") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERUSERNAME_COL", tableName: "user")
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-8") {
        createIndex(indexName: "author_Idx", tableName: "book") {
            column(name: "author")
        }
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-9") {
        createIndex(indexName: "transaction_Idx", tableName: "borrow") {
            column(name: "transaction_id")
        }
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-10") {
        addForeignKeyConstraint(baseColumnNames: "borrower_id", baseTableName: "borrow", constraintName: "FK5745ehusnigepril8saky3e07", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-11") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK859n2jvi8ivhui0rl0esws6o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-12") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FKa68196081fvovjhkek5m97n3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", validate: "true")
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-13") {
        addForeignKeyConstraint(baseColumnNames: "book_id", baseTableName: "borrow", constraintName: "FKgqh01ty3c1u7ja2rjdua05c36", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "book", validate: "true")
    }

    changeSet(author: "kierpagdato (generated)", id: "1739907870769-14") {
        addForeignKeyConstraint(baseColumnNames: "processor_id", baseTableName: "borrow", constraintName: "FKhbaf4k5w6nj6cmcyminl9a4u8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }
}