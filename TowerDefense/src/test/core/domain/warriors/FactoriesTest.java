package test.core.domain.warriors;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationservice.warriorservices.TowerFactory;
import core.contract.MapConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.features.FirePower;
import core.domain.warriors.defenders.towers.features.FireRange;
import core.domain.warriors.defenders.towers.features.FireSpeed;
import core.domain.warriors.defenders.towers.towertype.AncientTower;
import core.domain.warriors.defenders.towers.towertype.KingTower;
import core.domain.warriors.defenders.towers.towertype.ModernTower;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;

public class FactoriesTest {
	private String modern;
	private String ancient;
	private String king;
	TowerFactory towerFactory;

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
			king = KingTower.class.getSimpleName();
			towerFactory = new TowerFactory();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testGetTower() {
		try {
			Tower modernObj = towerFactory.getTower("ModernTower");
			Tower ancientObj = towerFactory.getTower("AncientTower");
			Tower kingObj = towerFactory.getTower("KingTower");
			String modernExpected = modernObj.getClass().getSimpleName();
			String ancientExpected = ancientObj.getClass().getSimpleName(); 
			String kingExpected = kingObj.getClass().getSimpleName();

			// assert part
			assertEquals(modernExpected, modern);
			assertEquals(ancientExpected, ancient);
			assertEquals(kingExpected, king);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test 
	public void testGetDecoratedTower(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			Tower expDecorated = new FirePower(expected);
			expDecorated = new FirePower(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			expDecorated = new FireSpeed(expDecorated);

			Tower tower = towerFactory.getDecoratedTower(expDecorated.objectDetials());

			// assert part
			assertEquals(expDecorated.cost(), tower.cost());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testGetTowerByLevel(){
		try {
			Tower tower = towerFactory.getTower("ModernTower", TowerLevel.two);

			// expected
			Tower expected = towerFactory.getTower("ModernTower");
			Tower expDecorated = new FirePower(expected);
			expDecorated = new FirePower(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			expDecorated = new FireSpeed(expDecorated);

			// assert part
			assertEquals(expDecorated.cost(), tower.cost());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testGetRange(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			Tower expDecorated = new FireRange(expected);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireRange(expDecorated);
			double range = towerFactory.getRange(expDecorated);
			double expecteRange = 4;
			assertEquals((Double)expecteRange, (Double)range);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Test
	public void testGetSpeed(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			Tower expDecorated = new FireSpeed(expected);
			expDecorated = new FireSpeed(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			double speed = towerFactory.getSpeed(expDecorated);
			double expectedSpeed = 4;
			assertEquals((Double)expectedSpeed, (Double)speed);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Test
	public void testGetPower(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			Tower expDecorated = new FirePower(expected);
			expDecorated = new FirePower(expDecorated);
			expDecorated = new FirePower(expDecorated);
			expDecorated = new FirePower(expDecorated);
			double power = towerFactory.getPower(expDecorated);
			double expectePower = 4;
			assertEquals((Double)expectePower, (Double)power);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
