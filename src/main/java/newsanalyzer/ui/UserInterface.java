package newsanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import newsanalyzer.ctrl.Controller;
import newsapi.NewsApiBuilder;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;
import newsapi.enums.Language;
import newsapi.enums.SortBy;

public class UserInterface 
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		System.out.println("Headlines Österreich - Allgemein:");

		ctrl.process(
				"",
				Endpoint.TOP_HEADLINES,
				null,
				Language.de,
				Country.at,
				SortBy.POPULARITY
		);
	}

	public void getDataFromCtrl2(){
	}

	public void getDataFromCtrl3(){

		ctrl.process(
				"",
				Endpoint.TOP_HEADLINES,
				null,
				Language.de,
				Country.de,
				SortBy.POPULARITY
		);

	}
	
	public void getDataForCustomInput() {
		
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Headlines Österreich - Allgemein", this::getDataFromCtrl1);
		menu.insert("b", "Headlines Österreich - Health", this::getDataFromCtrl2);
		menu.insert("c", "News Österreich - Allgemein", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Imput:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
