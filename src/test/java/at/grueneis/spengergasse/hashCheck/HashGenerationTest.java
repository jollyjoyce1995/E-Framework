package at.grueneis.spengergasse.hashCheck;

import static org.junit.Assert.*;

import at.grueneis.spengergasse.lesson_plan.domain.BasePersistable;
import org.junit.Before;
import org.junit.Test;

public class HashGenerationTest {
	private Hashable base0;
	private Hashable base1;
    private HashGenerator hashGenerator;
	
	@Before
	public void init(){
        hashGenerator = new HashGenerator();
		base0 = new HashableImplementation();
		base0.setId(0l);
        base0.updateMd5Hash();

		base1 = new HashableImplementation();
		base1.setId(0l);
        base1.updateMd5Hash();
    }
	
	@Test
	public void hashEqualWithoutChanges(){
        base0.updateMd5Hash();
		String hash0 = base0.getMd5Hash();
        base0.updateMd5Hash();
		assertTrue(hash0.equals(base0.getMd5Hash()));
	}
	
	@Test
	public void hashEquivalent(){
		assertTrue(base0.getMd5Hash().equals(base1.getMd5Hash()));
	}
	
	@Test
	public void hashNotEquivalentAfterChanges(){
		base0.setId(10l);
        base0.updateMd5Hash();
		assertFalse(base0.getMd5Hash().equals(base1.getMd5Hash()));
	}
	
}
