package FSM;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {

    private static final long serialVersionUID=1L;

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        FlapStudMed.fsm.repaint(g);
    }

}

