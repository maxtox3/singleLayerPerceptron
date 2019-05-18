import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImageProcessor(
  private val path: String
) {

  var pixelCount: Int = 0

  fun processImages(): List<BinaryImage> {
    val imagesToProcess: MutableList<File> = mutableListOf()
    for (i in 0 until 10) {
      imagesToProcess.add(File("$path/$i.png"))
    }

    val result = mutableListOf<BinaryImage>()
    imagesToProcess.forEach {
      var bufImage = ImageIO.read(it)
      //преобразуем
      bufImage = binarizeImage(bufImage)
      //првращаем в список нулей и единиц - суем в модель
      result.add(transformToZerosAndOnes(bufImage))
    }
    return result
  }

  private fun binarizeImage(img_param: BufferedImage): BufferedImage {
    val image = BufferedImage(
      img_param.width,
      img_param.height,
      BufferedImage.TYPE_BYTE_BINARY
    )

    val g = image.graphics
    g.drawImage(img_param, 0, 0, null)
    g.dispose()

    return image
  }

  private fun transformToZerosAndOnes(bufImage: BufferedImage): BinaryImage {
    val sampleModel = bufImage.sampleModel
    val width = sampleModel.width
    val height = sampleModel.height
    pixelCount = width * height
    val data = bufImage.data

    val listOfZerosAndOnes = mutableListOf<Int>()

    for (y in 0 until height) {
      for (x in 0 until width) {
        val r = data.getSample(x, y, 0)
        listOfZerosAndOnes.add(if (r == 0) 1 else 0)
      }
    }
    return BinaryImage(listOfZerosAndOnes)
  }

}