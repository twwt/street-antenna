/**
 * Created by taishi on 2016/01/26.
 */
trait Scrap {
  val baseUrl: String
  val ignoreRegex: String
  val fetchRegex: String
  val cache: String
  val threads: Int
}
