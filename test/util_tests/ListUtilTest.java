package util_tests;

import Util.ListUtil;
import model.shape.Line;
import model.shape.Shape;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 200011181
 * @version 1.0
 */
public class ListUtilTest {

    @Test
    public void testShapeList() {
        ListUtil<Shape> util = new ListUtil<>();
        List<Shape> origin = new ArrayList<>();
        Shape line = new Line(0, 0, 5, 5);
        origin.add(line);
        //serialize
        byte[] serializedContent = util.serialize(origin);
        assertTrue(serializedContent.length > 0);
        //deserialize
        List<Shape> deserializedShapeList = util.deserialize(serializedContent);
        assertNotNull(deserializedShapeList);
        assertEquals(origin.size(), deserializedShapeList.size());
        assertEquals(origin.get(0).getStartPoint(), deserializedShapeList.get(0).getStartPoint());
        assertEquals(origin.get(0).getEndPoint(), deserializedShapeList.get(0).getEndPoint());
    }

}
