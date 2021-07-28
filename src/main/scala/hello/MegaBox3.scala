package hello

import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.Node
import java.util.Collection
import collection.JavaConverters.asJavaCollectionConverter
import javafx.collections.ObservableList
import javafx.scene.layout.Pane
import scala.math.Ordering.Double.TotalOrdering

object Extensions {
  extension (v: Pane)
    def children = v.getChildren
    def children_=(c: Iterable[Node]): Boolean =
      children.setAll(c.asJavaCollection)

  case class Sam(s: String)

  extension (s: Sam) def addP: String = s.s + "P"

  System.out.println(Sam("a.").addP)
  val s2: Sam = new Sam("toto") {
    System.out.println(this.addP)
  }
}

object MegaBox3 {
  import Extensions._

  def get(): scalafx.scene.layout.HBox =
    val txt = "toto3"
    val jvbox = VBox()
    jvbox.getChildren.add(new Button("titi3"))
    jvbox.children.add(new Button("tata3"))
    jvbox.children = Seq(new Button("sisi3"), new Button("sasa4"))
    val testB = VBox()
    testB.children = Seq(new Button("pipi"), new Button("papa"))

    val jvbox2 = new VBox {
      this.children = Seq(new Button("baba3"))
      children(this).add(new Button("bubu3"))
      getChildren.add(new Button("bibi3"))
    }

    val hbox = new scalafx.scene.layout.HBox {
      spacing = 10
      children = Seq(
        new scalafx.scene.control.Button("tyty3") {
          text = "pouet"
        },
        scalafx.scene.layout.VBox(jvbox),
        scalafx.scene.layout.VBox(jvbox2)
      )
    }

    val hbox2 = scalafx.scene.layout.HBox()
    hbox2.spacing = 10

    println(myMacro("toto"))
    return hbox
}
