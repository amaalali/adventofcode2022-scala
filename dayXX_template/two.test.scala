import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class TwoSpec extends AnyFreeSpec {
  def testData = loadData.run("test_data")

  "run" ignore {
    "test" in {
      val result = two.run(testData)

      result mustEqual -1
    }
  }
}
