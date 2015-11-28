package com.templetree.model;

/**
 *
 * Used to define action to be taken on the model.
 *
 * Delete/Update/Insert the row in the appropriate table.
 *
 * @author Lalith Mannur
 */
public enum Action {
    I('I'), // Insert
    U('U'), // Update
    D('D'); // Delete

    char actionType;

    Action(char actionType) {
        this.actionType = actionType;
    }

    public char getActionType() {
        return actionType;
    }
}
