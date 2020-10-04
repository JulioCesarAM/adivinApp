package adivinarAPP;
import java.util.Optional;

import adivinarAPP.AdiviniApp;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterfazGraficaAdivinApp extends Application{
	AdiviniApp numero = new AdiviniApp();
	private int numeroIntroducidoPorUsuario;
	private int intentos;
	private int alertStatus;
	
	private TextField cajaTextoInicial;
	private Label labelCajaInicial;
	private Button botonComprobarTextoInicial;
	
	private Alert victoria;
	private Alert derrota;
	private Alert error;
	
	
	public void start(Stage primaryStage) throws Exception {
		
		startInitialLabel();	
		startInitialTextBox();
		startInitialButton();
		setPrimaryScene(vboxLayoutFirstComponents(labelCajaInicial,cajaTextoInicial,botonComprobarTextoInicial),primaryStage);
	}
	
	private void startInitialLabel () {	
		labelCajaInicial =new Label();
		labelCajaInicial.setText("introduce un numero del uno al 100");
		labelCajaInicial.setMaxWidth(200);
		labelCajaInicial.setMaxHeight(200);
		labelCajaInicial.setAlignment(Pos.TOP_CENTER);
		
	}
	
	private void startInitialTextBox() {
		cajaTextoInicial =new TextField();
		cajaTextoInicial.prefColumnCountProperty();
		cajaTextoInicial.setPromptText("int values");
		cajaTextoInicial.setAlignment(Pos.BOTTOM_CENTER);
		cajaTextoInicial.setMaxWidth(100);	
	}
	
	private void startInitialButton() {
		botonComprobarTextoInicial= new Button();
		botonComprobarTextoInicial.setText("comprobar");
		botonComprobarTextoInicial.setDefaultButton(true);
		botonComprobarTextoInicial.setOnAction(p -> onInitialButtonAction(p) );
		
	}
	
	private void onInitialButtonAction(ActionEvent _event) {
		try {
		numeroIntroducidoPorUsuario=Integer.parseInt(cajaTextoInicial.getText());
		alertStatus=status(numero.getNumeroSelecionado());
				}
		catch (Exception e) {
			alertStatus=4;
		}
		alertSelector(alertStatus);
			
	}
	
	private void winnerAlert() {
		String aux = intentos >= 6?"vuelve a jugar y hazlo mejor :(":"bien jugado titan ;)";
		victoria =new Alert(AlertType.INFORMATION);
		victoria.setHeaderText("Has ganado!!!!");
		victoria.setContentText("Has necesidado: "+intentos+" intentos "+"\n"+aux);
		winnerAlertFunctionOnClick(victoria);
	}
	
	private void errorAlert(){
		error=new Alert(AlertType.ERROR);
		error.setHeaderText("error");
		error.setContentText("el numero introducido no es valido");
		errorAlertFunctionOnClick(error);	
	}
	
	private void warningAlert() {
		String aux = numeroIntroducidoPorUsuario > numero.getNumeroSelecionado()?"El numero a adivinar es menor que "+numeroIntroducidoPorUsuario:
		"El numero a adivinar es mayor que "+numeroIntroducidoPorUsuario;
		derrota =new Alert(AlertType.WARNING);
		derrota.setHeaderText("Has fallado!!!! :_(");
		derrota.setContentText(aux+"\n"+"vuelve a intentarlo");
		warningAlertFunctionOnClick(derrota);
		
	}
	
	private void warningAlertFunctionOnClick(Alert _ventanaWarning) {
		Optional<ButtonType> aux = _ventanaWarning.showAndWait();
		if (!aux.isPresent()) {
			intentos+=1;	
		}
		else if (aux.get()== ButtonType.OK) {
			intentos+=1;	
		}
	}
	
	private void winnerAlertFunctionOnClick(Alert _ventanaVictoria) {
		Optional<ButtonType> aux = _ventanaVictoria.showAndWait();
		if (!aux.isPresent()) {
			intentos=0;
			numero.generarNuevoNumero();	
		}
		else if (aux.get()== ButtonType.OK) {
			intentos=0;
			numero.generarNuevoNumero();	
		}
	}
	private void errorAlertFunctionOnClick(Alert _ventanaError) {
		Optional<ButtonType> aux = _ventanaError.showAndWait();
		if (!aux.isPresent()) {
			intentos=0;
			numero.generarNuevoNumero();	
		}
		else if (aux.get()== ButtonType.OK) {
			intentos=0;
			numero.generarNuevoNumero();	
		}
	}
	
	private int status(int value) {
		if (numeroIntroducidoPorUsuario > value ||numeroIntroducidoPorUsuario < value )
			return -1;
		else
			return 1;
	}
	
	private void alertSelector(int _status) {
		if(_status==1)
			winnerAlert();
		else if (_status==-1)
			warningAlert();
		else
			errorAlert();	
	}
	
	private VBox vboxLayoutFirstComponents (Node... objetos) {
		int aux=0;
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		for (Node i:objetos) {
			root.getChildren().add(objetos[aux++]);	
		}
		return root;
	}

	private void setPrimaryScene(VBox root,Stage primary) {
		Scene primaryScene  =new Scene (root,300,300);
		primary.setScene(primaryScene);
		primary.setTitle("AdivinApp");
		primary.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);}
}
