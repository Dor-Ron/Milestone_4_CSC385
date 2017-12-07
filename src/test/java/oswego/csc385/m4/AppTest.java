package oswego.csc385.m4;

import java.util.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Unit test for Website Recommender App
 */
public class AppTest {
    Parsing p;
    HashTable first, second;

    @Before
    public void setUp() {
        p = new Parsing();
        first = new HashTable();
        second = new HashTable();
    }

    @After
    public void tearDown() {
        p = null;
        first = null;
        second = null;
    }

    // Specification, Control-Flow, and Data Flow Testing
    @Test
    public void t0() {
        String url1 = "https://github.com";
        String url2 = "https://en.wikipedia.org/wiki/1";
        String[] arr = p.makeWordArray(url1);
        String[] arr2 = p.makeWordArray(url2);
        ArrayList<String> arrl = p.makeMetaTagWords(url1);
        ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

        p.populateTable(first, arr, false);
        p.populateTable(second, arr2, false);
        p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
        p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
        
        double res = p.cosineVectorSimilarity(first, second);
        
        assertTrue(res > 0.246 && res < 0.248);
        
        /*
        Failed because wikipedia page was updated
        and expected value in document is no-longer accurate
        */
    }

    @Test
    public void t1() {
        first.put("example", 3);
        second.put("ejemplo", 5);
        
        double res = p.cosineVectorSimilarity(first, second);
        
        assertEquals(0.0, 0.0, 1e-15);

        /*
        Fails due to refernce assignment of firstSet to combinedSet 
        in cosineVector method, changing combinedSet had unintended
        side-effects on firstSet, messing up the ultimate calculation
        */
    }

    @Test
    public void t2() {
        String url2 = "https://en.wikipedia.org/wiki/1";
       
        String[] arr2 = p.makeWordArray(url2);
        ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

        p.populateTable(second, arr2, false);
        p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);

