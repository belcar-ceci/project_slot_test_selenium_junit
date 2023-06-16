package com.example.automation;


import com.example.automation.dropdownMenu.DropdownMenuSuiteTest;
import com.example.automation.inventory.InventoryGamesSuiteTest;
import com.example.automation.login.LoginSuiteTest;
import com.example.automation.logout.LogoutSuiteTest;
import com.example.automation.taps.TapSuiteTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginSuiteTest.class,
        InventoryGamesSuiteTest.class,
        DropdownMenuSuiteTest.class,
        TapSuiteTest.class,
        LogoutSuiteTest.class
})
public class RunTest {

}
