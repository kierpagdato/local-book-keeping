<!doctype html>
<html>
    <head>
        <title>Bad Request</title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <ul class="col-12 errors">
                    <li>Error: Bad request</li>
                    <li>Path: ${request.forwardURI}</li>
                </ul>
            </section>
        </div>
    </div>
    </body>
</html>
