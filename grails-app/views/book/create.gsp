<%@ page import="com.bookkeeping.Book" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Create book</title>
    </head>
    <body>

        <div class="columns is-mobile is-centered">

            <div class="column is-half">

                <g:render template="breadcrumbs" model="[active:'add']"/>

                </br>

                <g:form resource="${this.book}" method="POST">

                    <div class="card">
                      <header class="card-header">
                        <p class="card-header-title">Add book</p>
                      </header>
                      <div class="card-content">
                        <div class="content">

                            <div class="field">
                                <label class="label ${hasErrors(field: 'title', bean: book, 'has-text-danger')}">Title</label>
                                <div class="control ${hasErrors(field: 'title', bean: book, 'has-icons-right')}">
                                    <g:field class="input" type="text" name="title" id="title" placeholder="Title" value="${book.title}"/>

                                    <g:hasErrors bean="${book}" field="title">
                                        <span class="icon is-small is-right">
                                            <i class="fas fa-exclamation-triangle"></i>
                                        </span>
                                    </g:hasErrors>
                                </div>

                                <g:hasErrors bean="${book}" field="title">
                                    <p class="help is-danger">${book.errors.getFieldErrors("title")}</p>
                                </g:hasErrors>
                            </div>

                            <div class="field">
                                <label class="label ${hasErrors(field: 'author', bean: book, 'has-text-danger')}">Author</label>
                                <div class="control ${hasErrors(field: 'author', bean: book, 'has-icons-right')}">
                                    <g:field class="input" type="text" name="author" id="author" placeholder="Author" value="${book.author}"/>

                                    <g:hasErrors bean="${book}" field="author">
                                        <span class="icon is-small is-right">
                                            <i class="fas fa-exclamation-triangle"></i>
                                        </span>
                                    </g:hasErrors>
                                </div>

                                <g:hasErrors bean="${book}" field="author">
                                    <p class="help is-danger">${book.errors.getFieldErrors("author")}</p>
                                </g:hasErrors>
                            </div>

                            <div class="field">
                                <label class="label ${hasErrors(field: 'isbn', bean: book, 'has-text-danger')}">ISBN</label>
                                <div class="control ${hasErrors(field: 'isbn', bean: book, 'has-icons-right')}">
                                    <g:field class="input" type="text" name="isbn" id="isbn" placeholder="ISBN" value="${book.isbn}"/>

                                    <g:hasErrors bean="${book}" field="isbn">
                                        <span class="icon is-small is-right">
                                            <i class="fas fa-exclamation-triangle"></i>
                                        </span>
                                    </g:hasErrors>
                                </div>

                                <g:hasErrors bean="${book}" field="isbn">
                                    <p class="help is-danger">${book.errors.getFieldErrors("isbn")}</p>
                                </g:hasErrors>
                            </div>

                            <div class="field">
                                <label class="label ${hasErrors(field: 'status', bean: book, 'has-text-danger')}">Status</label>
                                <div class="control ${hasErrors(field: 'status', bean: book, 'has-icons-right')}">
                                    <div class="select is-expanded ${hasErrors(field: 'status', bean: book, 'is-danger')} ">
                                        <g:select name="status" id="status"
                                                  from="${Book.Status.values()}"
                                                  key="${Book.Status.values()*.name()}"
                                                  optionValue="text"
                                                  value="${book.status}"/>
                                    </div>

                                    <g:hasErrors bean="${book}" field="status">
                                        <span class="icon is-small is-right">
                                            <i class="fas fa-exclamation-triangle"></i>
                                        </span>
                                    </g:hasErrors>
                                </div>

                                <g:hasErrors bean="${book}" field="status">
                                    <p class="help is-danger">${book.errors.getFieldErrors("status")}</p>
                                </g:hasErrors>
                            </div>

                            <div class="field">
                                <label class="label ${hasErrors(field: 'description', bean: book, 'has-text-danger')}">Description</label>
                                <div class="control ${hasErrors(field: 'description', bean: book, 'has-icons-right')}">
                                    <g:textArea class="textarea" name="description" id="description" placeholder="Description" value="${book.description}"/>

                                    <g:hasErrors bean="${book}" field="description">
                                        <span class="icon is-small is-right">
                                            <i class="fas fa-exclamation-triangle"></i>
                                        </span>
                                    </g:hasErrors>
                                </div>

                                <g:hasErrors bean="${book}" field="description">
                                    <p class="help is-danger">${book.errors.getFieldErrors("description")}</p>
                                </g:hasErrors>
                            </div>

                            <div class="field">
                                <label class="label ${hasErrors(field: 'quantity', bean: book, 'has-text-danger')}">Quantity</label>
                                <div class="control ${hasErrors(field: 'quantity', bean: book, 'has-icons-right')}">
                                    <g:field class="input" type="number" name="quantity" id="quantity" placeholder="Quantity" value="${book.quantity}" min="1"/>

                                    <g:hasErrors bean="${book}" field="quantity">
                                        <span class="icon is-small is-right">
                                            <i class="fas fa-exclamation-triangle"></i>
                                        </span>
                                    </g:hasErrors>
                                </div>

                                <g:hasErrors bean="${book}" field="quantity">
                                    <p class="help is-danger">${book.errors.getFieldErrors("quantity")}</p>
                                </g:hasErrors>
                            </div>
                        </div>
                      </div>
                      <footer class="card-footer">
                        <g:submitButton name="create" class="button is-text card-footer-item" value="Add" />
                      </footer>
                    </div>
                </g:form>
            </div>
        </div>

    </body>
</html>