<!DOCTYPE html>
<html>
    <head>
        <g:set var="page" value="books" scope="request"/>
        <meta name="layout" content="main" />
        <title>Book list</title>
        <g:set var="selectedList" value="${params.selected as List}"/>
    </head>
    <body>

        <div class="columns is-mobile is-centered">

            <div class="column is-full">

                <g:render template="breadcrumbs" model="[active:'list']"/>

                <g:if test="${flash.message}">
                    <div class="notification is-light pt-3 pb-3">
                        <span>
                            ${flash.message}
                        </span>
                    </div>
                    <br>
                </g:if>


                <sec:ifLoggedIn>
                    <g:form controller="borrowApi" action="addToBasket" method="POST" name="borrowBasketForm">
                        <div class="field is-grouped">
                            <p class="control">
                                <g:actionSubmit id="addToBasketBtn" action="addToBasket" class="button is-text" value="Add to basket"/>
                            </p>
                        </div>
                    </g:form>
                </sec:ifLoggedIn>

                <br>

                <g:render template="table"/>

                <br>

                <p>Query result: ${count}</p>
                <bl:paginate controller="book" action="index" total="${count}" max="5" params="${params}"/>
            </div>
        </div>

        <script type="text/javascript">
            $(function() {
                $('#headerSelected').click(function() {
                    var checked = this.checked;
                    $('.checkbox-select').prop('checked', checked);
                });

                $('#addToBasketBtn').click(function(e) {
                    e.preventDefault();
                    var data = $('#borrowBasketForm').serialize()
                    $.ajax({
                        url: $('#borrowBasketForm').attr('action'),
                        type: "POST",
                        data: data,
                        success: function (data) {
                            console.log('borrow basket size: ' + data.size)
                            $('#borrowBasketSize').text(data.size);
                            alert('Books added to basket.');
                        }
                    });
                });
            });
        </script>

    </body>
</html>