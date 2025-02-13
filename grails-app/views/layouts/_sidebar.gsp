<aside class="menu">
    <p class="menu-label">General</p>
    <ul class="menu-list">
        <li>
            <g:link controller='book' action='index' class="${page == 'books'? 'is-active' : ''}">Books</g:link>
        </li>
        <li><a class="${page == 'borrow'? 'is-active' : ''}">Borrow</a></li>
    </ul>

    <sec:ifAnyGranted roles="ROLE_LIBRARIAN">
        <p class="menu-label">Librarian</p>
        <ul class="menu-list">
            <li>
                <g:link controller='user' action='create' class="${page == 'userCreate'? 'is-active' : ''}">Create user</g:link>
            </li>
            <li>
                <g:link controller='user' action='index' class="${page == 'userList'? 'is-active' : ''}">User masterlist</g:link>
            </li>
        </ul>
    </sec:ifAnyGranted>
</aside>