import scala.collection.mutable.ArrayBuffer

object Twins {

  def main(args: Array[String]): Unit = {
    val a = Array("cdab", "dcba")
    val b = Array("abcd", "abcd")
    //println(twins(a, b).toList)
    val array = Array(5, 1, 2, 4, 3, 5, 3, 6, 9, 12)
    val rotate = Array(6, 9, 7)
    println(getMaxElementIndices(array, rotate).toList)
  }

  def twins(a: Array[String], b: Array[String]): Array[String] = {
    var op = ArrayBuffer[String]()
    for (i <- a.indices) {
      val e1 = a(i)
      val e2 = b(i)
      if (e1.length != e2.length) {
        "NO"
      } else if (e1 == e2) {
        "Yes"
      } else {
        val oddElements = e1.zipWithIndex.filter(_._2 % 2 == 1).map(_._1).mkString
        val evenElements = e1.zipWithIndex.filter(_._2 % 2 == 0).map(_._1).mkString
        val swapped = oddElements + evenElements
        println(swapped)
        if (swapped == e2) {
          op += "Yes"
        } else {
          op += "No"
        }
      }
    }
    op.toArray
  }

  def rotateArray(a: Array[Int], i: Int) = a.drop(i) ++ a.take(i)

  def maxElement(a: Array[Int]) = {println(a.toList); a.indexOf(a.max)}
  
  def getMaxElementIndices(a: Array[Int], rotate: Array[Int]): Array[Int] = {
    rotate.map(e => maxElement(rotateArray(a, e)))
  }

}