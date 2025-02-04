<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
    </head>
    <body>

        <div class="columns is-mobile is-centered">

            <div class="column is-half">

                <g:render template="breadcrumbs" model="[active:'show']"/>

                </br>

        <p>Create date: ${this.book.dateCreated}</p>
        <p>Last modified date: ${this.book.lastUpdated}</p>
                <g:form resource="${this.book}" method="POST">

                    <div class="card">
                      <header class="card-header">
                        <p class="card-header-title">Book info</p>
                      </header>
                      <div class="card-content">
                        <div class="content">
                            <f:all bean="book" order="['author','title', 'status', 'quantity', 'description']"/>
                        </div>
                      </div>
                      <footer class="card-footer">
                            <g:submitButton name="update" class="button is-text card-footer-item" value="Update" />
                      </footer>
                    </div>
                </g:form>
            </div>
        </div>
    </body>
</html>