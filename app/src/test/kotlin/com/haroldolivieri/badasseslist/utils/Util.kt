@file:Suppress("UNCHECKED_CAST")

import org.mockito.Mockito
import org.powermock.api.support.membermodification.MemberModifier
import java.io.File

fun nullDataOnItemsFile(javaClass: Class<*>): File = getFile(javaClass, "badasses_with_null_data.json")
fun diffItemsFile(javaClass: Class<*>): File = getFile(javaClass, "badasses_two_diff_items.json")
fun equalItemsFile(javaClass: Class<*>): File = getFile(javaClass, "badasses_two_eq_items.json")

fun <T> any(): T {
    Mockito.any<T>()
    return uninitialized()
}

private fun <T> uninitialized(): T = null as T


private fun getFile(javaClass: Class<*>, fileName: String): File {
    val path = javaClass.classLoader.getResource(fileName)
    return File(path.toURI())
}


fun callPrivateMethod(instance: Any, methodName: String, parameter: Array<Any?>): Any? {
    val method = MemberModifier.method(instance::class.java, methodName)
    method.isAccessible = true
    return method.invoke(instance, *parameter)
}