package com.elka.coloringedges;

import com.elka.coloringedges.domain.Vertex;
import org.junit.Assert;
import org.junit.Test;

public class VertexTest extends Assert {

    @Test
    public void shouldAddingVertexAssignId() {
        Vertex vertex = new Vertex((long) 1);
        assertTrue((long) 1 == vertex.getId());
    }
}
