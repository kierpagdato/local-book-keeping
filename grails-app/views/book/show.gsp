<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Book info</title>
        <style>
            ol li {
                list-style-type: none;
            }
        </style>
    </head>
    <body>

        <div class="columns is-mobile is-centered">

            <div class="column is-half">

                <g:render template="breadcrumbs" model="[active:'show']"/>

                <br>

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
                        <ol>
                            <li>
                                <span>
                                    <strong>
                                        Title
                                    </strong>
                                </span>
                                <div>${book.title}</div>
                            </li>
                            <li>
                                <span>
                                    <strong>
                                        Author
                                    </strong>
                                </span>
                                <div>${book.author}</div>
                            </li>
                            <li>
                                <span>
                                    <strong>
                                        ISBN
                                    </strong>
                                </span>
                                <div>${book.isbn}</div>
                            </li>
                            <li>
                                <span>
                                    <strong>
                                        Status
                                    </strong>
                                </span>
                                <div>${book.status?.text}</div>
                            </li>
                            <li>
                                <span>
                                    <strong>
                                        Description
                                    </strong>
                                </span>
                                <div>${book.description}</div>
                            </li>
                        </ol>
                    </div>
                  </div>
                    <sec:ifAnyGranted roles="ROLE_LIBRARIAN">
                        <g:form resource="${this.book}" method="DELETE">
                          <footer class="card-footer">
                                <g:link class="card-footer-item" action="edit" resource="${this.book}">Edit</g:link>
                                <g:submitButton name="delete" class="button is-text card-footer-item" value="Delete" />
                          </footer>
                        </g:form>
                    </sec:ifAnyGranted>
                </div>
            </div>
        </div>
    </body>
</html>