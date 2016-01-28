/**
 * Created by taishi on 2016/01/27.
 */
class CrawlSiteFactory(val siteName: String) {
  def create =
    siteName match {
      case "fashionsnap" => FashionSnapCrawl
    }
}