package at.grueneis.spengergasse.hashCheck;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashGenerationTest {
	private Hashable hashable0;
	private Hashable hashable1;
    private HashGenerator hashGenerator;
	
	@Before
	public void init(){
        hashGenerator = new HashGenerator();
		hashable0 = new HashableImplementation();
		hashable0.setId(0l);
		hashable0.updateMd5Hash();

		hashable1 = new HashableImplementation();
        hashable1 = new HashableImplementation();
        hashable1.setId(hashable0.getId());
        hashable1.updateMd5Hash();

    }
	
	@Test
	public void hashEqualWithoutChanges(){
        hashable0.setMd5Hash(hashGenerator.generateMd5Hash(hashable0));
		String hash0 = hashable0.getMd5Hash();
        hashable0.setMd5Hash(hashGenerator.generateMd5Hash(hashable0));
		assertTrue(hash0.equals(hashable0.getMd5Hash()));
	}
	
	@Test
	public void hashEquivalent(){
        //both hashables have the same attributes
		assertTrue(hashable0.getMd5Hash().equals(hashable1.getMd5Hash()));
	}
	
	@Test
	public void hashNotEquivalentAfterChanges(){
		hashable0.setId(10l);
        hashable0.setMd5Hash(hashGenerator.generateMd5Hash(hashable0));
		assertFalse(hashable0.getMd5Hash().equals(hashable1.getMd5Hash()));
	}
	
}
