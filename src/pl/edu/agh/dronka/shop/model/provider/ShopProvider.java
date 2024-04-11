package pl.edu.agh.dronka.shop.model.provider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.edu.agh.dronka.shop.model.*;

public class ShopProvider {

	public static Shop getExampleShop() {
		Shop shop = new Shop();

		shop.addUser(getExampleUser());

		Index itemsIndex = new Index();

		for (Item item : getExampleItems()) {
			itemsIndex.addItem(item);
		}

		registerExampleCategories(itemsIndex);
		shop.setItemsIndex(itemsIndex);

		return shop;
	}

	public static User getExampleUser() {
		return new User("Jan", "Rejnor");
	}

	public static List<Item> getExampleItems() {
		List<Item> items = new ArrayList<>();

		CSVReader booksReader = new CSVReader("resources/books.csv");
		items.addAll(readItems(booksReader, Category.BOOKS));
		
		CSVReader electronicsReader = new CSVReader("resources/electronics.csv");
		items.addAll(readItems(electronicsReader, Category.ELECTRONICS));
		
		CSVReader foodReader = new CSVReader("resources/food.csv");
		items.addAll(readItems(foodReader, Category.FOOD));
		
		CSVReader musicReader = new CSVReader("resources/music.csv");
		items.addAll(readItems(musicReader, Category.MUSIC));
		
		CSVReader sportReader = new CSVReader("resources/sport.csv");
		items.addAll(readItems(sportReader, Category.SPORT));

		return items;
	}

	public static void registerExampleCategories(Index index) {
		for (Category category : Category.values()) {
			index.registerCategory(category);
		}
	}

	private static List<Item> readItems(CSVReader reader, Category category) {
		List<Item> items = new ArrayList<>();

		try {
			reader.parse();
			List<String[]> data = reader.getData();

			for (String[] dataLine : data) {
				String name = reader.getValue(dataLine, "Nazwa");
				int price = Integer.parseInt(reader.getValue(dataLine, "Cena"));
				int quantity = Integer.parseInt(reader.getValue(dataLine, "Ilość"));
				boolean isPolish = Boolean.parseBoolean(reader.getValue(dataLine, "Tanie bo polskie"));
				boolean isSecondhand = Boolean.parseBoolean(reader.getValue(dataLine, "Używany"));

				Item item = null;
				switch (category) {
					case BOOKS:
						int pagesNumber = Integer.parseInt(reader.getValue(dataLine, "Liczba stron"));
						boolean hardCover = Boolean.parseBoolean(reader.getValue(dataLine, "Twarda oprawa"));
						item = new BookItem(name, category, price, quantity, pagesNumber, hardCover);
						break;
					case ELECTRONICS:
						boolean mobile = Boolean.parseBoolean(reader.getValue(dataLine, "Mobilny"));
						boolean warranty = Boolean.parseBoolean(reader.getValue(dataLine, "Gwarancja"));
						item = new ElectronicsItem(name, category, price, quantity, mobile, warranty);
						break;
					case FOOD:
						String dateString = reader.getValue(dataLine, "Data przydatności");
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date expirationDate = dateFormat.parse(dateString);
						item = new FoodItem(name, category, price, quantity, expirationDate);
						break;
					case MUSIC:
						MusicGenre genre = MusicGenre.valueOf(reader.getValue(dataLine, "Gatunek muzyczny").toUpperCase());
						boolean includesVideo = Boolean.parseBoolean(reader.getValue(dataLine, "Video"));
						item = new MusicItem(name, category, price, quantity, genre, includesVideo);
						break;
					case SPORT:
						item = new SportItem(name, category, price, quantity);
						break;
					default:
						throw new IllegalStateException("Unexpected category: " + category);
				}
				item.setPolish(isPolish);
				item.setSecondhand(isSecondhand);
				items.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return items;
	}

}
