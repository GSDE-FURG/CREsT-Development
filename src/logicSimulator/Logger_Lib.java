package logicSimulator;

import org.w3c.dom.Text;

import java.awt.*;
import java.util.logging.Logger;

public class Logger_Lib {

    private Object SWT;
    private Logger logger;

        // inject class for database access
        //@Inject private DatabaseAccessClass dao;

    public Logger_Lib(Logger logger) {
        this.logger = logger;

    }

    public void createControls(Composite parent) {
        /*
            logger.info("UI will start to build");
            Label label = new Label(parent, SWT.NONE);
            label.setText("Eclipse 4");
            Text text = new Text(parent, SWT.NONE);
            text.setText("x");
        }
        */

    }

}
