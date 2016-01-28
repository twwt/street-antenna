/**
 * Created by taishi on 2016/01/27.
 */
class FashionSnapScrap(html: String) extends Site {
  val siteId = 0
  val tags = getTags(html, _.\\( """*[@class="col"][1]""").\( """ul[@class="text"]""").\("li"))
}

object FashionSnapCrawl extends Scrap {
  val baseUrl = """http://www.fashionsnap.com/streetsnap/"""
  val ignoreRegex = """.*(\\.(css|xml|js|gif|jpg|png|mp3|mp3|zip|gz))$|^http://www.fashionsnap.com/streetsnap"""
  val fetchRegex = """^http://www.fashionsnap.com/streetsnap/2016-01-26/68536/"""
  val cache = "./cache"
  val threads = 1
}