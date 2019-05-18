class Perceptron(
  private val images: List<BinaryImage>,
  private val neurons: MutableList<Neuron>
) {


  fun learn(menu: () -> Unit) {
    val count = readConsoleInputToInt("Введите количество эпох")
    for (i in 0 until count) {
      epoch(i)
    }
    menu.invoke()
  }

  private fun epoch(epochIndex: Int) {
    for (i in 0 until 10000) { //todo почему именно это число?
      val random = (0..9).shuffled().first() //случайное число, которое будем отдавать нейронам на вход
      for (j in 0 until 10) {
        val neuron = neurons[j]
        //достаем изображение и отдаем его как инпут для нейрона
        neuron.inputs = images[random].binaryPixels.toIntArray()
        neuron.sum()
        if (random != neuron.value) {
          if (neuron.output) neuron.reduce()
        } else {
          if (!neuron.output) neuron.increase()
        }
      }
    }
    println("Эпоха $epochIndex пройдена успешно")
    for (i in 0 until 10) {
      println(neurons[i].value)
      neurons[i].weights.forEach { print(it) }
      println()
    }
  }

  fun test(menu: () -> Unit) {
    println("Какое число будем проверять? от 0 до 9, пожалуйста")
    val digit = readFromZeroToNine()
    for (i in 0 until 10) {
      val neuron = neurons[i]
      neuron.inputs = images[digit].binaryPixels.toIntArray()
      neuron.sum()
      if (neuron.output) {
        println("БИНГО! Шайтан машина работает :) \nЧисло = ${neuron.value}")
      } else {
        println("у тебя проблемы, число $i не подходит")
      }
    }
    menu.invoke()
  }

  fun example(menu: () -> Unit) {
    val testMas = arrayOf(
      intArrayOf(1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1),
      intArrayOf(1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1),
      intArrayOf(1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1),
      intArrayOf(1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1),
      intArrayOf(1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1),
      intArrayOf(1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1)
    )
    for (j in 0 until testMas.size) {
      for (i in 0 until 10) {
        val neuron = neurons[i]
        neuron.inputs = testMas[j]
        neuron.sum()
        if (neuron.output) {
          println("Число = ${neuron.value}")
        } else {
          println("у тебя проблемы, число $i не подходит")
        }
      }
      menu.invoke()
    }
  }

}