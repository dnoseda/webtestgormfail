package webtfail



class ItemWebTests extends grails.util.WebTest {
	
	// Unlike unit tests, functional tests are sometimes sequence dependent.
	// Methods starting with 'test' will be run automatically in alphabetical order.
	// If you require a specific sequence, prefix the method name (following 'test') with a sequence
	// e.g. test001XclassNameXListNewDelete
	
	void testSomething() {
		String descriptionValue = "cualquier cosa"
		invoke '/item/list'
		storeXPath(xpath:'count(//table//tbody//tr)',property:"uri", description:'cantidad previa')
		clickElement(xpath:"//a[@class='create']")
		setInputField(name:'description', value:descriptionValue)
		clickElement(xpath:"//input[@id='create']")
		verifyXPath(xpath:"//div[@class='message']/text()='Item 1 created'")
		int itemAfterCant=-1
		
		invoke '/item/list',description:"${Item.count()}"
		storeXPath(xpath:'count(//table//tbody//tr)',property:"delete", description:'cantidad luego')
		verifyXPath(xpath:'count(//table//tbody//tr)>#{uri}',description:"tamaño mayor a uir:#{uri}")
	}
}