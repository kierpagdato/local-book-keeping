import com.bookkeeping.services.MyUserDetailsService
import com.bookkeeping.security.UserPasswordEncoderListener

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)

    userDetailsService(MyUserDetailsService)
}