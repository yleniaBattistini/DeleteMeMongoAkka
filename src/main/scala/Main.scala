import org.mongodb.scala._
import Helpers._
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.Updates.set
import spray.json.JsObject

//TODO: Conviene sostituire con id o codice fiscale SONO BELLO
trait Repository {

  def listCollection(): Unit

  def findPatient(namePatient: String): JsObject

  def updatePatient(namePatient: String,
                    fieldToChange: String,
                    valueToChange: String): JsObject
}

case class RepositoryImpl(databaseName: String = "Hospital")
    extends Repository {
  private val client = MongoClient()
  private val db = client.getDatabase(databaseName)

  override def findPatient(namePatient: String): JsObject = {
    val coll: MongoCollection[BsonDocument] =
      db.getCollection[BsonDocument]("Pazienti")
    coll.find(equal("name", "Enrico")).JsonResults()
  }

  override def updatePatient(namePatient: String,
                             fieldToChange: String,
                             valueToChange: String): JsObject = {
    val coll: MongoCollection[BsonDocument] =
      db.getCollection[BsonDocument]("Pazienti")
    //Set only one property
    coll
      .updateOne(equal("name", namePatient), set(fieldToChange, valueToChange))
      .JsonResults()
  }

  override def listCollection(): Unit = db.listCollectionNames().printResults()

}

object TryRepository extends App {
  val repo = RepositoryImpl()
  println(repo.findPatient("Enrico").prettyPrint)

}

object Read extends App {
  private val client = MongoClient()
  private val db = client.getDatabase("Hospital")
  val coll: MongoCollection[BsonDocument] =
    db.getCollection[BsonDocument]("Pazienti")

  println("Print result with println")
  coll.find(equal("name", "Enrico")).printResults()

  println("Print result from JSON")
  println(coll.find(equal("name", "Enrico")).JsonResults().prettyPrint)
}

object WritePatient extends App {
  private val client = MongoClient()
  private val db = client.getDatabase("Hospital")
  val coll: MongoCollection[BsonDocument] =
    db.getCollection[BsonDocument]("Pazienti")

  val document = BsonDocument(
    "name" -> "Café Con Leche",
    "contact" -> Document("phone" -> "228-555-0149",
                          "email" -> "cafeconleche@example.com",
                          "location" -> Seq(-73.92502, 40.8279556)),
    "stars" -> 3,
    "categories" -> Seq("Bakery", "Coffee", "Pastries")
  )

  //val document1 = BsonDocument.apply(JSONFILE)
  coll.insertOne(document).printResults()

  coll
    .updateOne(equal("name", "Enrico"), set("name", "Marco"))
    .printHeadResult("Update Result: ")

}
