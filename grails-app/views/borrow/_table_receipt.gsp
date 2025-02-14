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
                    ${bean.book.title}
                </td>
            </tr>
        </g:each>
    </tbody>
</table>