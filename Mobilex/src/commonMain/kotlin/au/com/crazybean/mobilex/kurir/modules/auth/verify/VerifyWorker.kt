package au.com.crazybean.mobilex.kurir.modules.auth.verify

import au.com.crazybean.mobilex.foundation.saw.Worker
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class VerifyWorker(private val repository: UsersRepository?) : Worker()