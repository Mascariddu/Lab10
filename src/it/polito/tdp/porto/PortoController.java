package it.polito.tdp.porto;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import it.polito.tdp.porto.model.Paper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {

	Model m;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
    
    	txtResult.clear();	
    	
    if(boxPrimo.getValue() != null) {
    	txtResult.appendText("CoAutori di "+this.boxPrimo.getValue()+":\n");
    	m.creaGrafo();
    	List<Author> coAutori = m.getCoAutori(this.boxPrimo.getValue());
    	for(Author author : coAutori)
    		txtResult.appendText(author.toString()+"\n");
    	
    	List<Author> autori = m.getAllAutori();
    	Collections.sort(autori);
    	boxSecondo.getItems().addAll(autori);
        List<Author> rimozione = m.getCoAutori(boxPrimo.getValue());
        rimozione.add(boxPrimo.getValue());
        boxSecondo.getItems().removeAll(rimozione);
    	
    } else {
		txtResult.appendText("Seleziona un valore!");
	}
		
    }

    @FXML
    void handleSequenza(ActionEvent event) {

    	txtResult.clear();
    	
    	if(boxSecondo.getValue() != null) {
    		
    		txtResult.appendText("Cammino dall'autore "+boxPrimo.getValue()+" all'autore "+boxSecondo.getValue()+"\n");
    		for(Paper paper : m.getPapers(boxPrimo.getValue(),boxSecondo.getValue()))
    			txtResult.appendText(paper.toString()+"\n");
    		
    	} else {
			txtResult.appendText("Seleziona un autore!");
		}
    	
    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.m = model;
		List<Author> autori = m.getAllAutori();
		Collections.sort(autori);
		boxPrimo.getItems().addAll(autori);
	}
}
