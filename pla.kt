
import java.io.File

fun makeList(file: File): List<String> =
        file.readLines().filter(String::isNotBlank).toList().map { str ->
            str.replace("\\s".toRegex(), "")
        }

fun fromQ(lines: List<String>): List<String> =
        lines.fold(listOf()) { qs: List<String>, str: String ->
            qs + str.split("&")
                    .map(String::toInt)
                    .foldIndexed("") { i, result : String, num: Int ->
                        result + when (num) {
                            0 -> "\\overline{Q_${i + 1}}"
                            else -> "Q_${i + 1}"
                        } + when (i + 1) {
                            7 -> ""
                            else -> " \\cdot "
                        }
                    }

        }

fun fromD(lines: List<String>, qs: List<String>): List<String> =
        lines.foldIndexed(listOf("", "", "", "", "", "", "")) { k, result: List<String>, str: String ->
            str.split("&")
                    .map(String::toInt)
                    .foldIndexed(listOf()) { i, list: List<String>, num: Int ->
                        list + when (num) {
                            1 -> {
                                val q: String  = when (result[i].split("\n").size) {
                                    1 -> "&=& " + qs[k] + " "
                                    else -> when (result[i].split("\n").size % 2) {
                                        1 -> "&+& " + qs[k]
                                        else -> "+ " + qs[k] + when (result[i].split("\n").size) {
                                            2 -> " \\\\"
                                            else -> " \\nonumber \\\\"
                                        }
                                    }
                                }
                                q + "\n"
                            }
                            else -> ""
                        }
                    }
                    .zip(result) { child, parent ->
                        parent + child
                    }
        }

fun main(args: Array<String>) {
    fromD(
            makeList(File("plafileD").absoluteFile)
            , fromQ(makeList(File("plafileQ").absoluteFile))
    )
            .forEachIndexed { i, str: String ->
                println("D_${i + 1} ${
                    when (i + 1) {
                        7 -> "$str \\nonumber"
                        else ->
                            when (str.split("\n").size % 2) {
                                1 -> "$str \\nonumber \\\\ "
                                else -> "$str \\nonumber \\\\ \\nonumber \\\\ "
                            }
                    }
                }")
            }
}
