

object CaseClasses {
  
  case class Account(id: Int, name: String)
  
  val input: String =
  """background-color: #A03300;
    |background-image: url(img/header100.png);
    |background-position: top center;
    |background-repeat: repeat-x;
    |background-size: 2160px 108px;
    |margin: 0;
    |height: 108px;
    |width: 100%;""".stripMargin
    
  def main(args: Array[String]): Unit = {
    val accnt_1 = Account(1, "SBI")
    val accnt_2 = accnt_1.copy(name="ICICI")
    println(accnt_1)
    println(accnt_2)
    println(input)
  }
  
}