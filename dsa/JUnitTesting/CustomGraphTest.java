/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import org.junit.AfterClass;
import org.junit.Test;

/**
 *
 * @author nayan
 */
public class CustomGraphTest {
    
    public CustomGraphTest() {
    }
    
    @AfterClass
    public static void printSuccessfulMsg(){
        System.out.println("All test cases are passed.");
    } 
    
    
    @Test
    public void testMain(){
        Graph graph = new Graph();
        
        //entered 0 as graph size- number of nodes
        graph.makeArray(0);
        //-------------------------------------------------------------------
        
        graph.makeArray(4);
        
        //add an edge- entered invalid vertices
        graph.addEdge(6, 1);
        //-------------------------------------------------------------------
        
        //add an edge- valid vertices
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(1, 4);
        
        System.out.print("After add: ");
        graph.display();
        System.out.println();
        //-------------------------------------------------------------------
        
        //apply DFS
        System.out.print("Applying DFS: ");
        graph.dfs(0, new boolean[4], -1);
        System.out.println();
        //-------------------------------------------------------------------
        
        //apply BFS
        System.out.print("Applying BFS: ");
        graph.bfs(1, 4);
        System.out.println();
        //-------------------------------------------------------------------
        
        //remove an edge
        System.out.print("After remove: ");
        graph.removeEdge(1, 4);
        System.out.println();
        //-------------------------------------------------------------------
        
    }
    
}
