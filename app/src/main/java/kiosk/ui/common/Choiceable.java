package kiosk.ui.common;

import kiosk.model.choice.Choice;

public interface Choiceable extends Displayable {
    Choice getChoice();
}
