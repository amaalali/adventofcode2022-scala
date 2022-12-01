import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class OneSpec extends AnyFreeSpec {
  def testData = loadData.run("test_data")

  "run" ignore {
    "test" in {
      val result = one.run(testData)

      result mustEqual -1
    }
  }
}
