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

                <div class="tags">
                    <span class="tag is-light">
                        <strong>Create date:</strong> ${this.book.dateCreated}
                    </span>
                    <span class="tag is-light">
                        <strong>Last modified date:</strong> ${this.book.lastUpdated}
                    </span>
                </div>

                <div class="card">
                  <header class="card-header">
                    <p class="card-header-title">Book info</p>
                  </header>
                  <div class="card-content">
                    <div class="content">
                        <f:display bean="book" order="['title', 'author', 'status', 'copy', 'description']"/>
                    </div>
                  </div>
                    <g:form resource="${this.book}" method="DELETE">
                      <footer class="card-footer">
                            <g:link class="card-footer-item" action="edit" resource="${this.book}">Edit</g:link>
                            <g:submitButton name="delete" class="button is-text card-footer-item" value="Delete" />
                      </footer>
                    </g:form>
                </div>
            </div>
        </div>
    </body>
</html>