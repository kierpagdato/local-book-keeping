<%@ page import="com.bookkeeping.security.User;com.bookkeeping.borrow.BorrowBasket" %>
<!DOCTYPE html>
<html>
    <head>
        <g:set var="page" value="borrowBasket" scope="request"/>
        <g:set var="borrowBasket" value="${session.getAttribute(BorrowBasket.SESSION_KEY)}"/>
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

                <g:form controller="borrow" method="POST" id="checkOutForm">

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
                                            <g:select name="userId" id="userId"
                                                      from="${userList}"
                                                      optionKey="id"
                                                      optionValue="${{it.firstName + ' ' + it.lastName + (secUserId == it.id.toString()? ' (Me)' : '')}}"
                                            value="${secUserId}"/>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>

                        <g:if test="${borrowBasket?.bookIds?.size() > 0}">
                            <footer class="card-footer">
                                <g:actionSubmit action="checkOut" name="proceed" id="checkOutBtn" class="button is-text card-footer-item" value="Proceed" />
    %{--                            <g:actionSubmit action="clearBasket" name="proceed" class="button is-warning card-footer-item" value="Clear basket" />--}%
                            </footer>
                        </g:if>
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

                $('#checkOutBtn').click(function(e) {
                    e.preventDefault();
                    $.ajax({
                        url: '/api/borrow/v1/checkOut',
                        type: "POST",
                        data: {user: $('#userId').val()},
                        success: function (data, status, xhr) {
                            if(xhr.status == 204) {
                                alert('Basket is empty.');
                            } else {
                                if(data.transactionId) {
                                    window.location.replace('/borrow/receipt/' + data.transactionId)
                                }
                            }
                        },
                        error: function (xhr) {
                            var response = JSON.parse(xhr.responseText)
                            alert(response.error)
                        }
                    });
                });
            });
        </script>

    </body>
</html>