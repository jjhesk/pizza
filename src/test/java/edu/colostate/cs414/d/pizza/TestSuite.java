package edu.colostate.cs414.d.pizza;

import edu.colostate.cs414.d.pizza.api.menu.MenuManagerTest;
import edu.colostate.cs414.d.pizza.api.order.OrderManagerTest;
import edu.colostate.cs414.d.pizza.api.user.UserFactoryTest;
import edu.colostate.cs414.d.pizza.api.user.UserManagerTest;
import edu.colostate.cs414.d.pizza.db.DailySpecialDatabaseControllerTest;
import edu.colostate.cs414.d.pizza.db.MenuDatabaseControllerTest;
import edu.colostate.cs414.d.pizza.db.OrderDatabaseControllerTest;
import edu.colostate.cs414.d.pizza.db.UserDatabaseControllerTest;
import edu.colostate.cs414.d.pizza.utilities.UtilityTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ MenuManagerTest.class, OrderManagerTest.class, UserManagerTest.class, UserFactoryTest.class, DailySpecialDatabaseControllerTest.class, MenuDatabaseControllerTest.class, OrderDatabaseControllerTest.class, UserDatabaseControllerTest.class, UtilityTest.class })
public class TestSuite {

}
