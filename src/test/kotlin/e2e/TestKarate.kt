package e2e

import com.intuit.karate.junit5.Karate

class TestKarate {

    @Karate.Test
    fun print(): Karate {

        return Karate().feature("classpath:e2e") // all tests here
    }

}