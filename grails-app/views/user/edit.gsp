<!DOCTYPE html>
<html>
    <head>
        <g:set var="page" value="userList" scope="request"/>
        <meta name="layout" content="main" />
        <title>Update user</title>
    </head>
    <body>

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

        <div class="columns is-mobile is-centered">

            <div class="column is-half">

                <div class="tags">
                    <span class="tag is-light">
                        <strong>Create date:</strong> ${this.user.dateCreated}
                    </span>
                    <span class="tag is-light">
                        <strong>Last modified date:</strong> ${this.user.lastUpdated}
                    </span>
                </div>

                <g:form resource="${this.user}" method="PUT">

                    <div class="card">
                        <header class="card-header">
                            <p class="card-header-title">User info</p>
                        </header>
                        <div class="card-content">
                            <div class="content">
                                <g:render template="form"/>
                            </div>
                        </div>
                        <footer class="card-footer">
                            <g:submitButton name="update" class="button is-text card-footer-item" value="Update" />
                        </footer>
                    </div>
                </g:form>
            </div>
        </div>
    </body>
</html>