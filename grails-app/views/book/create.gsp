<%@ page import="com.bookkeeping.book.Book" %>
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

                <br>

                <g:form resource="${this.book}" method="POST">

                    <div class="card">
                      <header class="card-header">
                        <p class="card-header-title">Add book</p>
                      </header>
                      <div class="card-content">
                        <div class="content">

                            <g:render template="form"/>

                            <div class="field">
                                <label class="label ${hasErrors(field: 'quantity', bean: book, 'has-text-danger')}">Quantity</label>
                                <div class="control ${hasErrors(field: 'quantity', bean: book, 'has-icons-right')}">
                                    <g:field class="input ${hasErrors(field: 'quantity', bean: book, 'is-danger')}" type="number" name="quantity" id="quantity" placeholder="Quantity" value="${book.quantity}" min="1"/>

                                    <g:hasErrors bean="${book}" field="quantity">
                                        <span class="icon is-small is-right">
                                            <i class="fas fa-exclamation-triangle"></i>
                                        </span>
                                    </g:hasErrors>
                                </div>

                                <g:hasErrors bean="${book}" field="quantity">
                                    <p class="help is-danger"><g:fieldError bean="${book}" field="quantity" /></p>
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