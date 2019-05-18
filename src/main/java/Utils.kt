fun readConsoleInputToInt(beforeOutput: String): Int {
  println(beforeOutput)
  try {
    val result = readLine()
    return if (result?.isNotEmpty() == true) {
      return if (result.toInt() <= 0) {
        showError(beforeOutput)
      } else {
        result.toInt()
      }
    } else {
      showError(beforeOutput)
    }

  } catch (e: Exception) {
    return showError(beforeOutput)
  }
}

fun showError(beforeOutput: String): Int {
  println("Может все-таки число представленное в менюшке?)")
  return readConsoleInputToInt(beforeOutput)
}

fun readFromZeroToNine(): Int {
  return try {
    val a = readLine()?.toInt() ?: 0
    if (a < 0 || a > 9) {
      readFromZeroToNine()
    } else {
      a
    }
  } catch (e: Exception) {
    e.printStackTrace()
    0
  }
}