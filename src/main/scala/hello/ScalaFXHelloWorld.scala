package hello

import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text
import scalafx.scene.control.Button
import scalafx.scene.Node
import scalafx.scene.layout.VBox
import scalafx.scene.control.Separator
import scalafx.scene.control.CheckBox
import scalafx.scene.control.Label
import scalafx.geometry.Orientation.Vertical
import scalafx.collections.ObservableBuffer
import javafx.scene.layout.Pane

object ScalaFXHelloWorld extends JFXApp3 {
  extension [G](ob: ObservableBuffer[G])
    def <<(g: G): ObservableBuffer[G] =
      ob.add(g)
      ob

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      //    initStyle(StageStyle.Unified)
      title = "ScalaFX Hello World"
      scene = new Scene {
        fill = Color.rgb(230, 230, 230)
        content = new HBox {
          padding = Insets(10, 20, 10, 20)
          spacing = 10
          children
            << Button("butt")
            << (new VBox {
              spacing = 10
              children <<
                new HBox {
                  padding = Insets(10)
                  spacing = 10
                  children
                    << Label("propertie")
                    << (new Button("button"):
                      onAction = _ => ca.disable = !ca.disable()
                    )
                    << Label("Hello!")
                }
              val ca = new CheckBox("check") { disable = false }
              val cb = new CheckBox("second"):
                id = "cb"
                disable = !ca.disable()
                disable.addListener({ (_, _, newVal) =>
                  ca.disable() = !newVal
                })
                ca.disable.addListener({ (_, _, newVal) =>
                  disable() = !newVal
                })
              children << ca << cb
            })
            << (new Button("late button") {
              onAction = _ => {
                val cb =
                  CheckBox(
                    stage
                      .getScene()
                      .lookup("#cb")
                      .asInstanceOf[javafx.scene.control.CheckBox]
                  )
                cb.disable = !cb.disable()
                // action()
              }
            })
            << (new Separator { orientation = Vertical })
            << MegaBox3.get()
        }
      }
    }
  }

  def action() =
    val cb = CheckBox(
      stage
        .getScene()
        .lookup("#cb")
        .asInstanceOf[javafx.scene.control.CheckBox]
    )
    cb.disable = !cb.disable()

}

/*

VBox:
  spacing = 10
  children =
    HBox:
      children =
        Label("toto")
        Button("but"):
          onAction = _ => println("pressed")
        CheckBox("check"):
          disable = false
      children
        << Label("hello")
        << Button("toto"):
            some = other
    Separator:
      orientation = Vertical
    CheckBox

 */
