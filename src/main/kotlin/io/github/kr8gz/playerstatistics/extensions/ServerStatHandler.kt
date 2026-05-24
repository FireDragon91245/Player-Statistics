package io.github.kr8gz.playerstatistics.extensions

import io.github.kr8gz.playerstatistics.access.ServerStatHandlerAccess
import net.minecraft.stat.ServerStatHandler
import net.minecraft.stat.Stat
import org.apache.commons.io.FilenameUtils
import java.io.File
import java.nio.file.Path
import java.util.UUID

object ServerStatHandler {
    val ServerStatHandler.uuid: UUID
        get() = statsFileName
            .let(FilenameUtils::getBaseName)
            .let(UUID::fromString)

    val ServerStatHandler.statsFileName: String
        get() = reflectivePath("path")?.fileName?.toString()
            ?: reflectiveFile("file")?.name
            ?: error("Unsupported ServerStatHandler implementation: neither 'path' nor 'file' field is available")

    @Suppress("UNCHECKED_CAST")
    private fun <T> ServerStatHandler.reflectiveField(name: String): T? {
        return runCatching {
            javaClass.getDeclaredField(name).apply { isAccessible = true }.get(this) as? T
        }.getOrNull()
    }

    private fun ServerStatHandler.reflectivePath(name: String): Path? = reflectiveField(name)
    private fun ServerStatHandler.reflectiveFile(name: String): File? = reflectiveField(name)

    fun ServerStatHandler.takeChangedStats(): Map<Stat<*>, Int> {
        return (this as ServerStatHandlerAccess).takeChangedStats()
    }
}
