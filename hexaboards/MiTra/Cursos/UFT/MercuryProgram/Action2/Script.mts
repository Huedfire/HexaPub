Browser("name:=Find a Flight: Mercury Tours:").Page("title:=Find a Flight: Mercury Tours:").WebRadioGroup("name:=tripType").Select "oneway"
Browser("name:=Find a Flight: Mercury Tours:").Page("title:=Find a Flight: Mercury Tours:").WebList("name:=passCount").Select "2"
Browser("name:=Find a Flight: Mercury Tours:").Page("title:=Find a Flight: Mercury Tours:").WebList("name:=fromPort").Select "Acapulco"
Browser("name:=Find a Flight: Mercury Tours:").Page("title:=Find a Flight: Mercury Tours:").WebList("name:=fromDay").Select "20"
Browser("name:=Find a Flight: Mercury Tours:").Page("title:=Find a Flight: Mercury Tours:").WebList("name:=toPort").Select "London"
Browser("name:=Find a Flight: Mercury Tours:").Page("title:=Find a Flight: Mercury Tours:").WebList("name:=toDay").Select "20"
Browser("name:=Find a Flight: Mercury Tours:").Page("title:=Find a Flight: Mercury Tours:").WebRadioGroup("name:=servClass").Select "First"
Browser("name:=Find a Flight: Mercury Tours:").Page("title:=Find a Flight: Mercury Tours:").WebList("name:=airline").Select "Blue Skies Airlines"
Browser("name:=Find a Flight: Mercury Tours:").Page("title:=Find a Flight: Mercury Tours:").Image("name:=findFlights").Click

