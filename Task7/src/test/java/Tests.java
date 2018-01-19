import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class Tests {
    Prim prim;

    /**
     * Some super awesome unit tests ^.^
     */

    @Test
    public void testPrimTrue(){
        prim = new Prim();
        assertTrue(prim.isPrim(179441839, 4));
    }

    @Test
    public void testPrimFalse(){
        prim = new Prim();
        assertFalse(prim.isPrim(200, 4));
    }

}
