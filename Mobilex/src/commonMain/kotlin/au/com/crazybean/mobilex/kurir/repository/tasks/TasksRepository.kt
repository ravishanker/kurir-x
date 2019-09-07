package au.com.crazybean.mobilex.kurir.repository.tasks

import au.com.crazybean.mobilex.foundation.repository.Repository
import au.com.crazybean.mobilex.kurir.repository.tasks.network.TasksNetworkSource

class TasksRepository(private val dbSource: TasksSource?,
                      private val networkSource: TasksSource? = TasksNetworkSource()) : Repository()