package at.binarycheese.binaryeframework.StatementExcecution.Test;

import org.junit.Assert;

import at.binarycheese.binaryeframework.StatementExcecution.EFrameWorkUserIsIdiotException;
import at.binarycheese.binaryeframework.StatementExcecution.UpdateExecution;

public class UpdateExecutionTest {

	public void executeTest() {
		UpdateExecution<Object> ue = new UpdateExecution<Object>(null);
		int result = 0;
		try {
			result = ue.execute(new Object());
		} catch (EFrameWorkUserIsIdiotException e) {
			Assert.assertEquals(true, true);
		}

		Assert.assertEquals(1, result);
	}
}
