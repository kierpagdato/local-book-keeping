<%@ page import="com.bookkeeping.book.Book" %>
<table class="table is-hoverable is-fullwidth">
    <thead>
        <tr>
            <td>Title</td>
        </tr>
    </thead>
    <tbody>
        <g:each in="${list}" var="bean">
            <tr>
                <td>

                    <g:link method="GET" resource="${bean.book}">
                        ${bean.book.title}
                    </g:link>
                </td>
            </tr>
        </g:each>
    </tbody>
</table>