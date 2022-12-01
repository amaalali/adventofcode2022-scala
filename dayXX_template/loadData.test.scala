import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class LoadDataSpec extends AnyFreeSpec {

  "run" ignore {
    "reads the test file and returns the parsed data" in {
      loadData.run("test_data") mustEqual ???
    }
  }
}
