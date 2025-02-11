<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>
            <g:layoutTitle default="Grails"/>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

        <asset:stylesheet src="bookkeeping.css"/>
        <link rel="stylesheet" href="https://bulma.io/vendor/fontawesome-free-6.5.2-web/css/all.min.css">

        <g:layoutHead/>
    </head>

    <body>

        <nav class="navbar" role="navigation" aria-label="main navigation">
          <div class="navbar-brand">
            <a class="navbar-item" href="/">
              <asset:image src="grails.svg" alt="Grails Logo"/>

            </a>

            <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
              <span aria-hidden="true"></span>
              <span aria-hidden="true"></span>
              <span aria-hidden="true"></span>
              <span aria-hidden="true"></span>
            </a>
          </div>

          <div id="navbarBasicExample" class="navbar-menu">
            <div class="navbar-start">

            </div>
            <div class="navbar-search">
                <g:form method="GET" controller="book" action="index">
                    <div class="navbar-item">
                        <div class="field has-addons is-expanded">
                          <p class="control">
                            <input class="input" type="text" id="keyword" name="keyword" placeholder="Search book" value="${params?.keyword}">
                          </p>
                          <p class="control">
                            <button class="button is-info">
                              Search
                            </button>
                          </p>
                        </div>
                    </div>
                </g:form>
            </div>

            <div class="navbar-end">
                <sec:ifLoggedIn>
                    <g:link class="navbar-item" controller='account' action='edit'>
                            Welcome Back <sec:loggedInUserInfo field='firstName'/>!
                    </g:link>
                    <div class="navbar-item">
                        <div class="buttons">
                            <form action="/logout" method="POST">
                                <g:submitButton name="logout" class="button" value="Logout" />
                            </form>
                        </div>
                    </div>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <div class="navbar-item">
                        <div class="buttons">
                            <g:link class="button is-primary" controller='login' action='auth'>Login</g:link>
                        </div>
                    </div>
                </sec:ifNotLoggedIn>

            </div>
          </div>
        </nav>

    <div class="columns ml-4 mr-4 mt-2">

        <sec:ifLoggedIn>
            <div id="app-sidebar-column"
                 class="column is-2 is-hidden-touch">
                <g:include view="/layouts/_sidebar.gsp"/>
            </div>
        </sec:ifLoggedIn>

        <div class="column pt-0">

            <g:layoutBody/>

        </div>

    </div> <!-- columns -->

        <footer class="footer">
          <div class="content has-text-centered">
            <p>
              <strong>Library:</strong>
              Find your books here.
            </p>
          </div>
        </footer>

        <div id="spinner" class="spinner" style="display:none;">
            <g:message code="spinner.alt" default="Loading&hellip;"/>
        </div>

        <asset:javascript src="application.js"/>

    </body>
</html>
