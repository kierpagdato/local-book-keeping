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

        <p>Create date: ${this.book.dateCreated}</p>
        <p>Last modified date: ${this.book.lastUpdated}</p>

        <div id="show-book" class="content scaffold-show" role="main">
            <g:form resource="${this.book}" method="POST">
                <fieldset class="form">
                    <f:all bean="book"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="Update" class="save" value="Update" />
                </fieldset>
            </g:form>
            <g:form resource="${this.book}" method="DELETE">
                <fieldset class="buttons">
                    <input class="delete" type="submit" value="delete" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>