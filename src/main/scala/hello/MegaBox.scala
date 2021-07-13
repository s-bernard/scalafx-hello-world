package hello

import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.Node
import java.util.Collection
import collection.JavaConverters.asJavaCollectionConverter

object MegaBox {
  implicit class VBoxImprovements(val v: VBox) {
      def children = v.getChildren
      def children_=(c: Iterable[Node]) = {
        children.setAll(c.asJavaCollection)
      }

  }

  def get(): scalafx.scene.layout.HBox =
    val txt = "toto"
    val jvbox = new VBox
    jvbox.getChildren.add(new Button("titi"))
    jvbox.children.add(new Button("tata"))
    jvbox.children = Seq(new Button("sisi"), new Button("sasa"))

    val jvbox2 = new VBox {
      getChildren.add(new Button("bibi"))
      // children.add(new Button("bubu"))
      // children = Seq(new Button("baba"))
    }

    val hbox = new scalafx.scene.layout.HBox {
      children = Seq(
        new scalafx.scene.control.Button("tyty"),
        new scalafx.scene.layout.VBox(jvbox),
        new scalafx.scene.layout.VBox(jvbox2)
      )
    }

    return hbox
}
