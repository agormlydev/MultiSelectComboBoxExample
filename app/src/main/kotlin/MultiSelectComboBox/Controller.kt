package MultiSelectComboBox

import javax.swing.JList
import javax.swing.ListSelectionModel
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener

class Controller(view: View) {
    val listener = Listener(view)

    init {
        view.addActionListeners(this)
    }

    inner class Listener(val view: View) : ListSelectionListener {
        override fun valueChanged(p0: ListSelectionEvent?) {
            val list = p0?.source as JList<*>
            if (list.valueIsAdjusting) {
                return
            }
            println(list.selectedValuesList)
        }
    }
}