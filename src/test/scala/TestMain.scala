import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner


/**
 * Test for repository
 */
@RunWith(classOf[JUnitRunner])
class TestMain extends AnyFunSuite {
  test("Repository should respond an interrogation fo existing patient") {
    assert(RepositoryImpl().findPatient("Enrico").prettyPrint.length > 0)
  }
}
