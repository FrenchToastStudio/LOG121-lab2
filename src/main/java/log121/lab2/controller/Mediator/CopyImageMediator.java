package log121.lab2.controller.Mediator;

import log121.lab2.controller.ModificationController;
import log121.lab2.controller.strategy.copy.CopyAllStrategy;
import log121.lab2.controller.strategy.copy.ICopyStrategy;
import log121.lab2.controller.strategy.copy.PositionCopyStategy;
import log121.lab2.controller.strategy.copy.SizeCopyStategy;
import log121.lab2.model.Perspective;
import log121.lab2.view.copyStrategyViews.CopyStrategyEnum;

public class CopyImageMediator {

    private ICopyStrategy copyStrategy;
    private static CopyImageMediator instance;


    public static CopyImageMediator getInstance()
    {
        if(instance == null)
            instance = new CopyImageMediator();
        return instance;
    }

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

    public void setCopyStrategy(ICopyStrategy copyStrategy)
    {
        this.copyStrategy = copyStrategy;
    }

    public void copy(Perspective perspective)
    {
        if(copyStrategy != null) {
            this.copyStrategy.copy(perspective);
        }
    }

    public void paste(ModificationController modificationController)
    {
        if(copyStrategy != null) {
            this.copyStrategy.paste(modificationController);
        }
    }
}
