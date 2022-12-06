package log121.lab2.controller.Mediator;

import log121.lab2.controller.ModificationController;
import log121.lab2.controller.strategy.copy.CopyAllStrategy;
import log121.lab2.controller.strategy.copy.ICopyStrategy;
import log121.lab2.controller.strategy.copy.PositionCopyStategy;
import log121.lab2.controller.strategy.copy.SizeCopyStategy;
import log121.lab2.model.Perspective;
import log121.lab2.view.copyStrategyViews.CopyStrategyEnum;

/**

 Classe qui gère le médiateur pour la copie d'images.

 Elle permet de définir la stratégie de copie à utiliser et d'exécuter les opérations de copie et de collage.
 */
public class CopyImageMediator {

    private ICopyStrategy copyStrategy;
    private static CopyImageMediator instance;

    /**

     Retourne l'instance unique de la classe CopyImageMediator.
     @return l'instance unique de la classe
     */
    public static CopyImageMediator getInstance()
    {
        if(instance == null)
            instance = new CopyImageMediator();
        return instance;
    }

    /**

     Définit la stratégie de copie à utiliser en fonction de l'énumération passée en paramètre.
     @param strategyEnum l'énumération correspondant à la stratégie de copie à utiliser
     */
    public void setCopyStrategy(CopyStrategyEnum strategyEnum)
    {
        switch (strategyEnum)
        {
            case POSITION -> setCopyStrategy(new PositionCopyStategy());
            case SIZE -> setCopyStrategy(new SizeCopyStategy());
            case ALL -> setCopyStrategy(new CopyAllStrategy());
            default -> this.copyStrategy = null;
        }
    }

    /**

     Définit la stratégie de copie à utiliser en fonction de l'interface passée en paramètre.
     @param copyStrategy l'interface correspondant à la stratégie de copie à utiliser
     */
    public void setCopyStrategy(ICopyStrategy copyStrategy)
    {
        this.copyStrategy = copyStrategy;
    }

    /**

     Exécute l'opération de copie en utilisant la stratégie de copie actuelle.
     @param perspective la perspective à copier
     */
    public void copy(Perspective perspective)
    {
        if(copyStrategy != null) {
            this.copyStrategy.copy(perspective);
        }
    }

    /**

     Exécute l'opération de collage en utilisant la stratégie de copie actuelle.
     @param modificationController le contrôleur de modification à utiliser pour le collage
     */
    public void paste(ModificationController modificationController)
    {
        if(copyStrategy != null) {
            this.copyStrategy.paste(modificationController);
        }
    }
}
