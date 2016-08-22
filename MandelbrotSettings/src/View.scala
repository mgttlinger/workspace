
import java.awt.event.{ActionEvent, ActionListener, FocusEvent, FocusListener}
import java.awt.{Container, Dimension, GridLayout}
import javax.swing.{JTextField, _}


case class View(model: Model) extends JFrame("MandelbrotSettings") {

  case class HintTextField(hint: String) extends JTextField(hint) with FocusListener {
    var showingHint = true
    super.addFocusListener(this)

    override def focusGained(e: FocusEvent) =
      if (this.getText().isEmpty()) {
        super.setText("")
        showingHint = false
      }

    override def focusLost(e: FocusEvent) =
      if (this.getText().isEmpty()) {
        super.setText(hint)
        showingHint = true
      }

    override def getText(): String = if (showingHint) "" else super.getText()
  }

  setLayout(new GridLayout(0, 1))

  /*val viewportField = new HintTextField("Viewport")
  //controllerFrame.add(viewportField)

  val fractalsContainer = new Container
  fractalsContainer.setLayout(new GridLayout(0, 3))

  val fractals = (1 to 2) map {
    i => val fractal = HintTextField("Fractal")
      val factory = HintTextField("Factory")
      val label = new JLabel(s"f_$i = ")
      fractalsContainer.add(label)
      fractalsContainer.add(fractal)
      fractalsContainer.add(factory)
      (fractal, factory)
  }

  controllerFrame.add(fractalsContainer)*/


  val ergLabel = new JLabel(s"ERG = ")
  val erg = new HintTextField("ERG(A, B, ...)")

  val HLabel = new JLabel(s"H = ")
  val H = new HintTextField("H(l)")

  val SLabel = new JLabel(s"S = ")
  val S = new HintTextField("S(l)")

  val VLabel = new JLabel(s"V = ")
  val V = new HintTextField("V(l)")

  val equalationsContainer = new Container
  equalationsContainer.setLayout(new GridLayout(0, 2))
  equalationsContainer.add(ergLabel)
  equalationsContainer.add(erg)
  equalationsContainer.add(HLabel)
  equalationsContainer.add(H)
  equalationsContainer.add(SLabel)
  equalationsContainer.add(S)
  equalationsContainer.add(VLabel)
  equalationsContainer.add(V)
  add(equalationsContainer)

  val button = new JButton("Show")
  add(button)



  setMinimumSize(new Dimension(400, 300))
  setVisible(true)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  def addButtonListener(listener: ActionListener) = button.addActionListener(listener)
}
