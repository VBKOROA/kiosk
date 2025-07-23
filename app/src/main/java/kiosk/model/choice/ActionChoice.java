package kiosk.model.choice;

import kiosk.model.action.KioskAction;

public interface ActionChoice extends Choice {
    KioskAction getAction();
}
