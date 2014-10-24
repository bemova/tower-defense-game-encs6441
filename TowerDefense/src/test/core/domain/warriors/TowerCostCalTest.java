package test.core.domain.warriors;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.domain.warriors.defenders.DefenderConstatns;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.features.FirePower;
import core.domain.warriors.defenders.towers.features.FireRange;
import core.domain.warriors.defenders.towers.features.FireSpeed;
import core.domain.warriors.defenders.towers.towerType.AncientTower;
import core.domain.warriors.defenders.towers.towerType.ModernTower;

public class TowerCostCalTest {
	private AncientTower ancient;
	private ModernTower modern;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ancient = new AncientTower();
		modern = new ModernTower();
	}

	@Test
	public void testTowerTypeCost() {
		try {
			assertEquals(DefenderConstatns.ANCIENT_TOWER, ancient.cost());
			assertEquals(DefenderConstatns.MODERN_TOWER, modern.cost());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testFireRangeCost() {
		try {
			long specialModern = DefenderConstatns.MODERN_TOWER
					+ DefenderConstatns.FIRE_RANGE;
			long specialAncient = DefenderConstatns.ANCIENT_TOWER
					+ DefenderConstatns.FIRE_RANGE;
			Tower mWithRange = new FireRange(modern);
			Tower aWithRange = new FireRange(ancient);
			assertEquals(specialModern, mWithRange.cost());
			assertEquals(specialAncient, aWithRange.cost());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testFireSpeedCost() {
		try {
			long specialModern = DefenderConstatns.MODERN_TOWER
					+ DefenderConstatns.FIRE_SPEED;
			long specialAncient = DefenderConstatns.ANCIENT_TOWER
					+ DefenderConstatns.FIRE_SPEED;
			Tower mWithSpeed = new FireSpeed(modern);
			Tower aWithSpeed = new FireSpeed(ancient);
			assertEquals(specialModern, mWithSpeed.cost());
			assertEquals(specialAncient, aWithSpeed.cost());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testFirePowerCost() {
		try {
			long specialModern = DefenderConstatns.MODERN_TOWER
					+ DefenderConstatns.FIRE_POWER;
			long specialAncient = DefenderConstatns.ANCIENT_TOWER
					+ DefenderConstatns.FIRE_POWER;
			Tower mWithPower = new FirePower(modern);
			Tower aWithPower = new FirePower(ancient);
			assertEquals(specialModern, mWithPower.cost());
			assertEquals(specialAncient, aWithPower.cost());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComboFeature() {
		try {
			long specialModern = DefenderConstatns.MODERN_TOWER
					+ DefenderConstatns.FIRE_POWER
					+ DefenderConstatns.FIRE_RANGE
					+ DefenderConstatns.FIRE_SPEED;
			long specialAncient = DefenderConstatns.ANCIENT_TOWER
					+ DefenderConstatns.FIRE_POWER
					+ DefenderConstatns.FIRE_RANGE
					+ DefenderConstatns.FIRE_SPEED;
			// crate modern with three features
			Tower modernCombo= new FirePower(modern);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);
			
			// crate ancient with three features
			Tower ancientCombo= new FirePower(ancient);
			ancientCombo = new FireRange(ancientCombo);
			ancientCombo = new FireSpeed(ancientCombo);
			
			// assert part
			assertEquals(specialModern, modernCombo.cost());
			assertEquals(specialAncient, ancientCombo.cost());
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
