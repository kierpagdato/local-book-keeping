<%@ page import="com.bookkeeping.security.Role" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Create user</title>
    </head>
    <body>

        <div class="columns is-mobile is-centered">

            <div class="column is-half">


                <g:form resource="${this.user}" method="POST">

                    <div class="card">
                        <header class="card-header">
                            <p class="card-header-title">Create user</p>
                        </header>
                        <div class="card-content">
                            <div class="content">

                                <g:render template="form_no_auth" model="[create:true]"/>

                                <div class="field">
                                    <label class="label">Roles</label>
                                    <div class="control">
                                        <div class="select is-multiple">
                                            <g:select name="roles" id="roles"
                                                      from="${Role.list()}"
                                                      optionKey="id"
                                                      optionValue="text"
                                                      multiple="multiple"/>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
                        <footer class="card-footer">
                            <g:submitButton name="create" class="button is-text card-footer-item" value="Create" />
                        </footer>
                    </div>
                </g:form>
            </div>
        </div>

    </body>
</html>