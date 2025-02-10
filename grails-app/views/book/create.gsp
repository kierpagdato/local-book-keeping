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
                            <f:all bean="book" order="['author','title', 'isbn', 'status', 'description']"/>

                            <div class="field">
                                <label class="label ${hasErrors(field: 'quantity', bean: book, 'has-text-danger')}">Quantity</label>
                                <div class="control ${hasErrors(field: 'quantity', bean: book, 'has-icons-right')}">
                                    <g:field class="input" type="number" name="quantity" id="quantity" values="${book.quantity}"/>

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