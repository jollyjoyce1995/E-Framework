package at.binarycheese.binaryeframework.StatementExcecution.Test;

import org.junit.Assert;

import at.binarycheese.binaryeframework.StatementExcecution.EFrameWorkUserIsIdiotException;
import at.binarycheese.binaryeframework.StatementExcecution.InsertExecution;

public class InsertExecutionTest {

	public void testExecute() {
		InsertExecution<Object> ie = new InsertExecution<Object>(null);
		int result = 0;
		try {
			result = ie.execute(new Object());
		} catch (EFrameWorkUserIsIdiotException e) {
			Assert.assertEquals(true, true);
		}

		Assert.assertEquals(1, result);
	}
}
