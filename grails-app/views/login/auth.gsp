<html>
    <head>
            <head>
                <meta name="layout" content="main" />
                <title>Login</title>
            </head>
        </head>

        <body>
            <div class="columns is-mobile is-centered">

                <div class="column is-half">

                    <g:if test='${flash.message}'>
                        <div class="notification is-light pt-3 pb-3">
                            <span>
                                ${flash.message}
                            </span>
                        </div>
                        <br>
                    </g:if>

                    <form action="${postUrl ?: '/login/authenticate'}" method="POST">

                        <div class="card">
                            <header class="card-header">
                                <p class="card-header-title">Login</p>
                            </header>
                            <div class="card-content">
                                <div class="content">
                                    <div class="field">
                                        <label for="email" class="label">Username</label>
                                        <div class="control has-icons-left">
                                            <input id="email" name="${usernameParameter ?: 'username'}" type="text" placeholder="e.g. bobsmith" class="input" required>
                                            <span class="icon is-small is-left">
                                                <i class="fa fa-user"></i>
                                            </span>
                                        </div>
                                    </div> <!-- email field -->

                                    <div class="field">
                                        <label for="password" class="label">Password</label>
                                        <div class="control has-icons-left">
                                            <input id="password" name="${passwordParameter ?: 'password'}" type="password" placeholder="*******" class="input" required>
                                            <span class="icon is-small is-left">
                                                <i class="fa fa-lock"></i>
                                            </span>
                                        </div>
                                    </div> <!-- password field -->
                                </div>
                            </div>
                            <footer class="card-footer">
                                <g:submitButton name="login" class="button is-text card-footer-item" value="Login"/>
                                <g:link class="card-footer-item" action="register" controller="user">Register</g:link>
                            </footer>
                        </div>
                    </form>
                </div>
            </div>

        </body>
</html>