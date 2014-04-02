package at.grueneis.spengergasse.hashCheck;

import static org.junit.Assert.*;

import at.grueneis.spengergasse.lesson_plan.domain.BasePersistable;
import org.junit.Before;
import org.junit.Test;

public class HashGenerationTest {
	private BasePersistable base0;
	private BasePersistable base1;
    private HashGenerator hashGenerator;
	
	@Before
	public void init(){
        hashGenerator = new HashGenerator();
		base0 = new BasePersistable() {
			private int integer  = 100;
			private String string = "Name";
			@Override
			public String[] getAllAttributesAsString() {
				return new String[]{""+this.getId(), ""+integer, string};
			}
		};
		base0.setId(0l);
		base0.setMd5Hash(hashGenerator.generateMd5Hash(base0));
		
		base1 = new BasePersistable() {
			private int integer  = 100;
			private String string = "Name";
			@Override
			public String[] getAllAttributesAsString() {
				return new String[]{""+this.getId(), ""+integer, string};
			}
		};
		base1.setId(0l);
        base1.setMd5Hash(hashGenerator.generateMd5Hash(base1));
    }
	
	@Test
	public void hashEqualWithoutChanges(){
        base0.setMd5Hash(hashGenerator.generateMd5Hash(base0));
		String hash0 = base0.getMd5Hash();
        base0.setMd5Hash(hashGenerator.generateMd5Hash(base0));
		assertTrue(hash0.equals(base0.getMd5Hash()));
	}
	
	@Test
	public void hashEquivalent(){
		assertTrue(base0.getMd5Hash().equals(base1.getMd5Hash()));
	}
	
	@Test
	public void hashNotEquivalentAfterChanges(){
		base0.setId(10l);
        base0.setMd5Hash(hashGenerator.generateMd5Hash(base0));
		assertFalse(base0.getMd5Hash().equals(base1.getMd5Hash()));
	}
	
}
