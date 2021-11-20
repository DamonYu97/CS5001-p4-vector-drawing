package shape_tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author 200011181
 * @version 1.0
 */
public class ShapeTestSuiteRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ShapeTestSuite.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if(result.wasSuccessful()) {
            System.out.println("Passed all " + result.getRunCount() +" Piece Shape JUnit tests!");
            System.exit(0);
        }
        else {
            System.out.println("Failed " + result.getFailureCount() + " out of " + result.getRunCount() +" Piece Shape JUnit tests!");
            System.exit(1);
        }
    }
}
