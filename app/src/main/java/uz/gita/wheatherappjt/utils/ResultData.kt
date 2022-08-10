package uz.gita.wheatherappjt.utils

sealed class ResultData<out T> {
    data class Success<T>(val data: T) : ResultData<T>()
    data class Fail(val message: MessageData) : ResultData<Nothing>()

    companion object
}

sealed class MessageData {
    data class Text(val message: String) : MessageData()
    data class Resource(val resourceId: Int) : MessageData()
}

fun <T> ResultData.Companion.success(data: T) = ResultData.Success(data)
fun ResultData.Companion.fail(message: MessageData) = ResultData.Fail(message)
fun ResultData.Companion.fail(text: String) = ResultData.Fail(MessageData.Text(text))
fun ResultData.Companion.fail(resId: Int) = ResultData.Fail(MessageData.Resource(resId))

val <T> ResultData<T>.isSuccess get() = this is ResultData.Success<T>
val <T> ResultData<T>.isFail get() = this is ResultData.Fail
val <T> ResultData<T>.isText get() = isFail && (this as ResultData.Fail).message is MessageData.Text
val <T> ResultData<T>.isResource get() = isFail && (this as ResultData.Fail).message is MessageData.Resource

val <T> ResultData<T>.asSuccess get(): ResultData.Success<T> = this as ResultData.Success<T>
val <T> ResultData<T>.asFail get(): ResultData.Fail = this as ResultData.Fail
val <T> ResultData<T>.asText get():MessageData.Text = (this.asFail.message as MessageData.Text)
val <T> ResultData<T>.asResource get():MessageData.Resource = (this.asFail.message as MessageData.Resource)

fun <T> ResultData<T>.onSuccess(block: T.() -> Unit): ResultData<T> {
    if (this.isSuccess) block(this.asSuccess.data)
    return this
}

fun <T> ResultData<T>.onFail(block: ResultData.Fail.() -> Unit): ResultData<T> {
    if (this.isFail) block(this.asFail)
    return this
}

fun <T> ResultData<T>.onText(block: MessageData.Text.() -> Unit): ResultData<T> {
    if (this.isText) block(this.asText)
    return this
}

fun <T> ResultData<T>.onResource(block: MessageData.Resource.() -> Unit): ResultData<T> {
    if (this.isResource) block(this.asResource)
    return this
}