class Neuron(
  val value: Int,
  private val pixelCount: Int
) {

  val weights: IntArray = IntArray(pixelCount)
  var activationValue: Int = 8
  var inputs: IntArray = IntArray(pixelCount)
  var output: Boolean = false

  fun sum() {
    var sum = 0
    for (i in 0 until pixelCount) {
      sum += inputs[i] * weights[i]
    }
    output = sum >= activationValue
  }

  fun reduce() {
    for (i in 0 until pixelCount) {
      if (inputs[i] == 1) {
        weights[i] -= 1
      }
    }
  }

  fun increase() {
    for (i in 0 until pixelCount) {
      if (inputs[i] == 1) {
        weights[i] += 1
      }
    }
  }


}