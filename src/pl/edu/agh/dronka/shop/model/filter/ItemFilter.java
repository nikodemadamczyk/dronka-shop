package pl.edu.agh.dronka.shop.model.filter;

import pl.edu.agh.dronka.shop.model.*;

public class ItemFilter {

	private Item itemSpec;

	public ItemFilter(Category category) {
		switch (category) {
			case BOOKS:
				this.itemSpec = new BookItem();
				break;
			case ELECTRONICS:
				this.itemSpec = new ElectronicsItem();
				break;
			case FOOD:
				this.itemSpec = new FoodItem();
				break;
			case MUSIC:
				this.itemSpec = new MusicItem();
				break;
			case SPORT:
				this.itemSpec = new SportItem();
				break;
			default:
				this.itemSpec = new Item();
				break;
		}
	}

	public Item getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(Item itemSpec) {
		this.itemSpec = itemSpec;
	}

	public boolean appliesTo(Item item) {
		if (itemSpec.getName() != null
				&& !itemSpec.getName().equals(item.getName())) {
			return false;
		}
		if (itemSpec.getCategory() != null
				&& !itemSpec.getCategory().equals(item.getCategory())) {
			return false;
		}

		// applies filter only if the flag (secondHand) is true)
		if (itemSpec.isSecondhand() && !item.isSecondhand()) {
			return false;
		}

		// applies filter only if the flag (polish) is true)
		if (itemSpec.isPolish() && !item.isPolish()) {
			return false;
		}
		switch (itemSpec.getCategory()) {
			case BOOKS:
				BookItem bookSpec = (BookItem) this.itemSpec;
				BookItem bookItem = (BookItem) item;
				if (bookSpec.getPages() != 0 && bookSpec.getPages() != bookItem.getPages()) {
					return false;
				}
				if (bookSpec.isHardcover() != bookItem.isHardcover()) {
					return false;
				}
				break;
			case ELECTRONICS:
				ElectronicsItem electronicSpec = (ElectronicsItem) this.itemSpec;
				ElectronicsItem electronicItem = (ElectronicsItem) item;
				if (electronicSpec.isMobile() != electronicItem.isMobile()) {
					return false;
				}
				if (electronicSpec.isWarranty() != electronicItem.isWarranty()) {
					return false;
				}
				break;
			case MUSIC:
				MusicItem musicSpec = (MusicItem) this.itemSpec;
				MusicItem musicItem = (MusicItem) item;
				if (musicSpec.getGenre() != null && musicSpec.getGenre() != musicItem.getGenre()) {
					return false;
				}
				if (musicSpec.isIncludesVideo() != musicItem.isIncludesVideo()) {
					return false;
				}
				break;
		}
		return true;
	}
}