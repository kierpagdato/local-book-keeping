<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Register</title>
    </head>
    <body>
    <div class="columns is-mobile is-centered">

        <div class="column is-half">

            <g:form resource="${this.user}" action="register" method="POST">

                <div class="card">
                    <header class="card-header">
                        <p class="card-header-title">Registration form</p>
                    </header>
                    <div class="card-content">
                        <div class="content">
                            <f:all bean="user" order="['firstName','lastName', 'email', 'username', 'password']"/>
                        </div>
                    </div>
                    <footer class="card-footer">
                        <g:submitButton name="create" class="button is-text card-footer-item" value="Register" />
                    </footer>
                </div>
            </g:form>
        </div>
    </div>

    </body>
</html>
