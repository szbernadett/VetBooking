
package view;

import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.Stage;

/**
 * CustomStage class: Represents a Stage with heritable properties that help to avoid 
 *                    code duplication and enforce consistency and hierarchy in the application.
 * 
 */
public abstract class CustomStage extends Stage {
    
    private static final ColumnConstraints rightAlignCol = createConstraint();
    private static final ColumnConstraints emptyConstraint = new ColumnConstraints();
    
    protected abstract void initWindow();

    public static ColumnConstraints getRightAlignCol() {
        return rightAlignCol;
    }

    public static ColumnConstraints getEmptyConstraint() {
        return emptyConstraint;
    }
    

    private static ColumnConstraints createConstraint(){
        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.RIGHT);
        return col;
    }

  
}
