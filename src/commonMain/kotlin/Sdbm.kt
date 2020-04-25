package sdbm

import kotlinx.cinterop.cstr
import platform.posix.O_CREAT
import platform.posix.O_RDWR
import platform.posix.S_IRGRP
import platform.posix.S_IROTH
import platform.posix.S_IRUSR
import platform.posix.S_IWUSR
import sdbm.cinterop.dbm_open

fun main() {
    val dbm = dbm_open("hello".cstr, O_RDWR or O_CREAT, S_IRUSR or S_IWUSR or S_IRGRP or S_IROTH)
}
