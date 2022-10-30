package MultiSelectComboBox

import java.awt.Color
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.*


class View : JFrame() {
    private val listSelectedValuesContainer = JPanel()
    val listSelectedValuesText = JTextField().apply { isEditable = false }
    private val listLaunchButton = JButton("...")
    private val listModel = DefaultListModel<String>()
    internal val list = JList(listModel)
    private val listScrollPane = JScrollPane(list)
    private val popupFactory = PopupFactory()
    private var listSelectionPopup: Popup? = null
    var listIsVisible = false

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
            addElement("zero")
            addElement("one")
            addElement("two")
            addElement("three")
            addElement("four")
            addElement("five")
            addElement("six")
            addElement("seven")
            addElement("eight")
            addElement("nine")
            addElement("ten")
            addElement("eleven")
            addElement("twelve")
            addElement("thirteen")
            addElement("fourteen")
            addElement("fifteen")
        }

        listSelectedValuesContainer.apply {
            border = BorderFactory.createLineBorder(Color.BLACK)
            layout = GridBagLayout()
            val c = GridBagConstraints()
            c.fill = GridBagConstraints.BOTH
            c.weightx = 0.90
            c.weighty = 1.00
            c.gridx = 0
            listSelectedValuesText.preferredSize = Dimension()
            add(listSelectedValuesText, c)
            c.weightx = 0.10
            c.gridx = 1
            listLaunchButton.preferredSize = Dimension()
            add(listLaunchButton, c)
        }
    }

    private fun positionComponents() {
        layout = GridBagLayout()
        val c = GridBagConstraints()
        c.weightx = 0.33
        c.weighty = 0.48
        c.fill = GridBagConstraints.BOTH
        c.gridx = 0
        c.gridy = 0
        c.gridwidth = GridBagConstraints.REMAINDER
        add(Box.createGlue(), c)
        c.weighty = 0.04
        c.gridwidth = 1
        c.gridx = 0
        c.gridy = 1
        add(Box.createGlue(), c)
        c.gridx = 1
//        add(list)
        listSelectedValuesContainer.preferredSize = Dimension()
        add(listSelectedValuesContainer, c)
        c.gridx = 2
        add(Box.createGlue(), c)
        c.weighty = 0.48
        c.gridx = 0
        c.gridy = 2
        c.gridwidth = GridBagConstraints.REMAINDER
        add(Box.createGlue(), c)
    }

    fun showList() {
        val listSelectedValueContainerAbsolutePoint = listSelectedValuesContainer.locationOnScreen
        val x = listSelectedValueContainerAbsolutePoint.x
        val y = listSelectedValueContainerAbsolutePoint.y + listSelectedValuesContainer.height
        listScrollPane.preferredSize =
            Dimension(listSelectedValuesContainer.width, listSelectedValuesContainer.height * 2)
        listSelectionPopup = popupFactory.getPopup(this, listScrollPane, x, y)
        listSelectionPopup?.show()
        listIsVisible = true
    }

    fun hideList() {
        listSelectionPopup?.hide()
        listIsVisible = false
    }

    fun addListeners(controller: Controller) {
        addComponentListener(controller.listenerForFrameResize)
        listLaunchButton.addActionListener(controller.listenerForListLaunchButton)
        list.addListSelectionListener(controller.listenerForListSelectionChange)
        list.addFocusListener(controller.listenerForListFocus)
    }

}