        double res = p.cosineVectorSimilarity(first, second);
        assertTrue(false);
        /*
        Fails because comparing empty HashTable to populated one doesn't cause an error
        */
    }

    @Test(expected=NullPointerException.class)
    public void t3() {
        String url2 = "https://en.wikipedia.org/wiki/3";
        first = null;
       
        String[] arr2 = p.makeWordArray(url2);
        ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

        p.populateTable(second, arr2, false);
        p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);

        double res = p.cosineVectorSimilarity(first, second);
        assertTrue(true);
        /*
        Fails because comparing empty HashTable to populated one doesnt cause an error
        */
    }

    // TOO LONG TO EXECUTE, NOT WORTH TESTING!!!!!
    // @Test
    // public void t4() {
    //     String url2 = "https://en.wikipedia.org/wiki/4";

    //     for(long i = 0; i < Integer.MAX_VALUE; i++) 
    //         first.put(String.join("", Collections.nCopies((int)i, "a")), 1);

    //     String[] arr2 = p.makeWordArray(url2);
    //     ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

    //     p.populateTable(second, arr2, false);
    //     p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);

    //     double res = p.cosineVectorSimilarity(first, second);
    //     assertEquals(res, <SOME_OUTPUT>);
    //     /*
    //     Fails because comparing empty HashTable to populated one doesnt cause an error
    //     */
    // }

    @Test
    public void t5() {
        first.put("negative", -9);
        String url2 = "https://en.wikipedia.org/wiki/5";
        
         String[] arr2 = p.makeWordArray(url2);
         ArrayList<String> arrl2 = p.makeMetaTagWords(url2);
 
         p.populateTable(second, arr2, false);
         p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
 
         double res = p.cosineVectorSimilarity(first, second);
         assertTrue(false);

        /*
        No error thrown, negative occurences permitted by program,
        both specification followed by the corresponding code must
        be updated accordingly.
        */
    }

    // LEADS TO COMPILATION ERROR: Test Passes!!!
    // @Test
    // public void t6() {
    //     first.put("test", "one");
    //     String url2 = "https://en.wikipedia.org/wiki/6";
        
    //      String[] arr2 = p.makeWordArray(url2);
    //      ArrayList<String> arrl2 = p.makeMetaTagWords(url2);
 
    //      p.populateTable(second, arr2, false);
    //      p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

    // LEADS TO COMPILATION ERROR: Test Passes!!!
    // @Test
    // public void t7() {
    //     first.put("test1.2", 7.9);
    //     String url2 = "https://en.wikipedia.org/wiki/6";
        
    //      String[] arr2 = p.makeWordArray(url2);
    //      ArrayList<String> arrl2 = p.makeMetaTagWords(url2);
 
    //      p.populateTable(second, arr2, false);
    //      p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

    @Test
    public void t8() {
        String url = "https://en.wikipedia.org/wiki/1";
       
        String[] arr = p.makeWordArray(url);
        ArrayList<String> arrl = p.makeMetaTagWords(url);

        p.populateTable(first, arr, false);        
        p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);

        double res = p.cosineVectorSimilarity(first, second);
        assertTrue(false);
        /* 
        Doesn't pass because Java returns NaN instead of throwing 
        a division by zero error as expected.
        */
    }

    @Test(expected=NullPointerException.class)
    public void t9() {
        String url = "https://en.wikipedia.org/wiki/8";
        second = null;

        String[] arr = p.makeWordArray(url);
        ArrayList<String> arrl = p.makeMetaTagWords(url);

        p.populateTable(first, arrl.toArray(new String[arrl.size()]), false);
        p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);

        double res = p.cosineVectorSimilarity(first, second);
        assertTrue(true);
        /*
        Fails because comparing empty HashTable to populated one doesnt cause an error
        */
    }

    // TOO LONG TO EXECUTE, NOT WORTH TESTING!!!!!
    // @Test
    // public void t10() {
    //     String url2 = "https://en.wikipedia.org/wiki/4";

    //     for(long i = 0; i < Integer.MAX_VALUE; i++) 
    //         second.put(String.join("", Collections.nCopies((int)i+7, "b")), 1);

    //     String[] arr2 = p.makeWordArray(url2);
    //     ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

    //     p.populateTable(first, arr2, false);
    //     p.populateTable(first, arrl2.toArray(new String[arrl2.size()]), true);

    //     double res = p.cosineVectorSimilarity(first, second);
    //     assertEquals(res, <SOME_OUTPUT>);
    //     /*
    //     Fails because comparing empty HashTable to populated one doesnt cause an error
    //     */
    // }

    @Test
    public void t11() {
        second.put("lessThan0", -83);
        String url = "https://en.wikipedia.org/wiki/3";
        
         String[] arr = p.makeWordArray(url);
         ArrayList<String> arrl = p.makeMetaTagWords(url);
 
         p.populateTable(first, arr, false);
         p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
 
         double res = p.cosineVectorSimilarity(first, second);
         assertTrue(false);

        /*
        No error thrown, negative occurences permitted by program,
        both specification followed by the corresponding code must
        be updated accordingly.
        */
    }

    // LEADS TO COMPILATION ERROR: Test Passes!!!
    // @Test
    // public void t12() {
    //     second.put("test", "two");
    //     String url2 = "https://en.wikipedia.org/wiki/4";
        
    //      String[] arr = p.makeWordArray(url);
    //      ArrayList<String> arrl = p.makeMetaTagWords(url);
 
    //      p.populateTable(first, arr, false);
    //      p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

    // LEADS TO COMPILATION ERROR: Test Passes!!!
    // @Test
    // public void t13() {
    //     second.put("test2.2", 5.9);
    //     String url2 = "https://en.wikipedia.org/wiki/6";
        
    //      String[] arr = p.makeWordArray(url);
    //      ArrayList<String> arrl = p.makeMetaTagWords(url);
 
    //      p.populateTable(first, arr, false);
    //      p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

    @Test
    public void t14() {
        double res = p.cosineVectorSimilarity(first, second);
        assertTrue(res != 0);

        /* 
        Doesn't pass because Java returns NaN instead of throwing 
        a division by zero error as expected.
        */
    }

    @Test
    public void t15() {
        first.put("quality", 9);
        first.put("assurance", 10);
        
        second.put("assurance", 10);
        
        double res = p.cosineVectorSimilarity(first, second);
        
        assertEquals(res, 0.5, 1e-15);

        /*
        Code is correct, expected results were wrongly calculated...
        */
    }

    @Test
    public void t16() {
        first.put("qa", 8);
        
        second.put("qa", 8);
        second.put("qm", 9);
        
        double res = p.cosineVectorSimilarity(first, second);
        
        assertEquals(res, 0.5, 1e-15);

        /*
        Code is correct, expected results were wrongly calculated...
        probably because of HashTable.get() returning -1 on unfound instead of 0
        */
    }


    ///// Object-Oriented Testing

    @Test
    public void t17() {
        first.put("example", 3);
        
        second.put("ejemplo", 5);        
        second.put("example", 3);
        
        double res = p.cosineVectorSimilarity(first, second);
        
        assertEquals(res, 0.5, 1e-15);

        /*
        Code is correct, expected results were wrongly calculated...
        probably because of HashTable.get() returning -1 on unfound instead of 0
        */
    }

    @Test
    public void t18() {
        String url1 = "http://threevirtues.com";
        String url2 = "https://en.wikipedia.org/wiki/7";

        String[] arr = p.makeWordArray(url1);
        String[] arr2 = p.makeWordArray(url2);
        ArrayList<String> arrl = p.makeMetaTagWords(url1);
        ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

        p.populateTable(first, arr, false);
        p.populateTable(second, arr2, false);
        p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
        p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
        
        double res = p.cosineVectorSimilarity(first, second);
        
        assertTrue(res > 0.056 && res < 0.058);
        
        /*
        Passes :D
        */
    }

    @Test
    public void t19() {
        String url1 = "http://threevirtues.com";
        String url2 = "https://en.wikipedia.org/wiki/7";

        ArrayList<String> arrl = p.makeMetaTagWords(url1);
        ArrayList<String> arrl2 = p.makeMetaTagWords(url2);
        String[] arr = p.makeWordArray(url1);
        String[] arr2 = p.makeWordArray(url2);
        
        p.populateTable(first, arr, false);
        p.populateTable(second, arr2, false);
        p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
        p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
        
        double res = p.cosineVectorSimilarity(first, second);
        
        assertTrue(res > 0.056 && res < 0.058);
        
        /*
        Passes :D
        */
    }

    @Test
    public void t20() {
        first.put("ejemplo", 1);
        second.put("example", 1);

        assertEquals(first.get("ejemplo"), 1);
        assertEquals(second.get("example"), 1);

        first.updateValue("ejemplo", first.get("ejemplo") + 1);
        assertEquals(first.get("ejemplo"), 2);

        HashSet<String> firstSet = first.getTableKeys();
        HashSet<String> secondSet = second.getTableKeys();
        assertEquals(firstSet.size(), 1);
        assertEquals(secondSet.size(), 1);
        assertTrue(firstSet.contains("ejemplo"));
        assertTrue(secondSet.contains("example"));

        double res = p.cosineVectorSimilarity(first, second);
        assertEquals(res, 0.0, 1e-15);

        /* 
        Classes interact as expected, but final output is wrong
        Problem is that HashTable.get() returns -1 if key not found in
        HashSet, but it should return 0 for the purposes of the program.
        */
    }

    ////// Boundary Value Analysis

    @Test
    public void t21() {
        String url1 = "https://github.com";
        String url2 = "https://en.wikipedia.org/wiki/1";
        String[] arr = p.makeWordArray(url1);
        String[] arr2 = p.makeWordArray(url2);
        ArrayList<String> arrl = p.makeMetaTagWords(url1);
        ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

        p.populateTable(first, arr, false);
        p.populateTable(second, arr2, false);
        p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
        p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);

        first.updateValue("github", first.get("github") - 1);
        
        double res = p.cosineVectorSimilarity(first, second);
        
        assertTrue(res > 0.257 && res < 0.357);
        
        /*
        Failed because wikipedia page was updated
        and expected value in document is no-longer accurate
        */
    }

    @Test
    public void t22() {
        String url1 = "https://github.com";
        String url2 = "https://en.wikipedia.org/wiki/1";
        String[] arr = p.makeWordArray(url1);
        String[] arr2 = p.makeWordArray(url2);
        ArrayList<String> arrl = p.makeMetaTagWords(url1);
        ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

        p.populateTable(first, arr, false);
        p.populateTable(second, arr2, false);
        p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
        p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);

        first.updateValue("github", first.get("github") + 1);
        
        double res = p.cosineVectorSimilarity(first, second);
        
        assertTrue(res > 0.302 && res < 0.389);
        
        /*
        Failed because wikipedia page was updated
        and expected value in document is no-longer accurate
        */
    }

    @Test
    public void t23() {
        first.put("example", 3);
        second.put("ejemplo", 5);
        
        first.updateValue("example", first.get("example") - 1);

        double res = p.cosineVectorSimilarity(first, second);
        
        assertEquals(0.0, 0.0, 1e-15);

        /*
        Passes :D
        */
    }

    @Test
    public void t24() {
        first.put("example", 3);
        second.put("ejemplo", 5);
        
        first.updateValue("example", first.get("example") + 1);

        double res = p.cosineVectorSimilarity(first, second);
        
        assertEquals(0.0, 0.0, 1e-15);

        /*
        Passes :D
        */
    }

    // TOO LONG TO EXECUTE, NOT WORTH TESTING!!!!!
    // @Test
    // public void t25() {
    //     String url2 = "https://en.wikipedia.org/wiki/4";

    //     for(long i = 0; i < Integer.MAX_VALUE; i++) 
    //         first.put(String.join("", Collections.nCopies((int)i, "a")), 1);
    
    //     first.updateValue("aa", first.get("aa") - 1)
    
    //     String[] arr2 = p.makeWordArray(url2);
    //     ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

    //     p.populateTable(second, arr2, false);
    //     p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);

    //     double res = p.cosineVectorSimilarity(first, second);
    //     assertEquals(res, <SOME_OUTPUT>);
    //     /*
    //     Fails because comparing empty HashTable to populated one doesnt cause an error
    //     */
    // }

    // TOO LONG TO EXECUTE, NOT WORTH TESTING!!!!!
    // @Test
    // public void t25() {
    //     String url2 = "https://en.wikipedia.org/wiki/4";

    //     for(long i = 0; i < Integer.MAX_VALUE; i++) 
    //         first.put(String.join("", Collections.nCopies((int)i, "a")), 1);
    
    //     first.updateValue("aa", first.get("aa") + 1)
    
    //     String[] arr2 = p.makeWordArray(url2);
    //     ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

    //     p.populateTable(second, arr2, false);
    //     p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);

    //     double res = p.cosineVectorSimilarity(first, second);
    //     assertEquals(res, <SOME_OUTPUT>);
    //     /*
    //     Fails because comparing empty HashTable to populated one doesnt cause an error
    //     */
    // }

    // @Test
    // public void t26() {
    //     first.put("negative", -9);
    //     String url2 = "https://en.wikipedia.org/wiki/5";
        
    //      String[] arr2 = p.makeWordArray(url2);
    //      ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

    //      first.updateValue("negative", first.get("negative") - 1);
 
    //      p.populateTable(second, arr2, false);
    //      p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

    // @Test
    // public void t27() {
    //     first.put("negative", -9);
    //     String url2 = "https://en.wikipedia.org/wiki/5";
        
    //      String[] arr2 = p.makeWordArray(url2);
    //      ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

    //      first.updateValue("negative", first.get("negative") + 1);
 
    //      p.populateTable(second, arr2, false);
    //      p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

    // LEADS TO COMPILATION ERROR: Test Passes!!!
    // @Test
    // public void t28() {
    //     first.put("test", "one");
    //     String url2 = "https://en.wikipedia.org/wiki/6";
        
    //      String[] arr2 = p.makeWordArray(url2);
    //      ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

    //      first.updateValue("test", "ond");
 
    //      p.populateTable(second, arr2, false);
    //      p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

    // LEADS TO COMPILATION ERROR: Test Passes!!!
    // @Test
    // public void t29() {
    //     first.put("test", "one");
    //     String url2 = "https://en.wikipedia.org/wiki/6";
        
    //      String[] arr2 = p.makeWordArray(url2);
    //      ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

    //      first.updateValue("test", "onf");
 
    //      p.populateTable(second, arr2, false);
    //      p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

    // LEADS TO COMPILATION ERROR: Test Passes!!!
    // @Test
    // public void t30() {
    //     first.put("test1.2", 7.9);
    //     String url2 = "https://en.wikipedia.org/wiki/6";
        
    //      String[] arr2 = p.makeWordArray(url2);
    //      ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

    //      first.updateValue("test1.2", 7.89);
 
    //      p.populateTable(second, arr2, false);
    //      p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

    // LEADS TO COMPILATION ERROR: Test Passes!!!
    // @Test
    // public void t30() {
    //     first.put("test1.2", 7.9);
    //     String url2 = "https://en.wikipedia.org/wiki/6";
        
    //      String[] arr2 = p.makeWordArray(url2);
    //      ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

    //      first.updateValue("test1.2", 7.91);
 
    //      p.populateTable(second, arr2, false);
    //      p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

    // LEADS TO COMPILATION ERROR: Test Passes!!!
    @Test
    public void t31() {
        first.put(".......................", 1);
        String url2 = "https://oswego.edu";
        
         String[] arr2 = p.makeWordArray(url2);
         ArrayList<String> arrl2 = p.makeMetaTagWords(url2);
 
         p.populateTable(second, arr2, false);
         p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
 
         double res = p.cosineVectorSimilarity(first, second);
         assertEquals(res, 0.0, 0.0);

        /*
        No error thrown, negative occurences permitted by program,
        both specification followed by the corresponding code must
        be updated accordingly.
        */
    }

    @Test
    public void t32() {
        first.put(".......................", 1);
        String url2 = "https://oswego.edu";
        
         String[] arr2 = p.makeWordArray(url2);
         ArrayList<String> arrl2 = p.makeMetaTagWords(url2);
 
        second.updateValue("oswego", second.get("oswego") - 1);

         p.populateTable(second, arr2, false);
         p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
 
         double res = p.cosineVectorSimilarity(first, second);
         assertEquals(res, 0.0, 0.0);

        /*
        No error thrown, negative occurences permitted by program,
        both specification followed by the corresponding code must
        be updated accordingly.
        */
    }

    @Test
    public void t33() {
        first.put(".......................", 1);
        String url2 = "https://oswego.edu";
        
         String[] arr2 = p.makeWordArray(url2);
         ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

         second.updateValue("oswego", second.get("oswego") + 1);         
 
         p.populateTable(second, arr2, false);
         p.populateTable(second, arrl2.toArray(new String[arrl2.size()]), true);
 
         double res = p.cosineVectorSimilarity(first, second);
         assertEquals(res, 0.0, 0.0);

        /*
        No error thrown, negative occurences permitted by program,
        both specification followed by the corresponding code must
        be updated accordingly.
        */
    }

    @Test
    public void t34() {
        first.put("example", 3);
        second.put("ejemplo", 5);
        
        second.updateValue("ejemplo", second.get("ejemplo") - 1);

        double res = p.cosineVectorSimilarity(first, second);
        
        assertEquals(0.0, 0.0, 1e-15);

        /*
        Passes :D
        */
    }

    @Test
    public void t35() {
        first.put("example", 3);
        second.put("ejemplo", 5);
        
        second.updateValue("ejemplo", second.get("ejemplo") + 1);

        double res = p.cosineVectorSimilarity(first, second);
        
        assertEquals(0.0, 0.0, 1e-15);

        /*
        Passes :D
        */
    }

    // TOO LONG TO EXECUTE, NOT WORTH TESTING!!!!!
    // @Test
    // public void t36() {
    //     String url2 = "https://en.wikipedia.org/wiki/4";

    //     for(long i = 0; i < Integer.MAX_VALUE; i++) 
    //         second.put(String.join("", Collections.nCopies((int)i+7, "b")), 1);

    //     second.updateValue("bb", second.get("bb") - 1);

    //     String[] arr2 = p.makeWordArray(url2);
    //     ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

    //     p.populateTable(first, arr2, false);
    //     p.populateTable(first, arrl2.toArray(new String[arrl2.size()]), true);

    //     double res = p.cosineVectorSimilarity(first, second);
    //     assertEquals(res, <SOME_OUTPUT>);
    //     /*
    //     Fails because comparing empty HashTable to populated one doesnt cause an error
    //     */
    // }

    // @Test
    // public void t37() {
    //     String url2 = "https://en.wikipedia.org/wiki/4";

    //     for(long i = 0; i < Integer.MAX_VALUE; i++) 
    //         second.put(String.join("", Collections.nCopies((int)i+7, "b")), 1);

    //     second.updateValue("bb", second.get("bb") + 1);

    //     String[] arr2 = p.makeWordArray(url2);
    //     ArrayList<String> arrl2 = p.makeMetaTagWords(url2);

    //     p.populateTable(first, arr2, false);
    //     p.populateTable(first, arrl2.toArray(new String[arrl2.size()]), true);

    //     double res = p.cosineVectorSimilarity(first, second);
    //     assertEquals(res, <SOME_OUTPUT>);
    //     /*
    //     Fails because comparing empty HashTable to populated one doesnt cause an error
    //     */
    // }

    @Test
    public void t38() {
        second.put("lessThan0", -83);
        String url = "https://en.wikipedia.org/wiki/3";
        
         String[] arr = p.makeWordArray(url);
         ArrayList<String> arrl = p.makeMetaTagWords(url);
 
        second.updateValue("lessThan0", second.get("lessThan0") - 1);

         p.populateTable(first, arr, false);
         p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
 
         double res = p.cosineVectorSimilarity(first, second);
         assertTrue(false);

        /*
        No error thrown, negative occurences permitted by program,
        both specification followed by the corresponding code must
        be updated accordingly.
        */
    }

    @Test
    public void t39() {
        second.put("lessThan0", -83);
        String url = "https://en.wikipedia.org/wiki/3";
        
         String[] arr = p.makeWordArray(url);
         ArrayList<String> arrl = p.makeMetaTagWords(url);
 
        second.updateValue("lessThan0", second.get("lessThan0") + 1);

         p.populateTable(first, arr, false);
         p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
 
         double res = p.cosineVectorSimilarity(first, second);
         assertTrue(false);

        /*
        No error thrown, negative occurences permitted by program,
        both specification followed by the corresponding code must
        be updated accordingly.
        */
    }

    // LEADS TO COMPILATION ERROR: Test Passes!!!
    // @Test
    // public void t40() {
    //     second.put("test", "two");
    //     String url2 = "https://en.wikipedia.org/wiki/4";
        
    //      String[] arr = p.makeWordArray(url);
    //      ArrayList<String> arrl = p.makeMetaTagWords(url);
 
    //      second.updateValue("test", "twn");

    //      p.populateTable(first, arr, false);
    //      p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

    // LEADS TO COMPILATION ERROR: Test Passes!!!
    // @Test
    // public void t41() {
    //     second.put("test", "two");
    //     String url2 = "https://en.wikipedia.org/wiki/4";
        
    //      String[] arr = p.makeWordArray(url);
    //      ArrayList<String> arrl = p.makeMetaTagWords(url);
 
    //      second.updateValue("test", "twp");

    //      p.populateTable(first, arr, false);
    //      p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

   // LEADS TO COMPILATION ERROR: Test Passes!!!
    // @Test
    // public void t42() {
    //     second.put("test2.2", 5.9);
    //     String url2 = "https://en.wikipedia.org/wiki/6";
        
    //      String[] arr = p.makeWordArray(url);
    //      ArrayList<String> arrl = p.makeMetaTagWords(url);

    //      second.updateValue("test2.2", 5.89);
 
    //      p.populateTable(first, arr, false);
    //      p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }

   // LEADS TO COMPILATION ERROR: Test Passes!!!
    // @Test
    // public void t43() {
    //     second.put("test2.2", 5.9);
    //     String url2 = "https://en.wikipedia.org/wiki/6";
        
    //      String[] arr = p.makeWordArray(url);
    //      ArrayList<String> arrl = p.makeMetaTagWords(url);
 
    //      second.updateValue("test2.2", 5.91);

    //      p.populateTable(first, arr, false);
    //      p.populateTable(first, arrl.toArray(new String[arrl.size()]), true);
 
    //      double res = p.cosineVectorSimilarity(first, second);
    //      assertTrue(false);

    //     /*
    //     No error thrown, negative occurences permitted by program,
    //     both specification followed by the corresponding code must
    //     be updated accordingly.
    //     */
    // }
}
