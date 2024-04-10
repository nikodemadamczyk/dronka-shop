package pl.edu.agh.dronka.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Shop {

	private List<User> users = new ArrayList<>();
	
	private Index itemsIndex;
	
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public List<User> getUsers() {
		return new ArrayList<>(users);
	}
	
	public void setItemsIndex(Index itemsIndex) {
		this.itemsIndex = itemsIndex;
	}
	
	public Index getItemsIndex() {
		return itemsIndex;
	}

	public class Book extends Item {
		private int pageCount;
		private boolean hardcover;

		// Getters and setters
		public int getPageCount() {
			return pageCount;
		}

		public void setPageCount(int pageCount) {
			this.pageCount = pageCount;
		}

		public boolean isHardcover() {
			return hardcover;
		}

		public void setHardcover(boolean hardcover) {
			this.hardcover = hardcover;
		}
	}

}
