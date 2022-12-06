package log121.lab2.controller.commands;

/**

 Classe abstraite représentant une commande générique.

 Elle permet d'exécuter une action en vérifiant une condition préalable.
 */
public abstract class Command {

    private Object object;

    public Command() {
    }

    /**

     Méthode abstraite d'exécution de la commande.
     */
    public abstract void execute();

    /**

     Méthode abstraite pour vérifier si la condition pour exécuter la commande est remplie.
     @return true si la condition est remplie, false sinon.
     */
    public abstract boolean isConditionMet();


    /**

     Définit l'objet associé à la commande.
     @param object L'objet associé à la commande.
     */
    public void setClassId(Object object)
    {
        this.object = object;
    }

    /**

     Vérifie si l'objet passé en paramètre est associé à la commande.
     @param object L'objet à vérifier.
     @return true si l'objet est associé à la commande, false sinon.
     */
    public boolean checkIfCommandOfClass(Object object) {
        return this.object == object;
    }
}
