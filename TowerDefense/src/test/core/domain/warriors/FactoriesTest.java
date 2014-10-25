package test.core.domain.warriors;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationService.warriorServices.TowerFactory;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towerType.AncientTower;
import core.domain.warriors.defenders.towers.towerType.ModernTower;

public class FactoriesTest {
	private String modern;
	private String ancient;

	@BeforeClass
	public static void setUpBeforeClass(){
	}

	@AfterClass
	public static void tearDownAfterClass(){
	}

	@Before
	public void setUp(){
	
		try {
			modern = ModernTower.class.getSimpleName();
			ancient = AncientTower.class.getSimpleName();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void test() {
		try {
			TowerFactory towerFactory = new TowerFactory();
			Tower modernObj = towerFactory.getTower("ModernTower");
			Tower ancientObj = towerFactory.getTower("AncientTower");
			String modernExpected = modernObj.getClass().getSimpleName();
			String ancientExpected = ancientObj.getClass().getSimpleName(); 
			
			// assert part
			assertEquals(modernExpected, modern);
			assertEquals(ancientExpected, ancient);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
