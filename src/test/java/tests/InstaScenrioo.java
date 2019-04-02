package tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class) 
@CucumberOptions(
		         
		          features= {"D:\\WorkSpace\\InstaTest\\src\\test\\resources\\features\\feature1.feature"},
                  plugin= {"pretty","html:target\\resultApril1.txt"},
                  monochrome=true,
                  glue={"classpath:glue"}
                 
		          )
                 
public class InstaScenrioo { 

}
