package com.test.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

//import com.google.common.base.Function;
/*import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;*/

public class Commons {
   
    long starttime, endTime, totalTime;
    
    
  
    public void click(WebDriver driver, final String attribute) throws InterruptedException {
        // Disable implicitWait to resolve some SSL connectivity issue. Some object being killed too fast; causing webdriver to keep retrying on the page
        driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);

        final Wait<WebDriver> waitExt = new FluentWait<WebDriver>(driver)
                .withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class);

        //logger.info("Going to Click on the  [" + attribute + "] Field");

        try {
           // SeleniumScreenshotUtil.takeScreenshot(BaseProject.driver, BaseProject.genieScenario);
            /*WebElement elem = waitExt.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {*/
                    //WebElement element = getExactAttribute(driver, attribute);
                    WebElement element = waitExt.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(getExactAttributeBY(attribute))));
                    element.click();
                    //return element;
            /*    //}
            });*/

            //logger.info("Clicked on the  [" + attribute + "] Field");
            // Enable implicitWait back for general page loading time wait
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        } catch (Exception e) {
            //logger.error("[Exception caught! Unable to click on the  [" + attribute + "] Field");
            e.printStackTrace();
            Assert.fail();

        }
    }
   
    
    
    public void waitForElementVisibility(WebDriver driver,WebElement elem) {
        new WebDriverWait(driver, 60, 1000).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(elem));
        return;
    }
    ////========================================================================================

    public void waitForElementVisibility(WebDriver driver, String attribute, int time) {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(InvalidElementStateException.class);

        try {

            String attri = attribute.replace("\"", "").trim();//data retrieved with quotes are removed
            if (attri.contains("xpath=")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(attri.substring("xpath=".length()))));

            }
            if (attri.contains("//")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(attri)));

            }
            if (attri.contains("id=")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(attri.substring("id=".length()))));

            }
            if (attri.contains("css=")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(attri.substring("css=".length()))));

            }
            if (attri.contains("name=")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(attri.substring("name=".length()))));

            }
            if (attri.contains("linkText=")) {
                wait.until(ExpectedConditions.elementToBeClickable(By.linkText(attri.substring("linkText=".length()))));

            }


            if (attri.contains("partialLinkText=")) {
                wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(attri.substring("partialLinkText=".length()))));

            }
            if (attri.contains("tagName=")) {
                wait.until(ExpectedConditions.elementToBeClickable(By.tagName(attri.substring("tagName=".length()))));

            }
        } catch (Exception e) {
            //logger.error("Failed to wait for element visibility:" + attribute);
            e.printStackTrace();
            Assert.fail();
        }

    }
    
    
    public void waitForElement(WebDriver driver, String attribute, int time, String type) {
        try {
            Long waitTime = (long) time;
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            String attri = attribute.replace("\"", "").trim();//data retrieved with quotes are removed
            switch (type) {

                case "present":

                    if (attri.contains("id=")) {
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(attri.substring("id=".length()))));
                    } else if (attri.contains("//")) {
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(attri)));

                    } else if (attri.contains("xpath=")) {
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(attri.substring("xpath=".length()))));

                    } else if (attri.contains("tagName=")) {
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(attri.substring("tagName=".length()))));
                    } else if (attri.contains("name=")) {
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(attri.substring("name=".length()))));

                    } else if (attri.contains("linkText=")) {
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(attri.substring("linkText=".length()))));
                    } else if (attri.contains("partialLinkText=")) {
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(attri.substring("partialLinkText=".length()))));
                    } else if (attri.contains("css=")) {
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(attri.substring("css=".length()))));
                    }

                    break;

                case "visible":

                    if (attri.contains("id=")) {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(attri.substring("id=".length()))));
                    } else if (attri.contains("//")) {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(attri)));

                    } else if (attri.contains("xpath=")) {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(attri.substring("xpath=".length()))));

                    } else if (attri.contains("tagName=")) {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(attri.substring("tagName=".length()))));
                    } else if (attri.contains("name=")) {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(attri.substring("name=".length()))));

                    } else if (attri.contains("linkText=")) {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(attri.substring("linkText=".length()))));
                    } else if (attri.contains("partialLinkText=")) {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(attri.substring("partialLinkText=".length()))));
                    } else if (attri.contains("css=")) {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(attri.substring("css=".length()))));

                    }

                    break;

                case "click":

                    if (attri.contains("id=")) {
                        wait.until(ExpectedConditions.elementToBeClickable(By.id(attri.substring("id=".length()))));
                    } else if (attri.contains("//")) {
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(attri)));

                    } else if (attri.contains("xpath=")) {
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(attri.substring("xpath=".length()))));

                    } else if (attri.contains("tagName=")) {
                        wait.until(ExpectedConditions.elementToBeClickable(By.tagName(attri.substring("tagName=".length()))));
                    } else if (attri.contains("name=")) {
                        wait.until(ExpectedConditions.elementToBeClickable(By.name(attri.substring("name=".length()))));

                    } else if (attri.contains("linkText=")) {
                        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(attri.substring("linkText=".length()))));
                    } else if (attri.contains("partialLinkText=")) {
                        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(attri.substring("partialLinkText=".length()))));
                    } else if (attri.contains("css=")) {
                        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(attri.substring("css=".length()))));
                    }


                    break;

                case "select":

                    if (attri.contains("id=")) {
                        wait.until(ExpectedConditions.elementToBeSelected(By.id(attri.substring("id=".length()))));
                    } else if (attri.contains("//")) {
                        wait.until(ExpectedConditions.elementToBeSelected(By.xpath(attri)));

                    } else if (attri.contains("xpath=")) {
                        wait.until(ExpectedConditions.elementToBeSelected(By.xpath(attri.substring("xpath=".length()))));

                    } else if (attri.contains("tagName=")) {
                        wait.until(ExpectedConditions.elementToBeSelected(By.tagName(attri.substring("tagName=".length()))));
                    } else if (attri.contains("name=")) {
                        wait.until(ExpectedConditions.elementToBeSelected(By.name(attri.substring("name=".length()))));

                    } else if (attri.contains("linkText=")) {
                        wait.until(ExpectedConditions.elementToBeSelected(By.linkText(attri.substring("linkText=".length()))));
                    } else if (attri.contains("partialLinkText=")) {
                        wait.until(ExpectedConditions.elementToBeSelected(By.partialLinkText(attri.substring("partialLinkText=".length()))));
                    } else if (attri.contains("css=")) {
                        wait.until(ExpectedConditions.elementToBeSelected(By.cssSelector(attri.substring("css=".length()))));
                    }

                    break;

                case "allElements":

                    if (attri.contains("id=")) {
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(attri.substring("id=".length()))));
                    } else if (attri.contains("//")) {
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(attri)));

                    } else if (attri.contains("xpath=")) {
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(attri.substring("xpath=".length()))));

                    } else if (attri.contains("tagName=")) {
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(attri.substring("tagName=".length()))));
                    } else if (attri.contains("name=")) {
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(attri.substring("name=".length()))));

                    } else if (attri.contains("linkText=")) {
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(attri.substring("linkText=".length()))));
                    } else if (attri.contains("partialLinkText=")) {
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(attri.substring("partialLinkText=".length()))));
                    } else if (attri.contains("css=")) {
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(attri.substring("css=".length()))));
                    }

                    break;

                case "alert":

                    wait.until(ExpectedConditions.alertIsPresent());

                    break;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            //logger.error("The element is incorrect, please verify respective properties file:" + attribute);
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void waitForTextDisplay(WebDriver driver, String attribute, String data, int time, String type) {
        try {
            Long waitTime = (long) time;
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            String attri = attribute.replace("\"", "").trim();//data retrieved with quotes are removed

            switch (type) {
                case "ExactText":

                    if (attri.contains("id=")) {
                        wait.until(ExpectedConditions.textToBe(By.id(attri.substring("id=".length())), data));
                    } else if (attri.contains("//")) {
                        wait.until(ExpectedConditions.textToBe(By.xpath(attri), data));

                    } else if (attri.contains("xpath=")) {
                        wait.until(ExpectedConditions.textToBe(By.xpath(attri.substring("xpath=".length())), data));

                    } else if (attri.contains("tagName=")) {
                        wait.until(ExpectedConditions.textToBe(By.tagName(attri.substring("tagName=".length())), data));
                    } else if (attri.contains("name=")) {
                        wait.until(ExpectedConditions.textToBe(By.name(attri.substring("name=".length())), data));

                    } else if (attri.contains("linkText=")) {
                        wait.until(ExpectedConditions.textToBe(By.linkText(attri.substring("linkText=".length())), data));
                    } else if (attri.contains("partialLinkText=")) {
                        wait.until(ExpectedConditions.textToBe(By.partialLinkText(attri.substring("partialLinkText=".length())), data));
                    } else if (attri.contains("css=")) {
                        wait.until(ExpectedConditions.textToBe(By.cssSelector(attri.substring("css=".length())), data));
                    } else
                       System.out.println("The element is incorrect, please verify respective properties file");

                    break;

                case "PartialText":

                    if (attri.contains("id=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(attri.substring("id=".length())), data));
                    } else if (attri.contains("//")) {
                        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(attri), data));

                    } else if (attri.contains("xpath=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(attri.substring("xpath=".length())), data));

                    } else if (attri.contains("tagName=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName(attri.substring("tagName=".length())), data));
                    } else if (attri.contains("name=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.name(attri.substring("name=".length())), data));

                    } else if (attri.contains("linkText=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.linkText(attri.substring("linkText=".length())), data));
                    } else if (attri.contains("partialLinkText=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.partialLinkText(attri.substring("partialLinkText=".length())), data));
                    } else if (attri.contains("css=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(attri.substring("css=".length())), data));
                    }

                    break;

                case "ElementValue":

                    if (attri.contains("id=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id(attri.substring("id=".length())), data));
                    } else if (attri.contains("//")) {
                        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(attri), data));

                    } else if (attri.contains("xpath=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(attri.substring("xpath=".length())), data));

                    } else if (attri.contains("tagName=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementValue(By.tagName(attri.substring("tagName=".length())), data));
                    } else if (attri.contains("name=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(attri.substring("name=".length())), data));

                    } else if (attri.contains("linkText=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementValue(By.linkText(attri.substring("linkText=".length())), data));
                    } else if (attri.contains("partialLinkText=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementValue(By.partialLinkText(attri.substring("partialLinkText=".length())), data));
                    } else if (attri.contains("css=")) {
                        wait.until(ExpectedConditions.textToBePresentInElementValue(By.cssSelector(attri.substring("css=".length())), data));
                    } else
                       System.out.println("The element is incorrect, please verify respective properties file");

                    break;
            }


        } catch (Exception e) {
            //e.printStackTrace();
            //logger.error("The element is incorrect, please verify respective properties file");
        }
    }
    
    
    

    public String waitForElement(WebDriver driver, String attribute) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement elem = wait.until(
                ExpectedConditions.elementToBeClickable(By.id(attribute)));
        return attribute;
    }
    
    public void wait(int mseconds) throws InterruptedException {
        Thread.sleep(mseconds);
    }
    
    
    
    public void typeToTextBox(WebDriver driver, String value, String Inattribute) throws InterruptedException {
		try {
			System.out.println("typeToTextBox:The Value [" + value + "] going to Enter into the Field [" + Inattribute + "]");
			WebElement elem = getExactAttribute(driver, Inattribute);
			
			elem.click();
			elem.clear();
			elem.sendKeys(value);
			System.out.println("Value [" + value + "]Entered Successfully");
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}


	public java.util.List<WebElement> getElements(WebDriver driver, String Attribute) {///list of elements
		return getExactAttributes(driver, Attribute);
	}

	public WebElement getElement(WebDriver driver, String Attribute) {
		return getExactAttribute(driver, Attribute);
	}
	public WebElement getElementWithoutAssertion(WebDriver driver, String Attribute) {
		return getExactAttributeWithoutAssertion(driver, Attribute);
	}

	public void clear(WebDriver driver, String Attribute) {
		try {
			getExactAttribute(driver, Attribute).clear();
		} catch (Exception e) {
			
			e.printStackTrace();
			Assert.fail();
		}

	}

	public By getExactAttributeBY(String Attribute) {
		try {
			String attri = Attribute.replace("\"", "").trim();
			if (attri.contains("xpath")) {
				return getXpath(attri.substring("xpath=".length()));

			}

			if (attri.startsWith("//")) {
				return getXpath(attri);

			}

			if (attri.startsWith("(")) {
				return getXpath(attri);

			}

			if (attri.contains("css")) {
				return getCssSelector(attri.substring("css=".length()));

			}

			if (attri.contains("name")) {
				return getName(attri.substring("name=".length()));

			}

			if (attri.contains("id")) {
				return getId(attri.substring("id=".length()));

			}

			if (attri.contains("linkText")) {
				System.out.println(attri.substring("linkText=".length()));
				String tes = attri.substring("linkText=".length());
				System.out.println(tes);
				//driver1.findElement(By.linkText("\""+tes+"\"")).click();
				return getLinkText(tes);

			}
			if (attri.contains("className")) {
				return getClassName(attri.substring("className=".length()));

			}
			if (attri.contains("tagName")) {
				return getTagName(attri.substring("tagName=".length()));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	////=========================returns single web element==============================================

	public WebElement getExactAttribute(WebDriver driver, String Attribute) {
		try {
			String attri = Attribute.replace("\"", "").trim();
			if (attri.contains("xpath")) {
				return driver.findElement(getXpath(attri.substring("xpath=".length())));

			}

			if (attri.startsWith("//")) {
				return driver.findElement(getXpath(attri));

			}

			if (attri.startsWith("(")) {
				return driver.findElement(getXpath(attri));

			}

			if (attri.contains("css")) {
				return driver.findElement(getCssSelector(attri.substring("css=".length())));

			}

			if (attri.contains("name")) {
				return driver.findElement(getName(attri.substring("name=".length())));

			}

			if (attri.contains("id")) {
				return driver.findElement(getId(attri.substring("id=".length())));

			}

			if (attri.contains("linkText")) {
				System.out.println(attri.substring("linkText=".length()));
				String tes = attri.substring("linkText=".length());
				System.out.println(tes);
				
				return driver.findElement(getLinkText(tes));

			}
			if (attri.contains("className")) {
				return driver.findElement(getClassName(attri.substring("className=".length())));

			}
			if (attri.contains("tagName")) {
				return driver.findElement(getTagName(attri.substring("tagName=".length())));

			}
		} catch (Exception e) {
			System.out.println("Failed to get Attribute:" + Attribute);
		
		}
		return null;
	}

	public WebElement getExactAttributeWithoutAssertion(WebDriver driver, String Attribute) {
		try {
			String attri = Attribute.replace("\"", "").trim();
			if (attri.contains("xpath")) {
				return driver.findElement(getXpath(attri.substring("xpath=".length())));

			}

			if (attri.startsWith("//")) {
				return driver.findElement(getXpath(attri));

			}

			if (attri.contains("css")) {
				return driver.findElement(getCssSelector(attri.substring("css=".length())));

			}

			if (attri.contains("name")) {
				return driver.findElement(getName(attri.substring("name=".length())));

			}

			if (attri.contains("id")) {
				return driver.findElement(getId(attri.substring("id=".length())));

			}

			if (attri.contains("linkText")) {
				System.out.println(attri.substring("linkText=".length()));
				String tes = attri.substring("linkText=".length());
				System.out.println(tes);
				
				return driver.findElement(getLinkText(tes));

			}
			if (attri.contains("className")) {
				return driver.findElement(getClassName(attri.substring("className=".length())));

			}
			if (attri.contains("tagName")) {
				return driver.findElement(getTagName(attri.substring("tagName=".length())));

			}
		} catch (Exception e) {
			System.out.println("Failed to get Attribute:" + Attribute);
		
		}
		return null;
	}

	////============================returns a list of webElements==========================================

	public java.util.List<WebElement> getExactAttributesWithoutAssertion(WebDriver driver, String Attribute) {
		try {
			String attri = Attribute.replace("\"", "").trim();//data retrieved with quotes are removed
			if (attri.contains("xpath")) {
				return driver.findElements(getXpath(attri.substring("xpath=".length())));

			}
			if (attri.startsWith("//")) {
				return driver.findElements(getXpath(attri));

			}

			if (attri.contains("css")) {
				return driver.findElements(getCssSelector(attri.substring("css=".length())));

			}

			if (attri.contains("name")) {
				return driver.findElements(getName(attri.substring("name=".length())));

			}

			if (attri.contains("id")) {
				return driver.findElements(getId(attri.substring("id=".length())));

			}

			if (attri.contains("linkText")) {
				System.out.println(attri.substring("linkText=".length()));
				String tes = attri.substring("linkText=".length());
				System.out.println(tes);
				
				return driver.findElements(getLinkText(tes));

			}
			if (attri.contains("className")) {
				return driver.findElements(getClassName(attri.substring("className=".length())));

			}
			if (attri.contains("tagName")) {
				return driver.findElements(getTagName(attri.substring("tagName=".length())));

			}
		} catch (Exception e) {
			
		}
		return null;
	}

	public java.util.List<WebElement> getExactAttributes(WebDriver driver, String Attribute) {
		try {
			String attri = Attribute.replace("\"", "").trim();//data retrieved with quotes are removed
			if (attri.contains("xpath")) {
				return driver.findElements(getXpath(attri.substring("xpath=".length())));

			}
			if (attri.startsWith("//")) {
				return driver.findElements(getXpath(attri));

			}

			if (attri.contains("css")) {
				return driver.findElements(getCssSelector(attri.substring("css=".length())));

			}

			if (attri.contains("name")) {
				return driver.findElements(getName(attri.substring("name=".length())));

			}

			if (attri.contains("id")) {
				return driver.findElements(getId(attri.substring("id=".length())));

			}

			if (attri.contains("linkText")) {
				System.out.println(attri.substring("linkText=".length()));
				String tes = attri.substring("linkText=".length());
				System.out.println(tes);
				//driver1.findElement(By.linkText("\""+tes+"\"")).click();
				return driver.findElements(getLinkText(tes));

			}
			if (attri.contains("className")) {
				return driver.findElements(getClassName(attri.substring("className=".length())));

			}
			if (attri.contains("tagName")) {
				return driver.findElements(getTagName(attri.substring("tagName=".length())));

			}
		} catch (Exception e) {
			//logger.error("Failed to get Attribute:" + Attribute);
			e.printStackTrace();
			Assert.fail();
		}
		return null;
	}

	///==========================returns by type=====================================================

	public By getXpath(String Attribute) {
		return By.xpath(Attribute);

	}

	public By getCssSelector(String Attribute) {
		return By.cssSelector(Attribute);

	}

	public By getName(String Attribute) {
		return By.name(Attribute);

	}

	public By getId(String Attribute) {
		return By.id(Attribute);

	}

	public By getLinkText(String Attribute) {
		System.out.println(Attribute);
		return By.linkText(Attribute);

	}

	public By getClassName(String Attribute) {
		return By.className(Attribute);

	}

	public By getTagName(String Attribute) {
		return By.tagName(Attribute);

	}




	public void screenShot(WebDriver driver, String FilePath ,String screenshotname) throws IOException {

		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {

			FileUtils.copyFile(src, new File(FilePath + File.separator + screenshotname + ".jpg"));
		}

		catch (IOException e)
		{
			System.out.println(e.getMessage());

		}


	}

	public boolean scroll_to(WebDriver driver, String attribute) throws InterruptedException {
		System.out.println("Before scroll div section of the page");
		//String divScroll = "//*[@id='INNERDIV-SubSectionCaptureDetailsB']";
		//*[@id='INNERDIV-SubSectionCaptureDetailsB']/div/div[1]/div
		WebElement ele = getElement(driver, attribute);

		//new WebDriverWait(driver, 60, 1000).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(wrap.getExactAttributeBY(attribute)));      

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",ele);
		return true;

	}


	public String getTextValue(WebDriver driver, String attribute) throws InterruptedException {

		String str = driver.findElement(By.xpath(attribute)).getText();
		System.out.println("String value from the given attribute is : " + str);
		return str;


	}


	public void clickandwait(WebDriver driver,String attribute) throws InterruptedException{
		try
		{
			wait(1000);
			System.out.println("Going to Click on the  ["+attribute+"] Field");
			new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(getExactAttribute(driver,attribute)));
			getExactAttribute(driver,attribute).click();
		}
		catch(StaleElementReferenceException e)
		{
			wait(1000);	
			getExactAttribute(driver,attribute).click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
   


    public String getElementProperties(String proFileName, String propertivalue) throws IOException {
        File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "objectrepository" + File.separator + proFileName + ".properties");
        FileInputStream fis = new FileInputStream(file);
        Properties pro = new Properties();
        pro.load(fis);
        String value = pro.getProperty(propertivalue);
        return value;
    }


    }
