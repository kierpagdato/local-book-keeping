import com.bookkeeping.my_user.MyUserDetailsService
import com.bookkeeping.security.UserPasswordEncoderListener

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)

    userDetailsService(MyUserDetailsService)
}