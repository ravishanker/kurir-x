package au.com.crazybean.mobilex.kurir.repository.tasks.cloud

import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.data.model.Contact
import au.com.crazybean.mobilex.kurir.data.model.Parcel
import au.com.crazybean.mobilex.kurir.data.model.Task
import au.com.crazybean.mobilex.kurir.repository.tasks.TasksSource
import au.com.crazybean.mobilex.kurir.storage.CloudStorage

private const val PATH = "tasks"

// For Task
private const val kOrigin = "origin"
private const val kDest = "dest"
private const val kOwner = "owner"
private const val kPicker = "picker"
private const val kParcel = "parcel"
private const val kTimestamp = "timestamp"
private const val kRecipient = "recipient"

// For Address
private const val kStreet = "street"
private const val kSuburb = "suburb"
private const val kPostal = "postal"
private const val kCity = "city"
private const val kState = "awareness"
private const val kCountry = "country"

// For Parcel
private const val kDesc = "desc"
private const val kCategory = "category"
private const val kStatus = "status"
private const val kImageUrls = "imageUrls"

// For Contact
private const val kName = "name"
private const val kMobile = "mobile"
private const val kEmail = "email"

class CloudTasksSource(private val storage: CloudStorage) : TasksSource {
    override fun getTasks(picker: String?, completion: (List<Task>?) -> Unit) {
        storage.readArray(PATH, completion = { entities, throwable ->
            Logger.d(throwable)
            entities?.map { entity ->
                entity.toTask
            }?.let { result ->
                val response = picker?.takeIf { it.isNotBlank() }?.let { email ->
                    result.filter { it.picker.equals(email, true) }
                }?: result
                completion(response)
            }?: completion(null)
        })
    }

    override fun pickTask(task: Task, picker: String, completion: (Boolean) -> Unit) {
        // Remove the task from common pool to picker it self.
        deleteTask(task) { success ->
            if (success) {
                // Add the new task onto my
                createTask(task, picker) { result ->
                    completion(result)
                }
            } else {
                completion(false)
            }
        }
    }

    override fun createTask(task: Task, forPicker: String?, completion: (Boolean) -> Unit) {
        storage.writeData("$PATH/${task.timestamp}", task.toMap) { success, throwable ->
            Logger.d(throwable)
            completion(success && throwable == null)
        }
    }

    override fun deleteTask(task: Task, completion: (Boolean) -> Unit) {
        storage.delete("$PATH/${task.timestamp}") { success, throwable ->
            Logger.d(throwable)
            completion(success && throwable == null)
        }
    }

    private val Map<String, Any?>.toTask: Task
        get() = Task(
            (get(kOrigin) as Map<String, Any?>).toAddress,
            (get(kDest) as Map<String, Any?>).toAddress,
            get(kOwner) as String,
            (get(kParcel) as Map<String, Any?>).toParcel,
            get(kTimestamp) as Long,
            get(kPicker) as String?,
            (get(kRecipient) as Map<String, Any?>?)?.toContact
        )

    private val Map<String, Any?>.toAddress: Address
        get() = Address(
            get(kStreet) as String?,
            get(kSuburb) as String?,
            get(kPostal) as String?,
            get(kCity) as String?,
            get(kState) as String?,
            get(kCountry) as String?
        )

    private val Map<String, Any?>.toParcel: Parcel
        get() = Parcel(
            get(kDesc) as String,
            get(kCategory) as String,
            get(kStatus) as String,
            get(kImageUrls) as List<String>?
        )

    private val Map<String, Any?>.toContact: Contact
        get() = Contact(
            get(kName) as String,
            get(kMobile) as String,
            get(kEmail) as String?
        )

    private val Task.toMap: Map<String, Any?>
        get() = mapOf(
            Pair(kOrigin, origin.toMap),
            Pair(kDest, dest.toMap),
            Pair(kOwner, owner),
            Pair(kPicker, picker),
            Pair(kParcel, parcel.toMap),
            Pair(kTimestamp, timestamp),
            Pair(kRecipient, recipient?.toMap ?: emptyMap())
        )

    private val Address.toMap: Map<String, Any?>
        get() = mapOf(
            Pair(kStreet, street),
            Pair(kSuburb, suburb),
            Pair(kPostal, postal),
            Pair(kCity, city),
            Pair(kState, state),
            Pair(kCountry, country)
        )

    private val Parcel.toMap: Map<String, Any?>
        get() = mapOf(
            Pair(kDesc, desc),
            Pair(kCategory, category),
            Pair(kStatus, status),
            Pair(kImageUrls, imageUrls)
        )

    private val Contact.toMap: Map<String, Any?>
        get() = mapOf(
            Pair(kName, name),
            Pair(kMobile, mobile),
            Pair(kEmail, email)
        )
}