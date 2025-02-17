<%@ page import="com.bookkeeping.security.User" %>
<!DOCTYPE html>
<html>
    <head>
        <g:set var="page" value="borrowBasket" scope="request"/>
        <g:set var="secUserId" value="${sec.loggedInUserInfo(field: 'id')}"/>
        <meta name="layout" content="main" />
        <title>Basket</title>
    </head>
    <body>

        <g:if test="${flash.message}">
            <div class="notification is-light pt-3 pb-3">
                <span>
                    ${flash.message}
                </span>
            </div>
            <br>
        </g:if>

        <div class="columns is-mobile is-centered">

            <div class="column is-3">

                <g:form controller="borrow" method="POST">

                    <div class="card">
                        <header class="card-header">
                            <p class="card-header-title">Checkout</p>
                        </header>
                        <div class="card-content">
                            <div class="content">


                                <div class="field">
                                    <label class="label">Borrowing user</label>
                                    <div class="control">
                                        <div class="select">
                                            <g:select name="user" id="user"
                                                      from="${userList}"
                                                      optionKey="id"
                                                      optionValue="${{it.firstName + ' ' + it.lastName + (secUserId == it.id.toString()? ' (Me)' : '')}}"
                                            value="${secUserId}"/>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
                        <footer class="card-footer">
                            <g:actionSubmit action="checkOut" name="proceed" class="button is-text card-footer-item" value="Proceed" />
                            <g:actionSubmit action="clearBasket" name="proceed" class="button is-warning card-footer-item" value="Clear basket" />
                        </footer>
                    </div>

                </g:form>

            </div>
            <div class="column is-9">

                <g:render template="table_basket"/>

                <br>

                <p>Total item: ${list.size()}</p>
            </div>
        </div>

        <script type="text/javascript">
            $(function() {
                $('#headerSelected').click(function() {
                    var checked = this.checked;
                    $('.checkbox-select').prop('checked', checked);
                });
            });
        </script>

    </body>
</html>