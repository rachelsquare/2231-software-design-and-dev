import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * JUnit test fixture for {@code BinarySearchTreeMethods}'s static methods
 * isInTree (and removeSmallest).
 */
public final class BinarySearchTreeMethodsTest {

    /**
     * Constructs and return a BST created by inserting the given {@code args}
     * into an empty tree in the order in which they are provided.
     *
     * @param args
     *            the {@code String}s to be inserted in the tree
     * @return the BST with the given {@code String}s
     * @requires [the Strings in args are all distinct]
     * @ensures createBSTFromArgs = [the BST with the given Strings]
     */
    private static BinaryTree<String> createBSTFromArgs(String... args) {
        BinaryTree<String> t = new BinaryTree1<String>();
        for (String s : args) {
            BinaryTreeUtility.insertInTree(t, s);
        }
        return t;
    }

    @Test
    public void sampleTest() {
        /*
         * Set up variables
         */
        //   SimpleWriter out = new SimpleWriter1L();
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c");
        //  out.println(t2);
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    @Test
    public void smallest() {
        SimpleWriter out = new SimpleWriter1L();
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> t2 = createBSTFromArgs("b", "c");
        //    out.print(t1 + " " + t2);
        String small = BinarySearchTreeMethods.removeSmallest(t1);

        assertEquals(small, "a");
        assertEquals(t2, t1);
    }

    @Test
    public void smallest2() {
        SimpleWriter out = new SimpleWriter1L();
        BinaryTree<String> t1 = createBSTFromArgs("b");
        BinaryTree<String> t2 = createBSTFromArgs();
        String small = BinarySearchTreeMethods.removeSmallest(t1);
        assertEquals(small, "b");
        // out.print(t1 + " " + t2);
        assertEquals(t2, t1);
    }

    @Test
    public void smallest3() {
        SimpleWriter out = new SimpleWriter1L();
        BinaryTree<String> t1 = createBSTFromArgs("b", "c");
        BinaryTree<String> t2 = createBSTFromArgs("c");
        String small = BinarySearchTreeMethods.removeSmallest(t1);
        //  out.print(t1 + " " + t2);

        assertEquals(small, "b");
        assertEquals(t2, t1);
    }

    @Test
    public void smallest4() {
        SimpleWriter out = new SimpleWriter1L();
        BinaryTree<String> t1 = createBSTFromArgs("z", "c", "a");
        BinaryTree<String> t2 = createBSTFromArgs("z", "c");

        String small = BinarySearchTreeMethods.removeSmallest(t1);
        out.print(t1 + " " + t2);

        assertEquals(small, "a");
        assertEquals(t2, t1);
    }

}
