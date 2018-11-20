
import java.io.File

fun main(args: Array<String>) {
    val file1 = File("plafile").absoluteFile
    val file2 = File("plafile2").absoluteFile

    val lines = file1.readLines().filter(String::isNotBlank).toList()
    val lines2 = file2.readLines().filter(String::isNotBlank).toList()

    val qs: ArrayList<String> = ArrayList()
    val ds: ArrayList<String> = arrayListOf("", "", "", "", "", "", "")

    for (str: String in lines) {
        var i: Int = 1
        var result = ""
        for (num: Int in str.split("&").map{x -> x.replace(Regex("\\s"), "").toInt()}) {
            result += when (num) {
                0 -> "\\overline{Q_$i}"
                else -> "Q_$i"
            }
            result += when (i) {
                7 -> ""
                else -> " \\cdot "
            }
            i++
        }
        qs.add(result)
    }

    var k: Int = 0
    var j: ArrayList<Int> = arrayListOf(-1, -1, -1, -1, -1, -1, -1)
    for (str: String in lines2) {
        var i: Int = 0
        for (num: Int in str.split("&").map{x -> x.replace(Regex("\\s"), "").toInt()}) {
            ds[i] += when (num) {
                1 -> {
                    var q: String  = when (j[i]) {
                        1 -> "&+& " + qs[k] + " "
                        -1 -> "&=& " + qs[k] + " "
                        0 ->  "+ " + qs[k] + " \\\\ "
                        else -> "" + qs[k]
                    }
                    j[i] = when (j[i]) {
                        -1 -> 0
                        else -> 1 - j[i]
                    }
                    q + "\n"
                }
                else -> ""
            }
            i++
        }
        k++
    }

    var i: Int = 1
    for (str: String in ds) {
        print("D_$i " + str)
        i++
    }
}
