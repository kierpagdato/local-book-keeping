package bookkeeping

import com.bookkeeping.Book
import com.bookkeeping.User
import com.bookkeeping.enums.BookStatus
import com.bookkeeping.models.InitData
import com.bookkeeping.utils.JsonUtils
import com.bookkeeping.utils.RandomUtils

class BootStrap {

    def dataHolder;

    def init = { servletContext ->

        String contents = dataHolder.resource.file.text
        InitData data = JsonUtils.readValue(contents, InitData.class)

        def userList = new ArrayList()

        data.users.each { u ->
            User user = new User(name: u.name)
            user.save()
            userList.add(user);
        }

        data.books.each { b ->
            new Book(title: b.title,
                    author: userList.get(RandomUtils.randomize(data.users.size())),
                    status: BookStatus.IN)
                    .save()
        }
    }

    def destroy = {
    }

}
