
package view;

import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.Stage;

/**
 *
 * @author igbin
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
