<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Update book</title>
    </head>
    <body>

        <div class="columns is-mobile is-centered">

            <div class="column is-half">

                <g:render template="breadcrumbs" model="[active:'show']"/>

                </br>

                <div class="tags">
                    <span class="tag is-light">
                        <strong>Create date:</strong> ${this.book.dateCreated}
                    </span>
                    <span class="tag is-light">
                        <strong>Last modified date:</strong> ${this.book.lastUpdated}
                    </span>
                </div>

                <g:form resource="${this.book}" method="PUT">

                    <div class="card">
                      <header class="card-header">
                        <p class="card-header-title">Book info</p>
                      </header>
                      <div class="card-content">
                        <div class="content">

                            <g:render template="form"/>

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