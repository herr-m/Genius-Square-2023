package atl4.projet.controller.commands;

public interface Command {
    boolean execute();
    void undo();
}
