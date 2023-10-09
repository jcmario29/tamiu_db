import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class ItemChangeListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            Object item = event.getItem();
            // do something with object
        }
    }
}
