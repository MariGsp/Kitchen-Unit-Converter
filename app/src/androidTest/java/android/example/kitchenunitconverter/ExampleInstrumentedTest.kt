package android.example.kitchenunitconverter

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ConverterTest {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_cups_water() {
        // Calculates n of grams of 2 cups of water
        onView(withId(R.id.quantity_to_convert_edit))
            .perform(typeText("2"))

        onView(withId(R.id.cups_to_grams)).perform(click())

        onView(withId(R.id.water)).perform(click())

        onView(withId(R.id.convert_button)).perform(click())

        onView(withId(R.id.conversion_result))
            .check(matches(withText(containsString("473.2"))))

    }
}