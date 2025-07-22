package kiosk.ui.choice;

import kiosk.model.choice.Choice;
import kiosk.ui.common.Choiceable;

public abstract class AbstractChoiceable implements Choiceable {
    protected Choice choice;

    @Override
    // 자식이 수정하지 못하도록
    public final Choice getChoice() {
        return choice;
    }
}
