<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
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
                            <f:all bean="book" order="['author','title', 'status', 'quantity', 'description']"/>
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