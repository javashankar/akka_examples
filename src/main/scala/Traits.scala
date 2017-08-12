

class Traits extends EmptyBody {

  def empty {
    println("empty")
  }

  def main(args: Array[String]): Unit = {
    empty
  }
}

trait MarkerTrait

trait EmptyBody {
  def empty
}
