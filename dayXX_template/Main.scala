import scala.io.Source

object Main extends App {
  def data = loadData.run("puzzle_data")
  println("Part one: " + one.run(data))
  println("Part two: " + two.run(data))
}
