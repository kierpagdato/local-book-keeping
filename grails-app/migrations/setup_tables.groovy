databaseChangeLog = {
    changeSet(author: "Kier", id: "1111111111111-1") {
        createTable(tableName: "book") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "bookPK")
            }

            column(name: "title", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "author", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "isbn", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "text") {
                constraints(nullable: "false")
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
        }
    } // end create table book

    changeSet(author: "Kier", id: "1111111111111-2") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "name", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "role", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "date_joined", type: "datetime") {
                constraints(nullable: "false")
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
        }
    } // end create table user

    changeSet(author: "Kier", id: "1111111111111-3") {
        createTable(tableName: "borrow") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "borrowPK")
            }

            column(name: "date_borrowed", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "date_returned", type: "datetime") {
                constraints(nullable: "true")
            }

            column(name: "book_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "borrower_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "processor_id", type: "BIGINT") {
                constraints(nullable: "false")
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
        }
    } // end create table borrow
}
