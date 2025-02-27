<%@ page import="com.bookkeeping.borrow.Borrow" %>
<!DOCTYPE html>
<html>
    <head>
        <g:set var="page" value="borrow" scope="request"/>
        <meta name="layout" content="main" />
        <title>Borrow receipt</title>
        <style>
            ol > li {
                list-style-type: none;
            }
        </style>
    </head>
    <body>

        <g:render template="breadcrumbs" model="[active:'receipt']"/>

        <br>

        <g:if test="${flash.message}">
            <div class="notification is-light pt-3 pb-3">
                <span>
                    ${flash.message}
                </span>
            </div>
            <br>
        </g:if>

        <div class="columns is-mobile is-centered">

            <div class="column is-6">

                <g:form controller="borrow" action="returnBorrow" id="${list[0].transactionId}" method="POST">

                    <div class="card">
                        <header class="card-header">
                            <p class="card-header-title">Receipt</p>
                        </header>
                        <div class="card-content">
                            <div class="content">

                                <ol>
                                    <li>
                                        <span>
                                            <strong>
                                                Transaction ID
                                            </strong>
                                        </span>
                                        <div>${list[0].transactionId}</div>
                                    </li>
                                    <li>
                                        <span>
                                            <strong>
                                                Transaction type
                                            </strong>
                                        </span>
                                        <div>${list[0].type.text}</div>
                                    </li>
                                    <li>
                                        <span>
                                            <strong>
                                                Status
                                            </strong>
                                        </span>
                                        <div>
                                            <span class="tag ${list[0].status.color}">${list[0].status.text}</span>
                                        </div>
                                    </li>
                                    <li>
                                        <span>
                                            <strong>
                                                Borrowed date
                                            </strong>
                                        </span>
                                        <div>${list[0].dateBorrowed}</div>
                                    </li>
                                    <li>
                                        <span>
                                            <strong>
                                                Return date
                                            </strong>
                                        </span>
                                        <div>${list[0].dateReturned?: '-'}</div>
                                    </li>

                                </ol>

                            </div>
                        </div>

                        <g:if test="${list[0].status != Borrow.Status.RETURNED}">
                            <sec:ifAnyGranted roles="ROLE_LIBRARIAN">
                                <footer class="card-footer">
                                    <g:submitButton name="return" class="button is-text card-footer-item" value="Process return" />
                                </footer>
                            </sec:ifAnyGranted>
                        </g:if>
                    </div>

                </g:form>

            </div>

            <div class="column is-6">

                <g:render template="table_receipt"/>

            </div>
        </div>

    </body>
</html>