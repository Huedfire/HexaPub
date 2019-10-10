'Librerias
LoadFunctionLibrary "C:\LibrariesUFT\Objects3.vbs"
LoadFunctionLibrary "C:\LibrariesUFT\Wrappers2.vbs"

'Test cases
set_webEdit browserLogin,pageLogin,FirstName,"Hugo"
set_webEdit browserLogin,pageLogin,LastName,"Jacobo"
select_WRG browserLogin,pageLogin,radioSex,"Male"
select_WRG browserLogin,pageLogin,radioYears,"6"
'select_WCB browserLogin,pageLogin,WCB,"ON"
set_webEdit browserLogin,pageLogin,dateSun,"89"
select_WL browserLogin,pageLogin,listContinents,"Europe"
select_WL browserLogin,pageLogin,listSeleniums,"Navigation Commands"
select_Button browserLogin,pageLogin,finalButton


 @@ hightlight id_;_Browser("Demo Form for practicing").Page("Demo Form for practicing").WebCheckBox("profession")_;_script infofile_;_ZIP::ssf14.xml_;_

