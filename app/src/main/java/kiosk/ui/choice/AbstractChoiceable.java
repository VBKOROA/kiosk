package kiosk.ui.choice;

import java.util.Scanner;

import kiosk.model.choice.Choice;
import kiosk.ui.common.Choiceable;

public abstract class AbstractChoiceable implements Choiceable {
    protected Choice choice;
    protected final Scanner sc;

    public AbstractChoiceable(Scanner sc) {
        this.sc = sc;
    }

    @Override
    // 자식이 수정하지 못하도록
    public final Choice getChoice() {
        return choice;
    }
}
