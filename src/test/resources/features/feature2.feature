Feature:Instagram Login
Scenario:validate instagram login 
Given launch a site
When user enters userid and password
|   username      |   password     |
|rajashekarp488   |   vishnup801@  |
|rajashekarp488   |   112222       |
|fddwqqwqq11222   |   vishnup801   |
|rajashekarp488   |                |
And click login button
Then message displayed Login Successfully
When close a site 
