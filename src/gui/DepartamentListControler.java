package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Departamento;

public class DepartamentListControler implements Initializable {
	
	@FXML
	private TableView<Departamento> tableViewDepartamento;
	
	@FXML
	private TableColumn<Departamento, Integer> tableColunaId;
	
	@FXML
	private TableColumn<Departamento, String> tableColunaNome;
	
	@FXML
	private Button btNew;
	
	public void onBtNewAction() {
		
		System.out.println("onBtNewAction");
		
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
		initializeNodes();
		
	}


	private void initializeNodes() {
		// TODO Auto-generated method stub
		
		tableColunaId.setCellValueFactory(new PropertyValueFactory<>("idColuna"));
		tableColunaNome.setCellValueFactory(new PropertyValueFactory<>("nomeDepartamento"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartamento.prefHeightProperty().bind(stage.heightProperty());
		
	}
	
	
	
}
