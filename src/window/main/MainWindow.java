package window.main;

import javafx.collections.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
import model.DataTableRow;
import org.apache.commons.csv.*;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.*;
import org.swrlapi.sqwrl.exceptions.SQWRLException;
import org.swrlapi.sqwrl.values.SQWRLResultValue;
import window.Window;
import window.WindowControllerEventListener;
import window.choose_delimeter.ChooseDelimeterDialog;
import window.result.ResultDialog;
import java.io.*;
import java.util.*;

/**
 * Created by mashy on 23-May-17.
 */
public class MainWindow extends Window implements WindowControllerEventListener {

    public final String DEFAULT_TITLE = "Выюор метода визуализации";
    public final Double DEFAULT_SCENE_WIDTH = 800.0;
    public final Double DEFAULT_SCENE_HEIGHT = 400.0;

    private ObservableList<DataTableRow> headerItems;

    public MainWindow(Stage stage) {
        super(stage);

        try {
            this.windowLoader = new FXMLLoader(this.getClass().getResource("MainWindowView.fxml"));

            Parent parent = this.windowLoader.load();
            Scene scene = new Scene(parent, this.DEFAULT_SCENE_WIDTH, this.DEFAULT_SCENE_HEIGHT);

            this.windowStage.setTitle(this.DEFAULT_TITLE);
            this.windowStage.setScene(scene);

            MainWindowController controller = this.windowLoader.getController();

            controller.addEventListener(this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void show() {
        this.windowStage.show();
    }

    public Character showChooseDelimeterDialog() {
        Stage stage = new Stage();
        ChooseDelimeterDialog dialog = new ChooseDelimeterDialog(stage, this.windowStage);

        dialog.show();

        return dialog.getDelimeter();
    }

    public void showResultDialog(ArrayList<String> results) {
        Stage stage = new Stage();
        ResultDialog dialog = new ResultDialog(stage, this.windowStage);

        dialog.setVisualizationMethods(results);
        dialog.show();
    }

    @Override
    public void onOpenFile() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Открыть файл с данными");

        File file = fileChooser.showOpenDialog(this.windowStage);

        if (file == null) return;

        try {
            Reader in = new FileReader(file.getAbsolutePath());

            Character delimeter = showChooseDelimeterDialog();

            if (delimeter == null) return;

            CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withDelimiter(delimeter).withHeader());
            ArrayList<String> headers = new ArrayList<String>(parser.getHeaderMap().keySet());

            this.headerItems = FXCollections.observableArrayList();

            for (String header : headers) {
                if (!header.equals("")) {
                    DataTableRow headerItem = new DataTableRow();
                    headerItem.setName(header);
                    headerItem.setIsVisualize(true);
                    headerItems.add(headerItem);
                }
            }

            MainWindowController controller = this.windowLoader.getController();

            controller.setHeaderItems(this.headerItems);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onRun() {
        int count = 0;
        for (DataTableRow row : this.headerItems) {
            if (row.isVisualizeProperty().get()) {
                count ++;
            }
        }
        String str1 = "Method(?m) ^ haveDataStructure(?m, ?ds) ^ haveProperty(?ds, Quality2_Quantitative1) -> sqwrl:select(?m)";
        String str2 = "Method(?m) ^ haveDataStructure(?m, ?ds) ^ haveProperty(?ds, Quality1_Quantitative1) -> sqwrl:select(?m)";
        String str3 = "Method(?m) ^ haveDataStructure(?m, ?ds) ^ haveProperty(?ds, Quality2) -> sqwrl:select(?m)";

        // Create OWLOntology instances using the OWLAPI
        OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = null;
        try {
            ontology = ontologyManager.loadOntologyFromOntologyDocument(new File("src/CBR_visualization.owl"));
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        ontologyManager.getOntologyFormat(ontology).asPrefixOWLOntologyFormat().setDefaultPrefix(ontology.getOntologyID().getOntologyIRI().get().toString() + "#");

        // Create SQWRL query engine using the SWRLAPI
        SQWRLQueryEngine queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(ontology);

        // Create and execute a SQWRL query using the SWRLAPI
        SQWRLResult result = null;
        ArrayList<String> results = new ArrayList<String>();
        try {
            if (count == 2)
                result = queryEngine.runSQWRLQuery("q1",str2);
            else
                result = queryEngine.runSQWRLQuery("q1",str1);
            // Process the SQWRL result
            while (result.next()) {
                for (SQWRLResultValue value : result.getRow()) {
                    results.add(value.toString().substring(1));
                }
                //System.out.println("Name: " + result.getLiteral("m"));
            }

        } catch (SWRLParseException e) {
            e.printStackTrace();
        } catch (SQWRLException e) {
            e.printStackTrace();
        }

        showResultDialog(results);
    }
}
