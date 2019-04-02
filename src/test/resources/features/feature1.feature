Feature: Instagram login
Scenario Outline: Validate login
Given launch site
When enter userid as "<x>"
And enter password as "<y>"
And click login
Then validate output for criteria "<z>" for "<x>" and "<y>"
And close site
Examples:
|       x      |    y      |     z     |
|rajashekarp488|vishnup801@| all_valid |
|fddwqqwqq11222|vishnup801@|uid_invalid|
|rajashekarp488|  2656355  |pwd_invalid|
|rajashekarp488|           | blank_pwd |

