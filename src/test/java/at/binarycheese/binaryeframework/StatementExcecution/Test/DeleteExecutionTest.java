package at.binarycheese.binaryeframework.StatementExcecution.Test;

import org.junit.Assert;

import at.binarycheese.binaryeframework.StatementExcecution.DeleteExecution;
import at.binarycheese.binaryeframework.StatementExcecution.EFrameWorkUserIsIdiotException;

public class DeleteExecutionTest {

	public void executeTest() {
		DeleteExecution<Object> de = new DeleteExecution<Object>(null);
		int result = 0;
		try {
			result = de.execute(new Object());
		} catch (EFrameWorkUserIsIdiotException e) {
			Assert.assertEquals(true, true);
		}

		Assert.assertEquals(1, result);
	}

}
