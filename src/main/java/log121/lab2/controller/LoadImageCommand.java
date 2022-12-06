package log121.lab2.controller;

/**

 Classe qui représente la commande de chargement d'une image.

 Elle hérite de la classe abstraite {@link Command}.
 */
public class LoadImageCommand extends Command{

    private boolean hasToExecute;

    private MainController mainController;

    /**

     Constructeur de la classe.
     @param mainController Le contrôleur principal de l'application
     */
    public LoadImageCommand(MainController mainController) {
       this.mainController = mainController;
    }

    /**

     Méthode d'exécution de la commande LoadImage.
     */
    @Override
    public void execute() {
        this.mainController.load();
        if(hasToExecute)
            hasToExecute = false;
    }

    /**

     Vérifie si la condition pour exécuter la commande est remplie.
     @return true si la commande peut être exécutée, false sinon.
     */
    @Override
    public boolean isConditionMet() {
        return hasToExecute;
    }

    /**

     Définit la condition pour exécuter la commande.
     @param b La valeur de la condition (true si la commande peut être exécutée, false sinon)
     */
    public void setCondition(boolean b) {
        hasToExecute = b;
    }
}
