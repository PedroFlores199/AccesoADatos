package Tema3.EjerciciosPracticos;

import org.junit.*;

public class Ejercicio2 {
    
    @Test
    public void testPrueba1() {
        System.out.println("test - Prueba 1");
    }

    @Test
    public void testPrueba2() {
        System.out.println("test - Prueba 2");
    }

    @Before
    public void before() {
        System.out.println("before");
    }

    @After
    public void after() {
        System.out.println("after");
    }
    }

