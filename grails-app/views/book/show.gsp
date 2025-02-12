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

                <g:if test='${flash.message}'>
                    <div class="notification is-light pt-3 pb-3">
                        <span>
                            ${flash.message}
                        </span>
                    </div>
                    <br>
                </g:if>

                <div class="tags">
                    <span class="tag ${book.status.color}">
                        ${this.book.status.text}
                    </span>
                    <span class="tag is-light">
                        <strong>Create date:</strong> ${this.book.dateCreated}
                    </span>
                    <span class="tag is-light">
                        <strong>Last modified date:</strong> ${this.book.lastUpdated}
                    </span>
                </div>

                <br>

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
                                        Description
                                    </strong>
                                </span>
                                <div>${book.description}</div>
                            </li>
                        </ol>
                    </div>
                  </div>

                    <sec:ifLoggedIn>
                        <footer class="card-footer">
                            <sec:ifAnyGranted roles="ROLE_LIBRARIAN">
                                <g:link class="card-footer-item" action="edit" resource="${this.book}">Edit</g:link>
                            </sec:ifAnyGranted>

                            <sec:ifAnyGranted roles="ROLE_LIBRARIAN, ROLE_USER">
                                <g:if test="${this.book.status == com.bookkeeping.book.Book.Status.SHELVED}">
                                    <g:form controller="borrow" action="selfCheckout" method="POST" id="${this.book.id}" class="card-footer-item">
                                        <g:submitButton name="borrow" class="button is-text" value="Borrow" />
                                    </g:form>
                                </g:if>
                            </sec:ifAnyGranted>

                            <sec:ifAnyGranted roles="ROLE_LIBRARIAN">
                                <g:form resource="${this.book}" method="DELETE" class="card-footer-item has-background-danger">
                                    <g:submitButton name="delete" class="button is-danger" value="Delete" />
                                </g:form>
                            </sec:ifAnyGranted>
                        </footer>
                    </sec:ifLoggedIn>
                </div>
            </div>
        </div>
    </body>
</html>