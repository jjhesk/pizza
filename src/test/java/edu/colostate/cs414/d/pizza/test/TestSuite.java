package edu.colostate.cs414.d.pizza.test;

import edu.colostate.cs414.d.pizza.test.api.menu.MenuManagerTest;
import edu.colostate.cs414.d.pizza.test.api.order.OrderManagerTest;
import edu.colostate.cs414.d.pizza.test.api.user.UserFactoryTest;
import edu.colostate.cs414.d.pizza.test.api.user.UserManagerTest;
import edu.colostate.cs414.d.pizza.test.db.DailySpecialDatabaseControllerTest;
import edu.colostate.cs414.d.pizza.test.db.MenuDatabaseControllerTest;
import edu.colostate.cs414.d.pizza.test.db.OrderDatabaseControllerTest;
import edu.colostate.cs414.d.pizza.test.db.UserDatabaseControllerTest;
import edu.colostate.cs414.d.pizza.test.utilities.UtilityTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ MenuManagerTest.class, OrderManagerTest.class, UserManagerTest.class, UserFactoryTest.class, DailySpecialDatabaseControllerTest.class, MenuDatabaseControllerTest.class, OrderDatabaseControllerTest.class, UserDatabaseControllerTest.class, UtilityTest.class })
public class TestSuite {

}
