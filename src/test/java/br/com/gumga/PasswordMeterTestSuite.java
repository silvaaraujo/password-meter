package br.com.gumga;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	AdditionTest.class,
	DeductionTest.class,
	FinalScoreTest.class
})
public class PasswordMeterTestSuite {

}
