package bookkeeping

import com.bookkeeping.Book
import com.bookkeeping.dao.BookDaoService
import com.bookkeeping.User
import com.bookkeeping.dao.UserDaoService
import com.bookkeeping.models.InitData
import com.bookkeeping.utils.JsonUtils
import com.bookkeeping.utils.RandomUtils

class BootStrap {

    def dataHolder;
    UserDaoService userDaoService
    BookDaoService bookDaoService

    def init = { servletContext ->

        String contents = dataHolder.resource.file.text
        InitData data = JsonUtils.readValue(contents, InitData.class)

        def userList = new ArrayList()

        data.users.each { u ->

            User user = new User(name: u.name)
            userDaoService.save(user)
            userList.add(user)
        }

        data.books.each { b ->
            bookDaoService.save(
                    new Book(title: b.title,
                            author: b.author,
                            status: Book.Status.In,
                            quantity: RandomUtils.randomize(3) + 1,
                            description: b.description))
        }
    }

    def destroy = {
    }

}
