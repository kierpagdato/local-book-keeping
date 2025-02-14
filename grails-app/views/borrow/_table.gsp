<%@ page import="com.bookkeeping.book.Book" %>
<table class="table is-hoverable is-fullwidth">
    <thead>
        <tr>
            <g:sortableColumn property="transactionId" title="Transaction ID" />
            <g:sortableColumn property="dateBorrowed" title="Borrowed date" />
            <g:sortableColumn property="dateReturned" title="Return date" />
            <g:sortableColumn property="status" title="Status" />
            <g:sortableColumn property="type" title="Type" />
        </tr>
    </thead>
    <tbody>
        <g:each in="${list}" var="bean">
            <tr>
                <td>
                    <g:link method="GET" action="receipt" id="${bean.transactionId}">
                        ${bean.transactionId}
                    </g:link>
                </td>
                <td>
                    ${bean.dateBorrowed}
                </td>
                <td>
                    ${bean.dateReturned}
                </td>
                <td>
                    <span class="tag ${bean.status.color}">${bean.status.text}</span>
                </td>
                </td>
                <td>
                    ${bean.type.text}
                </td>
            </tr>
        </g:each>
    </tbody>
</table>