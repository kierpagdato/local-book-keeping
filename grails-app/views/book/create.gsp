<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
    </head>
    <body>

        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="list" action="index">Book list</g:link></li>
            </ul>
        </div>

        <div id="create-book" class="content scaffold-create" role="main">
            <g:form resource="${this.book}" method="POST">
                <fieldset class="form">
                    <f:all bean="book"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="Create" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>