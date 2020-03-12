package rentasad.library.tools.guiTools;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class JavaFxTools
{
    public static final String BUTTON_YES_TEXT = "Ja";
    public static final String BUTTON_NO_TEXT = "Nein";
    public JavaFxTools()
    {
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * Description: 
     * 
     * @param title
     * @param headerText
     * @param contentText
     * @return
     * Creation: 05.09.2017 by mst
     */
    public static Optional<ButtonType> showYesNoButton(final String title, String headerText, final String contentText)
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        ButtonType buttonTypeOne = new ButtonType(BUTTON_YES_TEXT);
        ButtonType buttonTypeTwo = new ButtonType(BUTTON_NO_TEXT);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        // Optional<ButtonType> result = alert.showAndWait();
        return alert.showAndWait();
        // if (result.get() == buttonTypeOne){
        // // ... user chose "One"
        // } else if (result.get() == buttonTypeTwo) {
        // // ... user chose "Two"
        // } else if (result.get() == buttonTypeThree) {
        // // ... user chose "Three"
        // } else {
        // // ... user chose CANCEL or closed the dialog
        // }
    }

}
