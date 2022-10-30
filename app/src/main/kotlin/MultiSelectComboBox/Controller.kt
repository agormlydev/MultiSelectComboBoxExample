package MultiSelectComboBox

import java.awt.event.*
import javax.swing.JList
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener

class Controller(view: View) {
    val listenerForListLaunchButton = ListenerForListLaunchButton(view)
    val listenerForListSelectionChange = ListenerForListSelectionChange(view)
    val listenerForListFocus = ListenerForListFocus(view)
    val listenerForFrameResize = ListenerForFrameResize(view)

    init {
        view.addListeners(this)
    }

    inner class ListenerForListSelectionChange(private val view: View) : ListSelectionListener {
        override fun valueChanged(p0: ListSelectionEvent?) {
            val list = p0?.source as JList<*>
            if (list.valueIsAdjusting) {
                return
            }
            view.listSelectedValuesText.text = list.selectedValuesList.toString()
                .replace("[", "")
                .replace("]", "")
        }
    }

    inner class ListenerForListLaunchButton(private val view: View) : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            if (view.listIsVisible) {
                view.hideList()
            } else {
                view.showList()
            }
        }
    }

    inner class ListenerForFrameResize(private val view: View) : ComponentAdapter() {
        override fun componentResized(e: ComponentEvent) {
            if (!view.listIsVisible) return
            view.hideList()
            view.showList()
        }
    }


    inner class ListenerForListFocus(private val view: View) : FocusListener {
        override fun focusGained(e: FocusEvent?) {

        }

        override fun focusLost(e: FocusEvent?) {
            view.hideList()
        }

    }
}
