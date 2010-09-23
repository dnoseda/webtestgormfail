package webtfail



class ItemWebTests extends grails.util.WebTest {
	
	// Unlike unit tests, functional tests are sometimes sequence dependent.
	// Methods starting with 'test' will be run automatically in alphabetical order.
	// If you require a specific sequence, prefix the method name (following 'test') with a sequence
	// e.g. test001XclassNameXListNewDelete
	
	void testSomething() {
		String descriptionValue = "cualquier cosa"
		int itemCant = Item.count()
		invoke '/item/list'
		storeXPath(xpath:'count(//table//tbody//tr)',property:"uri", description:'cantidad previa')
		clickElement(xpath:"//a[@class='create']")
		setInputField(name:'description', value:descriptionValue)
		clickElement(xpath:"//input[@id='create']")
		verifyXPath(xpath:"//div[@class='message']/text()='Item 1 created'")
		int itemAfterCant=-1
		Item.withNewSession { org.hibernate.Session session ->
			itemAfterCant = Item.count()
		}
		
		invoke '/item/list'
		storeXPath(xpath:'count(//table//tbody//tr)',property:"uri", description:'cantidad luego')
		verifyXPath(xpath:'count(//table//tbody//tr)=1')
		
		if(itemCant==itemAfterCant){
			not{
				invoke url:"/", description:"itemCant=${itemCant} itemAfterCant: ${itemAfterCant}"
			}
		}else{
			invoke url:"/", description:"itemCant=${itemCant} itemAfterCant: ${itemAfterCant}"
		}
	}
}