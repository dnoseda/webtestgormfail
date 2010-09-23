package webtfail



class ItemWebTests extends grails.util.WebTest {
	
	// Unlike unit tests, functional tests are sometimes sequence dependent.
	// Methods starting with 'test' will be run automatically in alphabetical order.
	// If you require a specific sequence, prefix the method name (following 'test') with a sequence
	// e.g. test001XclassNameXListNewDelete
	
	void testSomething() {
		int itemCant = Item.getAll().size()
		invoke '/item/list'
		clickXPath(xpath:"//a[@class='create']")
		setInputField(name:'description', value:'cualquier cosa')
		clickElement(xpath:"//input[@id='create']")
		verifyXPath(xpath:"//div[@class='message']/text()='Item 1 created'")
		int itemAfterCant = Item.getAll().size()
		if(itemCant==itemAfterCant){
			not{
				invoke url:"/", description:"itemCant=${itemCant} itemAfterCant: ${itemAfterCant}"
			}
		}else{
			invoke url:"/", description:"itemCant=${itemCant} itemAfterCant: ${itemAfterCant}"
		}
	}
}