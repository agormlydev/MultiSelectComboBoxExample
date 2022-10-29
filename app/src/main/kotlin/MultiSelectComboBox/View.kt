package MultiSelectComboBox

import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.*


class View : JFrame() {
    private val listModel = DefaultListModel<String>()
    val list = JList(listModel)

    init {
        initSelf()
        initComponents()
        positionComponents()
    }

    private fun initSelf() {
        size = Dimension(800, 600)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
    }

    private fun initComponents() {
        list.apply {
            selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
        }

        listModel.apply {
            addElement("one")
            addElement("two")
            addElement("three")
            addElement("four")
            addElement("five")
        }
    }

    private fun positionComponents() {
        layout = GridBagLayout()
        val c = GridBagConstraints()
        c.weightx = 0.33
        c.weighty = 0.33
        c.gridx = 0
        c.gridy = 0
        c.gridwidth = GridBagConstraints.REMAINDER
        add(Box.createGlue(), c)
        c.gridwidth = 1
        c.gridx = 0
        c.gridy = 1
        add(Box.createGlue(), c)
        c.gridx = 1
        add(list)
        c.gridx = 2
        add(Box.createGlue(), c)
        c.gridx = 0
        c.gridy = 2
        c.gridwidth = GridBagConstraints.REMAINDER
        add(Box.createGlue(), c)
    }

    fun addActionListeners(controller: Controller) {
        list.addListSelectionListener(controller.listener)
    }
}