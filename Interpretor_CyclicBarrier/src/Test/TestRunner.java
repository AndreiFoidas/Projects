package Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import Test.ExpressionTests.*;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static boolean runTest(){
        Result result = JUnitCore.runClasses(ExpressionTests.class, StatementTests.class);
        for( Failure failure:result.getFailures()){
            System.out.println(failure.toString());
        }

        return result.wasSuccessful();
    }
}
