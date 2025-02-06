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

            User user = new User(name: u.name, dateJoined: new Date())

            user.role = RandomUtils.randomize(2) == 0? User.Role.USER : User.Role.LIBRARIAN

            userDaoService.save(user)
            userList.add(user)
        }

        data.books.each { b ->

            int copies = RandomUtils.randomize(5)

            for(int i = 0; i <= copies; i++) {

                Book book = new Book(title: b.title,
                        author: b.author,
                        isbn: b.isbn,
                        status: generateStatus(RandomUtils.randomize(2)),
                        description: b.description)

                bookDaoService.save(book)
            }
        }
    }

    def destroy = {
    }

    private Book.Status generateStatus(int num) {
        switch (num){
            case 0:
                return Book.Status.SHELVED
            case 1:
                return Book.Status.ARCHIVED
        }

        return Book.Status.COMING
    }

}
