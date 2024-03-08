package atl4.projet;

import atl4.projet.controller.Controller;
import atl4.projet.model.Model;
import atl4.projet.view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
    }
}