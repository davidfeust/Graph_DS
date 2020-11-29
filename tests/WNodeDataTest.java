package ex0.tests;

import ex0.src.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class WNodeDataTest {

    @BeforeEach
    void setUp() {
        NodeData n = new NodeData();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getKey() {
        NodeData n = new NodeData();
        assertEquals(n.getKey(), 1);
    }

    @Test
    void getNi() {
    }

    @Test
    void hasNi() {
    }

    @Test
    void addNi() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void getInfo() {
    }

    @Test
    void setInfo() {
    }

    @Test
    void getTag() {
    }

    @Test
    void setTag() {
    }
}