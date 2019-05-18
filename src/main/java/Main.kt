import java.nio.file.Paths

object Main {

  private lateinit var imageProcessor: ImageProcessor
  private lateinit var perceptron: Perceptron

  private lateinit var images: List<BinaryImage>
  private lateinit var neurons: MutableList<Neuron>

  @JvmStatic
  fun main(args: Array<String>) {
    init()

    menu()
  }

  fun init() {
    val path = Paths.get(".").toAbsolutePath().normalize().toString() + "/text/"
    imageProcessor = ImageProcessor(path)
    images = imageProcessor.processImages()
    neurons = mutableListOf()
    val pixelCount = imageProcessor.pixelCount
    for (i in 0 until 10) {
      neurons.add(Neuron(i, pixelCount))
    }

    perceptron = Perceptron(images, neurons)
  }

  private fun menu() {

    val chosenPoint = readConsoleInputToInt(
      "Меню:\n" +
        "1 - Обучение:\n" +
        "2 - Тест\n" +
        "3 - Тестовая выборка"
    )
    when (chosenPoint) {
      1 -> perceptron.learn(this::menu)
      2 -> perceptron.test(this::menu)
      3 -> perceptron.example(this::menu)
    }
  }


}
